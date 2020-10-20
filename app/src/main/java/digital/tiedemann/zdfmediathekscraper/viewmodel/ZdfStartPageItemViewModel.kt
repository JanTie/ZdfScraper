package digital.tiedemann.zdfmediathekscraper.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import digital.tiedemann.zdfmediathekscraper.service.repository.Repository

class ZdfStartPageItemViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {
    val items = repository.getStartPageData()

    fun refreshData() {
        repository.refreshStartPageData()
    }
}