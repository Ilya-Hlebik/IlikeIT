package com.idglebik.ilikeit.controller;

import com.idglebik.ilikeit.config.Response;
import com.idglebik.ilikeit.dto.LifePositionDto;
import com.idglebik.ilikeit.dto.SearchDto;
import com.idglebik.ilikeit.dto.UserDto;
import com.idglebik.ilikeit.enumerated.Hate;
import com.idglebik.ilikeit.enumerated.Language;
import com.idglebik.ilikeit.enumerated.Like;
import com.idglebik.ilikeit.enumerated.Position;
import com.idglebik.ilikeit.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("RestController")
@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @ApiOperation("find user by positions")
    @GetMapping("/usingPosition")
    @ResponseBody
    public ResponseEntity<Response<List<SearchDto>>> findUserByPosition(@RequestParam Position position) {
        return searchService.searchUsersByPosition(position);
    }

    @ApiOperation("find user by hates")
    @GetMapping("/usingHate")
    @ResponseBody
    public ResponseEntity<Response<List<SearchDto>>> findUserByHate(@RequestParam Hate hate) {
        return searchService.searchUsersByHate(hate);
    }

    @ApiOperation("find user by likes")
    @GetMapping("/usingLike")
    @ResponseBody
    public ResponseEntity<Response<List<SearchDto>>> findUserByLike(@RequestParam Like like) {
        return searchService.searchUsersByLike(like);
    }

    @ApiOperation("find user by lang")
    @GetMapping("/usingLang")
    @ResponseBody
    public ResponseEntity<Response<List<SearchDto>>> findUserByLang(@RequestParam Language language) {
        return searchService.searchUsersByLang(language);
    }

    @ApiOperation("find user by LifePosition")
    @GetMapping("/usingLifePosition")
    @ResponseBody
    public ResponseEntity<Response<List<SearchDto>>> findUserByLifePosition(LifePositionDto lifePositionDto) {
        return searchService.searchUsersByLifePosition(lifePositionDto);
    }

    @ApiOperation("find user by Like and Hate")
    @GetMapping("/usingLikeAndHate")
    @ResponseBody
    public ResponseEntity<Response<List<SearchDto>>> findUserByLikeAndHate(@RequestParam Like like, @RequestParam Hate hate) {
        return searchService.searchUsersByLikeAndHate(like, hate);
    }

    @ApiOperation("find user by UserDto")
    @PostMapping("/userDbo")
    @ResponseBody
    public ResponseEntity<Response<List<SearchDto>>> findByUserDbo(@RequestBody UserDto userDto) {
        return searchService.findByFirstNameAndLastName(userDto);
    }

    @ApiOperation("find user by LastName, City, Country")
    @GetMapping("/usingLastNameAndCityAndCountry")
    @ResponseBody
    public ResponseEntity<Response<List<SearchDto>>> findUserByLastNameAndCityAndCountry(@RequestParam String lastName, @RequestParam String city,
                                                             @RequestParam String country) {
        return searchService.searchUsersByLastNameAndCityAndCountry(lastName, city, country);
    }
}
