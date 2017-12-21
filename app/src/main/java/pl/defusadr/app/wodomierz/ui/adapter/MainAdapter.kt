package pl.defusadr.app.wodomierz.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import pl.defusadr.app.wodomierz.model.WaterMeterValue

class MainAdapter(var itemList : MutableList<WaterMeterValue>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun lastItemPosition(): Int {
        return itemList.size - 1
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}