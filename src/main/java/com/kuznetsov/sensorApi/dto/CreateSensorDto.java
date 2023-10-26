package com.kuznetsov.sensorApi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class CreateSensorDto {

    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 3,max = 30,message = "Name should be between 3 and 30 characters")
    @NotNull
    private String name;

}
