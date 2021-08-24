package co.jobis.sample.appstore.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.jobis.sample.appstore.domain.entity.AppInfo
import co.jobis.sample.appstore.domain.entity.History
import co.jobis.sample.appstore.domain.repository.AppStoreRepository
import co.jobis.sample.appstore.domain.usecase.AddHistory
import co.jobis.sample.appstore.domain.usecase.GetApps
import co.jobis.sample.appstore.domain.usecase.GetHistories
import co.jobis.sample.appstore.ui.search.history.HistoryListAdapter
import co.jobis.sample.appstore.ui.search.result.AppListAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getApps: GetApps,
    private val getHistories: GetHistories,
    private val addHistory: AddHistory,
    private val appStoreRepository: AppStoreRepository,
) : ViewModel(), AppListAdapter.OnClickListener, HistoryListAdapter.OnClickListener {

    val appInfoList: MutableLiveData<List<AppInfo>> = MutableLiveData()
    val historyList: MutableLiveData<List<History>> = MutableLiveData()
    val typedTerms: MutableLiveData<String> = MutableLiveData()
    val selectedAppInfo: MutableLiveData<AppInfo> = MutableLiveData()
    val selectedHistory: MutableLiveData<String> = MutableLiveData()

    fun getApps(terms: String) {
        viewModelScope.launch {
            getApps(GetApps.Input(terms)) { result ->
                result.onSuccess { output ->
                    output.apps.size
                    appInfoList.value = output.apps
                    Log.d("WoongTest", "app list size = ${output.apps.size}")
                }.onFailure { error ->
                    Log.d("WoongTest", "error = ${error.message}")
                }
            }
        }
    }

    fun getHistories() {
        viewModelScope.launch {
            getHistories(GetHistories.Input()) { result ->
                result.onSuccess { output ->
                    historyList.value = output.histories
                }.onFailure { error ->
                    Log.d("WoongTest", "error = ${error.message}")
                }
            }
        }
    }

    fun addHistory(terms: String) {
        viewModelScope.launch {
            appStoreRepository.addHistory(terms)
//            addHistory(AddHistory.Input(terms))
        }
    }

    override fun onClickApp(item: AppInfo) {
        selectedAppInfo.value = item
    }

    override fun onClickHistory(item: String) {
        selectedHistory.value = item
    }
}