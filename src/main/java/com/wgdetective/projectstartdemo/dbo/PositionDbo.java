package com.wgdetective.projectstartdemo.dbo;


import com.wgdetective.projectstartdemo.enumerated.Position;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name ="POSITION")
public class PositionDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private long id;
    @NotNull
    private Position position;
}
