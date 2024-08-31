package nextstep.shoppingcart.ui.product.detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import nextstep.shoppingcart.data.Cart
import nextstep.shoppingcart.model.Product
import nextstep.shoppingcart.ui.cart.CartActivity
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

class ProductDetailActivity : ComponentActivity() {

    private val product: Product by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_PRODUCT, Product::class.java)
                ?: throw IllegalArgumentException("productId is required")
        } else {
            intent.getParcelableExtra(EXTRA_PRODUCT)
                ?: throw IllegalArgumentException("Product is required")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingCartTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductDetailScreen(
                        product = product,
                        onClickBackButton = { finish() },
                        onClickCartAddButton = {
                            Cart.addOne(product)
                            startActivity(Intent(this, CartActivity::class.java))
                        }
                    )
                }
            }
        }
    }

    companion object {
        const val EXTRA_PRODUCT = "product"
    }
}
