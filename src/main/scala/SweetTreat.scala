object SweetTreat extends App {

  val Bobby = Courier("Booby", List(9,13), true, 5, 1.75, false)
  val Martin = Courier("Martin", List(9,17), false, 3, 1.50, false)
  val Geoff =  Courier("Geoff",List(10,16), true, 4, 2.0, true)
  val listOfCourier= List(Bobby, Martin, Geoff )
  val order1 = Order(10, 3.0, true)

  def checkCourierCriteria(order:Order, courier:Courier):Boolean={
    val isCourierAvailable = availableCourier(courier.time, order.time)
    val isDistanceAvailable = availableDistance(courier.distance, order.distance)
    val isRefrigeratorAvailable = availableRefrigerator(courier.hasRefrigerator, order.needRefrigerator)
    isCourierAvailable && isDistanceAvailable && isRefrigeratorAvailable
  }

//  def selectCheapestCourier(courierList: List[Courier] = listOfCourier, order: Order = order1): Option[Courier] ={
//    val filteredList = courierList.filter(x => checkCourierCriteria(order, x))
//
//
//    val listToTuples = filteredList.map(x => (x.name, x.chargePerMile * order.distance))
//
//
//    val listWithTotalCostPerOrder = generateListWithTotalCostPerOrder(filteredList, order)
//    val preferredCourier = listWithTotalCostPerOrder.filter(_.preferredCourier).sortBy(_.chargePerMile)
//    val priceDifference = if(listWithTotalCostPerOrder.nonEmpty && preferredCourier.nonEmpty) preferredCourier.head.chargePerMile - listWithTotalCostPerOrder.head.chargePerMile
//    else 100
//    if(priceDifference < 1 && preferredCourier.nonEmpty) Some(preferredCourier.head)
//    else if(listWithTotalCostPerOrder.nonEmpty) Some(listWithTotalCostPerOrder.head)
//    else None
//  }

  def selectTheCheapestCourier(list: List[Courier] = listOfCourier, order: Order = order1): Option[Courier]={
    val filteredList = list.filter(x => checkCourierCriteria(order, x))
    val preferredCourier = smallestCost(filteredList.filter(_.preferredCourier))
    val cheapestCourier = smallestCost(filteredList)
    println("preferredCourier" + preferredCourier)
    println("cheapestCourier" + cheapestCourier)
    val a = preferredCourier.getOrElse(-1)
    val b = cheapestCourier.getOrElse(-1)
    val priceDifference = if(a != -1 && b != -1) (preferredCourier.get.chargePerMile * order.distance) - (cheapestCourier.get.chargePerMile * order.distance)
    else 2
    if(priceDifference < 1 ) Some(preferredCourier.head)
    else if(cheapestCourier.nonEmpty) Some(cheapestCourier.head)
    else None
  }

  def availableCourier(time:List[Int], orderTime:Int):Boolean={
    val range = time(0) to time(1)
    range.contains(orderTime)
  }

  def availableDistance(distance:Double, orderDistance:Double):Boolean={
    orderDistance < distance
  }

  def availableRefrigerator(hasRefrigerator:Boolean, needRefrigerator:Boolean):Boolean={
    if (!needRefrigerator) true else hasRefrigerator
  }

  def smallestCost(courier:List[Courier]):Option[Courier]={
    if(courier.nonEmpty)  Some(courier.minBy(_.chargePerMile))
    else None
  }

  def availableCouriersOrderedByPrice(courierList: List[Courier], order:Order):List[Courier]={
    val filteredList = courierList.filter(x => checkCourierCriteria(order, x))
    filteredList.sortBy(_.chargePerMile)
  }

  def generateListWithTotalCostPerOrder(list: List[Courier], order: Order): List[Courier] = {
    list.map(x =>
      Courier(x.name, x.time, x.hasRefrigerator, x.distance, x.chargePerMile * order.distance, x.preferredCourier)
    ).sortBy(_.chargePerMile)
  }

//  def cheapestPreferredCourier(couriersList: List[Courier], order: Order): Option[Courier] = {
//    val filteredList = couriersList.filter(x => checkCourierCriteria(order, x))
//    val listWithTotalCostPerOrder = generateListWithTotalCostPerOrder(filteredList, order)
//    val preferredCourier = listWithTotalCostPerOrder.filter(_.preferredCourier).sortBy(_.chargePerMile)
//    if((preferredCourier.head.chargePerMile - listWithTotalCostPerOrder.head.chargePerMile) < 1) Some(preferredCourier.head)
//    else Some(listWithTotalCostPerOrder.head)
//  }

}

  case class Courier (name:String, time:List[Int], hasRefrigerator:Boolean, distance:Double, chargePerMile:Double, preferredCourier:Boolean)
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