package com.kuznetsov.sensorApi.dto;

import com.kuznetsov.sensorApi.model.Sensor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor //Jackson use by default behavior, to use only all args constructor(without lombok) use @JsonCreator
public final class CreateMeasurementDto {

    @Range(min = -100,max = 100,message = "Value should be between -100 and 100 numbers")
    @NotNull(message = "Value should not be null")
    private Float value;

    @NotNull(message = "Raining should not be null")
    private Boolean raining;

    @NotNull(message = "Sensor should not be null")
    private Sensor sensor;


}
