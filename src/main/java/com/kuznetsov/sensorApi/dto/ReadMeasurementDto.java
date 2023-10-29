package com.kuznetsov.sensorApi.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public final class ReadMeasurementDto {
    private Integer id;
    private Float value;
    private Boolean raining;
    private LocalDateTime creatingDateTime;
    private ReadSensorDto sensor;

}
