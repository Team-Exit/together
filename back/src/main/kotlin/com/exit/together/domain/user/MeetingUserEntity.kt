package com.exit.together.domain.user

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "MEETING_USR")
@IdClass(MeetingUserPK::class)
class MeetingUserEntity {

    @Id
    @Column(name = "MEETING_SEQNO")
    var meetingSeqno: Int? = 0

    @Id
    @Column(name = "USR_ID")
    var userId: String? = ""

    @Column(name = "MEETING_ROLE")
    var auth: String? = ""

    @Column(name = "REG_DTTM")
    var registerDateTime: LocalDateTime? = LocalDateTime.now()

}


data class MeetingUserPK(
    val meetingSeqno: Int,
    val userId: String
) : Serializable