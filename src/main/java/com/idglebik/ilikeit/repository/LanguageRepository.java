package com.idglebik.ilikeit.repository;

import com.idglebik.ilikeit.dbo.LangDbo;
import com.idglebik.ilikeit.enumerated.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<LangDbo, Long> {
    LangDbo findByLanguage(Language language);
}
