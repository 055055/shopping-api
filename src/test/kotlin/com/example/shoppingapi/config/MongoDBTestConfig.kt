package com.example.shoppingapi.config

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration
class MongoDBTestConfig : AbstractReactiveMongoConfiguration() {
    companion object {
        const val IMAGE_NAME = "mongo:4.0.10"
        const val DATABASE_NAME = "testcontainers"
    }

    override fun getDatabaseName(): String = DATABASE_NAME

    @Bean
    override fun reactiveMongoClient(): MongoClient = MongoClients.create(mongoDBDocker().getReplicaSetUrl())

    @Bean
    fun mongoDBDocker() = MongoDBDocker()

    @Bean
    fun reactiveMongoTemplate() = ReactiveMongoTemplate(reactiveMongoClient(), DATABASE_NAME)

    inner class MongoDBDocker {

        private val mongoDBContainer: MongoDBContainer = MongoDBContainer(DockerImageName.parse(IMAGE_NAME))
            .withReuse(true)

        init {
            mongoDBContainer.start()
        }

        fun getReplicaSetUrl(): String = mongoDBContainer.replicaSetUrl

        fun deleteAll() =
            reactiveMongoTemplate().collectionNames.flatMap { reactiveMongoTemplate().remove(Query(), it) }
    }
}