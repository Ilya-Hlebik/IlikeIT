package com.idglebik.ilikeit.dbo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STUDY")
public class StudyDbo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonIgnore
    private long id;

    @Column(name = "INSTITUTION")
    private String institution;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "USER_ID")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private UserDbo userDbo;
}
