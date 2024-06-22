package tj.tajsoft.loyalrsn.data.local.shared_preferance

import tj.tajsoft.loyalrsn.data.remote.model.sale.ResponseSale
import javax.inject.Inject

class SaleStore @Inject constructor(): BaseStore<ResponseSale>("SaleStore",ResponseSale::class.java)