package com.levi.featurespitter.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

import com.levi.featurespitter.model.Store
import com.levi.featurespitter.model.StoreSeason
import com.levi.featurespitter.repository.StoreRepository
import com.levi.featurespitter.service.StoreService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import com.levi.featurespitter.service.EndPointService
import com.levi.featurespitter.main
import java.util.Collections
import org.springframework.web.bind.annotation.CrossOrigin

@RestController
class StoreController(private val storeService: StoreService) {

    @CrossOrigin
	@GetMapping("/stores")
	fun getAllStores(): List<Store> {

		var allStores: List<Store> = storeService.findAll()

		return allStores;
	}

    @CrossOrigin
	@PutMapping("/store/{storeid}")
	fun updateStoreName(@RequestBody store: Store, @PathVariable storeid: Long) : Store{
		
		println(store.toString())
		return storeService.updateStore(store)
	}

	@GetMapping("/store/csv")
	fun exportCSV() {
		//TODO
	}

	@CrossOrigin
	@GetMapping("/saveStoreDataInDatabase")
	fun saveStoreDataInDatabase(): String {

		var stores: List<Store> = EndPointService.getStores()

		var count: Int = 0
		
		stores.forEach { store ->
			storeService.createStore(store)
			count++
		}

		return "Done adding " + count + " stores info in database"

	}

	@CrossOrigin
	@GetMapping("/saveStoreAndSeasonDataInDatabase")
	fun saveStoreAndSeasonDataInDatabase(): String {

		var storesSeasons: List<StoreSeason> = EndPointService.getStoreAndSeasons()

		var count: Int = 0
		storesSeasons.forEach { storeSeason ->
				
			if ( storeService.storeExists(storeSeason.storeId.toLong())){
				var store: Store = storeService.findById(storeSeason.storeId.toLong())
				store.season = storeSeason.season
				storeService.updateStore(store)
				count++
			}else{
				println("----------------------------------no store found with id : " + storeSeason.storeId.toLong())
			}


		}

		return "Done adding updating" + count + " stores seasons info in database"

	}


}
