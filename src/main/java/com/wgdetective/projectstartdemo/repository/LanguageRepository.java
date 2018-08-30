package com.wgdetective.projectstartdemo.repository;

import com.wgdetective.projectstartdemo.dbo.LangDbo;
import com.wgdetective.projectstartdemo.dbo.PositionDbo;
import com.wgdetective.projectstartdemo.enumerated.Language;
import com.wgdetective.projectstartdemo.enumerated.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<LangDbo, Long> {
    LangDbo findByLanguage(Language language);
}
