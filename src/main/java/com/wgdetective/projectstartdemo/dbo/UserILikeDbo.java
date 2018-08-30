package com.wgdetective.projectstartdemo.dbo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/*@Data
@NoArgsConstructor
@Entity
@Table(name = "USER_I_LIKE")*/
public class UserILikeDbo {

/*
    @NotNull
    @Column(name = "REASON")
    private String reason;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "I_LIKE_ID")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference
    private LikeDbo like;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "USER_ID")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference
    private UserDbo user;
*/
}
