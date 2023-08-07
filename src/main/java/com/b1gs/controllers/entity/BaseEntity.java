package com.b1gs.controllers.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "create_dt")
    private LocalDateTime createDate;

    @Column(name = "update_dt")
    private LocalDateTime updateDate;

    @PrePersist
    public void prePersist() {
        createDate = LocalDateTime.now();
        updateDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = LocalDateTime.now();
    }

}
