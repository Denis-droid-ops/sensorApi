package com.kuznetsov.sensorApi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Value should not be empty")
    @Size(min = -100,max = 100,message = "Value should be between -100 and 100 numbers")
    private Float value;
    @NotEmpty(message = "Raining should not be empty")
    private Boolean raining;
    @Column(name = "creating_date_time")
    private LocalDateTime creatingDateTime;
    @ManyToOne()
    @NotEmpty(message = "Sensor should not be empty")
    private Sensor sensor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public LocalDateTime getCreatingDateTime() {
        return creatingDateTime;
    }

    public void setCreatingDateTime(LocalDateTime creatingDateTime) {
        this.creatingDateTime = creatingDateTime;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        sensor.getMeasurements().add(this);
        this.sensor = sensor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Measurement)) return false;
        Measurement that = (Measurement) o;
        return Objects.equals(id, that.id) && Objects.equals(value, that.value) && Objects.equals(raining, that.raining) && Objects.equals(creatingDateTime, that.creatingDateTime) && Objects.equals(sensor, that.sensor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, raining, creatingDateTime, sensor);
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", value=" + value +
                ", raining=" + raining +
                ", creatingDateTime=" + creatingDateTime +
                '}';
    }
}
