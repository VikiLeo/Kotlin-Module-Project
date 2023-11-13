

fun main() {
    val archiveList = mutableListOf<Archive>()
    val menu = Menu(archiveList)
    println("Добро пожаловать в приложение Заметки\n")
    menu.runMenu()
   }