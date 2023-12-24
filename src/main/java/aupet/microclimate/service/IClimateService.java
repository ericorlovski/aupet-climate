package aupet.microclimate.service;

import aupet.microclimate.model.dto.HumTempDto;
import aupet.microclimate.web.GenericResponse;

public interface IClimateService {
    GenericResponse<Boolean> pushHumTemp(HumTempDto dto);
}
