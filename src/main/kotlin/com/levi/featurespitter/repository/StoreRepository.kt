package com.levi.featurespitter.repository

import com.levi.featurespitter.model.Store
import org.springframework.data.repository.CrudRepository

interface StoreRepository : CrudRepository<Store, Long>{
	
}
