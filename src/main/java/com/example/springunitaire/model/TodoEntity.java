package com.example.springunitaire.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "todos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TodoEntity {

    @Id
    private String id;
    private String title;
    private String description;
    private boolean done;
}
