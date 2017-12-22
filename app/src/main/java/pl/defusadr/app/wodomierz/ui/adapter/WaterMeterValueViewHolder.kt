package pl.defusadr.app.wodomierz.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_water_meter_value.view.*
import pl.defusadr.app.wodomierz.model.WaterMeterValue
import java.text.SimpleDateFormat
import java.util.*

class WaterMeterValueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(value: WaterMeterValue) {
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

        itemView.itemTvDate.text = sdf.format(value.date)
        itemView.itemTvDescription.text = "Opis: " + value.description
        itemView.itemTvValue.text = value.amount.toString()
    }
}