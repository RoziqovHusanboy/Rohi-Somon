package tj.tajsoft.loyalrsn.data.local.shared_preferance.model_cash

import tj.tajsoft.loyalrsn.data.remote.model.transactionV2.Status

fun statusModelCashMapper(response:List<Status>):List<StatusModelCash>{
    return response.map {
            StatusModelCash(
                count = it.count,
                currentMax = it.currentMax,
                left = it.left,
                name = it.name,
                status = it.status

            )
        }


}