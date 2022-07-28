package com.exit.together.repository.meeting

import com.exit.together.domain.meeting.MeetingEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MeetingRepository : JpaRepository<MeetingEntity, Int> {
}