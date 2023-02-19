package model

data class ToDo(
    var slug: String,
    val taskName: String,
    var isComplete: Boolean = false
)
