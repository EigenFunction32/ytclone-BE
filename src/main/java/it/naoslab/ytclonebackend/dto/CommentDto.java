package it.naoslab.ytclonebackend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    //    @NotBlank
    private String commentText;
    //    @NotBlank
    private String authorId;
    //    @Min(value = 0)
    private int likeCount;
    //    @Min(value = 0)
    private int disLikeCount;
}
