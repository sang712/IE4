package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"id"})
public class EduClass extends BaseEntity{
    int grade;
    int classNo;
    String timetable;
    String classMotto;
    String conferenceUrl;


}
