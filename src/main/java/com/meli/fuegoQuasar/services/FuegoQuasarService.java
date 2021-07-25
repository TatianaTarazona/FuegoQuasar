package com.meli.fuegoQuasar.services;

import com.meli.fuegoQuasar.exceptions.LocationException;
import com.meli.fuegoQuasar.exceptions.MessageException;
import com.meli.fuegoQuasar.model.InterceptionRequest;
import com.meli.fuegoQuasar.model.InterceptionResponse;
import com.meli.fuegoQuasar.model.SatelliteReport;
import org.springframework.http.RequestEntity;

import java.util.List;

public interface FuegoQuasarService {

    public InterceptionResponse topSecretService (List<SatelliteReport> satellites) throws LocationException, MessageException;
    public void topSecretSplitService (SatelliteReport satellite);
    public InterceptionResponse topSecretSplitService() throws LocationException, MessageException;
}
