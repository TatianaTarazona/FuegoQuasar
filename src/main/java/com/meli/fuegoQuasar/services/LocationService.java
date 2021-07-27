package com.meli.fuegoQuasar.services;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.meli.fuegoQuasar.exceptions.LocationException;
import com.meli.fuegoQuasar.model.SatelliteReport;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static com.meli.fuegoQuasar.constant.SatellitesPosition.*;

@Service
public class LocationService {


    /**
     * Determina la localización del emisor a partir del reporte de los satellites
     * @param satelliteReportList Lista de los satellites y sus respectivos reportes
     * @return Localización del emisor
     * @throws LocationException
     */
    public double[] getLocation(List<SatelliteReport> satelliteReportList) throws LocationException, NoSuchElementException {
        double[] result;
        if (satelliteReportList.isEmpty() || satelliteReportList.size() < values().length) {
            throw new LocationException("No hay suficiente información");
        }
        try {
            float distance_kenobi = satelliteReportList.stream().filter(satellite -> satellite.getName().equalsIgnoreCase(KENOBI.name())).findFirst().get().getDistance();
            float distance_skywalker = satelliteReportList.stream().filter(satellite -> satellite.getName().equalsIgnoreCase(SKYWALKER.name())).findFirst().get().getDistance();
            float distance_sato = satelliteReportList.stream().filter(satellite -> satellite.getName().equalsIgnoreCase(SATO.name())).findFirst().get().getDistance();
            double[] distances = new double[]{distance_kenobi, distance_skywalker, distance_sato};
            double[][] positions = new double[][]{{KENOBI.getX(), KENOBI.getY()}, {SKYWALKER.getX(), SKYWALKER.getY()}, {SATO.getX(), SATO.getY()}};

            TrilaterationFunction trilaterationFunction = new TrilaterationFunction(positions, distances);
            NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(trilaterationFunction,
                    new LevenbergMarquardtOptimizer());

            result = solver.solve().getPoint().toArray();


        } catch (NoSuchElementException e){
            throw new LocationException("Intercepción fallida");
        }

        return result;
    }
}
