package com.example.wanderwave.repository

import com.example.wanderwave.model.ChallenegesModel
import com.example.wanderwave.model.ProductModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChallengeRepoImpl: ChallengeRepo {
    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    var ref : DatabaseReference = database.getReference("Challenges")
    override fun addChallenge(
        challenge: ChallenegesModel,
        callback: (Boolean, String) -> Unit
    ) {
        val challengeId = ref.push().key.toString()

        ref.child(challengeId)
            .setValue(challenge)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true, "Challenge added successfully")
                } else {
                    callback(false, it.exception?.message.toString())
                }
            }
    }

    // FIXED - use single event listener
    override fun getAllChallenges(callback: (Boolean, String, List<ChallenegesModel>?) -> Unit) {
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allChallenges = mutableListOf<ChallenegesModel>()
                for (data in snapshot.children) {
                    val challenge = data.getValue(ChallenegesModel::class.java)
                    if (challenge != null) allChallenges.add(challenge)
                }
                callback(true, "Challenges Found", allChallenges)
            }

            override fun onCancelled(error: DatabaseError) {
                callback(false, error.message, emptyList())
            }
        })
    }
    override fun getChallengeById(
        challengeId: String,
        callback: (Boolean, String, ChallenegesModel?) -> Unit
    ) {
        ref.child(challengeId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val challenge =
                            snapshot.getValue(ChallenegesModel::class.java)
                        callback(true, "Challenge Found", challenge)
                    } else {
                        callback(false, "Challenge Not Found", null)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(false, error.message, null)
                }
            })
    }

    override fun deleteChallenge(
        challengeId: String,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(challengeId)
            .removeValue()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true, "Challenge Deleted")
                } else {
                    callback(false, it.exception?.message.toString())
                }
            }
    }
    override fun updateChallenge(
        challenge: ChallenegesModel,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(challenge.challengeId)
            .updateChildren(challenge.toMap())
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true, "Product updated successfully")
                } else {
                    callback(false, it.exception?.message ?: "Update failed")
                }
            }
    }
    }
