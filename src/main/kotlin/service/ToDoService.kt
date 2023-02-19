package service

import model.ToDo
import repository.ToDoRepository


class ToDoService(private val toDoRepository: ToDoRepository) {

    private fun generateSlug(taskName: String): String {
        val consonant = "bcdfghjklmnpqrstvwxyz"
        return taskName.filter { it in consonant }
    }

    private fun addToDoItem (taskName: String) {
        var slug = generateSlug(taskName)
        val toDoItem = ToDo(slug = slug, taskName = taskName, isComplete = false)
        toDoRepository.addToDoItem(toDoItem)
    }

    private fun removeToDoItem (toDoItem: ToDo){
        toDoRepository.removeToDoItem(toDoItem)
    }

    private fun makeIsCompleted(toDoItem: ToDo) {
        toDoItem.isComplete = true
        toDoRepository.updateToDoItem(toDoItem)
    }

    private fun makeUncompleted(toDoItem: ToDo) {
        toDoItem.isComplete = false
        toDoRepository.updateToDoItem(toDoItem)
    }

    private fun completeCheck(toDoItem: ToDo) {
        when {
            !toDoItem.isComplete -> println(" [X] ")
            else -> println(" [V] ")
        }
    }

    private fun userCreateTask() {
        println(" Masukan Judul Task = ")
        var taskName = readln().toString()
        addToDoItem(taskName)
    }


    fun mainTodo() {
        println("==== ToDo List =====")
        println("")
        printCollection()


        println("ketik slug untuk detail / 99 untuk membuat Kegiatan")
        val userChoose = readln().toString()
        if (userChoose == "99") {
            userCreateTask()
        } else if ( toDoRepository.isSlugExist(userChoose)  ) {
            val selectedItem = toDoRepository.findBySlug(userChoose)
            if (selectedItem != null){
                subMenu(selectedItem)
            } else {
                println("Data Tidak ditemukan !!")
            }

        } else {
            println("salah input")
        }
        mainTodo()
    }


    private fun subMenu(toDoItem: ToDo) {

        println("1. Make Completed")
        println("2. Make Uncompleted")
        println("3. Delete")
        println("Pilih Action = ")
        var userChoose = readln().toInt()
        when {
            userChoose == 1 -> makeIsCompleted(toDoItem)
            userChoose == 2 -> makeUncompleted(toDoItem)
            userChoose == 3 -> removeToDoItem(toDoItem)
            else -> println("inputan salah")
        }

    }

    private fun printCollection() {
        println("Slug | Task Name = Status Task")
        toDoRepository.getAllToDoItems().forEach{
            selectedItem -> print("${selectedItem.slug}  | ${selectedItem.taskName} = ")
            completeCheck(selectedItem)
        }
    }

}