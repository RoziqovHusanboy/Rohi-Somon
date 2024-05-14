package tj.tajsoft.loyalrsn.presintation.gasstation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.remote.model.branches.ResponseBranches
import tj.tajsoft.loyalrsn.data.remote.model.product.fuel.ResponseFuel
import tj.tajsoft.loyalrsn.domain.repo.ProductRepository
import javax.inject.Inject

@HiltViewModel
class GasstationDetailViewModel @Inject constructor (
    private val repo:ProductRepository
): ViewModel() {
    val branches = MutableLiveData<ResponseBranches>()
    val error = MutableLiveData(false)
    val loading = MutableLiveData(false)
    val fuel = MutableLiveData<ResponseFuel>()


    init {
        getBranches()
        getFuel()
    }

    private fun getBranches() = viewModelScope.launch {
        loading.postValue(true)
        error.postValue(false)
        try {
            val response =  repo.getBranches()
            branches.postValue(response)
        }catch (e:Exception){
            error.postValue(true)
            loading.postValue(false)
        }finally {
            loading.postValue(false)
        }
    }

    private fun getFuel() = viewModelScope.launch {
        loading.postValue(true)
        error.postValue(false)
        try {
            fuel.postValue(repo.getFuel())
        }catch (e:Exception){
            error.postValue(true)
            loading.postValue(false)
        }finally {
            loading.postValue(false)
        }
    }

}
