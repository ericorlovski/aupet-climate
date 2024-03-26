package aupet.microclimate.service;

import aupet.microclimate.model.dto.HumTempDto;
import aupet.microclimate.web.GenericResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface IClimateService {
    GenericResponse<Boolean> pushHumTemp(HumTempDto dto);
    void readDataFromGoogleSheet();
}
