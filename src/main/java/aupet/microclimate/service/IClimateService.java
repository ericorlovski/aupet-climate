package aupet.microclimate.service;

import aupet.microclimate.model.dto.ClimateDto;
import aupet.microclimate.web.GenericResponse;

public interface IClimateService {
    GenericResponse<String> pushClimateTable(ClimateDto dto);
}
