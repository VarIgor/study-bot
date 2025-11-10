package com.palastrov.study_bot.entity.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "user_details")
class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null

    @Column(name = "username")
    var username: String? = null

    @Column(name = "first_name")
    var firstName: String? = null

    @Column(name = "last_name")
    var lastName: String? = null

    @Column(name = "registered_at")
    var registeredAt: LocalDateTime? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null

    constructor()

    constructor(
        username: String? = null,
        firstName: String? = null,
        lastName: String? = null,
        registeredAt: LocalDateTime? = null,
        user: User? = null
    ) : this() {
        this.username = username
        this.firstName = firstName
        this.lastName = lastName
        this.registeredAt = registeredAt
        this.user = user
    }
}
