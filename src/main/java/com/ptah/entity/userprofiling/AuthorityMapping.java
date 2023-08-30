package com.ptah.entity.userprofiling;

import com.ptah.common.BaseEntity;
import com.ptah.common.converter.StringListConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
public class AuthorityMapping extends BaseEntity {
    private String description;
    @Convert(converter = StringListConverter.class)
    private Set<String> authorities;
    private String role;
}
