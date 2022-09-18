package singha.com.amit.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import singha.com.amit.databinding.FragmentCartBinding
import singha.com.amit.databinding.ItemBasketCardBinding
import singha.com.amit.dataclasses.Item
import singha.com.amit.singletons.Capitalizer
import singha.com.amit.singletons.DecimalFormatter
import singha.com.amit.singletons.VirtualDB


class CartItemAdapter(  private val items: MutableList<Item>,val fragmentCartBinding: FragmentCartBinding) : RecyclerView.Adapter<CartItemAdapter.TaskViewHolder>() {
    class TaskViewHolder(val binding: ItemBasketCardBinding) : RecyclerView.ViewHolder(binding.root)
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        context=parent.context
        return TaskViewHolder(
            ItemBasketCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    fun removeItem(currentItem:Item) {
            items.remove(currentItem)
            notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
            val currentItem = items[position]
            holder.binding.apply {
                itemNameText.text = Capitalizer.capitalized(currentItem.name.toString())
                itemQuantityText.text = currentItem.quantity.toString()
                totalPriceText.text=DecimalFormatter.format(currentItem.totalPrice)
                val id = context.resources.getIdentifier(
                    currentItem.image,
                    "drawable",
                    context.packageName
                )
                itemImage.setImageResource(id)
                moreButton.setOnClickListener {
                    val quan= VirtualDB.increaseItemCount(currentItem.name)
                    itemQuantityText.text=quan.toString()
                    totalPriceText.text=DecimalFormatter.format(currentItem.totalPrice)
                    val total=VirtualDB.calculateTotal()
                    fragmentCartBinding.subtotalText.text=DecimalFormatter.format(total)
                    fragmentCartBinding.totalText.text=DecimalFormatter.format(total+VirtualDB.deliveryFee)
                }
                lessButton.setOnClickListener {
                    val quan= VirtualDB.decreaseItemCount(currentItem.name)
                    if (quan!=-1){
                    itemQuantityText.text=quan.toString()
                    totalPriceText.text=DecimalFormatter.format(currentItem.totalPrice)
                    }else{
                        removeItem(currentItem)
                    }
                    val total=VirtualDB.calculateTotal()
                    fragmentCartBinding.subtotalText.text=DecimalFormatter.format(total)
                    fragmentCartBinding.totalText.text=DecimalFormatter.format(total+VirtualDB.deliveryFee)
                }
            }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}