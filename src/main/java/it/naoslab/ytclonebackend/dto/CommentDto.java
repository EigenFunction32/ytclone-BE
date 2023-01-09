package it.naoslab.ytclonebackend.dto;
// NON COMPLETO!

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
    private String commentAuthor;
//    @Min(value = 0)
    private int likeCount;
//    @Min(value = 0)
    private int disLikeCount;
}
