package pl.defusadr.app.wodomierz.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import pl.defusadr.app.wodomierz.R
import pl.defusadr.app.wodomierz.model.WaterMeterValue
import pl.defusadr.app.wodomierz.util.inflate

class MainAdapter(var itemList: MutableList<WaterMeterValue>, var deleteMode: Boolean) : RecyclerView.Adapter<WaterMeterValueViewHolder>() {

    var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterMeterValueViewHolder {
        val view = parent inflate R.layout.item_water_meter_value
        return WaterMeterValueViewHolder(view, { position ->
            selectedPosition = position
            notifyDataSetChanged()
        })
    }

    override fun onBindViewHolder(holder: WaterMeterValueViewHolder, position: Int) {
        if (deleteMode) {
            holder.radioButton?.visibility = View.VISIBLE
            holder.radioButton?.isChecked = position == selectedPosition
        } else {
            holder.radioButton?.visibility = View.INVISIBLE
        }
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun getSelectedItem(): WaterMeterValue = itemList[selectedPosition]

    fun resetSelectedItem() {
        selectedPosition = 0
    }
}