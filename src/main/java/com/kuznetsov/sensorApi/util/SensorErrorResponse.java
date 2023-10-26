package com.kuznetsov.sensorApi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SensorErrorResponse {
    private String message;
    private Long timestamp;


}
