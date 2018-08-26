package com.wgdetective.projectstartdemo.dbo;
import com.wgdetective.projectstartdemo.enumerated.Position;
import com.wgdetective.projectstartdemo.enumerated.Sex;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name ="USER")
public class UserDbo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private long id;
    @NotNull
    @Column(name ="FIRST_NAME")
    private String firstName;
    @NotNull
    @Column(name ="LAST_NAME")
    private String lastName;
    @NotNull
    @Column(name ="AGE")
    private int age;
/*
    @NotNull
    @ElementCollection(targetClass = Sex.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "USER_SEX", joinColumns = @JoinColumn(name="USER_ID"))
    @Enumerated(EnumType.STRING)
    private Set<Sex> sex;

   @NotNull
    @ElementCollection(targetClass = Position.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "USER_POSITION", joinColumns = @JoinColumn(name="USER_ID"))
    @Enumerated(EnumType.STRING)
    private Set<Position> position;*/
@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "USER_POSITION",
        joinColumns = { @JoinColumn(name = "USER_ID") },
        inverseJoinColumns = { @JoinColumn(name = "POSITION_ID") })
@EqualsAndHashCode.Exclude
    private Set<PositionDbo> position;
}
