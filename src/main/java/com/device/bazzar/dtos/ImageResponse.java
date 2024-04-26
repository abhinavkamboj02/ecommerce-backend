package com.device.bazzar.dtos;
import lombok.*;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class ImageResponse {
    private String imageName;
    private String message;
    private boolean success;
    private HttpStatus status;
}
