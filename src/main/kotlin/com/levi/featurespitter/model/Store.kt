package com.levi.featurespitter.model

import javax.persistence.*

import org.springframework.format.annotation.DateTimeFormat
import java.util.Date

@Entity
data class Store constructor(
	@Id var id: Long=0,
	@Lob
	var code: String="",
	@Lob
	var description: String="",
	@Lob
	var name: String="",
	@Lob
	@Basic
	@Temporal(TemporalType.DATE)
	var openingDate: Date = Date(),
	@Lob
	var storeType: String="",
	@Lob
	var season: String=""
)


