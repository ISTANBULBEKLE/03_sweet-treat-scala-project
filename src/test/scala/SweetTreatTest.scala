
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest

class SweetTreatTest  extends PlaySpec with GuiceOneAppPerTest {

    " checkCourierCriteria method in SweetTreat.scala" should {
        val Bobby = Courier("Booby", List(9,13), true, 5, 1.75, false)
        val Martin = Courier("Martin", List(9,17), false, 3, 1.50, false)
        val Geoff =  Courier("Geoff",List(10,16), true, 4, 2.0, true)

        val order1 = Order(10, 3.0, true)

        "gives the valid couriers" when{
            "all three methods are satisfied" in{
                SweetTreat.checkCourierCriteria(order1, Bobby) mustBe true
            }
        }
        "gives the invalid couriers" when{
            val order1 = Order(10, 8.0, true)
            "all three methods are not satisfied" in{
                SweetTreat.checkCourierCriteria(order1, Martin) mustBe false
            }
        }

    }

    " availableCourier method in SweetTreat.scala" should {
        "gives the valid range" when{
            "the range is between 9-13 is true" in{
                SweetTreat.availableCourier(List(9,13), 10) mustBe true
            }
        }
        "gives the invalid range" when{
            "the range is not between 9-13 is false" in{
                SweetTreat.availableCourier(List(9,13), 16) mustBe false
            }
        }
    }

    " availableDistance method in SweetTreat.scala" should {
        "gives the valid distance" when{
            "the distance is not more then 5 is true" in{
                SweetTreat.availableDistance(5, 2) mustBe true
            }
        }
        "gives the invalid distance" when{
            "the distance is greater then 5 is false" in{
                SweetTreat.availableDistance(5, 6) mustBe false
            }
        }
    }

    " availableRefrigerator method in SweetTreat.scala" should {
        "give the valid refrigerator" when{
            "the Order.needRefrigerator is valid, returns true" in{
                SweetTreat.availableRefrigerator(true, true) mustBe true
            }
        }
        "is invalid refrigerator" when{
            "the Order.needRefrigerator is not valid, returns false" in{
                SweetTreat.availableRefrigerator(false, true) mustBe false
            }
        }
    }

    " smallestCost method in SweetTreat.scala" should {
        val Bobby = Courier("Booby", List(9,13), true, 5, 1.75, false)
        val Martin = Courier("Martin", List(9,17), false, 3, 1.50, false)
        val Geoff =  Courier("Geoff",List(10,16), true, 4, 2.0, true)

        "give the valid cost" when{
            "the ListOfCourier is valid, returns Some(Courier)" in{
                SweetTreat.smallestCost(List(Bobby, Martin, Geoff)) mustBe Some(Martin)
            }
        }

        "give the invalid cost" when{
            "the ListOfCourier is not valid, returns None" in{
                SweetTreat.smallestCost(List()) mustBe None
            }
        }
    }

    "selectTheCheapestCourier method in SweetTreat.scala" should {
        val order1 = Order(10, 3.0, true)
        val Bobby = Courier("Booby", List(9,13), true, 5, 1.75, false)
        val Martin = Courier("Martin", List(9,17), true, 3, 1.50, false)
        val Geoff =  Courier("Geoff",List(10,16), true, 4, 2.0, true)

        "give the cheapest courier" when{
            "the list of courier is equal or more than 1" in{
                SweetTreat.selectTheCheapestCourier(List(Bobby, Martin, Geoff ), order1) mustBe Some(Martin)
            }
        }
        "give invalid order" when{
            val order1 = Order(10, 7.0, true)
            "the list of courier is not valid" in{
                SweetTreat.selectTheCheapestCourier(List(Bobby, Martin, Geoff), order1) mustBe None
            }
        }
    }

    ////////////Part II

    "availableCouriersOrderedByPrice method in SweetTreat.scala" should {
        "give a list of couriers sorted by cheapest to expensive" when{

            val Bobby = Courier("Booby", List(9,13), true, 5, 1.75, false)
            val Martin = Courier("Martin", List(9,17), false, 3, 1.50, false)
            val Geoff =  Courier("Geoff",List(10,16), true, 4, 2.0, true)
            val order1 = Order(11, 2.0, false)

            "return an ordered list of available couriers" in{
                SweetTreat.availableCouriersOrderedByPrice(List(Bobby, Martin, Geoff), order1) mustBe List(Martin, Bobby, Geoff)
            }
        }

        "give invalid courier List" when{
            val Bobby1 = Courier("Booby", List(5,9), true, 5, 1.75, false)
            val Martin1 = Courier("Martin", List(9,17), false, 3, 1.50, false)
            val Geoff1 =  Courier("Geoff",List(10,16), true, 4, 2.0, true)
            val order2 = Order(11, 10, false)

            "return an empty list" in{
                SweetTreat.availableCouriersOrderedByPrice(List(Bobby1, Martin1, Geoff1), order2) mustBe List()
            }
        }
    }
//
//    "cheapestPreferredCourier method in SweetTreat.scala" should {
//        "give a list of total cost couriers sorted by cheapest to expensive " when{
//
//            val Bobby = Courier("Booby", List(9,13), true, 5, 1.75, false)
//            val Martin = Courier("Martin", List(9,17), false, 3, 1.50, false)
//            val Geoff =  Courier("Geoff",List(10,16), true, 4, 2.0, true)
//            val order1 = Order(11, 2.0, false)
//
//            "return an ordered list of available couriers" in{
//                SweetTreat.cheapestPreferredCourier(List(Bobby, Martin, Geoff), order1) mustBe List(Martin, Bobby, Geoff)
//            }
//        }
//
//        "give invalid courier List" when{
//            val Bobby1 = Courier("Booby", List(5,9), true, 5, 1.75, false)
//            val Martin1 = Courier("Martin", List(9,17), false, 3, 1.50, false)
//            val Geoff1 =  Courier("Geoff",List(10,16), true, 4, 2.0, true)
//            val order2 = Order(11, 10, false)
//
//            "return an empty list" in{
//                SweetTreat.cheapestPreferredCourier(List(Bobby1, Martin1, Geoff1), order2) mustBe List()
//            }
//        }
//    }

}
