package aupet.microclimate.service.implementation;

import aupet.microclimate.model.enums.ClimateStatusType;
import aupet.microclimate.model.dto.HumTempDto;
import aupet.microclimate.model.entity.HumTemp;
import aupet.microclimate.model.enums.StandartCode;
import aupet.microclimate.model.repository.HumTempRepository;
import aupet.microclimate.service.IClimateService;
import aupet.microclimate.web.GenericResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
public class ClimateService implements IClimateService {

    private final HumTempRepository humTempRepository;

    @Override
    public GenericResponse<Boolean> pushHumTemp(HumTempDto dto) {

        return new GenericResponse<>(pushClimateTable(dto.getPlace(), dto.getTemperature(), dto.getHumidity()));
    }

    private Boolean pushClimateTable(String place, Integer temperature, Integer humidity) {

        val humTemp = new HumTemp();

        humTemp.setPlace(place);
        humTemp.setDate(LocalDateTime.now());
        humTemp.setHumidity(String.valueOf(humidity));
        humTemp.setTemperature(String.valueOf(temperature));
        humTemp.setStatus(ClimateStatusType.AVERAGE);

        if (temperature > StandartCode.TEMPERATURE_STAND.getCode() && humidity > StandartCode.HUMIDITY_STAND.getCode()) {
            humTemp.setStatus(ClimateStatusType.HIGH);
        } else if (temperature < StandartCode.TEMPERATURE_STAND.getCode() && humidity < StandartCode.HUMIDITY_STAND.getCode()) {
            humTemp.setStatus(ClimateStatusType.LOW);
        }

        log.info("Humidity data: {}, temperature data: {}, in place: {}", humidity, temperature, place);

        humTempRepository.save(humTemp);
        return true;
    }
}
