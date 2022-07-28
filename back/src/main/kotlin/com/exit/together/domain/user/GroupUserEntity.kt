package com.exit.together.domain.user

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "GRP_USR")
@IdClass(GroupUserPK::class)
class GroupUserEntity {

    @Id
    @Column(name = "GRP_SEQNO")
    var groupSeqno: Int? = 0

    @Id
    @Column(name = "USR_ID")
    var userId: String? = ""

    @Column(name = "GRP_ROLE")
    var auth: String? = ""

    @Column(name = "REG_DTTM")
    var registerDateTime: LocalDateTime? = LocalDateTime.now()

}

data class GroupUserPK(
    val groupSeqno: Int,
    val userId: String
) : Serializable