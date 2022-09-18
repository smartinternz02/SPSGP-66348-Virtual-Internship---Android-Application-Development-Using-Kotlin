package singha.com.amit.dataclasses

data class Item(val name: String?, val price: Float, val image: String?,var quantity: Int=1, var totalPrice:Float=quantity*price)
