package pl.defusadr.app.wodomierz.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import pl.defusadr.app.wodomierz.R
import pl.defusadr.app.wodomierz.model.WaterMeterValue
import pl.defusadr.app.wodomierz.util.inflate

class MainAdapter(var itemList: MutableList<WaterMeterValue>, var editMode: Boolean) : RecyclerView.Adapter<WaterMeterValueViewHolder>() {

    private var checkedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterMeterValueViewHolder {
        val view = parent inflate R.layout.item_water_meter_value
        return WaterMeterValueViewHolder(view, { position ->
            checkedPosition = position
            notifyDataSetChanged()
        })
    }

    override fun onBindViewHolder(holder: WaterMeterValueViewHolder, position: Int) {
        if (editMode) {
            holder.radioButton?.visibility = View.VISIBLE
            holder.radioButton?.isChecked = position == checkedPosition
        } else {
            holder.radioButton?.visibility = View.INVISIBLE
        }
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun getSelectedItem(): WaterMeterValue {
        return itemList[checkedPosition]
    }
}