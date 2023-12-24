package aupet.microclimate.model.repository;

import aupet.microclimate.model.entity.HumTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HumTempRepository extends JpaRepository<HumTemp, Long> {

    @Query(
            value = "SELECT * FROM climate c",
            nativeQuery = true)
    List<HumTemp> getAllClimateList();
}