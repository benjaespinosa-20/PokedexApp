package com.example.pokedexapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.pokedexapp.data.repository.PokeApiRepository
import com.example.pokedexapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class PokeApiViewModel(private val repository: PokeApiRepository): ViewModel() {

    fun getPokeList() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getPokeList()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

//patron factory para inyeccion de dependencias
class PokeApiViewModelFactory(private val repo: PokeApiRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PokeApiRepository::class.java).newInstance(repo)
    }

}