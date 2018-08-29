package com.wgdetective.projectstartdemo.dbo;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wgdetective.projectstartdemo.enumerated.Hate;
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
@Table(name = "I_HATE")
public class HateDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "HATE")
    private Hate hate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "USER_HATE",
            joinColumns = {@JoinColumn(name = "HATE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference
    private Set<UserDbo> users;

}
