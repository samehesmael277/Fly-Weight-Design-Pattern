// Flyweight interface
interface CoffeeOrder {
    fun serveCoffee(flavor: String)
}

// Concrete Flyweight
class CoffeeOrderImpl(private val coffeeFlavor: String) : CoffeeOrder {
    override fun serveCoffee(flavor: String) {
        println("Serving coffee with flavor '$coffeeFlavor' to customer with flavor '$flavor'")
    }
}

// Flyweight Factory
class CoffeeOrderFactory {
    private val coffeeOrders: MutableMap<String, CoffeeOrder> = mutableMapOf()

    fun getCoffeeOrder(flavor: String): CoffeeOrder {
        return coffeeOrders.computeIfAbsent(flavor) {
            println("Creating new coffee order for flavor '$flavor'")
            CoffeeOrderImpl(flavor)
        }
    }
}

// Client code
fun main() {
    val coffeeFactory = CoffeeOrderFactory()

    // Clients order different flavors of coffee
    val order1 = coffeeFactory.getCoffeeOrder("Cappuccino")
    val order2 = coffeeFactory.getCoffeeOrder("Espresso")
    val order3 = coffeeFactory.getCoffeeOrder("Cappuccino")
    val order4 = coffeeFactory.getCoffeeOrder("Latte")

    // Serve coffee to customers
    order1.serveCoffee("Sugarless")
    order2.serveCoffee("Double-shot")
    order3.serveCoffee("Regular")
    order4.serveCoffee("Vanilla")
}