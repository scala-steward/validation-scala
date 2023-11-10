package bitlap.validation

import javax.validation.{ ConstraintValidator, ConstraintValidatorContext }

import bitlap.validation.Utils._

/**
 * Validates that the wrapped value passed is some
 */
class AssertSomeValidator extends ConstraintValidator[AssertSome, IterableOnce[_]] {

  override def initialize(constraintAnnotation: AssertSome): Unit = {}

  override def isValid(value: IterableOnce[_], context: ConstraintValidatorContext): Boolean =
    checkForOption(value) { x =>
      x.isDefined
    }
}
