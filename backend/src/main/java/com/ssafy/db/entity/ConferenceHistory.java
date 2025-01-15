package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ConferenceHistory extends BaseEntity {
    int conferenceId;
    int userId;
    int action;
    String insertedTime;

    @PrePersist
    public void prePersist() {
        this.insertedTime = LocalDateTime.now().toString();
    }
}
