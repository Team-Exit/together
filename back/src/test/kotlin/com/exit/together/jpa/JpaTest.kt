package com.exit.together.jpa

import com.exit.together.domain.TestEntity
import com.exit.together.repository.TestRepository
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.persistence.EntityManager
import javax.sql.DataSource
import javax.transaction.Transactional

@ExtendWith(SpringExtension::class)
@EnableAutoConfiguration
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class JpaTest @Autowired constructor(
    val dataSource: DataSource,
    val jdbcTemplate: JdbcTemplate,
    val entityManager: EntityManager,
    val testRepository: TestRepository
) {

    val TEST_ID = 1

    @Test
    @Order(1)
    fun injectedComponentsAreNotNull(): Unit {
        assert(dataSource != null)
        assert(jdbcTemplate != null)
        assert(entityManager != null)
        assert(testRepository != null)
    }

    @Test
    @Order(2)
    @Rollback(false)
    fun insertTest(): Unit {
        val id = TEST_ID
        var testEntity = TestEntity()
        testEntity.id = id
        testEntity.content = "First Test"
        var savedEntity = testRepository.save(testEntity)
        assert(id == savedEntity.id)
    }

    @Test
    @Order(3)
    @Rollback(false)
    fun updateTest(): Unit {
        val content = "First Test Change"
        var nullableTestEntity = testRepository.findById(TEST_ID)
        assert(nullableTestEntity.isPresent)

        var testEntity = nullableTestEntity.get()
        testEntity.content = content
        var savedEntity = testRepository.save(testEntity)
        assert(content == savedEntity.content)
    }

    @Test
    @Order(4)
    @Rollback(false)
    fun deleteTest(): Unit {
        var nullableTestEntity = testRepository.findById(TEST_ID)
        assert(nullableTestEntity.isPresent)

        var testEntity = nullableTestEntity.get()
        testRepository.delete(testEntity)
        assert(testRepository.findById(TEST_ID).isEmpty)
    }

}