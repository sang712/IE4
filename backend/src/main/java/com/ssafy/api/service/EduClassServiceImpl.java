package com.ssafy.api.service;

import com.ssafy.api.response.EduClassMem;
import com.ssafy.db.entity.EduClass;
import com.ssafy.db.repository.EduClassRepository;
import com.ssafy.db.repository.UserPointRepository;
import com.ssafy.db.repository.UserRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service("myClassService")
public class EduClassServiceImpl implements EduClassService {
    @Autowired
    EduClassRepository eduClassRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserPointRepository userPointRepository;

    @Override
    public EduClass getEduClassByEduClassId(int eduClassId) {

        EduClass c = new EduClass();
        c = eduClassRepository.findEduClassById(eduClassId).orElse(null);

        return c;
    }

    @Override
    public EduClass setConferenceActiveByEduClassId(int eduClassId, String conferenceActive){
        EduClass c = eduClassRepository.findEduClassById(eduClassId).orElse(null);
        c.setConferenceActive(conferenceActive);

        return eduClassRepository.save(c);
    }

    @Override
    public String updateTimetable(int eduClassId, MultipartHttpServletRequest request) {

        String uploadFolder = "timetable";
        String uploadPath = "/home/ubuntu/ie4_upload";
        String saveUrl = "";

        try {
            MultipartFile file = request.getFile("file");

            File uploadDir = new File(uploadPath + File.separator + uploadFolder);
            if(!uploadDir.exists()) uploadDir.mkdir();

            String fileUrl = eduClassRepository.findByEduClassId(eduClassId).orElse(null);

            if(fileUrl != null) {
                File deleteFile = new File(uploadPath + File.separator, fileUrl);
                if(deleteFile.exists()) deleteFile.delete();
            }

            String fileName = file.getOriginalFilename();

            // Random File Id
            UUID uuid = UUID.randomUUID();

            // file extension
            String extension = FilenameUtils.getExtension(fileName);
            String savingFileName = uuid + "." + extension;
            File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);

            file.transferTo(destFile);

            EduClass c = eduClassRepository.findEduClassById(eduClassId).get();
            saveUrl = "ie4_upload/" + uploadFolder + "/" + savingFileName;
            c.setTimetable(saveUrl);
            eduClassRepository.save(c);

        } catch(IOException e) {
            e.printStackTrace();

        }

        return saveUrl;
    }

    @Override
    public List<EduClassMem> getEduClassMem(int classId) {
        List<EduClassMem> list = new LinkedList<>();

        list.addAll(userRepository.findByClassIdAndPositionOrderByName(classId, "교사").orElse(null));
        list.addAll(userRepository.findByClassIdAndPositionOrderByName(classId, "학생").orElse(null));

        return list;
    }


    @Override
    public List<String> getRank(int classId) {

        List<String> list = userPointRepository.findAllBy(classId).orElse(null);

        return list;
    }




}
