package digital.tiedemann.zdfmediathekscraper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import digital.tiedemann.zdfmediathekscraper.service.model.TeaserImage
import digital.tiedemann.zdfmediathekscraper.service.repository.Repository
import kotlin.math.abs

class ZdfStartPageImageViewModel(private val repository: Repository = Repository) : ViewModel() {
    val images: LiveData<Repository.Resource<List<TeaserImage>>> = liveData {
        emitSource(repository.getStartPageData().map {
            when (it) {
                is Repository.Resource.Success -> Repository.Resource.Success(it.data?.stage?.mapNotNull { item ->
                    getImageForSize(
                        item.teaserImage
                    )
                } ?: listOf())
                is Repository.Resource.Error -> Repository.Resource.Error(it.message)
                is Repository.Resource.Loading -> Repository.Resource.Loading<List<TeaserImage>>()
            }
        })
    }

    fun refreshData() {
        repository.refreshStartPageData()
    }

    private fun getImageForSize(image: Map<Int, TeaserImage>): TeaserImage? {
        if (image.containsKey(IMAGE_SIZE)) {
            return image[IMAGE_SIZE]
        }
        //take image size with least difference to wanted size or return null in case no key is found
        return image.keys.minBy { abs(it - IMAGE_SIZE) }?.let {
            image[it]
        }
    }

    companion object {
        private const val IMAGE_SIZE = 1280
    }
}