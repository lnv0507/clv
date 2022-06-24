package com.clt.apps.opus.dou.doutraining.carriermgmt.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.dou.doutraining.carriermgmt.vo.CarrierCdVO;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

public class CLV_TRN_0004HTMLAction extends HTMLActionSupport {

	//The serialVersionUID attribute is an identifier that is used to serialize/deserialize an object
	private static final long serialVersionUID = 1L;
	/**
	 * PRACTICE_0001HTMLAction 객체를 생성
	 */
	public CLV_TRN_0004HTMLAction() {}

	/**
	 * HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
	 * HttpRequst의 정보를 DouTrainingEvent로 파싱하여 request에 셋팅<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event Event interface를 구현한 객체
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		//Get f_command
		FormCommand command = FormCommand.fromRequest(request);
		//Create event object
		ClvTrn0004Event event = new ClvTrn0004Event();
		//if f_command is SEARCH
		if(command.isCommand(FormCommand.SEARCH)) {
			//Create CarrierVO object
			CarrierCdVO carrierVO = new CarrierCdVO();
			
			//set parameter has name s_err_msg for ErrMsg
			carrierVO.setJoCrrCd(JSPUtil.getParameter(request, "s_carrier",""));
			carrierVO.setVndrSeq(JSPUtil.getParameter(request, "s_vndr_seq",""));
			carrierVO.setCreDtFr(JSPUtil.getParameter(request, "s_cre_dt_fr",""));
			carrierVO.setCreDtTo(JSPUtil.getParameter(request, "s_cre_dt_to",""));
			//set VO for event
			event.setCarrierCdVO(carrierVO);
		}else if(command.isCommand(FormCommand.MULTI)) {
			//mapping request to VO base on ErrMsgVO class
			//getVOs return array of VO (we use VOs not VO because we save/insert/delete many rows at same time not only one row)
			//set VOs for event
			event.setCarrierVOs((CarrierCdVO[])getVOs(request, CarrierCdVO.class,""));
		}else if(command.isCommand(FormCommand.SEARCH01)) {
			CarrierCdVO carrierVO = new CarrierCdVO();
			carrierVO.setCustCntCd(JSPUtil.getParameter(request, "s_cust_cnt_cd",""));
			carrierVO.setCustSeq(JSPUtil.getParameter(request, "s_cust_seq",""));
			//mapping request to VO base on ErrMsgVO class
			//getVOs return array of VO (we use VOs not VO because we save/insert/delete many rows at same time not only one row)
			//set VOs for event
			event.setCarrierCdVO(carrierVO);
		}
		return  event;
	}

	/**
	 * HttpRequest의 attribute에 업무시나리오 수행결과 값 저장<br>
	 * ServiceCommand에서 View(JSP)로 실행결과를 전송하는 ResultSet을 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse EventResponse interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		//set attribute has key EventResponse for request
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * HttpRequest의 attribute에 HttpRequest 파싱 수행결과 값 저장<br>
	 * HttpRequest 파싱 수행결과 값 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param event Event interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		//set attribute has key Event for request
		request.setAttribute("Event", event);
	}


}