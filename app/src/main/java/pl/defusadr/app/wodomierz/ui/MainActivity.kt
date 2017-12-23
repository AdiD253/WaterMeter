package pl.defusadr.app.wodomierz.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.InputMethodManager
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

    private val mainAdapter: MainAdapter by lazy {
        MainAdapter(mutableListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
        initRecyclerView()
        mainBtnSave.setOnClickListener {
            presenter.trySaveValue(mainIntegerInput.text.toString(), mainDecimalInput.text.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
        tryHideKeyboard()
    }

    override fun onPause() {
        presenter.detachView()
        super.onPause()
    }

    override fun loadData() {
        presenter.loadData()
    }

    override fun populateList(values: MutableList<WaterMeterValue>) {
        mainAdapter.itemList = values.reversed().toMutableList()
        mainAdapter.notifyDataSetChanged()
    }

    override fun addValue(value: WaterMeterValue) {
        if (mainAdapter.itemCount == 0) {
            hideEmptyView()
        }
        mainAdapter.itemList.add(0, value)
        mainAdapter.notifyItemInserted(0)
        mainRecyclerView.scrollToPosition(0)
        clearView()
    }

    override fun showError(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showEmptyView() {
        mainTvEmptyList.visibility = View.VISIBLE
    }

    private fun hideEmptyView() {
        mainTvEmptyList.visibility = View.GONE
    }

    private fun initRecyclerView() {
        mainRecyclerView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }
    }

    private fun clearView() {
        mainIntegerInput.text.clear()
        mainDecimalInput.text.clear()
        tryHideKeyboard()
    }

    private fun tryHideKeyboard() {
        this.currentFocus?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}