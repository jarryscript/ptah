package com.ptah.repository.userprofiling;

import com.ptah.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ContactInfo extends BaseEntity {
    private String name;
    private String address1;
    private String address2;
    private String city;
    private String province;
    private String postalCode;
    private String phoneNumber;
}
