package com.idglebik.ilikeit.repository;

import com.idglebik.ilikeit.dbo.ChatMessageDbo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessageDbo, Long> {
    List<ChatMessageDbo> findAllBySenderAndRecipientOrRecipientAndSenderOrderByDateDesc(long sender1, long recipient1, long recipient2, long sender2);
}
