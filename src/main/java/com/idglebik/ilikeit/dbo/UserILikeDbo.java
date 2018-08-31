package com.idglebik.ilikeit.dbo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/*@Data
@NoArgsConstructor
@Entity
@Table(name = "USER_I_LIKE")*/
public class UserILikeDbo {
/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserDbo user;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "I_LIKE_ID", referencedColumnName = "ID")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private LikeDbo like;

    @Column(name = "REASON")
    private String reason;
*/

}
