package com.idglebik.ilikeit.dbo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idglebik.ilikeit.enumerated.Aligment;
import com.idglebik.ilikeit.enumerated.MainInLife;
import com.idglebik.ilikeit.enumerated.MainInPeople;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "LIFE_POSITION")
public class LifePositionDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonIgnore
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ALIGMENT")
    private Aligment aligment;

    @Enumerated(EnumType.STRING)
    @Column(name = "MAIN_IN_LIFE")
    private MainInLife mainInLife;

    @Enumerated(EnumType.STRING)
    @Column(name = "MAIN_IN_PEOPLE")
    private MainInPeople mainInPeople;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private UserDbo user;
}
