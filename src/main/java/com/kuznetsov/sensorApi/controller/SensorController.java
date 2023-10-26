package com.kuznetsov.sensorApi.controller;

import com.kuznetsov.sensorApi.dto.CreateSensorDto;
import com.kuznetsov.sensorApi.exception.SensorValidationException;
import com.kuznetsov.sensorApi.service.SensorService;
import com.kuznetsov.sensorApi.util.SensorErrorResponse;
import com.kuznetsov.sensorApi.util.SensorValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorValidator sensorValidator;
    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorValidator sensorValidator, SensorService sensorService) {
        this.sensorValidator = sensorValidator;
        this.sensorService = sensorService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid CreateSensorDto createSensorDto
                                                   , BindingResult bindingResult){
        sensorValidator.validate(createSensorDto,bindingResult);
        if(bindingResult.hasErrors()){
           StringBuilder sbErrMsg = new StringBuilder();
           List<FieldError> errorList = bindingResult.getFieldErrors();
           errorList.forEach(err-> sbErrMsg.append(err.getField()).append(" - ").append(err.getDefaultMessage()).append("; "));
           throw new SensorValidationException(sbErrMsg.toString());
        }
        sensorService.save(createSensorDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handleException(SensorValidationException ex){
        SensorErrorResponse sensorErrorResponse =
                new SensorErrorResponse(ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(sensorErrorResponse,HttpStatus.BAD_REQUEST);
    }
}
