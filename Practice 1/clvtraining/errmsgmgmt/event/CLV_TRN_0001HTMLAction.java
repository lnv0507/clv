/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : CLV_TRN_0001HTMLAction.java
 *@FileTitle : Error Message Management
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.06.13
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.06.13 
 * 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.event;

import javax.servlet.http.HttpServletRequest;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.vo.ErrMsgVO;

/**
 * HTTP Parser<br>
 * - com.clt.apps.opus.esm.clv.clvtraining 화면을 통해 서버로 전송되는 HTML DOM 객체의 Value를
 * 자바 변수로 Parsing<br>
 * - Parsing 한 정보를 Event로 변환, request에 담아 CLVTrainingSC로 실행요청<br>
 * - CLVTrainingSC에서 View(JSP)로 실행결과를 전송하는 EventResponse를 request에 셋팅<br>
 * 
 * @author Lam
 * @see CLVTrainingEvent 참조
 * @since J2EE 1.6
 */

public class CLV_TRN_0001HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * CLV_TRN_0001HTMLAction 객체를 생성
	 */
	public CLV_TRN_0001HTMLAction() {
	}

	/**
	 * HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
	 * HttpRequst의 정보를 CLVTrainingEvent로 파싱하여 request에 셋팅<br>
	 * 
	 * @param request
	 *            HttpServletRequest HttpRequest
	 * @return Event Event interface를 구현한 객체
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {

		FormCommand command = FormCommand.fromRequest(request);
		ClvTrn0001Event event = new ClvTrn0001Event();

		ErrMsgVO errMsgVo = new ErrMsgVO();

		if (command.isCommand(FormCommand.MULTI)) {
			event.setErrMsgVOS((ErrMsgVO[]) getVOs(request, ErrMsgVO.class, ""));
		} else if (command.isCommand(FormCommand.SEARCH)) {
			errMsgVo.setErrMsgCd(JSPUtil.getParameter(request, "s_err_msg_cd",
					""));
			errMsgVo.setErrMsg(JSPUtil.getParameter(request, "s_err_msg", ""));
			errMsgVo.setLangTpCd(JSPUtil
					.getParameter(request, "lang_tp_cd", ""));
			event.setErrMsgVO(errMsgVo);
		}
		request.setAttribute("Event", event);

		return event;
	}

	/**
	 * HttpRequest의 attribute에 업무시나리오 수행결과 값 저장<br>
	 * ServiceCommand에서 View(JSP)로 실행결과를 전송하는 ResultSet을 request에 셋팅<br>
	 * 
	 * @param request
	 *            HttpServletRequest HttpRequest
	 * @param eventResponse
	 *            EventResponse interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);

	}

	/**
	 * HttpRequest의 attribute에 HttpRequest 파싱 수행결과 값 저장<br>
	 * HttpRequest 파싱 수행결과 값 request에 셋팅<br>
	 * 
	 * @param request
	 *            HttpServletRequest HttpRequest
	 * @param event
	 *            Event interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}