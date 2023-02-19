package model

data class ToDo(
    val id: Int,
    var slug: String,
    val taskName: String,
    var isComplete: Boolean = false
)
