package com.github.jarryzhou.ptah.entity.userprofiling

import com.github.jarryzhou.ptah.auth.PasswordEncryptor
import com.github.jarryzhou.ptah.entity.contract.Participant
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User(
    var login: String? = null,
    var avatar: String? = null,
    @Convert(converter = PasswordEncryptor::class) var password: String? = null,
    @OneToOne var contactInfo: ContactInfo? = null,
    @OneToOne var currentOrganization: Organization? = null
) : Participant()
