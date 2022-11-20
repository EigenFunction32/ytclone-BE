package it.naoslab.ytclonebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// NON COMPLETO! Da integrare con il sistema di autenticazione!!!


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
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
//    @PostMapping("subscribe/{userId}")
//    @ResponseStatus(HttpStatus.OK)
//    public void subscribeUser(@PathVariable String userId) {
//        userService.subscribeUser(userId);
//    }
}
