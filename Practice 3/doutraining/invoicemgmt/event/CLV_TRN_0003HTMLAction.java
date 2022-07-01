package com.clt.apps.opus.dou.doutraining.invoicemgmt.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceTradeVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceVO;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

public class CLV_TRN_0003HTMLAction extends HTMLActionSupport{
	private static final long serialVersionUID = 1L;
	
	public CLV_TRN_0003HTMLAction(){
		
	}
	@Override
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		FormCommand command = FormCommand.fromRequest(request);
		ClvTrn0003Event event = new ClvTrn0003Event();
		
		//Check if FormCommand is
		// SEARCH , we will search data for summary grid 
		if(command.isCommand(FormCommand.SEARCH)) {
			InvoiceVO invoice = new InvoiceVO();
			invoice.setJoCrrCd(JSPUtil.getParameter(request, "s_jo_crr_cd", ""));
			invoice.setRlaneCd(JSPUtil.getParameter(request, "s_rlane_cd", ""));
			InvoiceTradeVO invoiceTrade = new InvoiceTradeVO();
			String a =JSPUtil.getParameter(request, "s_trd_cd", "");
			invoiceTrade.setTrdCd(a);
			invoiceTrade.setFrAcctYrmon(JSPUtil.getParameter(request, "fr_acct_yrmon", ""));
			invoiceTrade.setToAcctYrmon(JSPUtil.getParameter(request, "to_acct_yrmon", ""));
			event.setInvoiceVO(invoice);
			event.setInvoiceTradeVO(invoiceTrade);
		}
		// SEARCH01 , we will get data for land combo box 
		else if(command.isCommand(FormCommand.SEARCH01)) {
			InvoiceVO invoice = new InvoiceVO();
			invoice.setJoCrrCd(JSPUtil.getParameter(request, "s_jo_crr_cd", ""));
			event.setInvoiceVO(invoice);
		}
		// SEARCH02 , we will get data for trade combo box
		else if(command.isCommand(FormCommand.SEARCH02)){
			InvoiceVO invoice = new InvoiceVO();
			invoice.setJoCrrCd(JSPUtil.getParameter(request, "s_jo_crr_cd", ""));
			invoice.setRlaneCd(JSPUtil.getParameter(request, "s_rlane_cd", ""));
			event.setInvoiceVO(invoice);
		}
		//SEARCH03 , we will search data for detail grid
		else if(command.isCommand(FormCommand.SEARCH03)){
			InvoiceVO invoice = new InvoiceVO();
			invoice.setJoCrrCd(JSPUtil.getParameter(request, "s_jo_crr_cd", ""));
			invoice.setRlaneCd(JSPUtil.getParameter(request, "s_rlane_cd", ""));
			InvoiceTradeVO invoiceTrade = new InvoiceTradeVO();
			String a =JSPUtil.getParameter(request, "s_trd_cd", "");
			invoiceTrade.setTrdCd(a);
			invoiceTrade.setFrAcctYrmon(JSPUtil.getParameter(request, "fr_acct_yrmon", ""));
			invoiceTrade.setToAcctYrmon(JSPUtil.getParameter(request, "to_acct_yrmon", ""));
			event.setInvoiceVO(invoice);
			event.setInvoiceTradeVO(invoiceTrade);
		}
		return event;
	}

	/**
	 * Storing the business scenario execution result value in the attribute of HttpRequest<br>
	 * Setting the ResultSet that transmits the execution result from ServiceCommand to View (JSP) in the request<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse An object that implements the EventResponse interface.
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * Saving the HttpRequest parsing result value in the HttpRequest attribute<br>
	 * HttpRequest parsing result value and set in request<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param Event An object that implements the Event interface.
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}