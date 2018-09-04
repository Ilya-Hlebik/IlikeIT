package com.idglebik.ilikeit.dbo;

import com.idglebik.ilikeit.enumerated.Sex;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class UserDbo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;
    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;
    @NotNull
    @Column(name = "AGE")
    private int age;
    @Column(name = "CITY")
    private String city;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "GENERAL_INFO")
    private String generalInfo;
    @Column(name = "OTHER_INFO")
    private String otherInfo;
    @NotNull
    @ElementCollection(targetClass = Sex.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "USER_SEX", joinColumns = @JoinColumn(name = "USER_ID"))
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Exclude
    private Set<Sex> sex;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "USER_POSITION",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "POSITION_ID")})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<PositionDbo> position;

    @OneToMany(mappedBy = "userDbo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    public Set<StudyDbo> studys;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "USER_LANGUAGE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "LANGUAGE_ID")})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<LangDbo> language;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "USER_I_LIKE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "I_LIKE_ID")})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<LikeDbo> like;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "USER_I_HATE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "I_HATE_ID")})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<HateDbo> hate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private LIfePositionDbo lIfePosition;
}
