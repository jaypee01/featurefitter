package com.levi.featurespitter.service

import org.springframework.stereotype.Service
import com.levi.featurespitter.repository.StoreRepository
import com.levi.featurespitter.model.Store
import javassist.NotFoundException


@Service
class StoreService(private val sr: StoreRepository) {

	fun createStore(request: Store): Store {
		return sr.save(request)
	}

	fun findAll(): List<Store> = sr.findAll().toMutableList()


	fun findById(id: Long): Store {
		return sr.findById(id).get()
	}
	
	fun storeExists(id : Long): Boolean{
		 return sr.findById(id).isPresent()
	}

    fun updateStore(request: Store): Store {
        return sr.save(request)
    }

}