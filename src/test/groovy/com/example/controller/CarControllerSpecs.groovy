package com.example.controller

import com.example.model.Car
import com.example.service.CarServiceTest
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class CarControllerSpecs extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    @MockBean
    CarServiceTest carService

    def "Get Car Details by name"(){
        given:
        carService.getCarDetails(Mockito.anyString())>>new Car("Scala","Sadan")

        when:
        def response = restTemplate.getForEntity('/cars/Scala', Car)

        then:
        response.statusCode == HttpStatus.OK
        response.body!=null
    }
}