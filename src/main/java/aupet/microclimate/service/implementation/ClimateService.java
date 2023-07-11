package aupet.microclimate.service.implementation;

import aupet.microclimate.model.dto.ClimateDto;
import aupet.microclimate.model.entity.Climate;
import aupet.microclimate.model.repository.ClimateRepository;
import aupet.microclimate.service.IClimateService;
import aupet.microclimate.web.GenericResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class ClimateService implements IClimateService {

    private final ClimateRepository climateRepository;

    public GenericResponse<String> pushClimateTable(ClimateDto dto) { //Метод занесения нового заказа от Тикетона

        val climate = new Climate();

        climate.setPlace(dto.getPlace());
        climate.setDate(LocalDateTime.now());
        climate.setStatus("COOL");

        climateRepository.save(climate);

        try {
            return new GenericResponse<>("done");
        } catch (Exception e) {
            return GenericResponse.error(1, e.getMessage());
        }
    }
}
