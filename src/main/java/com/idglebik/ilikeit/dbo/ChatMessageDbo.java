package com.idglebik.ilikeit.dbo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "MESSAGE")
public class ChatMessageDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonIgnore
    private long id;
    private String content;
    private long sender;
    private long recipient;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "HISTORY_ID")
    private String historyId;
}