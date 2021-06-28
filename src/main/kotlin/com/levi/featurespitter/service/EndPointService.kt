package com.levi.featurespitter.service

import org.springframework.stereotype.Service
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.URI
import com.levi.featurespitter.model.Store
import com.levi.featurespitter.model.StoreSeason
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.springframework.beans.factory.annotation.Autowired

@Service
class EndPointService {


	companion object {
		fun getStores(): List<Store> {
			val client = HttpClient.newBuilder().build();
			val request = HttpRequest.newBuilder()
				.uri(URI.create("http://134.209.29.209/v1/stores/?page=1"))
				.header("apiKey", "nxzAAC1EQAAU7AGY8gUQnev_99A")
				.header("accept", "application/json")
				.build();


			val response = client.send(request, HttpResponse.BodyHandlers.ofString());
			println(response.body())

			var stores : List<Store> = emptyList()
			
			if (response.statusCode().equals(200)) {

				val gson = GsonBuilder().create()
				stores = gson.fromJson(response.body(), Array<Store>::class.java).toList()
				
			}

			return stores

		}

		fun getStoreAndSeasons() : List<StoreSeason> {
			val client = HttpClient.newBuilder().build();
			val request = HttpRequest.newBuilder()
				.uri(URI.create("http://134.209.29.209/other/stores_and_seasons"))
				.header("apiKey", "nxzAAC1EQAAU7AGY8gUQnev_99A")
				.header("accept", "application/json")
				.build();


			val response = client.send(request, HttpResponse.BodyHandlers.ofString());
			println(response.body())
			
			var storesSeasons : List<StoreSeason> = emptyList()
			
			if (response.statusCode().equals(200)) {

				val gson = GsonBuilder().create()
				storesSeasons = gson.fromJson(response.body(), Array<StoreSeason>::class.java).toList()
				
			}

			return storesSeasons
		}

		fun getExtraDataCsv() {
			val client = HttpClient.newBuilder().build();
			val request = HttpRequest.newBuilder()
				.uri(URI.create("http://134.209.29.209/extra_data.csv"))
				.header("apiKey", "nxzAAC1EQAAU7AGY8gUQnev_99A")
				.header("accept", "text/plain")
				.build();


			val response = client.send(request, HttpResponse.BodyHandlers.ofString());
			println(response.body())
		}
	}

}