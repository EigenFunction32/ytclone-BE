package it.naoslab.ytclonebackend.service;

import it.naoslab.ytclonebackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// NON COMPLETO! Da integrare con il sistema di autenticazione!!!

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

//    private final UserRepository userRepository;

//    public void registerUser() {
//        //Effettuare chiamata all'endpoint userInfo
//        Optional<User> existingUserOpt = userRepository.findByEmailAddress(userInfoDTO.getEmail());
//        if (existingUserOpt.isPresent()) {
//            userInfoDTO.setId(existingUserOpt.get().getId());
//            return;
//        }
//        //Recuperare i dati utente dall'endpoint e scriverli nel DB
//        var user = new User();
//        user.setSub(userInfoDTO.getSub());
//        user.setEmailAddress(userInfoDTO.getEmail());
//        user.setFirstName(userInfoDTO.getGivenName());
//        user.setLastName(userInfoDTO.getFamilyName());
//        user.setFullName(userInfoDTO.getName());
//        user.setPicture(userInfoDTO.getPicture());
//        user.setPicture(userInfoDTO.getPicture());
//        userRepository.save(user);
//    }
}
