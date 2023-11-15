package bitlap.validation

import scala.annotation.meta.field

import org.hibernate.validator.constraints.Length

import jakarta.validation.Valid

class ValidAnnotationSpec extends BaseSpec {

  case class MyBeanWithOption(
    @(Valid @field)
    option: Option[InnerBeanWithOption]
  )

  case class InnerBeanWithOption(
    @(Length @field)(max = 2)
    name: String
  )

  Seq(
    (MyBeanWithOption(Some(InnerBeanWithOption("1"))), 0),
    (MyBeanWithOption(Some(InnerBeanWithOption("123"))), 1)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }

  case class MyBeanWithSet(
    @(Valid @field)
    set: Set[InnerBeanWithSet]
  )

  case class InnerBeanWithSet(
    @(Length @field)(max = 2)
    name: String
  )

  Seq(
    (MyBeanWithSet(Set(InnerBeanWithSet("1"))), 0),
    (MyBeanWithSet(Set(InnerBeanWithSet("123"))), 1)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }

  case class MyBeanWithSeq(
    @(Valid @field)
    seq: Set[InnerBeanWithSeq]
  )

  case class InnerBeanWithSeq(
    @(Length @field)(max = 2)
    name: String
  )

  Seq(
    (MyBeanWithSeq(Set(InnerBeanWithSeq("1"))), 0),
    (MyBeanWithSeq(Set(InnerBeanWithSeq("123"))), 1)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }

  case class MyBeanWithMap(
    @(Valid @field)
    seq: Map[String, InnerBeanWithMap]
  )

  case class InnerBeanWithMap(
    @(Length @field)(max = 2)
    name: String
  )

  Seq(
    (MyBeanWithMap(Map("1" -> InnerBeanWithMap("1"))), 0),
    (MyBeanWithMap(Map("2" -> InnerBeanWithMap("123"))), 1)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }
}