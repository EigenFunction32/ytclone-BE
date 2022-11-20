package it.naoslab.ytclonebackend.dto;

import it.naoslab.ytclonebackend.model.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
// NON COMPLETO! Da integrare con il sistema di autenticazione!!!

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {
    private String id;
    private String title;
    private String description;
    private List<String> tags;
    private String videoUrl;
    private VideoStatus videoStatus;
    private String thumbnailUrl;
}
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class VideoDto {
//    private String videoId;
//    @NotBlank
//    private String userId;
//    @NotBlank
//    private String videoName;
//    @NotBlank
//    private String description;
//    @Size(min = 1)
//    private List<String> tags;
//    private VideoStatus videoStatus;
//    @NotBlank
//    private String url;
//    @NotBlank
//    private String thumbnailUrl;
//    @Min(value = 0)
//    private int likeCount;
//    @Min(value = 0)
//    private int dislikeCount;
//}
