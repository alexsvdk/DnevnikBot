package bot

import objects.Mark

object MessageGenerator {

    fun newMarkNotification(mark: Mark):String = "${mark.lessonStr} - ${mark.textValue}"

    val hi = "Привет !\nЯ ДневникБот\nЯ буду уведомлять тебя о полученных оценках на школьном портале\nДля работы необходимо авторизоваться"
    val login = "Для начала напиши мне свой логин школьного портала"
    val password = "Теперь пароль"
    val ready = "Готово !\nТеперь ты будешь получать уведомления о недавно полученных оценках"
    val wrong = "Увы, но логил или пароль неверный, попробуй ещё раз"
}