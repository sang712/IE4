package com.ssafy.db.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserPoint extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    User user;

    int point;
}
