package tj.tajsoft.loyalrsn.presintation.gasstation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.domain.model.AddLatLng
import javax.inject.Inject

@HiltViewModel
class GasstationViewModel @Inject constructor()
    : ViewModel() {
    val addLatLng:AddLatLng = AddLatLng()

    init {
        viewModelScope.launch {
            insertData()
        }
    }

     suspend  fun insertData():ArrayList<LatLng> {
         val data =   addLatLng.addLatLng()
         return data
    }

}



