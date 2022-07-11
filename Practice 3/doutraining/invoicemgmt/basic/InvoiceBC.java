package com.clt.apps.opus.dou.doutraining.invoicemgmt.basic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.ext.EventException;

import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceCarrierVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceDetailVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceTradeVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceVO;

public interface InvoiceBC {
	/**
	 * 
	 * @param invoiceCarrierVO
	 * @return List Summary
	 * @throws EventException
	 */
	public List<InvoiceVO> searchInvoiceVO(InvoiceCarrierVO invoiceCarrierVO)
			throws EventException;

	/**
	 * 
	 * @return List Partner
	 * @throws EventException
	 */
	public List<InvoiceVO> searchPartner() throws EventException;

	/**
	 * 
	 * @param invoiceList
	 * @return List Lane
	 * @throws EventException
	 */
	public List<InvoiceVO> searchLane(ArrayList<String> invoiceList)
			throws EventException;

	/**
	 * 
	 * @param invoiceList
	 * @param rlane
	 * @return List Trade
	 * @throws EventException
	 */
	public List<InvoiceTradeVO> searchTrade(ArrayList<String> invoiceList,
			String rlane) throws EventException;

	/**
	 * 
	 * @param invoiceCarrier
	 * @return List Detail
	 * @throws EventException
	 */
	public List<InvoiceDetailVO> searchInvoiceDetailVO(
			InvoiceCarrierVO invoiceCarrier) throws EventException;

	/**
	 * 
	 * @param invoiceDetailVO
	 * @return sheet Detail get Download
	 * @throws EventException
	 */
	public List<Object> searchDown2Excel(InvoiceDetailVO invoiceDetailVO)
			throws EventException;
}