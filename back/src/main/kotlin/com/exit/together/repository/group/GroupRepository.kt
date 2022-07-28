package com.exit.together.repository.group

import com.exit.together.domain.group.GroupEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GroupRepository : JpaRepository<GroupEntity, Int> {
}