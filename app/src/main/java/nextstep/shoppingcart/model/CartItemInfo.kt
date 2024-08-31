package nextstep.shoppingcart.model

data class CartItemInfo(
    val product: Product,
    val count: Int,
) {
    val totalPrice: Long get() = product.price * count
}
