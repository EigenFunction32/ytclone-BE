package it.naoslab.ytclonebackend.service;

import it.naoslab.ytclonebackend.dto.UploadVideoResponse;
import it.naoslab.ytclonebackend.dto.VideoDto;
import it.naoslab.ytclonebackend.model.Video;
import it.naoslab.ytclonebackend.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class VideoService {


    private final FileUploadService fileUploadService;
    private final VideoRepository videoRepository;

    public UploadVideoResponse uploadVideo(MultipartFile multipartFile) throws IOException {
        String videoUrl = fileUploadService.saveFile(multipartFile);
        var video = new Video();
        video.setVideoUrl(videoUrl);
        var savedVideo = videoRepository.save(video);
        return new UploadVideoResponse(savedVideo.getId(), savedVideo.getVideoUrl());
    }

    public VideoDto editVideo(VideoDto videoDto) {
       var savedVideo = getVideoById(videoDto.getId()) ;

       savedVideo.setTitle(videoDto.getTitle());
       savedVideo.setDescription(videoDto.getDescription());
       savedVideo.setTags(videoDto.getTags());
       savedVideo.setThumbnailUrl(videoDto.getThumbnailUrl());
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

        VideoDto videoDto = new VideoDto();
        videoDto.setVideoUrl(savedVideo.getVideoUrl());
        videoDto.setThumbnailUrl(savedVideo.getThumbnailUrl());
        videoDto.setId(savedVideo.getId());
        videoDto.setTitle(savedVideo.getTitle());
        videoDto.setDescription(savedVideo.getDescription());
        videoDto.setTags(savedVideo.getTags());
        videoDto.setVideoStatus(savedVideo.getVideoStatus());

        return videoDto;
    }
}
