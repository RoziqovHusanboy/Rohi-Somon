package tj.tajsoft.loyalrsn.data.local.shared_preferance

import tj.tajsoft.loyalrsn.data.remote.model.product.fuel.ResponseFuel
import javax.inject.Inject

class FuelStore @Inject constructor(): BaseStore<ResponseFuel>("FuelStore",ResponseFuel::class.java)