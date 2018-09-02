package com.idglebik.ilikeit.controller;

import com.idglebik.ilikeit.dto.LifePositionDto;
import com.idglebik.ilikeit.dto.UserDto;
import com.idglebik.ilikeit.enumerated.Hate;
import com.idglebik.ilikeit.enumerated.Language;
import com.idglebik.ilikeit.enumerated.Like;
import com.idglebik.ilikeit.enumerated.Position;
import com.idglebik.ilikeit.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("RestController")
@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @ApiOperation("find user by position")
    @GetMapping("/find/usingPosition")
    @ResponseBody
    public List<UserDto> findUserByPosition(@RequestParam Position position) {
        return searchService.searchUsersByPosition(position);
    }

    @ApiOperation("find user by hate")
    @GetMapping("/find/usingHate")
    @ResponseBody
    public List<UserDto> findUserByHate(@RequestParam Hate hate) {
        return searchService.searchUsersByHate(hate);
    }

    @ApiOperation("find user by like")
    @GetMapping("/find/usingLike")
    @ResponseBody
    public List<UserDto> findUserByLike(@RequestParam Like like) {
        return searchService.searchUsersByLike(like);
    }

    @ApiOperation("find user by lang")
    @GetMapping("/find/usingLang")
    @ResponseBody
    public List<UserDto> findUserByLang(@RequestParam Language language) {
        return searchService.searchUsersByLang(language);
    }

    @ApiOperation("find user by LifePosition")
    @GetMapping("/find/usingLifePosition")
    @ResponseBody
    public List<UserDto> findUserByLifePosition(LifePositionDto lifePositionDto) {
        return searchService.searchUsersByLifePosition(lifePositionDto);
    }

    @ApiOperation("find user by Like and Hate")
    @GetMapping("/find/usingLikeAndHate")
    @ResponseBody
    public List<UserDto> findUserByLikeAndHate(@RequestParam Like like, @RequestParam Hate hate) {
        return searchService.searchUsersByLikeAndHate(like, hate);
    }

    @ApiOperation("find user by LastName, City, Country")
    @GetMapping("/find/usingLastNameAndCityAndCountry")
    @ResponseBody
    public List<UserDto> findUserByLastNameAndCityAndCountry(@RequestParam String lastName, @RequestParam String city,
                                                             @RequestParam String country) {
        return searchService.searchUsersByLastNameAndCityAndCountry(lastName, city, country);
    }
}
