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
class UserDetails(
    @field:Column(name = "username") var username: String? = null,
    @field:Column(name = "first_name") var firstName: String? = null,
    @field:Column(name = "last_name") var lastName: String? = null,
    @field:Column(name = "registered_at", nullable = false) var registeredAt: LocalDateTime = LocalDateTime.now(),
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable = false) var user: User
) {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null
}
