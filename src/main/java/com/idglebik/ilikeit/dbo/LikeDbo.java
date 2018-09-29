package com.idglebik.ilikeit.dbo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idglebik.ilikeit.enumerated.Like;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "I_LIKE")
public class LikeDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonIgnore
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "LIKE")
    private Like like;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "USER_I_LIKE",
            joinColumns = {@JoinColumn(name = "I_LIKE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<UserDbo> users;

}
