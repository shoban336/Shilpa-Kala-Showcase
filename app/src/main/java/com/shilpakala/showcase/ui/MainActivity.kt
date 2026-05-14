package com.shilpakala.showcase.ui

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.core.view.setPadding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.shilpakala.showcase.R
import com.shilpakala.showcase.data.Product
import android.widget.ImageView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val viewModel: ShowcaseViewModel by viewModels()
    private val repository get() = viewModel.repository
    private val savedProductIds get() = viewModel.savedProductIds
    private lateinit var content: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(getColor(R.color.stone))
        }
        content = FrameLayout(this)
        val nav = BottomNavigationView(this).apply {
            setBackgroundColor(Color.WHITE)
            itemIconTintList = null
            menu.add(0, NAV_HOME, 0, getString(R.string.nav_home)).setIcon(android.R.drawable.ic_menu_view)
            menu.add(0, NAV_GALLERY, 1, getString(R.string.nav_gallery)).setIcon(android.R.drawable.ic_menu_gallery)
            menu.add(0, NAV_SAVED, 2, getString(R.string.nav_saved)).setIcon(android.R.drawable.btn_star_big_on)
            menu.add(0, NAV_SELLER, 3, getString(R.string.nav_seller)).setIcon(android.R.drawable.ic_menu_manage)
            setOnItemSelectedListener {
                when (it.itemId) {
                    NAV_HOME -> showHome()
                    NAV_GALLERY -> showGallery()
                    NAV_SAVED -> showSaved()
                    NAV_SELLER -> showSeller()
                }
                true
            }
        }

        root.addView(content, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f))
        root.addView(nav, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
        setContentView(root)
        nav.selectedItemId = NAV_HOME
    }

    private fun showHome() {
        content.replaceWith(scrollRoot().apply {
            addHeader("Shilpa-Kala Showcase", "Premium sculptures from verified Shilpis")
            addChipRow(listOf("Hoysala", "Dravidian", "Chola", "Modern", "Wood"))
            addSectionTitle("Featured Sculptures")
            addProductGrid(repository.products.take(4))
            addSectionTitle("Verified Shilpis")
            repository.sellers.forEach { seller ->
                addView(card {
                    addText(seller.name, 20f, R.color.charcoal, true)
                    addText("${seller.village} | ${seller.specialty}", 14f, R.color.charcoal)
                    addText("${seller.yearsOfExperience} years | Rating ${seller.rating}", 13f, R.color.charcoal)
                })
            }
            addSectionTitle("Heritage Stories")
            repository.stories.forEach { story ->
                addView(card {
                    addText(story.title, 18f, R.color.charcoal, true)
                    addText("${story.style}: ${story.description}", 14f, R.color.charcoal)
                })
            }
        })
    }

    private fun showGallery(filter: String? = null) {
        val products = filter?.let { f ->
            repository.products.filter { it.carvingStyle == f || it.material == f }
        } ?: repository.products

        content.replaceWith(scrollRoot().apply {
            addHeader("Gallery", "Browse active sculptures and commission-ready work")
            addChipRow(listOf("All", "Hoysala", "Dravidian", "Chola", "Black Granite", "Wood")) { value ->
                showGallery(if (value == "All") null else value)
            }
            addProductGrid(products)
        })
    }

    private fun showSaved() {
        val products = repository.products.filter { it.productId in savedProductIds }
        content.replaceWith(scrollRoot().apply {
            addHeader("Saved", "Your bookmarked sculptures")
            if (products.isEmpty()) {
                addView(card {
                    addText("No saved sculptures yet.", 18f, R.color.charcoal, true)
                    addText("Open any product and tap Save to keep it here.", 14f, R.color.charcoal)
                })
            } else {
                addProductGrid(products)
            }
        })
    }

    private fun showSeller() {
        val seller = repository.sellers.first()
        val products = repository.products.filter { it.sellerId == seller.uid }
        content.replaceWith(scrollRoot().apply {
            addHeader("Seller Dashboard", "Manage portfolio, enquiries, and heritage documentation")
            addView(card {
                addText(seller.name, 22f, R.color.charcoal, true)
                addText("${seller.specialty} | ${seller.village}", 14f, R.color.charcoal)
                addText("Verified artisan: ${if (seller.isVerifiedArtisan) "Yes" else "Pending"}", 14f, R.color.charcoal)
            })
            addView(metricRow("Products", products.size.toString(), "Views", products.sumOf { it.viewCount }.toString(), "Rating", seller.rating.toString()))
            addSectionTitle("Portfolio")
            addProductGrid(products)
            addView(Button(this@MainActivity).apply {
                text = "Add Product"
                setTextColor(Color.WHITE)
                setBackgroundColor(getColor(R.color.heritage_brown))
                setOnClickListener { showAddProductDialog() }
            }, paddedParams())
        })
    }

    private fun showProduct(product: Product) {
        val seller = repository.sellerFor(product)
        content.replaceWith(scrollRoot().apply {
            addView(TextView(this@MainActivity).apply {
                text = "< Gallery"
                textSize = 16f
                setTextColor(getColor(R.color.heritage_brown))
                setPadding(16.dp)
                setOnClickListener { showGallery() }
            })
            addHeader(product.title, product.productId)
            addView(FrameLayout(this@MainActivity).apply {
                layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300.dp)
                val progress = android.widget.ProgressBar(this@MainActivity).apply {
                    layoutParams = FrameLayout.LayoutParams(48.dp, 48.dp, Gravity.CENTER)
                }
                val imageView = ImageView(this@MainActivity).apply {
                    layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    setOnClickListener { showZoomedImage(product.imageUrl) }
                }
                addView(progress)
                addView(imageView)
                Glide.with(this).load(product.imageUrl).into(object : com.bumptech.glide.request.target.CustomTarget<android.graphics.drawable.Drawable>() {
                    override fun onResourceReady(resource: android.graphics.drawable.Drawable, transition: com.bumptech.glide.request.transition.Transition<in android.graphics.drawable.Drawable>?) {
                        progress.visibility = View.GONE
                        imageView.setImageDrawable(resource)
                    }
                    override fun onLoadCleared(placeholder: android.graphics.drawable.Drawable?) {
                        imageView.setImageDrawable(placeholder)
                    }
                })
            })
            addView(card {
                addText("INR ${product.price}", 24f, R.color.heritage_brown, true)
                addText("${product.availability} | ${product.material} | ${product.carvingStyle}", 15f, R.color.charcoal)
                addText("Dimensions: ${product.dimensions} | Weight: ${product.weight}", 14f, R.color.charcoal)
                addText("Stone freshness: ${product.stoneFreshness}", 14f, R.color.charcoal)
                addText(product.description, 15f, R.color.charcoal)
            })

            if (product.wipTimelineImages.isNotEmpty()) {
                addSectionTitle("Work-in-Progress (Stone to Statue)")
                addView(HorizontalScrollView(this@MainActivity).apply {
                    isHorizontalScrollBarEnabled = false
                    addView(LinearLayout(this@MainActivity).apply {
                        orientation = LinearLayout.HORIZONTAL
                        setPadding(16.dp, 8.dp, 16.dp, 16.dp)
                        product.wipTimelineImages.forEachIndexed { index, url ->
                            addView(LinearLayout(this@MainActivity).apply {
                                orientation = LinearLayout.VERTICAL
                                gravity = Gravity.CENTER
                                addView(card {
                                    setPadding(0)
                                    addView(ImageView(this@MainActivity).apply {
                                        layoutParams = FrameLayout.LayoutParams(120.dp, 120.dp)
                                        scaleType = ImageView.ScaleType.CENTER_CROP
                                        Glide.with(this).load(url).into(this)
                                        setOnClickListener { showZoomedImage(url) }
                                    })
                                })
                                addText("Stage ${index + 1}", 12f, R.color.charcoal)
                            }, LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                                marginEnd = 12.dp
                            })
                        }
                    })
                })
            }

            addView(card {
                addText(seller.name, 19f, R.color.charcoal, true)
                addText("${seller.village} | ${seller.yearsOfExperience} years experience", 14f, R.color.charcoal)
                addText(seller.bio, 14f, R.color.charcoal)
            })
            addView(actionRow(product, seller.whatsappNumber))
        })
    }

    private fun showZoomedImage(url: Any) {
        val root = FrameLayout(this).apply {
            setBackgroundColor(Color.BLACK)
            addView(PhotoView(this@MainActivity).apply {
                layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                Glide.with(this).load(url).into(this)
            })
            addView(TextView(this@MainActivity).apply {
                text = "✕"
                textSize = 32f
                setTextColor(Color.WHITE)
                setPadding(20.dp)
                layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.TOP or Gravity.END)
                setOnClickListener { dialog?.dismiss() }
            })
        }
        dialog = AlertDialog.Builder(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
            .setView(root)
            .show()
    }
    private var dialog: AlertDialog? = null

    private fun actionRow(product: Product, phone: String): View = LinearLayout(this).apply {
        orientation = LinearLayout.HORIZONTAL
        setPadding(16.dp)
        addView(Button(context).apply {
            text = if (product.productId in savedProductIds) "Saved" else "Save"
            setOnClickListener {
                viewModel.toggleSaved(product.productId)
                showProduct(product)
            }
        }, LinearLayout.LayoutParams(0, 52.dp, 1f).apply { marginEnd = 10.dp })
        addView(Button(context).apply {
            text = getString(R.string.btn_enquire_whatsapp)
            setTextColor(Color.WHITE)
            setBackgroundColor(getColor(R.color.leaf))
            setOnClickListener { openWhatsApp(phone, product) }
        }, LinearLayout.LayoutParams(0, 52.dp, 2f))
    }

    private fun openWhatsApp(phone: String, product: Product) {
        val message = "Hello, I am interested in ${product.title} (${product.productId}) priced at INR ${product.price}."
        val uri = Uri.parse("https://wa.me/$phone?text=${Uri.encode(message)}")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun showAddProductDialog() {
        AlertDialog.Builder(this)
            .setTitle("Add Product")
            .setMessage("The production form is scaffolded in the seller flow. Connect Firebase Storage to enable uploads.")
            .setPositiveButton("OK", null)
            .show()
    }

    private fun LinearLayout.addHeader(title: String, subtitle: String) {
        addView(LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(getColor(R.color.heritage_brown))
            setPadding(20.dp, 28.dp, 20.dp, 24.dp)
            addText(title, 28f, R.color.heritage_gold, true)
            addText(subtitle, 15f, android.R.color.white)
        }, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
    }

    private fun LinearLayout.addChipRow(values: List<String>, onClick: ((String) -> Unit)? = null) {
        addView(HorizontalScrollView(context).apply {
            isHorizontalScrollBarEnabled = false
            addView(ChipGroup(context).apply {
                setPadding(16.dp, 12.dp, 16.dp, 4.dp)
                values.forEach { value ->
                    addView(Chip(context).apply {
                        text = value
                        isCheckable = false
                        setOnClickListener { onClick?.invoke(value) }
                    })
                }
            })
        })
    }

    private fun LinearLayout.addSectionTitle(title: String) {
        addView(TextView(context).apply {
            text = title
            textSize = 20f
            setTextColor(getColor(R.color.charcoal))
            setPadding(16.dp, 20.dp, 16.dp, 8.dp)
            typeface = android.graphics.Typeface.DEFAULT_BOLD
        })
    }

    private fun LinearLayout.addProductGrid(products: List<Product>) {
        addView(RecyclerView(context).apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = ProductAdapter(products) { showProduct(it) }
            isNestedScrollingEnabled = false
        }, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
    }

    private fun scrollRoot(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(0, 0, 0, 20.dp)
        }
    }

    private fun FrameLayout.replaceWith(view: View) {
        removeAllViews()
        addView(ScrollView(this@MainActivity).apply { addView(view) })
    }

    private fun card(block: LinearLayout.() -> Unit): MaterialCardView {
        val inner = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16.dp)
            block()
        }
        return MaterialCardView(this).apply {
            radius = 8.dp.toFloat()
            cardElevation = 2.dp.toFloat()
            setCardBackgroundColor(Color.WHITE)
            addView(inner)
            layoutParams = paddedParams()
        }
    }

    private fun LinearLayout.addText(value: String, size: Float, colorRes: Int, bold: Boolean = false) {
        addView(TextView(context).apply {
            text = value
            textSize = size
            setTextColor(getColor(colorRes))
            if (bold) typeface = android.graphics.Typeface.DEFAULT_BOLD
            setPadding(0, 3.dp, 0, 3.dp)
        })
    }

    private fun metricRow(label1: String, value1: String, label2: String, value2: String, label3: String, value3: String): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(16.dp, 12.dp, 16.dp, 0)
            listOf(label1 to value1, label2 to value2, label3 to value3).forEach { (label, value) ->
                addView(card {
                    gravity = Gravity.CENTER
                    addText(value, 20f, R.color.heritage_brown, true)
                    addText(label, 12f, R.color.charcoal)
                }, LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f).apply { marginEnd = 8.dp })
            }
        }
    }

    private fun paddedParams() = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
        setMargins(16.dp, 8.dp, 16.dp, 8.dp)
    }

    companion object {
        private const val NAV_HOME = 1
        private const val NAV_GALLERY = 2
        private const val NAV_SAVED = 3
        private const val NAV_SELLER = 4
    }
}

private class ProductAdapter(
    private val products: List<Product>,
    private val onClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val card = MaterialCardView(parent.context).apply {
            radius = 8.dp.toFloat()
            cardElevation = 2.dp.toFloat()
            setCardBackgroundColor(Color.WHITE)
            layoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                setMargins(8.dp, 8.dp, 8.dp, 8.dp)
            }
        }
        return ProductViewHolder(card)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.bind(products[position], onClick)

    override fun getItemCount(): Int = products.size

    class ProductViewHolder(private val card: MaterialCardView) : RecyclerView.ViewHolder(card) {
        fun bind(product: Product, onClick: (Product) -> Unit) {
            card.removeAllViews()
            card.addView(LinearLayout(card.context).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(12.dp)
                addView(FrameLayout(context).apply {
                    layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 120.dp)
                    val progress = android.widget.ProgressBar(context).apply {
                        layoutParams = FrameLayout.LayoutParams(32.dp, 32.dp, Gravity.CENTER)
                    }
                    val imageView = ImageView(context).apply {
                        layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }
                    addView(progress)
                    addView(imageView)
                    Glide.with(this).load(product.imageUrl).into(object : com.bumptech.glide.request.target.CustomTarget<android.graphics.drawable.Drawable>() {
                        override fun onResourceReady(resource: android.graphics.drawable.Drawable, transition: com.bumptech.glide.request.transition.Transition<in android.graphics.drawable.Drawable>?) {
                            progress.visibility = View.GONE
                            imageView.setImageDrawable(resource)
                        }
                        override fun onLoadCleared(placeholder: android.graphics.drawable.Drawable?) {
                            imageView.setImageDrawable(placeholder)
                        }
                    })
                })
                addView(TextView(context).apply {
                    text = product.title
                    textSize = 16f
                    setTextColor(Color.rgb(34, 26, 23))
                    typeface = android.graphics.Typeface.DEFAULT_BOLD
                    setPadding(0, 10.dp, 0, 2.dp)
                })
                addView(TextView(context).apply {
                    text = "INR ${product.price}"
                    textSize = 14f
                    setTextColor(Color.rgb(62, 39, 35))
                })
                addView(TextView(context).apply {
                    text = "${product.material} | ${product.availability}"
                    textSize = 12f
                    setTextColor(Color.DKGRAY)
                })
            })
            card.setOnClickListener { onClick(product) }
        }
    }
}
