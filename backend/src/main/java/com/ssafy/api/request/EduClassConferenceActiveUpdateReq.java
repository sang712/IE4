package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 방 상태 수정 API ([PATCH] /classId/conferenceActive) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("EduClassConferenceActiveUpdateReqest")
public class EduClassConferenceActiveUpdateReq {
	String conferenceActive;
}
