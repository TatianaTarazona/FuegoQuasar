package com.meli.fuegoQuasar.controller;

import com.meli.fuegoQuasar.exceptions.LocationException;
import com.meli.fuegoQuasar.exceptions.MessageException;
import com.meli.fuegoQuasar.model.*;
import com.meli.fuegoQuasar.services.FuegoQuasarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestController
@RequestMapping("/")
public class fuegoQuasarController {

    @Autowired
    private FuegoQuasarService  fuegoQuasarService;


    @GetMapping (value = "/welcome")
    public String welcome(){

        return "Se deslplegó exitosamente";
    }

    @PostMapping(value = "/topsecret", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterceptionResponse> topSecret(@RequestBody InterceptionRequest interceptionRequest ){

        InterceptionResponse interceptionResponse = null;
        if (interceptionRequest == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se recibieron transmisiones");

        if (interceptionRequest.getSatelliteList().isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se recibieron transmisiones");

        try {
            interceptionResponse = fuegoQuasarService.topSecretService(interceptionRequest.getSatelliteList());
        } catch (LocationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (MessageException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }


        return ResponseEntity.ok(interceptionResponse);

    }

    @PostMapping (value = "/topsecret_split/{satellite_name}", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<String> topSecretSplit(@PathVariable (value = "satellite_name") String satelliteName,
                                                               @RequestBody InterceptionSplitRequest interceptionSplitRequest){



        SatelliteReport satellite = new SatelliteReport(satelliteName.toUpperCase() , interceptionSplitRequest.getDistance(), interceptionSplitRequest.getMessage());


        fuegoQuasarService.topSecretSplitService(satellite);



        return ResponseEntity.ok("Transmisión recibida con éxito");

    }

    @GetMapping (value = "/topsecret_split",  produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<InterceptionResponse> topSecretSplit(){

        InterceptionResponse interceptionResponse = null;

        try {
            interceptionResponse = fuegoQuasarService.topSecretSplitService();
        } catch (MessageException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage(), e);
        } catch (LocationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage(), e);
        }

        return ResponseEntity.ok(interceptionResponse);

    }
}
