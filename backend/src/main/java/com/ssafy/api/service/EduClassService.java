package com.ssafy.api.service;


import com.ssafy.api.response.EduClassMem;
import com.ssafy.db.entity.EduClass;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface EduClassService {
    EduClass getEduClassByEduClassId(int eduClassId);
    EduClass setConferenceActiveByEduClassId(int eduClassId, String conferenceActive);
    String updateTimetable(int eduClassId, MultipartHttpServletRequest request);
    List<String> getRank(int classId);
    List<EduClassMem> getEduClassMem(int classId);
}
