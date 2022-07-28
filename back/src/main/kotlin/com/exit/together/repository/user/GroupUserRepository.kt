package com.exit.together.repository.user

import com.exit.together.domain.user.GroupUserEntity
import com.exit.together.domain.user.GroupUserPK
import org.springframework.data.jpa.repository.JpaRepository

interface GroupUserRepository : JpaRepository<GroupUserEntity, GroupUserPK> {
}