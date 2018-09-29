package com.idglebik.ilikeit.dbo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FRIEND")
public class FriendDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonIgnore
    private long id;

    @Column(name = "FRIEND_ID")
    private long friendId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "USER_ID")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private UserDbo userDbo;
}
