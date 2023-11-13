import java.util.Scanner

class Archive(val name: String, val notes: MutableList <Note>) {

    val scanner = Scanner(System.`in`)
    fun enterNote(note: Note, archive: Archive, list: MutableList<Archive>){
        println("Введите текст")
                while (true) {
            val noteText = scanner.nextLine().trim()
            if (noteText.isEmpty()) {
                println("Ввод не должен быть пустым. Введите текст.")
            } else {
                note.text = note.text + "\n$noteText"
                return archive.viewNotes(archive, list)
            }
        }
    }

    fun showNote(note:Note, archive: Archive, list: MutableList<Archive>) {
        println("Название заметки: ${note.name}")
        println("Содержание заметки:\n${note.text}")
        println("=============================")
        println("0 - Назад")
        println("1 - Добавить текст")
        while (true) {
            val inputValue = scanner.nextLine()
            if (inputValue.toIntOrNull() == null) {
                println("Некорректное значение. Необходимо ввести цифру")
            } else {
                when (inputValue.toInt()) {
                    0 -> return archive.viewNotes(archive, list)
                    1 -> return archive.enterNote(note,archive,list)
                    else -> println("Некорректное значение, попробуйте снова")
                }
            }
        }


    }

    fun createNote(archive: Archive, list: MutableList<Archive>) {
        println("Введите название заметки:")
        while (true) {
            val name = scanner.nextLine().trim()
            if (name.isEmpty()) {
                println("Ввод не должен быть пустым. Введите текст.")
            } else {
                println("Введите текст заметки:")
                while (true) {
                    val text = scanner.nextLine().trim()
                    if (text.isEmpty()) {
                        println("Ввод не должен быть пустым. Введите текст.")
                    } else {
                        archive.notes.add(Note(name, text))
                        println("Заметка успешно создана.")
                        return archive.viewNotes(archive, list)
                    }
                }
            }
        }
    }
    fun viewNotes(archive: Archive, archiveList: MutableList<Archive>) {
        while (true) {
            if (archive.notes.isEmpty()) {
                println("В данный момент заметки в архиве отсутствуют")
                println("0 - Создать заметку")
                println("1 - Назад")
                val inputValue = scanner.nextLine()
                if (inputValue.toIntOrNull() == null) {
                    println("Некорректное значение. Необходимо ввести цифру")
return archive.viewNotes(archive, archiveList)
                } else {
                    val command = inputValue.toInt()
                    when {
                        (command == 0) -> createNote(archive, archiveList)
                        (command == 1) -> return
                     else -> {println("Некорректное значение, попробуйте снова"); return archive.viewNotes(archive, archiveList)}

                    }
                }


            } else {
                println("Список заметок:")
                println("0 - Создать заметку")
                for (i in archive.notes.indices) {
                    println("${i + 1} - ${archive.notes[i].name}")
                }
                println("${archive.notes.size + 1} - Назад")
                val inputValue = scanner.nextLine()
                if (inputValue.toIntOrNull() == null) {
                    println("Некорректное значение. Необходимо ввести цифру")
                } else {
                    val command = inputValue.toInt()
                    val exitCommand = archive.notes.size + 1
                    when {
                        (command == 0) -> createNote(archive, archiveList)
                        (command == exitCommand) ->  return
                        (command > 0 && command <= archive.notes.size) -> archive.showNote(archive.notes[command - 1], archive, archiveList)
                        else ->  println("Некорректное значение, попробуйте снова")
                    }

                }

            }
            return
        }
    }
}