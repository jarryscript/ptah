package com.ptah.entity.project;

import com.ptah.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
public class MileStone extends BaseEntity {
    private String name;
    private Instant startTime;
    private Instant endTime;
    private String comments;
    @ManyToOne
    private Project project;
}
