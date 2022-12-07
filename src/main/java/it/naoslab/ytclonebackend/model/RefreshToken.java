package it.naoslab.ytclonebackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Document(collection="refresh_token")
public class RefreshToken {
    @Id
    private String id;

    @DBRef
    private User user;

    @NotNull
    private String token;

    @NotNull
    private Instant expiryDate;
}
