package tj.tajsoft.loyalrsn.data.local.shared_preferance

import tj.tajsoft.loyalrsn.data.local.shared_preferance.model_cash.TransactionModelCash
import javax.inject.Inject

class TransactionSecondStore @Inject constructor(): BaseStore<TransactionModelCash>("TransactionSecondStore",TransactionModelCash::class.java)