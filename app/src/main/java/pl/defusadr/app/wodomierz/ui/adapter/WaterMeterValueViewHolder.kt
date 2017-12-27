package pl.defusadr.app.wodomierz.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RadioButton
import kotlinx.android.synthetic.main.item_water_meter_value.view.*
import pl.defusadr.app.wodomierz.model.WaterMeterValue
import java.text.SimpleDateFormat
import java.util.*

class WaterMeterValueViewHolder(itemView: View, private val itemIndex: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

    val radioButton: RadioButton? = itemView.itemRadioButton

    fun bind(value: WaterMeterValue) {
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())

        itemView.itemTvDate.text = sdf.format(value.date)
        itemView.itemTvValue.text = value.amount.toString()

        itemView.setOnClickListener{ itemIndex(adapterPosition) }
    }
}