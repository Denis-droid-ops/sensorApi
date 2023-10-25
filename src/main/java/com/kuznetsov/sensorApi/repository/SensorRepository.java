package com.kuznetsov.sensorApi.repository;

import com.kuznetsov.sensorApi.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,Integer> {
}
