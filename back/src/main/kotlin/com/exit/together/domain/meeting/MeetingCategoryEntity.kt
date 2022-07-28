package com.exit.together.domain.meeting

import javax.persistence.*

@Entity
@Table(name = "MEETING_CATG")
class MeetingCategoryEntity(
    meetingCategoryType: MeetingCategoryType,
    name: String,
    logoPath: String,
    order: Int
) {

    @Id
    @Column(name = "MEETING_CATG_CD")
    @Convert(converter = MeetingCategoryTypeConverter::class)
    var meetingCategoryType: MeetingCategoryType? = meetingCategoryType

    @Column(name = "NAME")
    var name: String? = name

    @Column(name = "LOGO_PATH")
    var logoPath: String? = logoPath

    @Column(name = "ORDR")
    var order: Int? = order
}

enum class MeetingCategoryType(val code: String, val value: String) {
    DRINK("01", "술"),
    BOWLING("02", "볼링");

    companion object {
        fun getMeetingCategoryTypeByCode(code: String): MeetingCategoryType? =
            values().find { type -> type.code === code }
    }
}

@Converter
class MeetingCategoryTypeConverter : AttributeConverter<MeetingCategoryType, String> {
    override fun convertToDatabaseColumn(type: MeetingCategoryType): String {
        return type.code;
    }

    override fun convertToEntityAttribute(code: String): MeetingCategoryType? {
        return MeetingCategoryType.getMeetingCategoryTypeByCode(code);
    }
}