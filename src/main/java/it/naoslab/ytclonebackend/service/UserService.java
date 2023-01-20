package it.naoslab.ytclonebackend.service;


import it.naoslab.ytclonebackend.dto.VideoDto;
import it.naoslab.ytclonebackend.model.User;
import it.naoslab.ytclonebackend.repository.UserRepository;
import it.naoslab.ytclonebackend.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final VideoRepository videoRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Impossibile trovare l'utente con username: " + username));
        return UserDetailsImpl.build(user);
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Impossibile trovare l'utente con username - " + username));
    }

    public String getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findByUsername(username).get().getId();
    }

    private User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Impossibile trovare l'utente con id - " + userId));
    }

    public boolean checkUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return !(auth instanceof AnonymousAuthenticationToken);
    }

    public void addToLikedVideos(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.addToLikedVideos(videoId);
        userRepository.save(currentUser);
    }

    public boolean ifLikedVideo(String videoId) {
        return getCurrentUser().getLikedVideos().stream().anyMatch(likedVideo -> likedVideo.equals(videoId));
    }

    public boolean ifDisLikedVideo(String videoId) {
        return getCurrentUser().getDisLikedVideos().stream().anyMatch(likedVideo -> likedVideo.equals(videoId));
    }

    public void removeLikedVideos(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.removeFromLikedVideos(videoId);
        userRepository.save(currentUser);
    }

    public void removeDisLikedVideos(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.removeFromDisLikedVideo(videoId);
        userRepository.save(currentUser);
    }

    public void addToDisLikedVideos(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.addToDisLikedVideos(videoId);
        userRepository.save(currentUser);
    }

    public void addVideoToHistory(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.addToVideoHistory(videoId);
        userRepository.save(currentUser);
    }

    public void subscribeUser(String userId) {
        User currentUser = getCurrentUser();
        currentUser.addToSubscribedUsers(userId);
        User user = getUserById(userId);
        user.addToSubscribers(currentUser.getId());
        userRepository.save(currentUser);
        userRepository.save(user);
    }

    public void unSubscribeUser(String userId) {
        User currentUser = getCurrentUser();
        currentUser.removeFromSubscribedToUser(userId);
        User user = getUserById(userId);
        user.removeFromSubscribers(currentUser.getId());
        userRepository.save(currentUser);
        userRepository.save(user);
    }

    public List<Optional<VideoDto>> userHistory(String userId) {
        User user = getUserById(userId);
        Set<String> userHistory = user.getVideoHistory();
        List<Optional<VideoDto>> userVideoHistory = new ArrayList<>();
        for (String videoId : userHistory) {
            Optional<VideoDto> video = videoRepository.findById(videoId).map(VideoService::mapToVideoDto);
            userVideoHistory.add(video);
        }
        return userVideoHistory;
    }

    public List<Optional<VideoDto>> likedVideos(String userId) {
        User user = getUserById(userId);
        Set<String> likedVideos = user.getLikedVideos();
        List<Optional<VideoDto>> userLikedVideos = new ArrayList<>();
        for (String videoId : likedVideos) {
            Optional<VideoDto> video = videoRepository.findById(videoId).map(VideoService::mapToVideoDto);
            userLikedVideos.add(video);
        }
        return userLikedVideos;
    }
}