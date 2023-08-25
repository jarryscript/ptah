package com.ptah.entity.user;

import com.ptah.auth.PasswordEncryptor;
import com.ptah.entity.contract.ContractParty;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name="users")
public class User extends ContractParty {

    private String login;
    private String avatar;
    @Convert(converter = PasswordEncryptor.class)
    private String password;
//    private Organization currentOrganization;
//    private List<Organization> organizations;
}
