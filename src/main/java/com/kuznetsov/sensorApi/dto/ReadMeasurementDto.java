package com.kuznetsov.sensorApi.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.kuznetsov.sensorApi.model.Sensor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class ReadMeasurementDto {
    private Integer id;
    private Float value;
    private Boolean raining;
    private LocalDateTime creatingDateTime;
    private ReadSensorDto sensor;

}
