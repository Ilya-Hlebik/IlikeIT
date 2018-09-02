package com.idglebik.ilikeit.repository;

import com.idglebik.ilikeit.dbo.*;
import com.idglebik.ilikeit.enumerated.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserDbo, Long> {
    List<UserDbo> findByPosition(PositionDbo positionDbo);

    List<UserDbo> findByHate(HateDbo hate);

    List<UserDbo> findByLike(LikeDbo like);

    List<UserDbo> findByLanguage(LangDbo language);

    List<UserDbo> findAllByLIfePosition_AligmentAndLIfePosition_MainInLifeAndLIfePosition_MainInPeople(Aligment aligment, MainInLife mainInLife, MainInPeople mainInPeople);

    List<UserDbo> findByLikeAndHate(LikeDbo like, HateDbo hate);

    List<UserDbo> findAllByLastNameAndCityAndCountry(String lastName, String city, String country);
}
