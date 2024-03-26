package aupet.microclimate.job;

import aupet.microclimate.service.IClimateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Log4j2
public class ReadDataFromGoogleSheetJob {

    private final IClimateService climateService;

    @Scheduled(cron = "1 * * * * *")
    //@Scheduled(cron = "0 0 * * * *")
    @Transactional(rollbackFor = Exception.class)
    public void updateNotStartedTestJob() {
        climateService.readDataFromGoogleSheet();
    }
}