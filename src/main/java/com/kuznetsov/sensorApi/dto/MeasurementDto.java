package com.kuznetsov.sensorApi.dto;

import com.kuznetsov.sensorApi.model.Sensor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public final class MeasurementDto {

    @NotEmpty(message = "Value should not be empty")
    @Size(min = -100,max = 100,message = "Value should be between -100 and 100 numbers")
    @NotNull
    private Float value;

    @NotEmpty(message = "Raining should not be empty")
    @NotNull
    private Boolean raining;

    @Column(name = "creating_date_time")
    private LocalDateTime creatingDateTime;

    @NotEmpty(message = "Sensor should not be empty")
    @NotNull
    private Sensor sensor;


}
