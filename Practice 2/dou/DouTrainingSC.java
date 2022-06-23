/*=========================================================
 *Copyright(c) 2020 CyberLogitec
 *@FileName : DouTrainingSC.java
 *@FileTitle : Error Message Management
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2020.03.17
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2020.03.17 
 * 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.doutraining;

import java.util.List;

import com.clt.apps.opus.dou.doutraining.codeerrgmgt.basic.INTGCdMgmtBC;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.basic.INTGCdMgmtBCImpl;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.event.ClvTrn0002Event;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.vo.IntgCdDtlVO;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.vo.IntgCdVO;

/**
 * ALPS-DouTraining Business Logic ServiceCommand - Business for
 * ALPS-DouTraining Process the transaction.
 * 
 * @author Vi Nguyen
 * @see ErrMsgMgmtDBDAO
 * @since J2EE 1.6
 */

public class DouTrainingSC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	* DouTraining system task scenario precedent work<br>
	* Creating related internal objects when calling a business scenario<br>
	*/
	public void doStart() {
		log.debug("DouTrainingSC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	* DouTraining system work scenario finishing work<br>
	* Release related internal objects when the work scenario is finished<br>
	*/
	public void doEnd() {
		log.debug("DouTrainingSC 종료");
	}

	/**
	 * Carry out business scenarios for each event<br>
	 * Branch processing of all events occurring in ALPS-DouTraining system work<br>
	 * 
	 * @param e
	 *            Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// SC가 여러 이벤트를 처리하는 경우 사용해야 할 부분
		if (e.getEventName().equalsIgnoreCase("ClvTrn0002Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchMaster(e);
			} else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = manageMaster(e);
			} else if (e.getFormCommand().isCommand(FormCommand.MULTI01)) {
				eventResponse = manageDetail(e);
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH01)) {
				eventResponse = searchDetail(e);
			}
		}
		return eventResponse;
	}

	/**
	* DOU_TRN_001 : [Event]<br>
	* [Act] for [Business Target].<br>
	*
	* @param Event
	* e
	* @return EventResponse set list master return getList user need SC call BC
	* @exception EventException
	*/
	private EventResponse searchMaster(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvTrn0002Event event = (ClvTrn0002Event) e;
		INTGCdMgmtBC command = new INTGCdMgmtBCImpl();

		try {
			List<IntgCdVO> list = command.searchMaster(event.getIntgCdVO());
			eventResponse.setRsVoList(list);
		} catch (EventException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}

	/**
	 * 
	 * 
	 * @param Event
	 *            e
	 * @return EventResponse set list detail return getListDetail user need
	 * @exception EventException
	 */
	private EventResponse searchDetail(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvTrn0002Event event = (ClvTrn0002Event) e;
		INTGCdMgmtBC command = new INTGCdMgmtBCImpl();

		try {
			List<IntgCdDtlVO> list = command.searchIntgCdDtl(event
					.getIntgCdDtlVO());
			eventResponse.setRsVoList(list);
		} catch (EventException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}

	/**
	* DOU_TRN_001 : [Event]<br>
	* [Act] for [Business Target].<br>
	*
	* @param Event
	* e
	* @return EventResponse handle add, update, delete Master
	*
	* @exception EventException
	*/
	private EventResponse manageMaster(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvTrn0002Event event = (ClvTrn0002Event) e;
		INTGCdMgmtBC command = new INTGCdMgmtBCImpl();
		try {
			begin();
			command.manageMaster(event.getIntgCdVOs(), account);
			eventResponse.setUserMessage(new ErrorHandler("XXXXXXXXX")
					.getUserMessage());
			commit();
		} catch (EventException ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}

	/**
	 *
	 * @param Event
	 *            e
	 * @return EventResponse handle add, update, delete Detail
	 *
	 * @exception EventException
	 */
	private EventResponse manageDetail(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvTrn0002Event event = (ClvTrn0002Event) e;
		INTGCdMgmtBC command = new INTGCdMgmtBCImpl();
		try {
			begin();
			command.manageDetail(event.getIntgCdDtlVOs(), account);
			eventResponse.setUserMessage(new ErrorHandler("XXXXXXXXX")
					.getUserMessage());
			commit();
		} catch (EventException ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}
}