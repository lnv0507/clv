package com.clt.apps.opus.dou.doutraining.invoicemgmt.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.ext.EventException;

import com.clt.apps.opus.dou.doutraining.invoicemgmt.integration.InvoiceDBDAO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceCarrierVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceDetailVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceTradeVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;

public class InvoiceBCImpl implements InvoiceBC {
	private transient InvoiceDBDAO invoiceDBDAO = null;

	public InvoiceBCImpl() {
		invoiceDBDAO = new InvoiceDBDAO();
	}

	/**
	 * 
	 * @param invoiceCarrierVO
	 * @return List Summary
	 * @throws EventException
	 */
	@Override
	public List<InvoiceVO> searchInvoiceVO(InvoiceCarrierVO invoiceCarrierVO)
			throws EventException {
		try {
			ArrayList<String> invoiceList = new ArrayList<String>();
			if (invoiceCarrierVO.getJoCrrCd().contains(",")) {
				String[] jooCrrCdList = invoiceCarrierVO.getJoCrrCd()
						.split(",");
				for (String jooCdId : jooCrrCdList) {
					invoiceList.add(jooCdId);
				}
			} else {
				invoiceList.add(invoiceCarrierVO.getJoCrrCd());
			}
			return invoiceDBDAO.searchInvoiceVO(invoiceCarrierVO, invoiceList);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}

	/**
	 * 
	 * @return List Partner
	 * @throws EventException
	 */
	@Override
	public List<InvoiceVO> searchPartner() throws EventException {
		try {
			return invoiceDBDAO.searchPartner();
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}

	/**
	 * 
	 * @param invoiceList
	 * @param rlane
	 * @return List Trade
	 * @throws EventException
	 */
	@Override
	public List<InvoiceTradeVO> searchTrade(ArrayList<String> invoiceList,
			String rlane) throws EventException {
		try {
			return invoiceDBDAO.searchTrade(invoiceList, rlane);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}

	}

	/**
	 * 
	 * @param invoiceList
	 * @return List Lane
	 * @throws EventException
	 */
	@Override
	public List<InvoiceVO> searchLane(ArrayList<String> invoiceList)
			throws EventException {

		try {
			return invoiceDBDAO.searchLane(invoiceList);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}

	/**
	 * 
	 * @param invoiceCarrier
	 * @return List Detail
	 * @throws EventException
	 */
	@Override
	public List<InvoiceDetailVO> searchInvoiceDetailVO(
			InvoiceCarrierVO invoiceCarrier) throws EventException {
		try {
			ArrayList<String> invoiceCdList = new ArrayList<String>();
			if (invoiceCarrier.getJoCrrCd().contains(",")) {
				String[] jooCrrCdList = invoiceCarrier.getJoCrrCd().split(",");
				for (String jooCdId : jooCrrCdList) {
					invoiceCdList.add(jooCdId);
				}
			} else {
				invoiceCdList.add(invoiceCarrier.getJoCrrCd());
			}
			return invoiceDBDAO.searchInvoiceDetailVO(invoiceCarrier,
					invoiceCdList);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}

	/**
	 * 
	 * @param invoiceDetailVO
	 * @return sheet Detail get Download
	 * @throws EventException
	 */
	@Override
	public List<Object> searchDown2Excel(InvoiceDetailVO invoiceDetailVO)
			throws EventException {
		try {
			DBRowSet rs = invoiceDBDAO.searchDown2Excel(invoiceDetailVO);
			List<Object> li = new ArrayList<>();
			Map<Object, Object> mp = null;
			while (rs.next()) {
				mp = new HashMap<>();
				mp.put("csr_no", rs.getString("CSR_NO"));
				mp.put("inv_rev_act_amt", rs.getString("INV_REV_ACT_AMT"));
				mp.put("locl_curr_cd", rs.getString("LOCL_CURR_CD"));
				mp.put("cust_vndr_seq", rs.getString("CUST_VNDR_SEQ"));
				mp.put("jo_crr_cd", rs.getString("JO_CRR_CD"));
				mp.put("rlane_cd", rs.getString("RLANE_CD"));
				mp.put("rev_exp", rs.getString("REV_EXP"));
				mp.put("cust_vndr_cnt_cd", rs.getString("CUST_VNDR_CNT_CD"));
				mp.put("inv_no", rs.getString("INV_NO"));
				mp.put("cust_vndr_eng_nm", rs.getString("CUST_VNDR_ENG_NM"));
				mp.put("inv_exp_act_amt", rs.getString("INV_EXP_ACT_AMT"));
				mp.put("item", rs.getString("ITEM"));
				mp.put("prnr_ref_no", rs.getString("PRNR_REF_NO"));
				mp.put("apro_flg", rs.getString("APRO_FLG"));
				li.add(mp);
			}
			// Search in DBDAO
			return li;
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}
}