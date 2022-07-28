package com.exit.together.domain.meeting

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "MEETING")
class MeetingEntity(
    meetingCategoryType: MeetingCategoryType,
    latitude: Double,
    longitude: Double,
    detailLocation: String,
    beginDateTime: LocalDateTime,
    groupCreate: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEETING_SEQNO")
    var meetingSeqno: Int? = 0

    @Column(name = "MEETING_CATG_CD")
    @Convert(converter = MeetingCategoryTypeConverter::class)
    var meetingCategoryType: MeetingCategoryType? = meetingCategoryType

    @Column(name = "LATITUDE")
    var latitude: Double? = latitude

    @Column(name = "LONGITUDE")
    var longitude: Double? = longitude

    @Column(name = "DETL_LOCATION")
    var detailLocation: String? = detailLocation

    @Column(name = "BEGIN_DTTM")
    var beginDateTime: LocalDateTime? = beginDateTime

    @Column(name = "GROUP_CREATE_YN")
    var groupCreate: String? = groupCreate

    @Column(name = "REG_DTTM")
    var registerDateTime: LocalDateTime? = LocalDateTime.now()

}