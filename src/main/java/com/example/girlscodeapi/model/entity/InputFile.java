package com.example.girlscodeapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "input_file")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InputFile {
    @Id
    private Long id;
    private String fileName;
    private String fileUrl;
}
