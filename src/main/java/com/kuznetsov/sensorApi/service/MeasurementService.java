package com.kuznetsov.sensorApi.service;

import com.kuznetsov.sensorApi.dto.CreateMeasurementDto;
import com.kuznetsov.sensorApi.model.Measurement;
import com.kuznetsov.sensorApi.model.Sensor;
import com.kuznetsov.sensorApi.repository.MeasurementRepository;
import com.kuznetsov.sensorApi.repository.SensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final ModelMapper modelMapper;
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementService(ModelMapper modelMapper, MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.modelMapper = modelMapper;
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(CreateMeasurementDto createMeasurementDto){
        Optional<Sensor> sensor = sensorRepository.findAll().stream()
                .filter(s->s.getName().equals(createMeasurementDto.getSensor().getName()))
                .findFirst();
        sensor.ifPresent(createMeasurementDto::setSensor);
        measurementRepository.save(convertFromCreateDto(createMeasurementDto));
    }

    private CreateMeasurementDto convertToCreateDto(Measurement measurement){
        return modelMapper.map(measurement,CreateMeasurementDto.class);
    }

    private Measurement convertFromCreateDto(CreateMeasurementDto createMeasurementDto){
        Measurement measurement = modelMapper.map(createMeasurementDto,Measurement.class);
        setMeasurementInside(measurement);
        return measurement;
    }

    private void setMeasurementInside(Measurement measurement){
        measurement.setCreatingDateTime(LocalDateTime.now());
    }
}
