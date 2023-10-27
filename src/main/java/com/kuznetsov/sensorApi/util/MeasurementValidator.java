package com.kuznetsov.sensorApi.util;

import com.kuznetsov.sensorApi.dto.CreateMeasurementDto;
import com.kuznetsov.sensorApi.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CreateMeasurementDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateMeasurementDto createMeasurementDto = (CreateMeasurementDto) target;
        if(sensorService.findAll().stream()
                .noneMatch(s->s.getName().equals(createMeasurementDto.getSensor().getName()))){
            errors.rejectValue("sensor","","This sensor did not registered in DB!");
        }
    }
}
