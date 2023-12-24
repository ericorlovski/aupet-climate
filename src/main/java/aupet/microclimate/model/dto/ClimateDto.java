package aupet.microclimate.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ClimateDto {
    @NotBlank
    private String place;
}