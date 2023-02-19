package repository

import model.ToDo

class ToDoRepository {
    private val toDoCollection = mutableListOf<ToDo>()

    fun addToDoItem (toDoItem: ToDo) {
        toDoCollection.add(toDoItem)
    }

    fun removeToDoItem(toDoItem: ToDo) {
        val index = toDoCollection.indexOfFirst { it.slug == toDoItem.slug }
        if ( index != -1 ) {
            toDoCollection.removeAt(index)
        }
    }

    fun updateToDoItem(toDoItem: ToDo) {
        val index = toDoCollection.indexOfFirst { it.slug == toDoItem.slug }
        if ( index != -1 ) {
            toDoCollection[index] = toDoItem
        }
    }

    fun getAllToDoItems(): List<ToDo> = toDoCollection

    fun size() = toDoCollection.size

    fun findBySlug(slug: String): ToDo? {
        val index = toDoCollection.indexOfFirst { it.slug == slug }
        return if (index >= 0) toDoCollection[index] else null
    }

    fun isSlugExist(slug: String): Boolean {
        return toDoCollection.any{it.slug == slug}
    }



}