package com.exit.together.domain.user

import javax.persistence.*

@Entity
@Table(name = "USR")
class UserEntity(mail: String, name: String, nickName: String, gender: Gender, cell: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USR_SEQNO")
    var userSeqno: Long? = 0

    @Column(name = "MAIL")
    var mail: String? = mail

    @Column(name = "NAME")
    var name: String? = name

    @Column(name = "NICK_NAME")
    var nickName: String? = nickName

    @Column(name = "GENDER")
    @Convert(converter = GenderConverter::class)
    var gender: Gender? = gender

    @Column(name = "CELL")
    var cell: String? = cell

}

enum class Gender(val code: String, val value: String) {
    MALE("M", "남자"),
    FEMALE("F", "여자");

    companion object {
        fun getGenderByCode(code: String): Gender? =
            values().find { gender -> gender.code === code }
    }
}

@Converter
class GenderConverter : AttributeConverter<Gender, String> {
    override fun convertToDatabaseColumn(gender: Gender): String {
        return gender.code;
    }

    override fun convertToEntityAttribute(code: String): Gender? {
        return Gender.getGenderByCode(code);
    }
}