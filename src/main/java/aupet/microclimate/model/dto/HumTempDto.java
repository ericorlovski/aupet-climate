package aupet.microclimate.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class HumTempDto {
    @NotBlank
    private Integer temperature;
    @NotBlank
    private Integer humidity;
    @NotBlank
    private String place;
}