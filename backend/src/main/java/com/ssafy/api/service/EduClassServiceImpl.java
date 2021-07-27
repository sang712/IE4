package com.ssafy.api.service;

import com.ssafy.db.entity.EduClass;
import com.ssafy.db.repository.EduClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("myClassService")
public class EduClassServiceImpl implements EduClassService {
    @Autowired
    EduClassRepository eduClassRepository;

    @Override
    public EduClass getEduClassByEduClassId(int eduClassId) {

        EduClass c = new EduClass();
        c = eduClassRepository.findEduClassById(eduClassId).orElse(null);

        return c;

//        return eduClassRepository.findEduClassById(eduClassId).orElse(null);

    }

//    @Override
//    public int updateTimetable(int classId) {
//        return 0;
//    }
//
//    @Override
//    public List<String> getRank(int classId) {
//        return null;
//    }
//
//    @Override
//    public List<?> getClassMem(int classId) {
//        return null;
//    }
}
