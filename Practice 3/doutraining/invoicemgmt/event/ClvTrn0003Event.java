package com.clt.apps.opus.dou.doutraining.invoicemgmt.event;

import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceCarrierVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceTradeVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceVO;
import com.clt.framework.support.layer.event.EventSupport;

public class ClvTrn0003Event extends EventSupport {
	private static final long serialVersionUID = 1L;
	InvoiceVO invoiceVO = null;
	InvoiceVO[] invoiceVOs = null;
	InvoiceTradeVO invoiceTradeVO = null;
	InvoiceCarrierVO invoiceCarrierVO = null;
	public InvoiceCarrierVO getInvoiceCarrierVO() {
		return invoiceCarrierVO;
	}

	public void setInvoiceCarrierVO(InvoiceCarrierVO invoiceCarrierVO) {
		this.invoiceCarrierVO = invoiceCarrierVO;
	}

	public InvoiceTradeVO getInvoiceTradeVO() {
		return invoiceTradeVO;
	}

	public void setInvoiceTradeVO(InvoiceTradeVO invoiceTradeVO) {
		this.invoiceTradeVO = invoiceTradeVO;
	}
	public ClvTrn0003Event() {

	}

	public InvoiceVO getInvoiceVO() {
		return invoiceVO;
	}

	public void setInvoiceVO(InvoiceVO invoiceVO) {
		this.invoiceVO = invoiceVO;
	}

	public InvoiceVO[] getInvoiceVOs() {
		return invoiceVOs;
	}

	public void setInvoiceVOs(InvoiceVO[] invoiceVOs) {
		this.invoiceVOs = invoiceVOs;
	}
	
}