package com.idglebik.ilikeit.service;

import com.idglebik.ilikeit.config.Response;
import com.idglebik.ilikeit.converter.UserConverter;
import com.idglebik.ilikeit.dbo.FriendDbo;
import com.idglebik.ilikeit.dbo.LoginDbo;
import com.idglebik.ilikeit.dbo.UserDbo;
import com.idglebik.ilikeit.dto.SearchDto;
import com.idglebik.ilikeit.enumerated.Role;
import com.idglebik.ilikeit.repository.FriendRepository;
import com.idglebik.ilikeit.repository.LoginRepository;
import com.idglebik.ilikeit.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;
    private final SearchService searchService;
    private final UserConverter userConverter;

    public ResponseEntity<Response<SearchDto>> addFriend(final long friendId, final long userId, final Authentication auth) {
        if (userId != friendId) {
            LoginDbo loginDbo = loginRepository.findByUsername(auth.getName());
            final Optional<UserDbo> userDbo = userRepository.findById(userId);
            final List<UserDbo> userDboList = userRepository.findByLoginDboUsername(auth.getName());
            Boolean isRightUser = userDboList.stream().anyMatch(e -> e.getId() == userId) || loginDbo.getRoles().contains(Role.ADMIN);
            final Optional<UserDbo> friendUserDbo = userRepository.findById(friendId);
            if (!userDbo.isPresent() || !friendUserDbo.isPresent() || !isRightUser) {
                return new ResponseEntity(Response.error("can't add to friend List"), HttpStatus.BAD_REQUEST);
            }
            final Optional<FriendDbo> friendDboOptional = friendRepository.findByFriendIdAndUserDbo(friendId, userDbo.get());
            if (friendDboOptional.isPresent()) {
                return new ResponseEntity(Response.error("you are already friends"), HttpStatus.NOT_ACCEPTABLE);
            }
            FriendDbo friendDbo = new FriendDbo();
            friendDbo.setUserDbo(userDbo.get());
            friendDbo.setFriendId(friendId);
            friendRepository.save(friendDbo);
            return ResponseEntity.ok(Response.success(searchService.setSearchDtos(friendUserDbo.get())));
        } else {
            return new ResponseEntity(Response.error("you can't add yourself in friend list"), HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<Response<List<SearchDto>>> getFriendList(long userId) {
        final Optional<UserDbo> userDbo = userRepository.findById(userId);
        if (!userDbo.isPresent()) {
            return new ResponseEntity(Response.error("wrong userId"), HttpStatus.BAD_REQUEST);
        }
        final Set<FriendDbo> friendDbos = userDbo.get().getFriendDbos();
        final List<SearchDto> userDbos = new ArrayList<>();

        friendDbos.forEach(friendDbo -> {
            final Optional<UserDbo> dbo = userRepository.findById(friendDbo.getFriendId());
            if (dbo.isPresent()) {
                SearchDto searchDto = new SearchDto(userConverter.convertToDto(dbo.get()), friendDbo.getFriendId());
                userDbos.add(searchDto);
            } else {
                friendRepository.deleteByFriendId(friendDbo.getFriendId());
            }
        });
        return ResponseEntity.ok(Response.success(userDbos));
    }

    @Transactional
    public ResponseEntity<Response<String>> deleteFromFriendList(long friendId, long userId, Authentication auth) {
        if (userId != friendId) {
            LoginDbo loginDbo = loginRepository.findByUsername(auth.getName());
            final Optional<UserDbo> userDbo = userRepository.findById(userId);
            final List<UserDbo> userDboList = userRepository.findByLoginDboUsername(auth.getName());
            Boolean isRightUser = userDboList.stream().anyMatch(e -> e.getId() == userId) || loginDbo.getRoles().contains(Role.ADMIN);
            if (!userDbo.isPresent() || !isRightUser) {
                return new ResponseEntity(Response.error("can't remove from friend List"), HttpStatus.BAD_REQUEST);
            }
            final Optional<FriendDbo> friendUserDbo = friendRepository.findByFriendIdAndUserDbo(friendId, userDbo.get());
            if (friendUserDbo.isPresent()) {
                friendRepository.deleteById(friendUserDbo.get().getId());
                return ResponseEntity.ok(Response.success("Friend with id: " + friendUserDbo.get().getFriendId() + " was deleted"));
            }
            return new ResponseEntity(Response.error("friend not found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(Response.error("wrong user or friend Id"), HttpStatus.BAD_REQUEST);
        }
    }
}
