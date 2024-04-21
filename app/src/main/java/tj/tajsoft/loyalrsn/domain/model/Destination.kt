package tj.tajsoft.loyalrsn.domain.model

sealed class Destination {
     object Auth:Destination()
    object CheckNumber:Destination()
    object LogIn:Destination()


}