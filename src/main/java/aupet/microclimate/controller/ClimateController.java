package aupet.microclimate.controller;

import aupet.microclimate.model.dto.ClimateDto;
import aupet.microclimate.model.dto.HumTempDto;
import aupet.microclimate.service.IClimateService;
import aupet.microclimate.web.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/aupet-climate")
@RequiredArgsConstructor
public class ClimateController {

    private final IClimateService climateService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Operation(
            summary = "Endpoint for saving climate details",
            description = "Retrieving and saving climate details for the AUPET application"
    )
    @RequestMapping(value = "/set-hum-temp-details", method = RequestMethod.POST)
    public GenericResponse<Boolean> climateDetails(@RequestBody @Valid HumTempDto dto) {

        try {
            return climateService.pushHumTemp(dto);
        } catch (Exception exc) {
            return GenericResponse.error(1, exc.getMessage());
        }
    }
}