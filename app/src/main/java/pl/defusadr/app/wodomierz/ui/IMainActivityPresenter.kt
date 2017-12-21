package pl.defusadr.app.wodomierz.ui

interface IMainActivityPresenter<V : IMainActivityView> {

    fun onAttachView(view: V)
    fun onDetachView()
    fun loadData()
    fun saveValue(value: Float)
}