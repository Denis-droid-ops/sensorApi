package com.kuznetsov.sensorApi.controller;

import com.kuznetsov.sensorApi.dto.CreateMeasurementDto;
import com.kuznetsov.sensorApi.dto.ReadMeasurementDto;
import com.kuznetsov.sensorApi.exception.MeasurementValidationException;
import com.kuznetsov.sensorApi.service.MeasurementService;
import com.kuznetsov.sensorApi.util.MeasurementErrorResponse;
import com.kuznetsov.sensorApi.util.MeasurementValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid CreateMeasurementDto createMeasurementDto
                               , BindingResult bindingResult){
        measurementValidator.validate(createMeasurementDto,bindingResult);
        if(bindingResult.hasErrors()){
           StringBuilder sbErrMsg = new StringBuilder();
           List<FieldError> errorList = bindingResult.getFieldErrors();
           errorList.forEach(er->sbErrMsg.append(er.getField()).append(" - ").append(er.getDefaultMessage()+"; "));
           throw new MeasurementValidationException(sbErrMsg.toString());
        }
        measurementService.save(createMeasurementDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<ReadMeasurementDto> findAllMeasurements(){
        return measurementService.findAll();
    }

    @GetMapping("/rainyDaysCount")
    public Integer rainyDaysCount(){
        return measurementService.rainyDaysCount();
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handleException(MeasurementValidationException ex){
        MeasurementErrorResponse measurementErrorResponse =
                new MeasurementErrorResponse(ex.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(measurementErrorResponse,HttpStatus.BAD_REQUEST);
    }
}
