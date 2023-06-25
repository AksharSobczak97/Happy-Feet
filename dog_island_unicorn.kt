// Importing packages
import java.util.ArrayList
import java.util.HashMap

// Class ShoeStore
class ShoeStore {
    //Attributes
    private val stock: HashMap<String, ArrayList<Shoe>> = HashMap()
    private var totalSales: Int = 0

    //Function to add a new shoe to the store
    fun addShoe(shoe: Shoe) {
        if (stock.containsKey(shoe.brand)) {
            stock[shoe.brand]?.add(shoe)
        } else {
            stock[shoe.brand] = arrayListOf(shoe)
        }
    }

    //Function to get total sales
    fun getTotalSales(): Int {
        return this.totalSales
    }

    //Function to show the shoes available
    fun showInventory() {
        for (brand in stock.keys) {
            println("Brand: $brand")
            for (shoe in stock[brand]!!) {
                println("  Model: ${shoe.model}, Color: ${shoe.color}, Price: ${shoe.price}")
            }
            println()
        }
    }

    //Function to make a purchase
    fun purchase(shoe: Shoe) {
        if (stock.containsKey(shoe.brand) && stock[shoe.brand]?.contains(shoe) == true) {
            this.totalSales += shoe.price
            stock[shoe.brand]?.remove(shoe)
        } else {
            println("Shoe not found.")
        }
    }
    
    //Function to get all shoes in store of a specified brand
    fun getBrandInventory(brand: String): ArrayList<Shoe>? {
        return stock[brand]
    }
} 

// Class Shoe
class Shoe(val model: String, val color: String, val brand: String, val price: Int) {
    // Override object equals and hashCode to work properly with HashMap
    override fun equals(other: Any?): Boolean {
        if (other is Shoe) {
            if (other.model == this.model && other.brand == this.brand && other.color == this.color && other.price == this.price) {
                return true
            }
        }
        return false
    }

    override fun hashCode(): Int {
        var result = model.hashCode()
        result = 31 * result + color.hashCode()
        result = 31 * result + brand.hashCode()
        result = 31 * result + price
        return result
    }
}

// Program entry point
fun main() {
    // Creating a store instance
    val store = ShoeStore()

    // Adding shoes to the store
    store.addShoe(Shoe("A1", "black", "Nike", 100))
    store.addShoe(Shoe("A2", "white", "Adidas", 80))
    store.addShoe(Shoe("A1", "black", "Nike", 100))
    store.addShoe(Shoe("A3", "red", "Puma", 50))
    store.addShoe(Shoe("A1", "white", "Nike", 110))

    // Printing the store inventory
    store.showInventory()

    // Getting a shoe
    val shoe = store.getBrandInventory("Nike")?.get(0)

    // Make a purchase
    store.purchase(shoe!!)

    // Printing total sales
    println("Total sales: ${store.getTotalSales()}")
}