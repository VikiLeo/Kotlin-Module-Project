import java.util.Scanner
import kotlin.system.exitProcess

class Menu(val archiveList: MutableList<Archive>) {

    fun createObject() {
        println("Введите название архива:")
        while (true) {
            val name = Scanner(System.`in`).nextLine()
            if (name.isNullOrEmpty()) {
                println("Неверный ввод. Пожалуйста введите название архива снова.")
            } else {
                archiveList.add(Archive(name, mutableListOf()))
                return
            }
        }
    }

    fun runMenu() {
         while (true) {
             viewArchive(archiveList)
             val text = Scanner(System.`in`).nextLine()
             if (text.toIntOrNull() == null) {
                 println("Неверный ввод. Необходимо ввести цифру")
             } else {
                 val command = text .toInt()
                 when {
                     (command > archiveList.size + 1 || command < 0) -> println("Некорректное значение, попробуйте снова")
                     (command == archiveList.size + 1) -> exitProcess(0)
                     (command == 0) -> createObject()
                     (command <= archiveList.size) -> archiveList[command - 1].viewNotes(archiveList[command - 1], archiveList)
                 }
             }
         }
     }

    fun viewArchive(listOfArchives: MutableList<Archive>) {
        println("Главное меню\n")

        if (listOfArchives.isNotEmpty()) {
            println("0 - Создать архив\n")
            println("Список архивов:")
            for (i in listOfArchives.indices) {
                println("${i+1} - ${listOfArchives[i].name}")
            }
            println("*******************")
            println("${listOfArchives.size + 1} - Выход")



        } else {
            println("В данное время список архивов пуст\n0 - Создать архив\n1 - Выход")


        }

    }
}