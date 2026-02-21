package com.example.wanderwave.model

data class ChallenegesModel(
    val challengeId: String="",
    val title: String ="",
    val description: String ="",
    val time:String ="",
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "challengeId" to challengeId,
            "title" to title,
            "description" to description,
            "time" to time
        )
    }
}