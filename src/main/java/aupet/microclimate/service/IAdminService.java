package aupet.microclimate.service;

import aupet.microclimate.model.entity.HumTemp;

import java.util.List;

public interface IAdminService {

    List<HumTemp> getAllHunTempActive();
    void toggleClimateActivity(Long id);
}
