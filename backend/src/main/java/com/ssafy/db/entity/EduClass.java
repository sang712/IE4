package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DynamicUpdate
@JsonIgnoreProperties({"id"})
public class EduClass extends BaseEntity{
    int grade;
    int classNo;
    String timetable;
    String classMotto;
    String conferenceUrl;


}
