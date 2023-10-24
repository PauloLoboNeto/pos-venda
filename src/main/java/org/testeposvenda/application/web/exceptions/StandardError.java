package org.testeposvenda.application.web.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StandardError {
    private Long timeStamp;
    private Integer status;
    private String message;

    private String description;
    private String path;

}
