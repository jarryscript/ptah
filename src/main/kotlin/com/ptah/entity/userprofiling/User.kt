package com.ptah.entity.userprofiling

import com.ptah.auth.PasswordEncryptor
import com.ptah.entity.contract.Participant
import com.ptah.repository.userprofiling.ContactInfo
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import jakarta.persistence.Table


@Entity
@Table(name = "users")
class User : Participant() {
     var login: String? = null
     var avatar: String? = null

    @Convert(converter = PasswordEncryptor::class)
     val password: String? = null

    @OneToOne
     val contactInfo: ContactInfo? = null

    @OneToOne
     val currentOrganization: Organization? = null
}
