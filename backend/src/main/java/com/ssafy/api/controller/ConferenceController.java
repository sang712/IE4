package com.ssafy.api.controller;

import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.BoardFile;
import com.ssafy.db.entity.UserPoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Api(value = "회의방 API", tags = {"Conference"})
@RestController
@RequestMapping("/api/conference")
public class ConferenceController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/point/{userId}")
    @ApiOperation(value = "점수 주기", notes = "학생의 점수를 추가한다.")
    public ResponseEntity<? extends BaseResponseBody> getPoint(@PathVariable int userId) throws IOException {
        System.out.println("도르마무 점수를 거래하러왔다 >>>>>" + userId);
        UserPoint userpoint = userService.getPoint(userId);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
}
