package com.levi.featurespitter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.context.annotation.Bean
import org.springframework.boot.CommandLineRunner
import com.levi.featurespitter.service.EndPointService

@SpringBootApplication
@EntityScan(basePackages = ["com.levi.featurespitter.model"])
@EnableJpaRepositories(basePackages = ["com.levi.featurespitter.repository"])
class FeaturespitterApplication

fun main(args: Array<String>) {
	runApplication<FeaturespitterApplication>(*args)
}
