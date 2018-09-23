package com.idglebik.ilikeit.controller;

import com.idglebik.ilikeit.config.Response;
import com.idglebik.ilikeit.dto.SearchDto;
import com.idglebik.ilikeit.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("RestController")
@RestController
@AllArgsConstructor
@RequestMapping("/friend")
public class FriendController {
    private final FriendService friendService;

    @ApiOperation("add friend")
    @PostMapping
    @ResponseBody
    public ResponseEntity<Response<SearchDto>> addFriend(@RequestParam long friendId, @RequestParam long userId
            , Authentication auth) {
        return friendService.addFriend(friendId, userId, auth);
    }

    @ApiOperation("get friend list")
    @GetMapping
    @ResponseBody
    public ResponseEntity<Response<List<SearchDto>>> getFriendList(@RequestParam long userId) {
        return friendService.getFriendList(userId);
    }

    @ApiOperation("delete user from friend list")
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<Response<String>> deleteFromFriendList(@RequestParam long friendId, @RequestParam long userId
            , Authentication auth) {
        return friendService.deleteFromFriendList(friendId, userId, auth);
    }
}
