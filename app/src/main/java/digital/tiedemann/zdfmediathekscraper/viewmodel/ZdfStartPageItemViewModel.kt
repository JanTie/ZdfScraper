package digital.tiedemann.zdfmediathekscraper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import digital.tiedemann.zdfmediathekscraper.service.repository.Repository

class ZdfStartPageItemViewModel(private val repository: Repository = Repository) : ViewModel() {
    val items = liveData {
        emitSource(repository.getData())
    }

    fun refreshData() {
        repository.refreshData()
    }
}