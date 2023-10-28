package com.kuznetsov.sensorApi.repository;

import com.kuznetsov.sensorApi.dto.ReadMeasurementDto;
import com.kuznetsov.sensorApi.model.Measurement;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    @EntityGraph(value = "WithSensor")
    List<Measurement> findAll();
}
