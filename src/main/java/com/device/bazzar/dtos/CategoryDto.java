package com.device.bazzar.dtos;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {


    private String categoryId;
    @NotBlank
    @Size(min = 4, message = "tittle should be minimum of length 4 characters")
    private String categoryTittle;
    @NotBlank(message = "description should not be blank")
    private String categoryDescription;
    private String coverImage;
}
