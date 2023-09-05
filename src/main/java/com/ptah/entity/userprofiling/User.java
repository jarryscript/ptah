package com.ptah.entity.userprofiling;

import com.ptah.auth.PasswordEncryptor;
import com.ptah.entity.contract.Participant;
import com.ptah.repository.userprofiling.ContactInfo;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name="users")
public class User extends Participant {

    private String login;
    private String avatar;
    @Convert(converter = PasswordEncryptor.class)
    private String password;
    @OneToOne
    private ContactInfo contactInfo;
    @OneToOne
    private Organization currentOrganization;

}
