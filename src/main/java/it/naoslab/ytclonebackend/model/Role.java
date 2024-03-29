package it.naoslab.ytclonebackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    private String id;

    private ERole name;

    public Role(ERole name) {
        this.name = name;
    }
}

