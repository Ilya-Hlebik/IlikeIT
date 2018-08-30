package com.wgdetective.projectstartdemo.dbo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wgdetective.projectstartdemo.dto.LikeDto;
import com.wgdetective.projectstartdemo.dto.UserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity(name = "UserILikeDboEntity")
@Table(name = "USER_I_LIKE")
public class UserILikeDbo {

    @EmbeddedId
    private UserLikeId userLikeId;

    @NotNull
    @Column(name = "REASON")
    private String reason;

    public UserILikeDbo(UserDbo userDbo, LikeDbo likeDbo, UserDto userDto) {
        userLikeId = new UserILikeDbo.UserLikeId();
        userLikeId.iLikeId = likeDbo.getId();
        userLikeId.userId = userDbo.getId();
        reason = userDto.getReason();
    }

    @Embeddable
    @EqualsAndHashCode
    private static class UserLikeId {
        public long iLikeId;
        public long userId;
    }
}
