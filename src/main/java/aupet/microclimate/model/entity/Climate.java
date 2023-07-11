package aupet.microclimate.model.entity;

import aupet.microclimate.model.entity.common.BasicTimedEntityLarge;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "climate")
@Getter
@Setter
public class Climate extends BasicTimedEntityLarge {
    @Column(name = "place")
    private String place;

    @Column (name = "date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDateTime date;

    @Column(name = "status")
    private String status;

    @Column(name = "active")
    private boolean active = true;
}