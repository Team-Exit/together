package com.exit.together.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "TEST")
class TestEntity {

    @Id
    @Column(name = "TEST_SEQNO")
    var id:Int? = 0

    @Column(name = "TEST_CONTENT")
    var content:String = ""

}