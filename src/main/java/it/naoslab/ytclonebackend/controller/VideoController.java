package it.naoslab.ytclonebackend.controller;

import it.naoslab.ytclonebackend.dto.UploadVideoResponse;
import it.naoslab.ytclonebackend.dto.VideoDto;
import it.naoslab.ytclonebackend.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
// NON COMPLETO! Da integrare con il sistema di autenticazione!!!

@RestController
@RequestMapping("/api/videos/")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UploadVideoResponse uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
        return videoService.uploadVideo(file);
    }

    @PostMapping("/thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadThumbnail(@RequestParam("file") MultipartFile file, @RequestParam("videoId") String videoId) throws IOException {
        return videoService.uploadThumbnail(file, videoId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public VideoDto editVideoMetadata(@RequestBody VideoDto videoDto) {
        return videoService.editVideo(videoDto);
    }

    @GetMapping("/{videoId}")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto getVideoDetails(@PathVariable String videoId) {
        return videoService.getVideoDetails(videoId);
    }

//    @RestController
//    @RequestMapping("/api/video/")
//    @RequiredArgsConstructor
//    public class VideoController {
//
//        private final VideoService videoService;
//
//        @PostMapping("upload")
//        public ResponseEntity<UploadVideoResponse> uploadVideo(@RequestParam("file") MultipartFile file,
//                                                               @RequestParam("userId") String userId,
//                                                               UriComponentsBuilder uriComponentsBuilder) {
//            UploadVideoResponse videoResponse = videoService.uploadVideo(file, userId);
//            var uriComponents = uriComponentsBuilder.path("/{id}").buildAndExpand(videoResponse.getVideoId());
//            return ResponseEntity.created(uriComponents.toUri())
//                    .body(videoResponse);
//        }
//
//        @PostMapping("thumbnail/upload")
//        public ResponseEntity<String> uploadThumbnail(@RequestParam("file") MultipartFile file,
//                                                      @RequestParam("videoId") String videoId,
//                                                      UriComponentsBuilder uriComponentsBuilder) {
//            String thumbnailUrl = videoService.uploadThumbnail(file, videoId);
//            var uriComponents = uriComponentsBuilder.path("/{id}").buildAndExpand(thumbnailUrl);
//            return ResponseEntity.created(uriComponents.toUri())
//                    .body("Thumbnail Uploaded Successfully");
//        }
//
//        @PutMapping
//        @ResponseStatus(HttpStatus.OK)
//        public VideoDto editVideoMetadata(@RequestBody @Validated VideoDto videoMetaDataDto) {
//            return videoService.editVideoMetadata(videoMetaDataDto);
//        }
//
//        @GetMapping("{id}")
//        @ResponseStatus(HttpStatus.OK)
//        public VideoDto getVideoMetaData(@PathVariable String id) {
//            return videoService.getVideo(id);
//        }
//
//        @GetMapping
//        @ResponseStatus(HttpStatus.OK)
//        public List<VideoDto> getVideoMetaData() {
//            return videoService.getAllVideos();
//        }
//
//        @DeleteMapping("{id}")
//        @ResponseStatus(HttpStatus.NO_CONTENT)
//        public void deleteVideo(@PathVariable String id) {
//            videoService.deleteVideo(id);
//        }
//
//        @GetMapping("channel/{userId}")
//        @ResponseStatus(HttpStatus.OK)
//        public List<VideoDto> allChannelVideos(@PathVariable String userId) {
//            return videoService.getAllVideosByChannel(userId);
//        }
//
//        @PostMapping("{id}/like")
//        @ResponseStatus(HttpStatus.OK)
//        public VideoDto likeVideo(@PathVariable String id) {
//            return videoService.like(id);
//        }
//
//        @PostMapping("{id}/dislike")
//        @ResponseStatus(HttpStatus.OK)
//        public VideoDto disLikeVideo(@PathVariable String id) {
//            return videoService.dislike(id);
//        }
//
//        @GetMapping("suggested/{userId}")
//        @ResponseStatus(HttpStatus.OK)
//        public List<VideoDto> getSuggestedVideos(@PathVariable String userId) {
//            return videoService.getSuggestedVideos(userId);
//        }
//
//        @PostMapping("{id}/comment")
//        @ResponseStatus(HttpStatus.CREATED)
//        public void addComments(@PathVariable String id, @RequestBody CommentDto commentDto) {
//            videoService.addComment(commentDto, id);
//        }
//
//        @GetMapping("{id}/comment")
//        @ResponseStatus(HttpStatus.OK)
//        public List<CommentDto> getAllComments(@PathVariable String id) {
//            return videoService.getAllComments(id);
//        }
//    }
}
