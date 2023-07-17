package aupet.microclimate.service.implementation;

import aupet.microclimate.model.dto.ClimateDto;
import aupet.microclimate.model.entity.Climate;
import aupet.microclimate.model.repository.ClimateRepository;
import aupet.microclimate.service.IClimateService;
import aupet.microclimate.web.GenericResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Log4j2
public class ClimateService implements IClimateService {

    private final ClimateRepository climateRepository;

    public GenericResponse<String> pushClimateTable(ClimateDto dto) {

        val climate = new Climate();

        climate.setPlace(dto.getPlace());
        climate.setDate(LocalDateTime.now());
        climate.setStatus("COOL");

        climateRepository.save(climate);

        try {
            return new GenericResponse<>("Climate control has been successfully entered into the database");
        } catch (Exception e) {
            return GenericResponse.error(1, e.getMessage());
        }
    }
}
