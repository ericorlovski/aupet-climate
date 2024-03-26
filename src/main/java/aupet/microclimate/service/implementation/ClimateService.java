package aupet.microclimate.service.implementation;

import aupet.microclimate.model.enums.ClimateStatusType;
import aupet.microclimate.model.dto.HumTempDto;
import aupet.microclimate.model.entity.HumTemp;
import aupet.microclimate.model.enums.StandartCode;
import aupet.microclimate.model.enums.UniversityAuditorium;
import aupet.microclimate.model.repository.HumTempRepository;
import aupet.microclimate.service.IClimateService;
import aupet.microclimate.web.GenericResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
public class ClimateService implements IClimateService {

    private final HumTempRepository humTempRepository;

    @Value("${service.google.sheet}")
    private String googleSheet;

    @Value("${service.google.url}")
    private String googleUrl;

    @Override
    public GenericResponse<Boolean> pushHumTemp(HumTempDto dto) {
        return new GenericResponse<>(pushClimateTable(dto.getPlace(), dto.getTemperature(), dto.getHumidity()));
    }

    private Boolean pushClimateTable(String place, double temperature, double humidity) {

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

    @Override
    public void readDataFromGoogleSheet() {

        int countA = humTempRepository.getClimateCount();
        String range = String.format("A%d:B%d", countA, countA);

        String gid = "0";
        String url = String.format(googleUrl, googleSheet, gid, range);

        try (CSVParser parser = CSVParser.parse(new URL(url), StandardCharsets.UTF_8, CSVFormat.DEFAULT)) {
            for (CSVRecord csvRecord : parser) {
                double temperature = parseDouble(csvRecord.get(0));
                double humidity = parseDouble(csvRecord.get(1));

                pushClimateTable(UniversityAuditorium.B_248_AUD.getDesc(), temperature, humidity);
            }
        } catch (Exception e) {
            log.error("Ошибка при чтении данных из Google Таблицы: " + e.getMessage());
        }
    }

    private double parseDouble(String value) {
        return Double.parseDouble(value.replace(",", "."));
    }
}
