package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class UserConference extends BaseEntity {
    int conferenceId;
    int userId;
}
