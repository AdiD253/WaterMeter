package pl.defusadr.app.wodomierz.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import pl.defusadr.app.wodomierz.R
import pl.defusadr.app.wodomierz.ui.adapter.MainAdapter
import pl.defusadr.app.wodomierz.model.WaterMeterValue
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IMainActivityView {

    @Inject
    lateinit var presenter: MainActivityPresenter<IMainActivityView>

    private val adapter: MainAdapter by lazy {
        MainAdapter(mutableListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        loadData()
        mainBtnSave.setOnClickListener {
            presenter.trySaveValue(mainIntegerInput.text.toString(), mainDecimalInput.text.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onPause() {
        presenter.detachView()
        super.onPause()
    }

    override fun loadData() {
        presenter.loadData()
    }

    override fun populateList(values: MutableList<WaterMeterValue>) {
        adapter.itemList = values
        adapter.notifyDataSetChanged()
    }

    override fun addValue(value: WaterMeterValue)  {
        adapter.itemList.add(value)
        adapter.notifyItemInserted(adapter.lastItemPosition())
        clearInputs()
    }

    override fun showError(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView() {
        mainRecyclerView.apply {
            adapter = this.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun clearInputs() {
        mainIntegerInput.text.clear()
        mainDecimalInput.text.clear()
    }
}
