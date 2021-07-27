package com.meli.fuegoQuasar.services;

import com.meli.fuegoQuasar.constant.SatellitesPosition;
import com.meli.fuegoQuasar.exceptions.LocationException;
import com.meli.fuegoQuasar.exceptions.MessageException;
import com.meli.fuegoQuasar.model.InterceptionResponse;
import com.meli.fuegoQuasar.model.Position;
import com.meli.fuegoQuasar.model.SatelliteReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FuegoQuasarServiceImpl implements FuegoQuasarService {

    @Autowired
    LocationService locationService;

    @Autowired
    MessageService messageService;

    private List<SatelliteReport> satellites = new ArrayList<>();
    private List<String> satellitesName;

    public List<SatelliteReport> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<SatelliteReport> satellites) {
        this.satellites = satellites;
    }

    public FuegoQuasarServiceImpl() {

        satellitesName = Stream.of(SatellitesPosition.values()).map(Enum::name).collect(Collectors.toList());
    }

    /**
     * Procesa los reportes de los satélites y calcula la localización del emisor y el mensaje interceptado
     * @param satellites Lista de los satéllites y sus respectivos reportes
     * @return Localización del emisor y mensaje interceptado
     * @throws LocationException
     * @throws MessageException
     */
    @Override
    public InterceptionResponse topSecretService(List<SatelliteReport> satellites) throws LocationException, MessageException {

        double[] points = locationService.getLocation(satellites);
        Position position = new Position(points);
        List<String[]> messages = new ArrayList<String[]>();

        satellites = satellites.stream().filter(s -> s.getMessage().length > 0 ).collect(Collectors.toList()) ;

        for (SatelliteReport s : satellites) {
            messages.add(s.getMessage());
        }



        String message = messageService.getMessage(messages);

        return new InterceptionResponse(position, message);

    }

    /**
     * Recibe los reportes de cada uno de los satélites y los almacena, si ya existe reporte para un satélite lo
     * sobreescribe con el reporte más reciente
     * @param satellite Reporte de cada satéllite
     */
    @Override
    public void topSecretSplitService(SatelliteReport satellite) {

        if (satellitesName.contains(satellite.getName().toUpperCase())) {
            satellites.removeIf(s -> s.getName().equals(satellite.getName().toUpperCase()));

            satellites.add(satellite);
        }
    }

    /**
     * Procesa los reportes almacenados de cada uno de los satélites y calcula la localización del emisor y el mensaje interceptado
     * @return Localización del emisor y mensaje interceptado
     * @throws LocationException
     * @throws MessageException
     */
    @Override
    public InterceptionResponse topSecretSplitService() throws LocationException, MessageException {

        List<String[]> messages = new ArrayList<String[]>();
        double[] points = locationService.getLocation(satellites);
        Position position = new Position(points);

        for (SatelliteReport s : satellites) {
            messages.add(s.getMessage());
        }

        String message = messageService.getMessage(messages);

        return new InterceptionResponse(position, message);
    }
}
