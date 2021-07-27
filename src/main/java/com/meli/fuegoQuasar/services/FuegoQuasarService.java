package com.meli.fuegoQuasar.services;

import com.meli.fuegoQuasar.exceptions.LocationException;
import com.meli.fuegoQuasar.exceptions.MessageException;
import com.meli.fuegoQuasar.model.InterceptionResponse;
import com.meli.fuegoQuasar.model.SatelliteReport;

import java.util.List;

public interface FuegoQuasarService {

    InterceptionResponse topSecretService (List<SatelliteReport> satellites) throws LocationException, MessageException;
    void topSecretSplitService (SatelliteReport satellite);
    InterceptionResponse topSecretSplitService() throws LocationException, MessageException;
}
