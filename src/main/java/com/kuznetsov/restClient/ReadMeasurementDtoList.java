package com.kuznetsov.restClient;

import com.kuznetsov.sensorApi.dto.ReadMeasurementDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class ReadMeasurementDtoList {
    private List<ReadMeasurementDto> readMeasurementDtoList;

    public ReadMeasurementDtoList() {
        readMeasurementDtoList = new ArrayList<>();
    }
}
