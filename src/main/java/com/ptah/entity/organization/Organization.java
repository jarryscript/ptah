package com.ptah.entity.organization;

import com.ptah.entity.user.User;

import java.util.List;

//@Entity
public class Organization {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    @OneToMany
    private List<User> users;
}
