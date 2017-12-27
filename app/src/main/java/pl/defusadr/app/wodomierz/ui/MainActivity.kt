package pl.defusadr.app.wodomierz.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import pl.defusadr.app.wodomierz.R
import pl.defusadr.app.wodomierz.ui.adapter.MainAdapter
import pl.defusadr.app.wodomierz.model.WaterMeterValue
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IMainActivityView {

    private var activityViewType = ViewType.DEFAULT

    @Inject
    lateinit var presenter: MainActivityPresenter<IMainActivityView>

    private val mainAdapter: MainAdapter by lazy {
        MainAdapter(mutableListOf(), false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        loadData()
        initRecyclerView()
        mainBtnSave.setOnClickListener {
            presenter.saveValue(mainIntegerInput.text.toString(), mainDecimalInput.text.toString())
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

    override fun onBackPressed() {
        if (activityViewType != ViewType.DEFAULT) {
            resetView()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        when (activityViewType) {
            ViewType.DEFAULT -> menuInflater.inflate(R.menu.menu_main, menu)
            ViewType.DELETE -> menuInflater.inflate(R.menu.menu_main_delete, menu)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menuMainDelete -> setActivityViewType(ViewType.DELETE)
            R.id.menuMainDeleteSelected -> deleteSelectedValue()
        }
        return true
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

    override fun notifyItemRemoved() {
        val selectedPosition = mainAdapter.selectedPosition
        mainAdapter.itemList.removeAt(selectedPosition)
        mainAdapter.notifyItemRemoved(selectedPosition)
        resetView()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showEmptyView() {
        mainTvEmptyList.visibility = View.VISIBLE
    }

    private fun loadData() {
        presenter.loadData()
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

    private fun resetView() {
        setActivityViewType(ViewType.DEFAULT)
        mainAdapter.resetSelectedItem()
    }

    private fun setActivityViewType(viewType: Int) {
        activityViewType = viewType
        invalidateOptionsMenu()
        mainAdapter.deleteMode = viewType != ViewType.DEFAULT
        mainAdapter.notifyDataSetChanged()
    }

    private fun deleteSelectedValue() {
        val selectedValue = mainAdapter.getSelectedItem()
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Usunąć zaznaczony element?")
                .setMessage("Zapis z dnia ${sdf.format(selectedValue.date)} (wartość ${selectedValue.amount}) zostanie usunięty z bazy")
                .setNegativeButton("anuluj", null)
                .setPositiveButton("usuń", { _, _ -> presenter.deleteValue(selectedValue) })
                .show()
    }

    object ViewType {
        val DEFAULT = 0
        val DELETE = 1
    }
}