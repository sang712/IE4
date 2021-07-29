package com.ssafy.api.controller;

import com.ssafy.api.response.EduClassMem;
import com.ssafy.api.response.EduClassMemListRes;
import com.ssafy.api.service.EduClassService;
import com.ssafy.db.entity.EduClass;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "반 정보 API", tags = {"Class"})
@RestController
@RequestMapping("/class")
public class EduClassController {

    @Autowired
    EduClassService eduClassService;

    @GetMapping("/{classId}")
    @ApiOperation(value = "반 정보 조회", notes = "학년, 반, 시간표, 급훈, 화상회의 url 주소를 가져온다.")
    public ResponseEntity<EduClass> getEduClassInfo(@PathVariable int classId) {
        EduClass c = eduClassService.getEduClassByEduClassId(classId);

        // response에 id가 안들어가려면...
        // EduClass에서 id필드 추가하고 @JsonIgnore 해주거나
        // UserRes처럼 EduClassRes를 하나 만들어줘야 할듯?
        // 일단은 보류 ~~ ㅎㅎ
        return new ResponseEntity<EduClass>(c,HttpStatus.OK);

    }

    @PatchMapping("/timetable/{classId}")
    @ApiOperation(value = "시간표 수정", notes = "수정된 시간표 이미지의 url을 반환한다.")
    public ResponseEntity<Map<String, String>> updateTimeTable(@PathVariable int classId, MultipartHttpServletRequest request) {
        String url = eduClassService.updateTimetable(classId, request);
        Map<String, String> map = new HashMap<String, String>() {
            {
                put("url", url);
            }
        };

        return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
    }

    @GetMapping("/myclass/{classId}")
    @ApiOperation(value = "우리 반 구성원 조회", notes = "해당 반의 담임 선생님과 학생들 리스트를 가져온다.")
    public ResponseEntity<List<EduClassMemListRes>> getEduClassPeopleList(@PathVariable int classId) {
        
        // index 0은 교사, 1부터 학생
        List<EduClassMem> list = eduClassService.getEduClassMem(classId);

        return new ResponseEntity<List<EduClassMemListRes>>(EduClassMemListRes.of(list),HttpStatus.OK);

    }

}
