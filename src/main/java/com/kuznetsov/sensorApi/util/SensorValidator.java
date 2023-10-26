package com.kuznetsov.sensorApi.util;

import com.kuznetsov.sensorApi.dto.CreateSensorDto;
import com.kuznetsov.sensorApi.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CreateSensorDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateSensorDto createSensorDto = (CreateSensorDto) target;

        if(sensorService.findAll().stream().anyMatch(s->s.getName().equals(createSensorDto.getName()))){
           errors.rejectValue("name","","Sensor with this name already exists");
        }
    }
}
