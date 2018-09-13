package com.idglebik.ilikeit.service;

import com.idglebik.ilikeit.converter.UserConverter;
import com.idglebik.ilikeit.dto.LifePositionDto;
import com.idglebik.ilikeit.dto.UserDto;
import com.idglebik.ilikeit.enumerated.Hate;
import com.idglebik.ilikeit.enumerated.Language;
import com.idglebik.ilikeit.enumerated.Like;
import com.idglebik.ilikeit.enumerated.Position;
import com.idglebik.ilikeit.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchService {
    private final PositionRepository positionRepository;
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final HateRepository hateRepository;
    private final LikeRepository likeRepository;
    private final LanguageRepository languageRepository;


    public List<UserDto> searchUsersByPosition(Position position) {
        return userConverter.convertToDto(userRepository.findByPositions(positionRepository.findByPositionName(position)));
    }

    public List<UserDto> searchUsersByHate(Hate hate) {
        return userConverter.convertToDto(userRepository.findByHates(hateRepository.findByHate(hate)));
    }

    public List<UserDto> searchUsersByLike(Like like) {
        return userConverter.convertToDto(userRepository.findByLikes(likeRepository.findByLike(like)));
    }

    public List<UserDto> searchUsersByLang(Language language) {
        return userConverter.convertToDto(userRepository.findByLanguages(languageRepository.findByLanguage(language)));
    }

    public List<UserDto> searchUsersByLifePosition(LifePositionDto lifePositionDto) {
        return userConverter.convertToDto(userRepository.findAllByLIfePosition_AligmentAndLIfePosition_MainInLifeAndLIfePosition_MainInPeople(
                lifePositionDto.getAligment(), lifePositionDto.getMainInLife(), lifePositionDto.getMainInPeople()));
    }

    public List<UserDto> searchUsersByLikeAndHate(Like like, Hate hate) {
        return userConverter.convertToDto(userRepository.findByLikesAndHates(likeRepository.findByLike(like), hateRepository.findByHate(hate)));
    }

    public List<UserDto> searchUsersByLastNameAndCityAndCountry(String lastName, String city, String country) {
        return userConverter.convertToDto(userRepository.findAllByLastNameAndCityAndCountry(lastName, city, country));
    }
}
