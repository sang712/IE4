package com.ssafy.db.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Getter
@Setter
public class UserPoint extends BaseEntity {
    @ManyToOne
    @JoinColumn(name="user_id")
    User user;

    int point;
}
