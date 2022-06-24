package com.clt.apps.opus.dou.doutraining.carriermgmt.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.dou.doutraining.carriermgmt.vo.CarrierCdVO;
import com.clt.apps.opus.dou.doutraining.carriermgmt.integration.CarrierMgmtDBDAOCarrierRSQL;
import com.clt.apps.opus.dou.doutraining.carriermgmt.integration.CarrierMgmtDBDAOCarrierCSQL;
import com.clt.apps.opus.dou.doutraining.carriermgmt.integration.CarrierMgmtDBDAOCarrierDSQL;
import com.clt.apps.opus.dou.doutraining.carriermgmt.integration.CarrierMgmtDBDAOCarrierUSQL;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.vo.IntgCdVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;

public class CarrierMgmtDBDAO extends DBDAOSupport {
	
	/**
	 * 
	 * @param intgCdVO
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<CarrierCdVO> searchMaster(CarrierCdVO carrierCdVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<CarrierCdVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (carrierCdVO != null) {
				Map<String, String> mapVO = carrierCdVO.getColumnValues();
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery(
					(ISQLTemplate) new CarrierMgmtDBDAOCarrierRSQL(), param,
					velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, IntgCdVO.class);
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
	 * @param intgCdVO
	 * @throws DAOException
	 * @throws Exception
	 */
	public void addmanageMaster(CarrierCdVO carrierCdVO) throws DAOException,
			Exception {
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		try {
			Map<String, String> mapVO = carrierCdVO.getColumnValues();

			param.putAll(mapVO);
			velParam.putAll(mapVO);

			SQLExecuter sqlExe = new SQLExecuter("");
			int result = sqlExe.executeUpdate(
					(ISQLTemplate) new CarrierMgmtDBDAOCarrierCSQL(), param,
					velParam);
			if (result == Statement.EXECUTE_FAILED)
				throw new DAOException("Fail to insert SQL");
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
	}
	/**
	 * 
	 * @param intgCdVO
	 * @return
	 * @throws DAOException
	 * @throws Exception
	 */
	public int modifymanageMaster(CarrierCdVO carrierCdVO) throws DAOException,
			Exception {
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		int result = 0;
		try {
			Map<String, String> mapVO = carrierCdVO.getColumnValues();

			param.putAll(mapVO);
			velParam.putAll(mapVO);

			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate(
					(ISQLTemplate) new CarrierMgmtDBDAOCarrierUSQL(), param,
					velParam);
			if (result == Statement.EXECUTE_FAILED)
				throw new DAOException("Fail to insert SQL");
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return result;
	}
	/**
	 * 
	 * @param intgCdVO
	 * @return
	 * @throws DAOException
	 * @throws Exception
	 */
	public int removemanageMaster(CarrierCdVO carrierCdVO) throws DAOException,
			Exception {
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		int result = 0;
		try {
			Map<String, String> mapVO = carrierCdVO.getColumnValues();

			param.putAll(mapVO);
			velParam.putAll(mapVO);

			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate(
					(ISQLTemplate) new CarrierMgmtDBDAOCarrierDSQL(), param,
					velParam);
			if (result == Statement.EXECUTE_FAILED)
				throw new DAOException("Fail to insert SQL");
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return result;
	}
	/**
	 * 
	 * @param intgCdVO
	 * @return
	 * @throws DAOException
	 * @throws Exception
	 */
	public int[] addmanageMaster(List<CarrierCdVO> carrierCdVO) throws DAOException,
			Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (carrierCdVO.size() > 0) {
				insCnt = sqlExe.executeBatch(
						(ISQLTemplate) new CarrierMgmtDBDAOCarrierCSQL(),
						carrierCdVO, null);
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
	 * @param intgCdVO
	 * @return
	 * @throws DAOException
	 * @throws Exception
	 */
	public int[] modifymanageMaster(List<CarrierCdVO> carrierCdVO)
			throws DAOException, Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (carrierCdVO.size() > 0) {
				updCnt = sqlExe.executeBatch(
						(ISQLTemplate) new CarrierMgmtDBDAOCarrierUSQL(),
						carrierCdVO, null);
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
	 * @param intgCdVO
	 * @return
	 * @throws DAOException
	 * @throws Exception
	 */
	public int[] removemanageMaster(List<CarrierCdVO> carrierCdVO)
			throws DAOException, Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (carrierCdVO.size() > 0) {
				delCnt = sqlExe.executeBatch(
						(ISQLTemplate) new CarrierMgmtDBDAOCarrierDSQL(),
						carrierCdVO, null);
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

}