package com.meli.fuegoQuasar;

import com.meli.fuegoQuasar.exceptions.LocationException;
import com.meli.fuegoQuasar.model.SatelliteReport;
import com.meli.fuegoQuasar.services.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.meli.fuegoQuasar.constant.SatellitesPosition.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LocationServiceTest {

    @Autowired
    private LocationService locationService;

    @Test
    public void getLocationOK() {
         List<SatelliteReport> satellites = new ArrayList<SatelliteReport>();
        satellites.add(new SatelliteReport(KENOBI.name().toString(), (float) 100.0));
        satellites.add(new SatelliteReport(SKYWALKER.name().toString(), (float) 115.5));
        satellites.add(new SatelliteReport(SATO.name().toString(), (float)142.7));

        double[] expectedPosition = new double[]{-58.31524858154101, -69.55141718410351};
        double[] calculatedPosition = new double[0];
        try {
            calculatedPosition = locationService.getLocation(satellites);
        } catch (LocationException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < calculatedPosition.length; i++) {
            assertEquals(expectedPosition[i], calculatedPosition[i]);
        }

    }

    @Test
    public void insufficientInformationLocation() throws Exception {
        List<SatelliteReport> satellites = new ArrayList<SatelliteReport>();
        satellites.add(new SatelliteReport(KENOBI.name().toString(), (float) 100.0));
        satellites.add(new SatelliteReport(SATO.name().toString(), (float) 142.7));

        double[] calculatedPosition = new double[0];
        String expected = "No hay suficiente información";
        try {
            calculatedPosition = locationService.getLocation(satellites);
        } catch (LocationException e) {
            assertEquals(expected, e.getMessage());
        }

    }


}
