package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Conference extends BaseEntity {
    int ownerId;
    int conferenceCategory;
    String callStartTime;
    String callEndTime;
    String thumbnailUrl;
    String title;
    String description;
    int is_active;

    @PrePersist
    public void prePersist() {
        this.callEndTime = LocalDateTime.now().toString();
    }

    @PreUpdate
    public void preUpdate() {
        this.callEndTime = LocalDateTime.now().toString();
    }
}
