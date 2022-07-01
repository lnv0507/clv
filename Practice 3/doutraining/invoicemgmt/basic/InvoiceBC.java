package com.clt.apps.opus.dou.doutraining.invoicemgmt.basic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.ext.EventException;

import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceCarrierVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceDetailVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceTradeVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceVO;

public interface InvoiceBC{
	public List<InvoiceVO> searchInvoiceVO(InvoiceCarrierVO invoiceCarrierVO) throws EventException;
	public List<InvoiceVO> searchPartner() throws EventException;
	public List<InvoiceVO> searchLane(ArrayList<String> invoiceList) throws EventException;
	public List<InvoiceTradeVO> searchTrade(ArrayList<String> invoiceList,
			String rlane) throws EventException;
	public List<InvoiceDetailVO> searchInvoiceDetailVO(InvoiceCarrierVO invoiceCarrier)throws EventException;
}