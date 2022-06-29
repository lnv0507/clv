package com.clt.apps.opus.dou.doutraining.carriermgmt.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.dou.doutraining.carriermgmt.vo.CarrierVO;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

public class CLV_TRN_0004HTMLAction extends HTMLActionSupport {

	/**
	 * The serialVersionUID attribute is an identifier that is used to //
	 * serialize/deserialize an object
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create a CLV_TRN_0004HTMLAction object
	 */
	public CLV_TRN_0004HTMLAction() {
	}

	/**
	 * Parsing the HTML DOM object's Value as a Java variable<br>
	 * Parsing the information of HttpRequst as DouTrainingEvent and setting it
	 * in the request<br>
	 *
	 * @param request
	 *            HttpServletRequest HttpRequest
	 * @return Event An object that implements the Event interface.
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		/**
		 * Get f_command
		 */
		FormCommand command = FormCommand.fromRequest(request);
		/**
		 * Create event object
		 */
		ClvTrn0004Event event = new ClvTrn0004Event();
		CarrierVO carrierVO = new CarrierVO();
		/**
		 * if f_command is SEARCH
		 */
		if (command.isCommand(FormCommand.SEARCH)) {
			/**
			 * Search is function load data by condition Create CarrierVO object
			 * set parameter has name s_carrier for JoCrrCd
			 */
			carrierVO
					.setJoCrrCd(JSPUtil.getParameter(request, "s_carrier", ""));
			carrierVO.setVndrSeq(JSPUtil
					.getParameter(request, "s_vndr_seq", ""));
			/**
			 * set parameter has name s_vndr_seq for VndrSeq
			 */
			carrierVO
					.setCreDt(JSPUtil.getParameter(request, "s_cre_dt_fr", ""));
			/**
			 * set parameter has name s_cre_dt_fr for CreDt
			 */

			carrierVO
					.setUpdDt(JSPUtil.getParameter(request, "s_cre_dt_to", ""));
			/**
			 * set parameter has name s_vndr_seq for VndrSeq // set VO for event
			 * have data.
			 */
			event.setCarrierVO(carrierVO);
		} else if (command.isCommand(FormCommand.MULTI)) {
			/**
			 * MULTI is function add/update/delete mapping data on CarrierVO
			 * class getVOs return array of VO (we use VOs not VO because we
			 * save/insert/delete many rows at same time not only one row) set
			 * VOs for event
			 */
			event.setCarrierVOs((CarrierVO[]) getVOs(request, CarrierVO.class,
					""));
		} else if (command.isCommand(FormCommand.SEARCH01)) {
			/**
			 * Search01 is function for CUSTCNTCD and CUSTSEQ show in client
			 * Carrier and Rev. Lane have do Combo box
			 */
			carrierVO.setCustCntCd(JSPUtil.getParameter(request,
					"s_cust_cnt_cd", ""));
			carrierVO.setCustSeq(JSPUtil
					.getParameter(request, "s_cust_seq", ""));
			event.setCarrierVO(carrierVO);
		}
		return event;
	}

	/**
	 * 
	 * Save HttpRequest parsing result value in HttpRequest attribute<br>
	 * HttpRequest parsing result value set in request<br>
	 * 
	 * @param request
	 *            HttpServletRequest HttpRequest
	 * @param eventResponse
	 *            EventResponse show in client data
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		// set attribute has key EventResponse for request
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * 
	 * Save HttpRequest parsing result value in HttpRequest attribute<br>
	 * HttpRequest parsing result value set in request<br>
	 * 
	 * @param request
	 *            HttpServletRequest HttpRequest
	 * @param event
	 *            Event interface object that implements
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		// set attribute has key Event for request
		request.setAttribute("Event", event);
	}

}