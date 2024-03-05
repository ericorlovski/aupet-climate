package aupet.microclimate.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class HumTempDto {
    @NotNull
    private Integer temperature;
    @NotNull
    private Integer humidity;
    @NotBlank
    private String place;
}