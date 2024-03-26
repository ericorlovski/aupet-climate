package aupet.microclimate.model.repository;

import aupet.microclimate.model.entity.HumTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HumTempRepository extends JpaRepository<HumTemp, Long> {

    @Query("SELECT COUNT(c) FROM HumTemp c")
    Integer getClimateCount();

    List<HumTemp> getAllByActive(boolean active);
}