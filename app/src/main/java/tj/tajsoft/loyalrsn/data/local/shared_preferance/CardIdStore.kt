package tj.tajsoft.loyalrsn.data.local.shared_preferance

import javax.inject.Inject

class CardIdStore @Inject constructor(): BaseStore<Array<String>>("card_id",Array<String>::class.java)