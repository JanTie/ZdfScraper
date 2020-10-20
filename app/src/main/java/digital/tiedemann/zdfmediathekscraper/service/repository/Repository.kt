package digital.tiedemann.zdfmediathekscraper.service.repository

import androidx.lifecycle.MutableLiveData
import com.muellerwulff.viewmodelextensions.asLiveData
import digital.tiedemann.zdfmediathekscraper.service.model.StartPageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val client: ZdfMediathekApi) {

    private val dataDelegate =
        lazy { MutableLiveData<Resource<StartPageResponse>>() }
    private val data: MutableLiveData<Resource<StartPageResponse>> by dataDelegate

    fun getStartPageData() =
        if (dataDelegate.isInitialized()) {
            data.asLiveData()
        } else {
            //in case the data was not initialized before, start fetching now
            refreshStartPageData()
            data.asLiveData()
        }

    fun refreshStartPageData() {
        //set value to loading
        data.value = Resource.Loading()
        //load data async and write result(success/error) to data
        client.loadStartPage().enqueue(object : Callback<StartPageResponse> {
            override fun onFailure(call: Call<StartPageResponse>, t: Throwable) {
                data.value = Resource.Error(t.message)
            }

            override fun onResponse(
                call: Call<StartPageResponse>,
                response: Response<StartPageResponse>
            ) {
                val body = response.body()
                data.value =
                    if (body == null) Resource.Error("Unexpected data")
                    else Resource.Success(body)
            }
        })
    }

    sealed class Resource<T>(
        val data: T? = null,
        val message: String? = null
    ) {
        class Success<T>(data: T) : Resource<T>(data)
        class Loading<T>(data: T? = null) : Resource<T>(data)
        class Error<T>(message: String?, data: T? = null) : Resource<T>(data, message)
    }
}