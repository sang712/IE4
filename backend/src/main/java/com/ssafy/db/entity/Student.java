package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
public class Student extends BaseEntity{
    @OneToOne
    @JoinColumn(name="user_id")
    User user;

    int snum;
    String parentPhone;
    String passwordQuestion;
    String passwordAnswer;

}
