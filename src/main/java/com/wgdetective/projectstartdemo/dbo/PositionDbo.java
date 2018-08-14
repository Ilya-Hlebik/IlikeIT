package com.wgdetective.projectstartdemo.dbo;


import com.wgdetective.projectstartdemo.enumerated.Position;
import lombok.Data;
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
    private Position position;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "position")
    private Set<UserDbo> USER = new HashSet<>();
}
