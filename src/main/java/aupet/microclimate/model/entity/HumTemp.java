package aupet.microclimate.model.entity;

import aupet.microclimate.model.enums.ClimateStatusType;
import aupet.microclimate.model.entity.common.BasicTimedEntityLarge;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "humidity_temperature")
@Getter
@Setter
public class HumTemp extends BasicTimedEntityLarge {
    @Column(name = "place")
    private String place;

    @Column (name = "date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDateTime date;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ClimateStatusType status;

    @Column(name = "humidity")
    private String humidity;

    @Column(name = "temperature")
    private String temperature;

    @Column(name = "active")
    private boolean active = true;
}