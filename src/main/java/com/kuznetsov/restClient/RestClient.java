package com.kuznetsov.restClient;

import com.kuznetsov.sensorApi.dto.ReadMeasurementDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class RestClient {
    private static final String SENSOR_REGISTRATION_URL = "http://localhost:9090/sensors/registration";
    private static final String ADD_MEASUREMENTS_URL = "http://localhost:9090/measurements/add";
    private static final String GET_MEASUREMENTS_URL = "http://localhost:9090/measurements";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        //new sensor registration
        Map<String,Object> sensorJson = new HashMap<>();
        sensorJson.put("name","My test sensor3");
        restTemplate
               .postForObject(SENSOR_REGISTRATION_URL,sensorJson, String.class);

        //new measurements adding
        Map<String,Object> newMeasurementJson = new HashMap<>();
        for(int i = 0;i<=1000;i++){
            Random random =new Random();
            int value = random.nextInt(200)-100;
            boolean raining = random.nextBoolean();
            newMeasurementJson.put("value",value);
            newMeasurementJson.put("raining",raining);
            newMeasurementJson.put("sensor",sensorJson);
            restTemplate
                    .postForObject(ADD_MEASUREMENTS_URL,newMeasurementJson,String.class);
        }

        //getting all measurements
        ResponseEntity<ReadMeasurementDto[]> response =
                restTemplate.getForEntity(
                        GET_MEASUREMENTS_URL,
                        ReadMeasurementDto[].class);
        ReadMeasurementDto[] readMeasurementDtos = response.getBody();
        System.out.println("All measurements: ");
        Arrays.stream(readMeasurementDtos).forEach(System.out::println);


    }

}
