package com.ssafy.api.service;

import com.ssafy.api.response.EduClassMem;
import com.ssafy.db.entity.EduClass;
import com.ssafy.db.entity.UserPoint;
import com.ssafy.db.repository.EduClassRepository;
import com.ssafy.db.repository.UserPointRepository;
import com.ssafy.db.repository.UserRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.awt.print.Pageable;
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

//        return eduClassRepository.findEduClassById(eduClassId).orElse(null);

    }

    @Override
    public String updateTimetable(int eduClassId, MultipartHttpServletRequest request) {

        String uploadFolder = "timetable";
//        String uploadPath = request.getServletContext().getRealPath("/");       // uploadPath가 실행될때마다 계속 새로 생성되는 곳으로 바뀌기 때문에 서버에 올리기 전에 path 수정필요!
        String uploadPath = "C:" + File.separator + "Users" + File.separator + "multicampus"
                + File.separator + "Documents"
                + File.separator + "dev"
                + File.separator + "S05P13A601"
                + File.separator + "backend"
                + File.separator + "src"
                + File.separator + "main"
                + File.separator + "resources"
                + File.separator + "static";
        String saveUrl = "";

        try {
            MultipartFile file = request.getFile("file");

            File uploadDir = new File(uploadPath + File.separator + uploadFolder);
            if(!uploadDir.exists()) uploadDir.mkdir();

            String fileUrl = eduClassRepository.findByEduClassId(eduClassId).orElse(null);

            // fileUrl이 null이 아니라면 (이미 시간표 이미지가 업로드 되어 있다면) 기존의 이미지 삭제 후 업로드
            // fileUrl이 null이라면 그냥 바로 업로드 ㄱㄱ
            if(fileUrl != null) {
                File deleteFile = new File(uploadPath + File.separator, fileUrl);       // fileUrl <- 지울 파일의 url 가져오기 respository!!
                if(deleteFile.exists()) deleteFile.delete();
            }

            //
            String fileName = file.getOriginalFilename();

            // Random File Id
            UUID uuid = UUID.randomUUID();

            // file extension
            String extension = FilenameUtils.getExtension(fileName);
            String savingFileName = uuid + "." + extension;
            File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);

            System.out.println("파일 경로!!!>>> " + destFile);

            file.transferTo(destFile);

            EduClass c = eduClassRepository.findEduClassById(eduClassId).get();
            saveUrl = uploadFolder + "/" + savingFileName;
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

//        System.out.println("list 출력!!!!!>>>>>>> ");
//        for(int i=0; i<list.size(); i++) {
//            System.out.println(list.get(i).getName());
//        }

        return list;
    }


    @Override
    public List<String> getRank(int classId) {
//        PageRequest limitThree = PageRequest.of(0, 3);
//        List<String> list = userPointRepository.findAllBy(classId, limitThree).orElse(null);

        List<String> list = userPointRepository.findAllBy(classId).orElse(null);

//        System.out.println("Query 결과 리스트!!!! >>>>>>>>>>>>> ");
//        for(int i=0; i<list.size(); i++) {
//            System.out.println(list.get(i));
//        }

        return list;
    }

}
