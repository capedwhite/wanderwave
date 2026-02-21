package com.example.wanderwave.repository

import com.example.wanderwave.model.ChallenegesModel
interface ChallengeRepo {
    fun addChallenge(
        challenge: ChallenegesModel,
        callback: (Boolean, String) -> Unit
    )

    fun getAllChallenges(
        callback: (Boolean, String, List<ChallenegesModel>?) -> Unit
    )

    fun getChallengeById(
        challengeId: String,
        callback: (Boolean, String, ChallenegesModel?) -> Unit
    )
    fun updateChallenge(
        challenge : ChallenegesModel,
        callback: (Boolean, String) -> Unit
    )


    fun deleteChallenge(
        challengeId: String,
        callback: (Boolean, String) -> Unit
    )
}