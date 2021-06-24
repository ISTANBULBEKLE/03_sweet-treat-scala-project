import SweetTreat.{Bobby, Geoff, Martin}
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest

class SweetTreatTest  extends PlaySpec with GuiceOneAppPerTest {

    " readyCourier method in SweetTreat.scala" should {
        "gives the valid range" when{
            "the range is between 9-13 is true" in{
                SweetTreat.readyCourier(List(9,13), 10) mustBe true
            }
        }
        "gives the invalid range" when{
            "the range is not between 9-13 is false" in{
                SweetTreat.readyCourier(List(9,13), 16) mustBe false
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
        val Bobby = Courier("Booby", List(9,13), true, 5, 1.75)
        val Martin = Courier("Martin", List(9,17), false, 3, 1.50)
        val Geoff =  Courier("Geoff",List(10,16), true, 4, 2.0)

        "give the valid cost" when{
            "the ListOfCourier is valid, returns true" in{
                SweetTreat.smallestCost(List(Bobby, Martin, Geoff)) mustBe Martin
            }
        }
    }

    "selectTheCheapestCourier method in SweetTreat.scala" should {
        "give the cheapest courier" when{
            "the list of courier is equal or more than 1" in{
                SweetTreat.selectTheCheapestCourier(true, true) mustBe true
            }
        }
        "give invalid courier" when{
            "the list of courier is not valid" in{
                SweetTreat.selectTheCheapestCourier(false, true) mustBe false
            }
        }
    }


}
