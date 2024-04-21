package tj.tajsoft.loyalrsn.presintation.main

import SingleLiveEvent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tj.tajsoft.loyalrsn.domain.model.Destination
import tj.tajsoft.loyalrsn.domain.repo.RegisterRepo
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: RegisterRepo
) : ViewModel() {

    val events =SingleLiveEvent<Event>()

    init {

    }

    private fun getDestination() = viewModelScope.launch{
        try {
            repo.destinationFlow().collectLatest {
                events.postValue(Event.NavigateTo(it))
            }

        }catch (e:Exception){
            Log.d("Exception", "getDestination:$e ")
        }


    }


    sealed class Event {
        data class NavigateTo(val destination: Destination):Event()
    }

}