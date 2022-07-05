package com.clt.apps.opus.dou.doutraining.invoicemgmt.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceCarrierVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceDetailVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceVO;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

public class CLV_TRN_0003HTMLAction extends HTMLActionSupport {
	private static final long serialVersionUID = 1L;

	public CLV_TRN_0003HTMLAction() {

	}

	@Override
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		FormCommand command = FormCommand.fromRequest(request);
		ClvTrn0003Event event = new ClvTrn0003Event();

		// Check if FormCommand is
		// SEARCH , we will search data for summary grid
		if (command.isCommand(FormCommand.SEARCH)) {
			InvoiceCarrierVO invoiceCarrier = (InvoiceCarrierVO) getVO(request,
					InvoiceCarrierVO.class, "");
			event.setInvoiceCarrierVO(invoiceCarrier);
		}
		// SEARCH01 , we will get data for land combo box
		else if (command.isCommand(FormCommand.SEARCH01)) {
			InvoiceVO invoice = new InvoiceVO();
			invoice.setJoCrrCd(JSPUtil.getParameter(request, "s_jo_crr_cd", ""));
			event.setInvoiceVO(invoice);
		}
		// SEARCH02 , we will get data for trade combo box
		else if (command.isCommand(FormCommand.SEARCH02)) {
			InvoiceVO invoice = new InvoiceVO();
			invoice.setJoCrrCd(JSPUtil.getParameter(request, "s_jo_crr_cd", ""));
			invoice.setRlaneCd(JSPUtil.getParameter(request, "s_rlane_cd", ""));
			event.setInvoiceVO(invoice);
		}
		// SEARCH03 , we will search data for detail grid
		else if (command.isCommand(FormCommand.SEARCH03)) {
			InvoiceCarrierVO invoiceCarrier = (InvoiceCarrierVO) getVO(request,
					InvoiceCarrierVO.class, "");
			event.setInvoiceCarrierVO(invoiceCarrier);
		} else if (command.isCommand(FormCommand.COMMAND01)) {
			InvoiceDetailVO invoiceDetailVO = new InvoiceDetailVO();
			invoiceDetailVO.setAcctYrmonFr(JSPUtil.getParameter(request,
					"fr_acct_yrmon", ""));
			invoiceDetailVO.setAcctYrmonTo(JSPUtil.getParameter(request,
					"to_acct_yrmon", ""));
			invoiceDetailVO.setJoCrrCd(JSPUtil.getParameter(request,
					"s_jo_crr_cd", ""));
			invoiceDetailVO.setRlaneCd(JSPUtil.getParameter(request,
					"s_rlane_cd", ""));
			invoiceDetailVO.setTrdCd(JSPUtil.getParameter(request, "s_trd_cd",
					""));
			event.setInvoiceDetailVO(invoiceDetailVO);
		}
		return event;
	}

	/**
	 * Storing the business scenario execution result value in the attribute of
	 * HttpRequest<br>
	 * Setting the ResultSet that transmits the execution result from
	 * ServiceCommand to View (JSP) in the request<br>
	 * 
	 * @param request
	 *            HttpServletRequest HttpRequest
	 * @param eventResponse
	 *            An object that implements the EventResponse interface.
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * Saving the HttpRequest parsing result value in the HttpRequest attribute<br>
	 * HttpRequest parsing result value and set in request<br>
	 * 
	 * @param request
	 *            HttpServletRequest HttpRequest
	 * @param Event
	 *            An object that implements the Event interface.
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}