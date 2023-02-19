package service

import model.ToDo
import repository.ToDoRepository

class ToDoService(private val toDoRepository: ToDoRepository) {

    var numberTask: Int = 4

    fun addToDoItem (taskName: String) {
        val toDoItem = ToDo(id = numberTask, taskName = taskName, isComplete = false)
        numberTask += 1
        toDoRepository.addToDoItem(toDoItem)
    }

    fun makeIsCompleted(toDoItem: ToDo) {
        toDoItem.isComplete = true
        toDoRepository.updateToDoItem(toDoItem)
    }

    fun makeIncompleted(toDoItem: ToDo) {
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

        println("pilih kegiatan yang mau di edit / 99 untuk membuat Kegiatan")
        val userChoose = readln().toInt()
        if (userChoose == 99) {
            userCreateTask()
        } else if (userChoose <= 10 && userChoose >= 1) {
            val selectedItem = toDoRepository.findByIndex(userChoose-1)
            subMenu(selectedItem)
        } else {
            println("salah input")
        }
        mainTodo()
    }


    private fun subMenu(toDoItem: ToDo) {

        println("1. Make Completed")
        println("2. Make Uncompleted")
        println("Pilih Action = ")
        var userChoose = readln().toInt()
        when {
            userChoose == 1 -> makeIsCompleted(toDoItem)
            userChoose == 2 -> makeIncompleted(toDoItem)
            else -> println("inputan salah")
        }

    }

    private fun printCollection(index: Int = 0) {
        val selectedItem = toDoRepository.findByIndex(index)
        print("${selectedItem.id}. ${selectedItem.taskName} = ")
        completeCheck(selectedItem)

        var newIndex = index
        val maxIndexCollection = toDoRepository.size()
        if (newIndex < maxIndexCollection - 1){
            newIndex += 1
            printCollection(newIndex)
        }
    }

}