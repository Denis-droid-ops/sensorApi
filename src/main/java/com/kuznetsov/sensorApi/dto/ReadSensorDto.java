package com.kuznetsov.sensorApi.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public final class ReadSensorDto {
    private Integer id;
    private String name;
}
