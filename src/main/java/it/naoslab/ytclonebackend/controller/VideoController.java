package it.naoslab.ytclonebackend.controller;

import it.naoslab.ytclonebackend.dto.CommentDto;
import it.naoslab.ytclonebackend.dto.UploadVideoResponse;
import it.naoslab.ytclonebackend.dto.VideoDto;
import it.naoslab.ytclonebackend.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

//  Controller per gestione Video
@RestController
@RequestMapping("/api/videos/")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

//    Upload Video
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UploadVideoResponse uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
        return videoService.uploadVideo(file);
    }

//    Upload Thumbnail video
    @PostMapping("/thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadThumbnail(@RequestParam("file") MultipartFile file, @RequestParam("videoId") String videoId) throws IOException {
        return videoService.uploadThumbnail(file, videoId);
    }

//    Edit metadata video (titolo, descrizione, tags, ecc)
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public VideoDto editVideoMetadata(@RequestBody VideoDto videoDto) {
        return videoService.editVideo(videoDto);
    }

//    Visualizzazione video
    @GetMapping("/{videoId}")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto getVideoDetails(@PathVariable String videoId) {
        return videoService.getVideoDetails(videoId);
    }

//    Like video
    @PostMapping("/{videoId}/like")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto likeVideo(@PathVariable String videoId) {
        return videoService.likeVideo(videoId);
    }

//    Dislike video
    @PostMapping("/{videoId}/disLike")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto disLikeVideo(@PathVariable String videoId) {
        return videoService.disLikeVideo(videoId);
    }

//    Inserimento singolo Commento
    @PostMapping("/{videoId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public void addComments(@PathVariable String videoId, @RequestBody CommentDto commentDto) {
        videoService.addComment(commentDto, videoId);
    }

//    Visualizza tutti i commenti
    @GetMapping("/{videoId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllComments(@PathVariable String videoId) {
        return videoService.getAllComments(videoId);
    }

//    Visualizza tutti i video presenti nel DB
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VideoDto> getAllVideos() {
        return videoService.getAllVideos();
    }

//    Funzione di ricerca video (full-text) presenti nel DB
//    TODO Miglioramento ricerca
    @GetMapping("/search={searchPhrase}")
    @ResponseStatus(HttpStatus.OK)
    public List<VideoDto> search(@PathVariable String searchPhrase) {
        return videoService.fullTextSearch(searchPhrase);
    }
}
