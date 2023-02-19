import repository.ToDoRepository
import service.ToDoService

fun main(args: Array<String>) {
    val repository = ToDoRepository()
    val toDo = ToDoService(repository)
    toDo.mainTodo()
}