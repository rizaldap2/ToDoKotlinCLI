package model

data class ToDo(
    val id: Int,
    val taskName: String,
    var isComplete: Boolean = false
)
