package tj.tajsoft.loyalrsn.presintation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: RegisterRepo
) : ViewModel() {

    fun saveTokenUser(token:String) = viewModelScope.launch{
        try {
            repo.saveTokenUser(token)
        }catch (e:Exception){
            Log.d("TAG", "saveTokenUser: $e")
        }
    }



}