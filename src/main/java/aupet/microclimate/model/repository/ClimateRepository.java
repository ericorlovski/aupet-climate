package aupet.microclimate.model.repository;

import aupet.microclimate.model.entity.Climate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClimateRepository extends JpaRepository<Climate, Long> {

    @Query(
            value = "SELECT * FROM climate c",
            nativeQuery = true)
    List<Climate> getAllClimateList();
}