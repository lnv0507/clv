package com.clt.apps.opus.dou.doutraining.invoicemgmt.integration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceCarrierVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceDetailVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceTradeVO;
import com.clt.apps.opus.dou.doutraining.invoicemgmt.vo.InvoiceVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;

public class InvoiceDBDAO extends DBDAOSupport {
	@SuppressWarnings("unchecked")
	public List<InvoiceVO> searchInvoiceVO(InvoiceCarrierVO invoiceCarrierVO,
			ArrayList<String> invoiceList)
			throws DAOException {
		DBRowSet dbRowset = null;
		List<InvoiceVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if(invoiceCarrierVO != null){
				Map<String, String> mapVO = invoiceCarrierVO .getColumnValues();
			
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			if(!"ALL".equals(invoiceCarrierVO.getJoCrrCd())){
				velParam.put("jo_crr_cds", invoiceList);
				param.put("jo_crr_cds", "ALL");
			}else{
				velParam.put("jo_crr_cds", "ALL");
				param.put("jo_crr_cds", "ALL");
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new InvoiceDBDAOInvoiceRSQL(),param, velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, InvoiceVO.class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceVO> searchPartner() {
		DBRowSet dbRowset = null;
		List<InvoiceVO> list = null;
		try {
			dbRowset = new SQLExecuter("").executeQuery(
					(ISQLTemplate) new InvoiceDBDAOInvoiceSearchPastnerRSQL(),
					null, null);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, InvoiceVO.class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceVO> searchLane(ArrayList<String> invoiceList) {
		DBRowSet dbRowset = null;
		List<InvoiceVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (invoiceList.size() > 0) {
				velParam.put("jo_crr_cd", invoiceList);
			}
			dbRowset = new SQLExecuter("").executeQuery(
					(ISQLTemplate) new InvoiceDBDAOInvoiceLaneRSQL(), null,
					velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, InvoiceVO.class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			// throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			// throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceTradeVO> searchTrade(ArrayList<String> invoiceList,
			String rlane) {
		DBRowSet dbRowset = null;
		List<InvoiceTradeVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (invoiceList.size() > 0) {
				velParam.put("jo_crr_cd", invoiceList);
			}
			param.put("rlane_cd", rlane);
			dbRowset = new SQLExecuter("").executeQuery(
					(ISQLTemplate) new InvoiceDBDAOInvoiceTradeDAORSQL(),
					param, velParam);
			list = (List) RowSetUtil
					.rowSetToVOs(dbRowset, InvoiceTradeVO.class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			// throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			// throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceDetailVO> searchInvoiceDetailVO(
			InvoiceCarrierVO invoiceCarrier, ArrayList<String> invoiceList)
			throws DAOException {
		DBRowSet dbRowset = null;
		List<InvoiceDetailVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (invoiceCarrier != null) {
				Map<String, String> mapVO = invoiceCarrier.getColumnValues();

				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			if (!"ALL".equals(invoiceCarrier.getJoCrrCd())) {
				velParam.put("jo_crr_cds", invoiceList);
				param.put("jo_crr_cds", "ALL");
			} else {
				velParam.put("jo_crr_cds", "ALL");
				param.put("jo_crr_cds", "ALL");
			}
			dbRowset = new SQLExecuter("").executeQuery(
					(ISQLTemplate) new InvoiceDBDAOInvoiceDetailRSQL(), param,
					velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset,
					InvoiceDetailVO.class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}

}