package it.naoslab.ytclonebackend.controller;

import it.naoslab.ytclonebackend.dto.VideoDto;
import it.naoslab.ytclonebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("subscribe/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean subscribeUser(@PathVariable String userId) {
        userService.subscribeUser(userId);
        return true;
    }

    @PostMapping("unSubscribe/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean unSubscribeUser(@PathVariable String userId) {
        userService.unSubscribeUser(userId);
        return true;
    }

    @GetMapping("{userId}/history")
    @ResponseStatus(HttpStatus.OK)
    public List<Optional<VideoDto>> userHistory(@PathVariable String userId) {
        return userService.userHistory(userId);
    }

    @GetMapping("{userId}/likedVideos")
    @ResponseStatus(HttpStatus.OK)
    public List<Optional<VideoDto>> likedVideos(@PathVariable String userId) {
        return userService.likedVideos(userId);
    }
}
