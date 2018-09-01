package com.idglebik.ilikeit.dbo;

/*

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
*/
