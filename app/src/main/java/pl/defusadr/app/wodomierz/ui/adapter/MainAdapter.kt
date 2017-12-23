package pl.defusadr.app.wodomierz.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import pl.defusadr.app.wodomierz.R
import pl.defusadr.app.wodomierz.model.WaterMeterValue
import pl.defusadr.app.wodomierz.util.inflate

class MainAdapter(var itemList: MutableList<WaterMeterValue>) : RecyclerView.Adapter<WaterMeterValueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterMeterValueViewHolder {
        val view = parent inflate R.layout.item_water_meter_value
        return WaterMeterValueViewHolder(view)
    }

    override fun onBindViewHolder(holder: WaterMeterValueViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}