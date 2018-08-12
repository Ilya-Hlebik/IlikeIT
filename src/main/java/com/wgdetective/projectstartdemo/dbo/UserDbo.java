package com.wgdetective.projectstartdemo.dbo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name ="User")
public class UserDbo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private int age;
    @NotNull
    private String position;

    @NotNull
    @ElementCollection(targetClass = Sex.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "USER_SEX", joinColumns = @JoinColumn(name="USER_ID"))
    @Enumerated(EnumType.STRING)
    private Set<Sex> sex;
}
