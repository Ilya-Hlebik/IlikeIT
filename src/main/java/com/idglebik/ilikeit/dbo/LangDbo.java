package com.idglebik.ilikeit.dbo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idglebik.ilikeit.enumerated.Language;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "LANGUAGE")
public class LangDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonIgnore
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "LANG")
    private Language language;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "USER_LANGUAGE",
            joinColumns = {@JoinColumn(name = "LANGUAGE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<UserDbo> users;

}
