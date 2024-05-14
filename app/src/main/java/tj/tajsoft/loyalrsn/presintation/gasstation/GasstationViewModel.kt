package tj.tajsoft.loyalrsn.presintation.gasstation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.data.remote.model.branches.ResponseBranches
import tj.tajsoft.loyalrsn.domain.repo.ProductRepository
import javax.inject.Inject

@HiltViewModel
class GasstationViewModel @Inject constructor(
 private val repo:ProductRepository
): ViewModel() {
     val branches = MutableLiveData<ResponseBranches>()
    val error = MutableLiveData(false)
    val loading = MutableLiveData(false)

    init {
          getBranches()
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


}



