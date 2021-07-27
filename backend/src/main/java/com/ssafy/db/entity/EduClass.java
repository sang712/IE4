package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class EduClass extends BaseEntity{
    int grade;
    int classNo;
    String timetable;
    String classMotto;
    String conferenceUrl;
}
