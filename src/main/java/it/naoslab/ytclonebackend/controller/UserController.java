package it.naoslab.ytclonebackend.controller;

import it.naoslab.ytclonebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// NON COMPLETO!


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
//
//    private final UserService userService;
//    private final UserValidationService userValidationService;
//    private final UserRegistrationService userRegistrationService;
//
//    @GetMapping("{id}/history")
//    @ResponseStatus(HttpStatus.OK)
//    public Set<String> userHistory(@PathVariable String id) {
//        return userService.getHistory(id);
//    }
//
//    @GetMapping("validate")
//    @ResponseStatus(HttpStatus.OK)
//    public UserInfoDTO registerUser(HttpServletRequest httpServletRequest) {
//        var userInfoDTO = userValidationService.validate(httpServletRequest.getHeader("Authorization"));
//        userRegistrationService.register(userInfoDTO);
//        return userInfoDTO;
//    }
//

}
