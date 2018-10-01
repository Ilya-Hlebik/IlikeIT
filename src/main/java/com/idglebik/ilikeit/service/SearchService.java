package com.idglebik.ilikeit.service;

import com.idglebik.ilikeit.config.Response;
import com.idglebik.ilikeit.converter.UserConverter;
import com.idglebik.ilikeit.dbo.*;
import com.idglebik.ilikeit.dto.LifePositionDto;
import com.idglebik.ilikeit.dto.SearchDto;
import com.idglebik.ilikeit.dto.UserDto;
import com.idglebik.ilikeit.enumerated.Hate;
import com.idglebik.ilikeit.enumerated.Language;
import com.idglebik.ilikeit.enumerated.Like;
import com.idglebik.ilikeit.enumerated.Position;
import com.idglebik.ilikeit.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchService {
    private final PositionRepository positionRepository;
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final HateRepository hateRepository;
    private final LikeRepository likeRepository;
    private final LanguageRepository languageRepository;

    public ResponseEntity<Response<List<SearchDto>>> searchUsersByPosition(final Position position) {
        final PositionDbo positionDbo = positionRepository.findByPositionName(position);
        final List<UserDbo> userDbos = userRepository.findByPositions(positionDbo);
        return ResponseEntity.ok(Response.success(getSearchDtos(userDbos)));
    }

    public ResponseEntity<Response<List<SearchDto>>> searchUsersByHate(final Hate hate) {
        final HateDbo hateDbo = hateRepository.findByHate(hate);
        final List<UserDbo> userDbos = userRepository.findByHates(hateDbo);
        return ResponseEntity.ok(Response.success(getSearchDtos(userDbos)));
    }

    public ResponseEntity<Response<List<SearchDto>>> searchUsersByLike(final Like like) {
        final LikeDbo likeDbo = likeRepository.findByLike(like);
        final List<UserDbo> userDbos = userRepository.findByLikes(likeDbo);
        return ResponseEntity.ok(Response.success(getSearchDtos(userDbos)));
    }

    public ResponseEntity<Response<List<SearchDto>>> searchUsersByLang(final Language language) {
        final LangDbo langDbo = languageRepository.findByLanguage(language);
        final List<UserDbo> userDbos = userRepository.findByLanguages(langDbo);
        return ResponseEntity.ok(Response.success(getSearchDtos(userDbos)));
    }

    public ResponseEntity<Response<List<SearchDto>>> searchUsersByLifePosition(final LifePositionDto lifePositionDto) {
        final List<UserDbo> userDbos = userRepository.findAllByLifePosition_AligmentAndLifePosition_MainInLifeAndLifePosition_MainInPeople(
                lifePositionDto.getAligment(), lifePositionDto.getMainInLife(), lifePositionDto.getMainInPeople());
        return ResponseEntity.ok(Response.success(getSearchDtos(userDbos)));
    }

    public ResponseEntity<Response<List<SearchDto>>> searchUsersByLikeAndHate(final Like like, final Hate hate) {
        final HateDbo hateDbo = hateRepository.findByHate(hate);
        final LikeDbo likeDbo = likeRepository.findByLike(like);
        final List<UserDbo> userDbos = userRepository.findByLikesAndHates(likeDbo, hateDbo);
        return ResponseEntity.ok(Response.success(getSearchDtos(userDbos)));
    }

    public ResponseEntity<Response<List<SearchDto>>> searchUsersByLastNameAndCityAndCountry(final String lastName, final String city, final String country) {
        final List<UserDbo> userDbos = userRepository.findAllByLastNameAndCityAndCountry(lastName, city, country);
        return ResponseEntity.ok(Response.success(getSearchDtos(userDbos)));
    }

    public ResponseEntity<Response<List<SearchDto>>> findByFirstNameAndLastName(UserDto userDto) {
        final List<UserDbo> userDbos = userRepository.findAllByFirstNameAndLastName(userDto.getFirstName(), userDto.getLastName());
        return ResponseEntity.ok(Response.success(getSearchDtos(userDbos)));
    }

    public SearchDto setSearchDtos(UserDbo userDbo) {
        return new SearchDto(userConverter.convertToDto(userDbo), userDbo.getId());
    }

    public List<SearchDto> getSearchDtos(List<UserDbo> userDbos) {
        return userDbos.stream().map(this::setSearchDtos).collect(Collectors.toList());
    }
}
