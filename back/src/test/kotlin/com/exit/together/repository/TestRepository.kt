package com.exit.together.repository

import com.exit.together.domain.TestEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TestRepository : JpaRepository<TestEntity, Int> {
}