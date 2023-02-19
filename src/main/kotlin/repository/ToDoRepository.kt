package repository

import model.ToDo

class ToDoRepository {
    private val toDoCollection = mutableListOf<ToDo>()

    init {
        defaultFillToDo()
    }

    private fun defaultFillToDo() {
        var belajar = ToDo(1, "Belajar", false)
        var bersih = ToDo(2, "Bersih bersih rumah", false)
        var belanja = ToDo(3, "Belanja", false)

        toDoCollection.add(belajar)
        toDoCollection.add(bersih)
        toDoCollection.add(belanja)
    }

    fun addToDoItem (toDoItem: ToDo) {
        toDoCollection.add(toDoItem)
    }

    fun updateToDoItem(toDoItem: ToDo) {
        val index = toDoCollection.indexOfFirst { it.id == toDoItem.id }
        if ( index != -1 ) {
            toDoCollection[index] = toDoItem
        }
    }

    fun size() = toDoCollection.size
    fun findByIndex(index: Int): ToDo = toDoCollection[index]


}