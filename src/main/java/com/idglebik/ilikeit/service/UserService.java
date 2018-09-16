package com.idglebik.ilikeit.service;

import com.idglebik.ilikeit.config.Response;
import com.idglebik.ilikeit.converter.LifePositionConverter;
import com.idglebik.ilikeit.converter.StudyConverter;
import com.idglebik.ilikeit.converter.UserConverter;
import com.idglebik.ilikeit.dbo.*;
import com.idglebik.ilikeit.dto.*;
import com.idglebik.ilikeit.repository.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PositionRepository positionRepository;
    private final StudyConverter studyConverter;
    private final LanguageRepository languageRepository;
    private final LikeRepository likeRepository;
    private final HateRepository hateRepository;
    private final LifePositionConverter lifePositionConverter;
    private final LifePositionRepository lifePositionRepository;
    private final LoginRepository loginRepository;
    private final LoginService loginService;
    private static Logger log = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public ResponseEntity<Response<UserDto>> createUser(final UserDto userDto, final Authentication auth) {
        if (loginService.isCanCreateAccount(auth)) {
            final UserDbo userDbo;
            try {
                userDbo = userConverter.convertToDbo(userDto);
                final Set<StudyDbo> studyDbos = userDto.getStudies().stream().map(studyConverter::convertToDbo).collect(Collectors.toSet());
                studyDbos.forEach(studyDbo -> studyDbo.setUserDbo(userDbo));
                userDbo.setStudies(studyDbos);
                LIfePositionDbo lIfePositionDbo = lifePositionConverter.convertToDbo(userDto.getLifePosition());
                lIfePositionDbo.setUser(userDbo);
                userDbo.setLIfePosition(lIfePositionDbo);
                userDbo.setPositions(userDto.getPositions().stream().map(this::getPositionDBO).collect(Collectors.toSet()));
                userDbo.setLikes(userDto.getLikes().stream().map(this::getLikeDBO).collect(Collectors.toSet()));
                userDbo.setHates(userDto.getHates().stream().map(this::getHateDBO).collect(Collectors.toSet()));
                userDbo.setLanguages(userDto.getLanguages().stream().map(this::getLanguageDBO).collect(Collectors.toSet()));
                userDbo.setLoginDbo(loginRepository.findByUsername(auth.getName()));
                return ResponseEntity.ok(Response.success(userConverter.convertToDto(userRepository.save(userDbo))));
            } catch (Exception e) {
                log.error("failed during creating user", e);
                return new ResponseEntity(Response.error("Can't save, please check information in the fields"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        log.error("You can't save 2 users for 1 login");
        return new ResponseEntity(Response.error("You already have an account"), HttpStatus.BAD_REQUEST);

    }

    private HateDbo getHateDBO(final HateDto hateDto) {
        return hateRepository.findByHate(hateDto.getHate());
    }

    private LikeDbo getLikeDBO(final LikeDto likeDto) {

        return likeRepository.findByLike(likeDto.getLike());
    }

    @Transactional
    public LangDbo getLanguageDBO(final LangDto langDto) {
        return languageRepository.findByLanguage(langDto.getLanguage());
    }

    public ResponseEntity<Response<List<UserDto>>> getUserList() {
        return ResponseEntity.ok(Response.success(userRepository.findAll().stream().map(userConverter::convertToDto).collect(Collectors.toList())));
    }


    private PositionDbo getPositionDBO(final PositionDto positionDto) {
        return positionRepository.findByPositionName(positionDto.getPositionName());
    }

    @Transactional
    public ResponseEntity<Response<String>> deleteUser(final Authentication auth) {
        final LoginDbo login = loginRepository.findByUsername(auth.getName());
        final List<UserDbo> userDbos = userRepository.findByLoginDbo(login);
        if (userDbos.size() == 0) {
            return new ResponseEntity(Response.error("You don't have account"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userRepository.deleteByLoginDbo(login);
        return ResponseEntity.ok(Response.success("User was deleted"));
    }

    @Transactional
    public ResponseEntity<Response<UserDto>> updateUser(final Authentication auth, final UserDto userDto) {
        final List<UserDbo> userDbo = userRepository.findByLoginDbo(loginRepository.findByUsername(auth.getName()));
        if (userDbo.size() != 0) {
            try {
                final UserDbo user = userDbo.get(0);
                user.setSexes(userDto.getSexes());
                user.setAge(userDto.getAge());
                user.setFirstName(userDto.getFirstName());
                user.setLastName(userDto.getLastName());
                user.setAddress(userDto.getAddress());
                user.setCity(userDto.getCity());
                user.setCountry(userDto.getCountry());
                user.setGeneralInfo(userDto.getGeneralInfo());
                user.setOtherInfo(userDto.getOtherInfo());
                user.setLikes(userDto.getLikes().stream().map(this::getLikeDBO).collect(Collectors.toSet()));
                user.setHates(userDto.getHates().stream().map(this::getHateDBO).collect(Collectors.toSet()));
                user.setLanguages(userDto.getLanguages().stream().map(this::getLanguageDBO).collect(Collectors.toSet()));
                user.setPositions(userDto.getPositions().stream().map(this::getPositionDBO).collect(Collectors.toSet()));

                lifePositionRepository.removeByUser(user);
                LIfePositionDbo lIfePositionDbo = lifePositionConverter.convertToDbo(userDto.getLifePosition());
                lIfePositionDbo.setUser(user);
                user.setLIfePosition(lIfePositionDbo);

                final Set<StudyDbo> studyDbos = userDto.getStudies().stream().map(studyConverter::convertToDbo).collect(Collectors.toSet());
                studyDbos.forEach(studyDbo -> studyDbo.setUserDbo(user));
                user.setStudies(studyDbos);
                return ResponseEntity.ok(Response.success(userConverter.convertToDto(userRepository.save(user))));
            } catch (Exception e) {
                log.error("can't update current user", e);
                return new ResponseEntity(Response.error("Error updating current user"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity(Response.error("You don't have account"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Response<List<UserDto>>> getCurrentUser(Authentication authentication) {
        final LoginDbo login = loginRepository.findByUsername(authentication.getName());
        return ResponseEntity.ok(Response.success(userConverter.convertToDto(userRepository.findByLoginDbo(login))));
    }
}
