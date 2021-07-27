package com.ssafy.api.service;

import com.ssafy.db.entity.EduClass;

import java.util.List;

public interface EduClassService {
    EduClass getEduClassByEduClassId(int eduClassId);
//    int updateTimetable(int classId);
//    List<String> getRank(int classId);
//    List<?> getClassMem(int classId);
}
