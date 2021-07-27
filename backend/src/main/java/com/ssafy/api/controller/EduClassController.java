package com.ssafy.api.controller;

import com.ssafy.api.service.EduClassService;
import com.ssafy.db.entity.EduClass;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
