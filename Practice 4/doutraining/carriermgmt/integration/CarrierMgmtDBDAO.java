package com.clt.apps.opus.dou.doutraining.carriermgmt.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.dou.doutraining.carriermgmt.integration.CarrierMgmtDBDAOCarrierCrrCdRSQL;
import com.clt.apps.opus.dou.doutraining.carriermgmt.integration.CarrierMgmtDBDAOCarrierLnCdRSQL;
import com.clt.apps.opus.dou.doutraining.carriermgmt.vo.CarrierVO;
import com.clt.apps.opus.dou.doutraining.carriermgmt.integration.CarrierMgmtDBDAOCarrierRSQL;
import com.clt.apps.opus.dou.doutraining.carriermgmt.integration.CarrierMgmtDBDAOCarrierDuplicateRSQL;
import com.clt.apps.opus.dou.doutraining.carriermgmt.integration.CarrierMgmtDBDAOCarrierCSQL;
import com.clt.apps.opus.dou.doutraining.carriermgmt.integration.CarrierMgmtDBDAOCarrierDSQL;
import com.clt.apps.opus.dou.doutraining.carriermgmt.integration.CarrierMgmtDBDAOCarrierUSQL;
import com.clt.apps.opus.dou.doutraining.carriermgmt.integration.CarrierMgmtDBDAOCarrierCustRSQL;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;

public class CarrierMgmtDBDAO extends DBDAOSupport {

	public List<CarrierVO> getCrrCds() throws DAOException {
		// Store returning value from Database
		DBRowSet dbRowset = null;

		// List of VO
		List<CarrierVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			// Query
			dbRowset = new SQLExecuter("").executeQuery(
					(ISQLTemplate) new CarrierMgmtDBDAOCarrierCrrCdRSQL(),
					param, velParam);

			// Convert dbRowset to list
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, CarrierVO.class);
		} catch (SQLException se) {
			// Logging exception
			log.error(se.getMessage(), se);

			// throw new Exception
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}

	public List<CarrierVO> getLnCds() throws DAOException {
		// Store returning value from Database
		DBRowSet dbRowset = null;

		// List of VO
		List<CarrierVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			// Query
			dbRowset = new SQLExecuter("").executeQuery(
					(ISQLTemplate) new CarrierMgmtDBDAOCarrierLnCdRSQL(),
					param, velParam);

			// Convert dbRowset to list
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, CarrierVO.class);
		} catch (SQLException se) {
			// Logging exception
			log.error(se.getMessage(), se);

			// throw new Exception
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}

	/**
	 * 
	 * @param carrierVO
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<CarrierVO> searchCusPop(CarrierVO carrierVO)
			throws DAOException {
		DBRowSet dbRowset = null;
		List<CarrierVO> list = null;

		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (carrierVO != null) {
				Map<String, String> mapVO = carrierVO.getColumnValues();
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}

			dbRowset = new SQLExecuter("").executeQuery(
					(ISQLTemplate) new CarrierMgmtDBDAOCarrierCustRSQL(),
					param, velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, CarrierVO.class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}

	/**
	 * 
	 * @param carrierVO
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<CarrierVO> searchCarrier(CarrierVO carrierVO)
			throws DAOException {
		DBRowSet dbRowset = null;
		List<CarrierVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		List<String> crrCd = new ArrayList<>();
		try {
			if (carrierVO != null) {
				Map<String, String> mapVO = carrierVO.getColumnValues();
				param.putAll(mapVO);
				velParam.putAll(mapVO);
				String[] arrCd = carrierVO.getJoCrrCd().split(",");
				// split , because Client
				for (int i = 0; i < arrCd.length; i++) {
					crrCd.add(arrCd[i]);
				}
				// set array down DTB. Let DTB using foreach Doing function
				// multi search return a data same:
				// ETC,SAM => [ETC, SAM]
				velParam.put("array1", crrCd);
				param.put("array1", crrCd);
				// assign a String date -> call down DTB handle
				String crDtList = carrierVO.getCreDt();
				String frDtList = carrierVO.getUpdDt();
				param.put("date_fr", crDtList);
				velParam.put("date_fr", crDtList);
				param.put("date_to", frDtList);
				velParam.put("date_to", frDtList);
			}
			dbRowset = new SQLExecuter("").executeQuery(
					(ISQLTemplate) new CarrierMgmtDBDAOCarrierRSQL(), param,
					velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, CarrierVO.class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}

	/**
	 * 
	 * @param carrierVO
	 * @return
	 * @throws DAOException
	 * @throws Exception
	 */
	public int[] addmanageCarrier(List<CarrierVO> carrierVO)
			throws DAOException, Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (carrierVO.size() > 0) {
				insCnt = sqlExe.executeBatch(
						(ISQLTemplate) new CarrierMgmtDBDAOCarrierCSQL(),
						carrierVO, null);
				for (int i = 0; i < insCnt.length; i++) {
					if (insCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No" + i + " SQL");
				}
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return insCnt;
	}

	/**
	 * 
	 * @param carrierVO
	 * @return
	 * @throws DAOException
	 * @throws Exception
	 */
	public int[] modifymanageCarrier(List<CarrierVO> carrierVO)
			throws DAOException, Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (carrierVO.size() > 0) {
				updCnt = sqlExe.executeBatch(
						(ISQLTemplate) new CarrierMgmtDBDAOCarrierUSQL(),
						carrierVO, null);
				for (int i = 0; i < updCnt.length; i++) {
					if (updCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No" + i + " SQL");
				}
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return updCnt;
	}

	/**
	 * 
	 * @param carrierVO
	 * @return
	 * @throws DAOException
	 * @throws Exception
	 */
	public int[] removemanageCarrier(List<CarrierVO> carrierVO)
			throws DAOException, Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (carrierVO.size() > 0) {
				delCnt = sqlExe.executeBatch(
						(ISQLTemplate) new CarrierMgmtDBDAOCarrierDSQL(),
						carrierVO, null);
				for (int i = 0; i < delCnt.length; i++) {
					if (delCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No" + i + " SQL");
				}
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return delCnt;
	}

	public boolean checkDuplicate(CarrierVO carrierVO) {
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		DBRowSet dbRowset = null;
		List<CarrierVO> list = null;
		int count = 0;
		try {
			if (carrierVO != null) {
				Map<String, String> mapVO = carrierVO.getColumnValues();
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery(
					(ISQLTemplate) new CarrierMgmtDBDAOCarrierDuplicateRSQL(),
					param, velParam);
			// getInt() change data sql -> java = Integer put 1 because Result
			// set return in the unnamed column(COUNT)
			// only column in the result Set
			// call dbRow.next getInt(1) = count DB return
			// 0 is don't have
			// >0 is have data
			if (dbRowset.next()) {
				count = dbRowset.getInt(1);
			}
			// if count > 0 -> duplicate
			if (count > 0) {
				return false;
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		return true;
	}

}