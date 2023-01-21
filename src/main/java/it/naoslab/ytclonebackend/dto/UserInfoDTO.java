package it.naoslab.ytclonebackend.dto;

import it.naoslab.ytclonebackend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private String id;
    private String username;
    private String email;
    private String password;
    private Set<Role> roles;
    private String picture;
    private Set<String> subscribedToUsers;
    private Set<String> subscribers;
    private Set<String> videoHistory;
    private Set<String> likedVideos;
    private Set<String> disLikedVideos;

}