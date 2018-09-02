package com.idglebik.ilikeit.dbo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idglebik.ilikeit.enumerated.Position;
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
@Table(name = "POSITION")
public class PositionDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonIgnore
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "POSITION_NAME")
    private Position positionName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "USER_POSITION",
            joinColumns = {@JoinColumn(name = "POSITION_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<UserDbo> users;

}
