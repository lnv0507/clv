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
 * ALPS-DouTraining Business Logic ServiceCommand - ALPS-DouTraining 대한 비지니스
 * 트랜잭션을 처리한다.
 * 
 * @author Vi Nguyen
 * @see ErrMsgMgmtDBDAO
 * @since J2EE 1.6
 */

public class DouTrainingSC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * DouTraining system 업무 시나리오 선행작업<br>
	 * 업무 시나리오 호출시 관련 내부객체 생성<br>
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
	 * DouTraining system 업무 시나리오 마감작업<br>
	 * 업무 시나리오 종료시 관련 내부객체 해제<br>
	 */
	public void doEnd() {
		log.debug("DouTrainingSC 종료");
	}

	/**
	 * 각 이벤트에 해당하는 업무 시나리오 수행<br>
	 * ALPS-DouTraining system 업무에서 발생하는 모든 이벤트의 분기처리<br>
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
	 * DOU_TRN_001 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * 
	 * @param Event
	 *            e
	 * @return EventResponse
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
	 * DOU_TRN_001 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 *
	 * @param Event
	 *            e
	 * @return EventResponse
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