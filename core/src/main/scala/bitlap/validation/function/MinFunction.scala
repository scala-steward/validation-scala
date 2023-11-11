package bitlap.validation.function

import javax.validation.ConstraintValidatorContext
import javax.validation.constraints.Min

import org.hibernate.validator.internal.constraintvalidators.bv._

final case class MinFunction(annotation: Min) extends CheckOptionFunction {

  override def check(value: Option[_]): ConstraintValidatorContext => Boolean = ctx =>
    value match {
      case Some(x: CharSequence) =>
        val v = new MinValidatorForCharSequence
        v.initialize(annotation)
        v.isValid(x, ctx)
      case Some(x: Number)       =>
        val v = new MinValidatorForNumber
        v.initialize(annotation)
        v.isValid(x, ctx)
      case None                  =>
        true
      case Some(_)               =>
        throw new IllegalStateException("oops.")
    }
}