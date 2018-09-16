package com.idglebik.ilikeit.dbo;

import com.idglebik.ilikeit.enumerated.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "LOGIN")
public class LoginDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "loginDbo", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    public Set<UserDbo> users;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "LOGIN_ROLE", joinColumns = @JoinColumn(name = "LOGIN_ID"))
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Role> roles;
}