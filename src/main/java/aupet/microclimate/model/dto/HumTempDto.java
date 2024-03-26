package aupet.microclimate.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class HumTempDto {
    @NotNull
    private double temperature;
    @NotNull
    private double humidity;
    @NotBlank
    private String place;
}