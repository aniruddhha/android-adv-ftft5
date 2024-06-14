package com.ani.android.recycler

import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

data class DataItem(
    val img: Int,
    val ttl: String
)
class SimpleAdapter(
    private val dataSource: List<DataItem>
): RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder>() {

    private val _channel = Channel<DataItem>()
    val channel: Flow<DataItem> = _channel.receiveAsFlow()

    class SimpleViewHolder(
       val view: View
    ): RecyclerView.ViewHolder(view) {
        val image: ImageView
        val text: TextView

        init {
            view.apply {
                image = findViewById<ImageView>(R.id.imageView)
                text = findViewById<TextView>(R.id.textView)
            }
        }
    }

    override fun getItemCount(): Int = dataSource.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        return LayoutInflater.from(parent.context).let {
            val view = it.inflate(R.layout.recycler_item, parent, false)
            SimpleViewHolder(view)
        }
    }
    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {

//        val itm = dataSource[position]
//        itm.img
//        itm.ttl

        val (img, ttl) = dataSource[position]
        holder.image.setImageResource(img)
        holder.text.text = ttl
        holder.view.setOnClickListener {
            _channel.trySend(
                dataSource[position]
            )
        }
    }
}