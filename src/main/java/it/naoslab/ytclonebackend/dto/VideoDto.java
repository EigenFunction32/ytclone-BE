package it.naoslab.ytclonebackend.dto;

import it.naoslab.ytclonebackend.model.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {
    private String id;
    private String title;
    private String description;
    private String uploadedBy;
    private String uploadedById;
    private LocalDate uploadDate;
    private Long sizeInBytes;
    private String content;
    private List<String> tags;
    private String videoUrl;
    private VideoStatus videoStatus;
    private String thumbnailUrl;
    private Integer likeCount;
    private Integer disLikeCount;
    private Integer viewCount;

}