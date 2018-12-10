package util

import objects.Lesson
import objects.Mark
import objects.Subject
import objects.updates.DaylyUpdate
import objects.updates.MarkUpdate

object MessageGenerator {

    fun newMark(markUpdate: MarkUpdate):String = "${markUpdate.subjects.find { it.id==markUpdate.lessons.find { it.id==markUpdate.newMark.lesson }!!.subjectId }!!.name} - ${markUpdate.newMark.value}"

    fun daylyNotification(daylyUpdate: DaylyUpdate) = "Количество оценок за сегодня: ${daylyUpdate.marks.size}\nИх средний балл: ${if (daylyUpdate.marks.isNotEmpty()) (daylyUpdate.marks.sumBy { it.value.toInt() }.toFloat()/daylyUpdate.marks.size.toFloat()).toString().substring(0,3) else "N/A"}"


    val hi = "Привет !\nЯ ДневникБот\nЯ буду уведомлять тебя о полученных оценках на школьном портале\nДля работы необходимо авторизоваться"
    val login = "Для начала напиши мне свой логин школьного портала"
    val password = "Теперь пароль"
    val ready = "Готово !\nТеперь ты будешь получать уведомления о недавно полученных оценках"
    val wrong = "Увы, но логил или пароль неверный, попробуй ещё раз"
}