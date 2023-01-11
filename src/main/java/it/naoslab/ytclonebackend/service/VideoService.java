package it.naoslab.ytclonebackend.service;

import it.naoslab.ytclonebackend.dto.CommentDto;
import it.naoslab.ytclonebackend.dto.UploadVideoResponse;
import it.naoslab.ytclonebackend.dto.VideoDto;
import it.naoslab.ytclonebackend.model.Comment;
import it.naoslab.ytclonebackend.model.Video;
import it.naoslab.ytclonebackend.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {


    private final FileUploadService fileUploadService;
    private final VideoRepository videoRepository;
    private final UserService userService;

    public UploadVideoResponse uploadVideo(MultipartFile multipartFile) throws IOException {
        String videoUrl = fileUploadService.saveFile(multipartFile);
        var video = new Video();
        video.setVideoUrl(videoUrl);
        var savedVideo = videoRepository.save(video);
        return new UploadVideoResponse(savedVideo.getId(), savedVideo.getVideoUrl());
    }

    public VideoDto editVideo(VideoDto videoDto) {
        var savedVideo = getVideoById(videoDto.getId());

        savedVideo.setTitle(videoDto.getTitle());
        savedVideo.setDescription(videoDto.getDescription());
        savedVideo.setTags(videoDto.getTags());
        savedVideo.setVideoStatus(videoDto.getVideoStatus());

        videoRepository.save(savedVideo);
        return videoDto;

    }

    public String uploadThumbnail(MultipartFile file, String videoId) throws IOException {
        var savedVideo = getVideoById(videoId);
        String thumbnailUrl = fileUploadService.saveFile(file);
        savedVideo.setThumbnailUrl(thumbnailUrl);
        videoRepository.save(savedVideo);
        return thumbnailUrl;

    }

    Video getVideoById(String videoId) {
        return videoRepository.findById(videoId).orElseThrow(() -> new IllegalArgumentException("Impossibile trovare il video con id " + videoId));
    }

    public VideoDto getVideoDetails(String videoId) {
        Video savedVideo = getVideoById(videoId);
        increaseViewCount(savedVideo);
        if (userService.checkUser())
            userService.addVideoToHistory(videoId);
        return mapToVideoDto(savedVideo);
    }

    private void increaseViewCount(Video savedVideo) {
        savedVideo.increaseViewCount();
        videoRepository.save(savedVideo);
    }

    public VideoDto likeVideo(String videoId) {
        Video videoById = getVideoById(videoId);
        if (userService.ifLikedVideo(videoId)) {
            videoById.decreaseLikeCount();
            userService.removeLikedVideos(videoId);
        } else if (userService.ifDisLikedVideo(videoId)) {
            videoById.decreaseDisLikeCount();
            userService.removeDisLikedVideos(videoId);
            videoById.increaseLikeCount();
            userService.addToLikedVideos(videoId);
        } else {
            videoById.increaseLikeCount();
            userService.addToLikedVideos(videoId);
        }
        videoRepository.save(videoById);
        return mapToVideoDto(videoById);

    }

    public VideoDto disLikeVideo(String videoId) {
        Video videoById = getVideoById(videoId);
        if (userService.ifDisLikedVideo(videoId)) {
            videoById.decreaseDisLikeCount();
            userService.removeDisLikedVideos(videoId);
        } else if (userService.ifLikedVideo(videoId)) {
            videoById.decreaseLikeCount();
            userService.removeLikedVideos(videoId);
            videoById.increaseDisLikeCount();
            userService.addToDisLikedVideos(videoId);
        } else {
            videoById.increaseDisLikeCount();
            userService.addToDisLikedVideos(videoId);
        }
        videoRepository.save(videoById);
        return mapToVideoDto(videoById);
    }

    private static VideoDto mapToVideoDto(Video videoById) {
        VideoDto videoDto = new VideoDto();
        videoDto.setVideoUrl(videoById.getVideoUrl());
        videoDto.setThumbnailUrl(videoById.getThumbnailUrl());
        videoDto.setId(videoById.getId());
        videoDto.setTitle(videoById.getTitle());
        videoDto.setDescription(videoById.getDescription());
        videoDto.setTags(videoById.getTags());
        videoDto.setVideoStatus(videoById.getVideoStatus());
        videoDto.setLikeCount(videoById.getLikes().get());
        videoDto.setDisLikeCount(videoById.getDisLikes().get());
        videoDto.setViewCount(videoById.getViewCount().get());
        return videoDto;
    }

    public void addComment(CommentDto commentDto, String videoId) {
        Video video = getVideoById(videoId);
        Comment comment = new Comment();
        comment.setText(commentDto.getCommentText());
        comment.setAuthorId(comment.getAuthorId());
        video.addComment(comment);

        videoRepository.save(video);
    }

    public List<CommentDto> getAllComments(String videoId) {
        Video video = getVideoById(videoId);
        List<Comment> commentList = video.getCommentList();

        return commentList.stream().map(this::mapToCommentDto).toList();
    }

    private CommentDto mapToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentText(comment.getText());
        commentDto.setAuthorId(comment.getAuthorId());
        return commentDto;
    }

    public List<VideoDto> getAllVideos() {
        return videoRepository.findAll().stream().map(VideoService::mapToVideoDto).toList();
    }
}

