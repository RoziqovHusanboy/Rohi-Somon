package tj.tajsoft.loyalrsn.presintation.more

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val repo: RegisterRepo
):ViewModel() {

    fun clearNumber() = viewModelScope.launch {
        try {
            repo.clearNumber()
        }catch (e:Exception){
            Log.d("clearNumber", "clearNumber: $e")
        }
    }

}