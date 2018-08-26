package com.wgdetective.projectstartdemo.dbo;


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
@Table(name ="POSITION")
public class PositionDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private long id;

    @NotNull
    @Column(name ="POSITION_NAME")
    private String positionName;

  /*   @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST,
                        CascadeType.MERGE },
            mappedBy = "position")*/
    @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "USER_POSITION",
          joinColumns = { @JoinColumn(name = "POSITION_ID") },
          inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
    @EqualsAndHashCode.Exclude
    private Set<UserDbo> user = new HashSet<>();
}
