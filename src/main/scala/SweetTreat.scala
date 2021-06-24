object SweetTreat extends App {

  val Bobby = Courier("Booby", List(9,13), true, 5, 1.75)
  val Martin = Courier("Martin", List(9,17), false, 3, 1.50)
  val Geoff =  Courier("Geoff",List(10,16), true, 4, 2.0)
  val listOfCourier= List(Bobby, Martin, Geoff )
  val order1 = Order(10, 3.0, true)

  def selectTheCourier(order:Order, courier:Courier):Boolean={
    val isCourierAvailable = readyCourier(courier.time, order.time)
    val isDistanceAvailable = availableDistance(courier.distance, order.distance)
    val isRefrigeratorAvailable = availableRefrigerator(courier.hasRefrigerator, order.needRefrigerator)
    isCourierAvailable && isDistanceAvailable && isRefrigeratorAvailable
  }

  def selectTheCheapestCourier(list:List[Courier]=listOfCourier, order:Order = order1):Courier={
    val filteredList = list.filter(x => selectTheCourier(order, x))
    if (filteredList.length == 1) filteredList.head
    else smallestCost(filteredList)
  }
  // println(" selectTheCheapestCourier is" + selectTheCheapestCourier (List(Bobby, Martin, Geoff ),Order(11, 4.0, true) ) )

  def readyCourier(time:List[Int], orderTime:Int):Boolean={
    val range = time(0) to time(1)
    range.contains(orderTime)
  }

  def availableDistance(distance:Double, orderDistance:Double):Boolean={
    orderDistance < distance
  }

  def availableRefrigerator(hasRefrigerator:Boolean, needRefrigerator:Boolean):Boolean={
    if (!needRefrigerator) true else hasRefrigerator
  }

  def smallestCost(courier:List[Courier]):Courier={
     val oneCourier= courier.minBy(_.chargePerMile)
    println(" oneCourier is " +  oneCourier)
    oneCourier
  }

}

  case class Courier (name:String, time:List[Int], hasRefrigerator:Boolean, distance:Double, chargePerMile:Double)
  case class Order(time:Int, distance:Double, needRefrigerator:Boolean)




/*
Cake shop goes digital!
Part 1
Sweet Treats store is now open for deliveries direct to your door.
Sweet Treats has three contracted cycle-couriers who deliver products to customers.
The couriers charge different rates, have different working hours and different facilities available.
Sweet Treats needs to select the most appropriate courier for the job:
-Bobby works 9am to 1pm, will deliver up to 5 miles and has
a refrigerated box on his bike. He charges $1.75 per mile
-Martin works 9am-5pm, will deliver up to 3 miles with
no refrigerated box available. He charges $1.50 per mile
-Geoff works 10am – 4pm, will deliver up to 4 miles and has
a refrigerated box on his bike. He charges $2.00 per mile.
Sweet Treats needs to select the cheapest courier based on:
Working hours of the courier
The distance between the store and the customers location
Is refrigeration is required
- Create an application to help Sweet Treats select the best courier.
Inputs to the application are:
time of day the order is ready to be shipped
the distance of the required delivery
an indicator as to whether refrigeration is required
 */