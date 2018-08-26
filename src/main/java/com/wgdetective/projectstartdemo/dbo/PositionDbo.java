package com.wgdetective.projectstartdemo.dbo;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wgdetective.projectstartdemo.enumerated.Position;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "POSITION")
public class PositionDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "POSITION_NAME")
    private Position positionName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_POSITION",
            joinColumns = { @JoinColumn(name = "POSITION_ID") },
            inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private Set<UserDbo> users = new HashSet<>();
}
