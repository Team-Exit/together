package com.exit.together.repository.user

import com.exit.together.domain.user.MeetingUserEntity
import com.exit.together.domain.user.MeetingUserPK
import org.springframework.data.jpa.repository.JpaRepository

interface MeetingUserRepository : JpaRepository<MeetingUserEntity, MeetingUserPK> {
}