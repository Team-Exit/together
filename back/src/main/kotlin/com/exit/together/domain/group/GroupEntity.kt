package com.exit.together.domain.group

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "GRP")
class GroupEntity(name: String, description: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GRP_SEQNO")
    var groupSeqno: Int? = 0

    @Column(name = "GRP_NAME")
    var name: String? = name

    @Column(name = "DESCRIPTION")
    var description: String? = description

    @Column(name = "REG_DTTM")
    var registerDateTime: LocalDateTime? = LocalDateTime.now()

}