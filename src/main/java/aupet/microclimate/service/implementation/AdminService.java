package aupet.microclimate.service.implementation;

import aupet.microclimate.model.entity.HumTemp;
import aupet.microclimate.model.repository.HumTempRepository;
import aupet.microclimate.service.IAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class AdminService implements IAdminService {

    private final HumTempRepository humTempRepository;

    @Override
    public List<HumTemp> getAllHunTempActive() {
        return humTempRepository.getAllByActive(true);
    }

    @Override
    public void toggleClimateActivity(Long id) {
        val cat = humTempRepository.findById(id);
        cat.ifPresent(food -> food.setActive(!food.isActive()));
    }
}
