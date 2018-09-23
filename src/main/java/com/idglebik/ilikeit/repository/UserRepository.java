package com.idglebik.ilikeit.repository;

import com.idglebik.ilikeit.dbo.*;
import com.idglebik.ilikeit.enumerated.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<UserDbo, Long> {
    List<UserDbo> findByPositions(PositionDbo positionDbo);

    List<UserDbo> findByHates(HateDbo hate);

    List<UserDbo> findByLikes(LikeDbo like);

    List<UserDbo> findByLanguages(LangDbo language);

    List<UserDbo> findAllByLIfePosition_AligmentAndLIfePosition_MainInLifeAndLIfePosition_MainInPeople(Aligment aligment, MainInLife mainInLife, MainInPeople mainInPeople);

    List<UserDbo> findByLikesAndHates(LikeDbo like, HateDbo hate);

    List<UserDbo> findAllByLastNameAndCityAndCountry(String lastName, String city, String country);

    List<UserDbo> findByLoginDbo(LoginDbo loginDbo);

    List<UserDbo> findByLoginDboUsername(String userName);

    List<UserDbo> findAllByFirstNameAndLastName(String firstName, String lastName);

    void deleteByLoginDbo(LoginDbo loginDbo);
}
