package com.darrenthiores.ecoswap.domain.carbon.model

data class Challenge(
    val id: String,
    val title: String,
    val description: String,
    val tasks: List<Task>,
    val deadline: Long,
    val goals: Double,
    val progress: Double,
    val participants: List<Participant>
) {
    data class Task(
        val id: String,
        val task: String,
        val carbonReduced: Double
    )

    data class Participant(
        val id: String,
        val name: String,
        val progress: Double
    )
}
