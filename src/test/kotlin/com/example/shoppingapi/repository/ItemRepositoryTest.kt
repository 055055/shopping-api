package com.example.shoppingapi.repository

import com.example.shoppingapi.config.MongoDBTestConfig
import com.example.shoppingapi.domain.repository.ItemRepository
import com.example.shoppingapi.mock.itemMock
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.context.annotation.Import
import reactor.test.StepVerifier

@Import(MongoDBTestConfig::class)
@DataMongoTest
class ItemRepositoryTest(
    private val mongoDBDocker: MongoDBTestConfig.MongoDBDocker,
    private val itemRepository: ItemRepository,
) : FunSpec({

    beforeEach { mongoDBDocker.deleteAll()}

    test("Item Document를 생성할 수 있다.") {
        val itemMock = itemMock()
        StepVerifier.create(
            itemRepository.save(itemMock))
            .assertNext{
                it.name shouldBe itemMock.name
                it.price shouldBe itemMock.price
            }
            .verifyComplete()
    }
})