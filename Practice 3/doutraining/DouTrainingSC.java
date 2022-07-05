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

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.dou.doutraining.codeerrgmgt.vo.IntgCdDtlVO;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.vo.IntgCdVO;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.basic.INTGCdMgmtBC;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.basic.INTGCdMgmtBCImpl;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.event.ClvTrn0002Event;
import com.clt.apps.opus.dou.doutraining.carriermgmt.basic.CarrierMgmtBC;
import com.clt.apps.opus.dou.doutraining.carriermgmt.basic.CarrierMgmtBCImpl;
import com.clt.apps.opus.dou.doutraining.carriermgmt.event.ClvTrn0004Event;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.basic.InvoiceBC;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.basic.InvoiceBCImpl;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.event.ClvTrn0003Event;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceDetailVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceTradeVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceVO;
import com.clt.apps.opus.dou.doutraining.carriermgmt.vo.CarrierVO;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

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
		log.debug("DouTrainingSC start");
		try {

			// First comment --> Login check part
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
		log.debug("DouTrainingSC end");
	}

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
		/**
		 * If Event 0004 Search: get Dosearch of client call dtb DEFAULT:
		 * GetData for Combo box on client MULTI: doing Multi function of event
		 */
		if (e.getEventName().equalsIgnoreCase("ClvTrn0004Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchCarrier(e);
			} else if (e.getFormCommand().isCommand(FormCommand.DEFAULT)) {
				eventResponse = initCombox();
			} else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = manageCarrier(e);
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH01)) {
				eventResponse = searchCustomer(e);
			}

		}
		if (e.getEventName().equalsIgnoreCase("ClvTrn0003Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchInvoice(e);
			} else if (e.getFormCommand().isCommand(FormCommand.DEFAULT)) {
				eventResponse = initData();
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH01)) {
				eventResponse = searchLane(e);
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH02)) {
				eventResponse = searchTrade(e);
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH03)) {
				eventResponse = searchInvoiceDetail(e);
			} else if (e.getFormCommand().isCommand(FormCommand.COMMAND01)) {
				eventResponse = searchDown2Excel(e);
			}
		}
		return eventResponse;
	}

	private EventResponse searchDown2Excel(Event e) throws EventException {
		// TODO Auto-generated method stub
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvTrn0003Event event = (ClvTrn0003Event) e;
		InvoiceBC command = new InvoiceBCImpl();
		try {
			eventResponse.setRsVoList(command.searchDown2Excel((event
					.getInvoiceDetailVO())));
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}

	/**
	 * 
	 * @param e
	 * @return
	 * @throws EventException
	 */
	private EventResponse searchInvoice(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvTrn0003Event event = (ClvTrn0003Event) e;
		InvoiceBC command = new InvoiceBCImpl();
		try {
			List<InvoiceVO> list = command.searchInvoiceVO(event
					.getInvoiceCarrierVO());
			eventResponse.setRsVoList(list);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}

	private EventResponse initData() throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		InvoiceBC command = new InvoiceBCImpl();
		try {
			List<InvoiceVO> list = command.searchPartner();
			StringBuilder pastners = new StringBuilder();
			if (null != list && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					pastners.append(list.get(i).getJoCrrCd());
					if (i < list.size() - 1) {
						pastners.append("|");
					}
				}
			}
			eventResponse.setETCData("partner", pastners.toString());
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}

	private EventResponse searchLane(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvTrn0003Event event = (ClvTrn0003Event) e;
		InvoiceBC command = new InvoiceBCImpl();

		try {
			InvoiceVO invoice = event.getInvoiceVO();
			ArrayList<String> invoiceList = new ArrayList<String>();
			if (invoice.getJoCrrCd().contains(",")) {
				String[] invoiceCdList = invoice.getJoCrrCd().split(",");
				for (String invoiceCdId : invoiceCdList) {
					if (!"ALL".equals(invoiceCdId)) {
						invoiceList.add(invoiceCdId);
					}
				}
			} else {
				invoiceList.add(invoice.getJoCrrCd());
			}
			List<InvoiceVO> list = command.searchLane(invoiceList);
			StringBuilder lane = new StringBuilder();
			if (null != list && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					lane.append(list.get(i).getRlaneCd());
					if (i < list.size() - 1) {
						lane.append("|");
					}
				}
			}
			eventResponse.setETCData("lane", lane.toString());
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}

	private EventResponse searchTrade(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvTrn0003Event event = (ClvTrn0003Event) e;
		InvoiceBC command = new InvoiceBCImpl();

		try {
			InvoiceVO invoice = event.getInvoiceVO();
			ArrayList<String> invoiceList = new ArrayList<String>();

			String rlane = invoice.getRlaneCd();
			if (invoice.getJoCrrCd().contains(",")) {
				String[] invoiceCdList = invoice.getJoCrrCd().split(",");
				for (String invoiCd : invoiceCdList) {
					invoiceList.add(invoiCd);
				}
			} else {
				invoiceList.add(invoice.getJoCrrCd());
			}
			List<InvoiceTradeVO> list = command.searchTrade(invoiceList, rlane);
			StringBuilder trade = new StringBuilder();
			if (null != list && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					trade.append(list.get(i).getTrdCd());
					if (i < list.size() - 1) {
						trade.append("|");
					}
				}
			}
			eventResponse.setETCData("trade", trade.toString());
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}

	private EventResponse searchInvoiceDetail(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvTrn0003Event event = (ClvTrn0003Event) e;
		InvoiceBC command = new InvoiceBCImpl();

		try {
			List<InvoiceDetailVO> list = command.searchInvoiceDetailVO(event
					.getInvoiceCarrierVO());
			eventResponse.setRsVoList(list);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}

	/**
	 * Practice 2
	 * 
	 * @param e
	 * @return
	 * @throws EventException
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

	/**
	 * CLV_TRN_0004 : [Event]<br>
	 * [Act] for [Business Target].<br>
	 *
	 * @param Event
	 *            e
	 * @return EventResponse set list master return getList user need SC call BC
	 * @exception EventException
	 */
	private EventResponse searchCarrier(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvTrn0004Event event = (ClvTrn0004Event) e;
		CarrierMgmtBC command = new CarrierMgmtBCImpl();

		try {
			List<CarrierVO> list = command
					.searchCarrierVO(event.getCarrierVO());
			eventResponse.setRsVoList(list);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}

	/**
	 * CLV_TRN_0004 : [Event]<br>
	 * [Act] for [Business Target].<br>
	 *
	 * @param Event
	 *            e
	 * @return EventResponse handle add, update, delete Master
	 *
	 * @exception EventException
	 */
	private EventResponse manageCarrier(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvTrn0004Event event = (ClvTrn0004Event) e;
		CarrierMgmtBC command = new CarrierMgmtBCImpl();
		try {
			begin();
			command.manageCarrierVO(event.getCarrierVOs(), account);
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
	 * 
	 * @param Event
	 *            e
	 * @return EventResponse set list detail return getListDetail user need
	 * @exception EventException
	 */
	private EventResponse searchCustomer(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvTrn0004Event event = (ClvTrn0004Event) e;
		CarrierMgmtBC command = new CarrierMgmtBCImpl();

		try {
			List<CarrierVO> list = command.searchCus(event.getCarrierVO());
			eventResponse.setRsVoList(list);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
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
	private EventResponse initCombox() throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		CarrierMgmtBC command = new CarrierMgmtBCImpl();

		try {
			List<CarrierVO> crrCdList = command.getCrrCds();
			List<CarrierVO> lnCdList = command.getLnCds();
			StringBuilder crrCds = new StringBuilder();
			StringBuilder lnCds = new StringBuilder();
			if (crrCdList.size() != 0) {
				for (int i = 0; i < crrCdList.size(); i++) {
					crrCds.append(crrCdList.get(i).getJoCrrCd() + "|");
				}
				crrCds.deleteCharAt(crrCds.length() - 1);
			}
			if (lnCdList.size() != 0) {
				for (int i = 0; i < lnCdList.size(); i++) {
					lnCds.append(lnCdList.get(i).getRlaneCd() + "|");
				}
				lnCds.deleteCharAt(lnCds.length() - 1);
			}
			/**
			 * get Data on crrCds and lnCds by |. ACE|PDZ|...
			 */
			eventResponse.setETCData("crrCds", crrCds.toString());
			eventResponse.setETCData("lnCds", lnCds.toString());
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}
}