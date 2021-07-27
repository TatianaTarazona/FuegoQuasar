package com.meli.fuegoQuasar.controller;

import com.meli.fuegoQuasar.exceptions.LocationException;
import com.meli.fuegoQuasar.exceptions.MessageException;
import com.meli.fuegoQuasar.model.InterceptionRequest;
import com.meli.fuegoQuasar.model.InterceptionResponse;
import com.meli.fuegoQuasar.model.InterceptionSplitRequest;
import com.meli.fuegoQuasar.model.SatelliteReport;
import com.meli.fuegoQuasar.services.FuegoQuasarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/")
public class fuegoQuasarController {


    @Autowired
    private FuegoQuasarService fuegoQuasarService;


    @ApiOperation(value = "/topsecret", notes = "Método POST que devuelve la posición del emisor y el mensaje interceptado a partir de los reportes de los satélites")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operación exitosa", response = InterceptionResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 404, message = "No hay suficiente información", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping(value = "/topsecret", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterceptionResponse> topSecret(@RequestBody InterceptionRequest interceptionRequest) {

        InterceptionResponse interceptionResponse = null;
        if (interceptionRequest == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se recibieron transmisiones");

        if (interceptionRequest.getSatelliteList().isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se recibieron transmisiones");

        try {
            interceptionResponse = fuegoQuasarService.topSecretService(interceptionRequest.getSatelliteList());
        } catch (LocationException | MessageException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

        return ResponseEntity.ok(interceptionResponse);

    }


    @ApiOperation(value = "/topsecret_split/{satellite_name}", notes = "Metodo POST que recibe uno por uno la información reportada de cada satélite")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Transmisión recibida con éxito", response = String.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 404, message = "No hay suficiente información", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @PostMapping(value = "/topsecret_split/{satellite_name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> topSecretSplit(@PathVariable(value = "satellite_name") String satelliteName,
                                                 @RequestBody InterceptionSplitRequest interceptionSplitRequest) {

        SatelliteReport satellite = new SatelliteReport(satelliteName.toUpperCase(), interceptionSplitRequest.getDistance(), interceptionSplitRequest.getMessage());

        fuegoQuasarService.topSecretSplitService(satellite);

        return ResponseEntity.ok("Transmisión recibida con éxito");

    }


    @ApiOperation(value = "/topsecret_split", notes = "Método GET que devuelve la posición del emisor y el mensaje interceptado a partir " +
                          "de los reportes almacenados de los satélites")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Transmisión recibida con éxito", response = InterceptionResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 404, message = "No hay suficiente información", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping(value = "/topsecret_split", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterceptionResponse> topSecretSplit() {

        InterceptionResponse interceptionResponse = null;

        try {
            interceptionResponse = fuegoQuasarService.topSecretSplitService();
        } catch (MessageException | LocationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage(), e);
        }

        return ResponseEntity.ok(interceptionResponse);

    }
}
