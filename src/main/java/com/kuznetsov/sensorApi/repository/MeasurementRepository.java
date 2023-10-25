package com.kuznetsov.sensorApi.repository;

import com.kuznetsov.sensorApi.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
}
