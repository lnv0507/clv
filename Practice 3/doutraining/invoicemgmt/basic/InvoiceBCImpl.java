package com.clt.apps.opus.dou.doutraining.invoicemgmt.basic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.ext.EventException;

import com.clt.apps.opus.dou.doutraining.invoicemgmt.integration.InvoiceDBDAO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceCarrierVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceDetailVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceTradeVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.integration.DAOException;

public class InvoiceBCImpl implements InvoiceBC{
	private transient InvoiceDBDAO invoiceDBDAO = null;
	 public  InvoiceBCImpl() {
		 invoiceDBDAO = new InvoiceDBDAO();
	}
	
	@Override
	public List<InvoiceVO> searchInvoiceVO(InvoiceCarrierVO invoiceCarrierVO)
			throws EventException {
		try {
			ArrayList<String> invoiceList = new ArrayList<String>();
			if(invoiceCarrierVO.getJoCrrCd().contains(",")){
				String[] invoiceIdList =invoiceCarrierVO.getJoCrrCd().split(",");
				for(String invoiceId :invoiceIdList){
					invoiceList.add(invoiceId);
				}
			}else{
				invoiceList.add(invoiceCarrierVO.getJoCrrCd());
			}
			return invoiceDBDAO.searchInvoiceVO(invoiceCarrierVO , invoiceList);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}

	@Override
	public List<InvoiceVO> searchPartner() throws EventException {
		try {
			return invoiceDBDAO.searchPartner();
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}

	@Override
	public List<InvoiceTradeVO> searchTrade(ArrayList<String> invoiceList,
			String rlane) throws EventException {
		// TODO Auto-generated method stub
		return invoiceDBDAO.searchTrade(invoiceList,rlane);
	}

	@Override
	public List<InvoiceVO> searchLane(ArrayList<String> invoiceList)
			throws EventException {
		return  invoiceDBDAO.searchLane(invoiceList);
	}

	@Override
	public List<InvoiceDetailVO> searchInvoiceDetailVO(
			InvoiceCarrierVO invoiceCarrier)
			throws EventException {
		try {
			ArrayList<String> invoiceCdList = new ArrayList<String>();
			if(invoiceCarrier.getJoCrrCd().contains(",")){
				String[] jooCrrCdList =invoiceCarrier.getJoCrrCd().split(",");
				for(String jooCdId :jooCrrCdList){
					invoiceCdList.add(jooCdId);
				}
			}else{
				invoiceCdList.add(invoiceCarrier.getJoCrrCd());
			}
			return invoiceDBDAO.searchInvoiceDetailVO(invoiceCarrier , invoiceCdList);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
}