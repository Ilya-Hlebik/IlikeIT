package com.idglebik.ilikeit.dbo;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserILikeDboId implements Serializable {
    private long user;
    private long like;
}
