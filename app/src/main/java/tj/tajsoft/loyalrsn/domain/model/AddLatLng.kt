package tj.tajsoft.loyalrsn.domain.model

import tj.tajsoft.loyalrsn.presintation.gasstation.LatLng

class AddLatLng() {
      fun addLatLng():ArrayList<LatLng>{
        val latlan = arrayListOf(
            LatLng("38.5545616", "68.7212965"),
            LatLng("38.5575793", "68.7533963"),
            LatLng("38.5575793", "68.7533963"),
            LatLng("38.5575076", "68.7636497"),
            LatLng("38.55751", "68.7625231"),
        )
        return latlan
    }

}