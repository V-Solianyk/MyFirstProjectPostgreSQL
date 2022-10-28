package com.example.MyFirstProjectPostgreSQL.conroller.footballer;

import com.example.MyFirstProjectPostgreSQL.controller.FootballerController;
import com.example.MyFirstProjectPostgreSQL.dto.FootballerDTO;
import com.example.MyFirstProjectPostgreSQL.service.footballer.FootballerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class FootballerControllerTest {
    FootballerService footballerService = Mockito.mock(FootballerService.class);
    FootballerController footballerController = new FootballerController(footballerService);

    @Test
    void getAll() {
        Mockito.when(footballerService.getAll())
                .thenReturn(List.of(new FootballerDTO(), new FootballerDTO()));

        ResponseEntity<List<FootballerDTO>> response = footballerController.getAll();

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
