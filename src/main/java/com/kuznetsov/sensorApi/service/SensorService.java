package com.kuznetsov.sensorApi.service;

import com.kuznetsov.sensorApi.dto.CreateSensorDto;
import com.kuznetsov.sensorApi.dto.ReadSensorDto;
import com.kuznetsov.sensorApi.exception.SensorNotFoundException;
import com.kuznetsov.sensorApi.model.Sensor;
import com.kuznetsov.sensorApi.repository.SensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final ModelMapper modelMapper;
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(ModelMapper modelMapper, SensorRepository sensorRepository) {
        this.modelMapper = modelMapper;
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(CreateSensorDto createSensorDto){
        sensorRepository.save(convertFromCreateDto(createSensorDto));
    }

    public Optional<ReadSensorDto> findById(Integer id){
        return Optional.of(
                convertToReadDto(sensorRepository
                        .findById(id)
                        .orElseThrow(()->new SensorNotFoundException("Sensor with this id is not found")))
               );
    }

    public List<ReadSensorDto> findAll(){
        return sensorRepository.findAll().stream()
                .map(this::convertToReadDto)
                .collect(Collectors.toList());
    }

    private Sensor convertFromCreateDto(CreateSensorDto createSensorDto){
        return modelMapper.map(createSensorDto,Sensor.class);
    }

    private Sensor convertFromReadDto(ReadSensorDto readSensorDto){
        return modelMapper.map(readSensorDto,Sensor.class);
    }

    private CreateSensorDto convertToCreateDto(Sensor sensor){
        return modelMapper.map(sensor,CreateSensorDto.class);
    }
    private ReadSensorDto convertToReadDto(Sensor sensor){
        return modelMapper.map(sensor,ReadSensorDto.class);
    }
}
