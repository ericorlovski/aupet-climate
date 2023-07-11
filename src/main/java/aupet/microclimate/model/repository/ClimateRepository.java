package aupet.microclimate.model.repository;

import aupet.microclimate.model.entity.Climate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClimateRepository extends JpaRepository<Climate, Long> {

}