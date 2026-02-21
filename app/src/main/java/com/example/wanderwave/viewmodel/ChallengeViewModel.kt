package com.example.wanderwave.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wanderwave.model.ChallenegesModel
import com.example.wanderwave.model.ProductModel
import com.example.wanderwave.repository.ChallengeRepo

class ChallengeViewModel (
    private val repo: ChallengeRepo
) : ViewModel() {

    private val _allChallenges = MutableLiveData<List<ChallenegesModel>>()
    val allChallenges: LiveData<List<ChallenegesModel>> = _allChallenges
    private val _selectedChallenge = MutableLiveData<ChallenegesModel?>()
    val selectedChallenge: LiveData<ChallenegesModel?> get() = _selectedChallenge
    fun addChallenge(
        challenge: ChallenegesModel,
        callback: (Boolean, String) -> Unit
    ) {
        repo.addChallenge(challenge, callback)
    }

    fun getAllChallenges() {
        repo.getAllChallenges { success, _, list ->
            if (success && list != null) {
                _allChallenges.postValue(list)
            }
        }
    }
    fun updateChallenge(
        product: ChallenegesModel,
        callback: (Boolean, String) -> Unit
    ){
        repo.updateChallenge(product,callback)
    }

    fun deleteChallenge(
        challengeId: String,
        callback: (Boolean, String) -> Unit
    ) {
        repo.deleteChallenge(challengeId, callback)
    }

    fun getChallengeById(challengeId: String) {
        repo.getChallengeById(challengeId) { success, msg, data ->
            if (success) {
                _selectedChallenge.postValue(data)  // use postValue since it may be off main thread
            } else {
                _selectedChallenge.postValue(null)
            }
        }
    }
    }
