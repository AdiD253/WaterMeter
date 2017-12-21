package pl.defusadr.app.wodomierz.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import pl.defusadr.app.wodomierz.R
import pl.defusadr.app.wodomierz.ui.adapter.MainAdapter
import pl.defusadr.app.wodomierz.db.AppDatabase
import pl.defusadr.app.wodomierz.model.WaterMeterValue
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IMainActivityView {

    @Inject
    lateinit var presenter: MainActivityPresenter<IMainActivityView>

    private val adapter: MainAdapter by lazy {
        val itemList = AppDatabase.getDatabase(this).waterMeterDao().getAllValues()
        MainAdapter(itemList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        initRecyclerView()
        mainBtnSave.setOnClickListener {presenter.saveValue(getWaterMeterValue())}
    }

    override fun onStart() {
        super.onStart()
        presenter.onAttachView(this)
    }

    override fun onPause() {
        presenter.onDetachView()
        super.onPause()
    }

    override fun addValue(value: WaterMeterValue)  {
        adapter.itemList.add(value)
        adapter.notifyItemInserted(adapter.lastItemPosition())
    }

    private fun initRecyclerView() {
        mainRecyclerView.apply {
            adapter = this.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)

        }
    }

    private fun getWaterMeterValue() : Float {
        val integerValue = Integer.valueOf(mainIntegerInput.text.toString())
        var decimalValue = 0F

        val decimalInput = mainDecimalInput.text.toString()
        when (decimalInput.length) {
            3 -> decimalValue = decimalInput.toFloat()
            2 -> decimalValue = (decimalInput + "0").toFloat()
            1 -> decimalValue = (decimalInput + "00").toFloat()
            0 -> decimalValue = 0F
        }
        return integerValue + decimalValue/1000
    }
}
