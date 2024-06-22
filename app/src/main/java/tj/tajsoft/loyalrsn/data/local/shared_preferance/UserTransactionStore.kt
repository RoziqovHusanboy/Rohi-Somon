package tj.tajsoft.loyalrsn.data.local.shared_preferance

import tj.tajsoft.loyalrsn.data.remote.model.product.transaction.ResponseTransaction
import javax.inject.Inject

class UserTransactionStore @Inject constructor(): BaseStore<Array<Array<ResponseTransaction>>>("UserTransactionStore",Array< Array<ResponseTransaction>>::class.java )