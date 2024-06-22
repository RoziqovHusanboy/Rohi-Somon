package tj.tajsoft.loyalrsn.presintation.notification

import tj.tajsoft.loyalrsn.R

sealed class NotificationModel {
    data class Header(val title: String):NotificationModel()
    data class Body(val icon: Int, val title: String, val description: String, val data: String):NotificationModel()
}

object Database {
    val notificationModel = arrayListOf(
        NotificationModel.Header("Март, 2024"),
        NotificationModel.Body(
            R.drawable.ic_notification_dr1,
            "С праздником!",
            "От всего сердца поздравляем с 8 Марта! Пусть этот весенний праздник подарит нежность и любовь! Пусть осуществляются все мечты, а родные и близкие всегда будут рядом!",
            "10.03.2024, 10:25"
        ),
        NotificationModel.Body(
            R.drawable.ic_notification_dr2,
            "С днем рождения!",
            "Желаем счастья во всех сферах жизни, оптимизма, душевного равновесия, бодрости, осуществления всех планов и надежд!\n" +
                    "В честь Вашего дня рождения, мы повысили уровень Вашей карты!",
            "09.03.2024, 08:11"
        ),
        NotificationModel.Header("Февраль, 2024"),
        NotificationModel.Body(
            R.drawable.ic_notification_dr3,
            "Начисление бонуса",
            "Вам начислено 50 бонусов за заправку топливом\n" +
                    "от 08.02.2024.",
            "18.02.2024, 20:30"
        ),
        NotificationModel.Body(
            R.drawable.ic_notification_dr4,
            "Снятие бонуса",
            "Вам начислено 50 бонусов за заправку топливом\n" +
                    "от 08.02.2024.",
            "18.02.2024, 20:30"
        )

    )


}
