package com.clt.apps.opus.dou.doutraining.codeerrgmgt.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.dou.doutraining.codeerrgmgt.integration.INTGCdDtlMgmtDBDAOIntgCdDtlRSQL;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.integration.INTGCdDtlMgmtDBDAOIntgCdDtlCSQL;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.integration.INTGCdDtlMgmtDBDAOIntgCdDtlDSQL;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.integration.INTGCdDtlMgmtDBDAOIntgCdDtlUSQL;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.vo.IntgCdDtlVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;

public class INTGCdDtlMgmtDBDAO extends DBDAOSupport {
	@SuppressWarnings("unchecked")
	public List<IntgCdDtlVO> searchDetail(IntgCdDtlVO intgCdtlVO)
			throws DAOException {
		DBRowSet dbRowset = null;
		List<IntgCdDtlVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (intgCdtlVO != null) {
				Map<String, String> mapVO = intgCdtlVO.getColumnValues();

				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery(
					(ISQLTemplate) new INTGCdDtlMgmtDBDAOIntgCdDtlRSQL(),
					param, velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, IntgCdDtlVO.class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}

	public void addmanageDetail(IntgCdDtlVO intgCdtlVO) throws DAOException,
			Exception {
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		try {
			Map<String, String> mapVO = intgCdtlVO.getColumnValues();
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			SQLExecuter sqlExe = new SQLExecuter("");
			int result = sqlExe.executeUpdate(
					(ISQLTemplate) new INTGCdDtlMgmtDBDAOIntgCdDtlCSQL(),
					param, velParam);
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

	public int modifymanageDetail(IntgCdDtlVO intgCdtlVO) throws DAOException,
			Exception {
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		int result = 0;
		try {
			Map<String, String> mapVO = intgCdtlVO.getColumnValues();

			param.putAll(mapVO);
			velParam.putAll(mapVO);

			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate(
					(ISQLTemplate) new INTGCdDtlMgmtDBDAOIntgCdDtlUSQL(),
					param, velParam);
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

	public int removemanageDetail(IntgCdDtlVO intgCdtlVO) throws DAOException,
			Exception {
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		int result = 0;
		try {
			Map<String, String> mapVO = intgCdtlVO.getColumnValues();
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate(
					(ISQLTemplate) new INTGCdDtlMgmtDBDAOIntgCdDtlDSQL(),
					param, velParam);
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

	public int[] addmanageDetailS(List<IntgCdDtlVO> intgCdtlVO)
			throws DAOException, Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (intgCdtlVO.size() > 0) {
				insCnt = sqlExe.executeBatch(
						(ISQLTemplate) new INTGCdDtlMgmtDBDAOIntgCdDtlCSQL(),
						intgCdtlVO, null);
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

	public int[] modifymanageDetailS(List<IntgCdDtlVO> intgCdtlVO)
			throws DAOException, Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (intgCdtlVO.size() > 0) {
				updCnt = sqlExe.executeBatch(
						(ISQLTemplate) new INTGCdDtlMgmtDBDAOIntgCdDtlUSQL(),
						intgCdtlVO, null);
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

	public int[] removemanageDetailS(List<IntgCdDtlVO> intgCdtlVO)
			throws DAOException, Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (intgCdtlVO.size() > 0) {
				delCnt = sqlExe.executeBatch(
						(ISQLTemplate) new INTGCdDtlMgmtDBDAOIntgCdDtlDSQL(),
						intgCdtlVO, null);
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

	// public int duplicated(ErrMsgVO errMsgVO) throws DAOException, Exception {
	// DBRowSet dbRow = null;
	// // query parameter
	// Map<String, Object> param = new HashMap<String, Object>();
	// // velocity parameter
	// Map<String, Object> velParam = new HashMap<String, Object>();
	// int result = 0;
	// try {
	// Map<String, String> mapVO = errMsgVO.getColumnValues();
	// param.put("err_msg_cd",errMsgVO.getErrMsgCd());
	// // put values in mapVO into velParam
	// // velParam.putAll(mapVO);
	// // execute queries in ErrMsgMgmtDBDAOErrMsgDuplicateRSQL with param and
	// velParam
	// dbRow = new SQLExecuter("").executeQuery((ISQLTemplate)new
	// INTGCdMgmtDBDAOIngCdDuplicatedRSQL(), param, null);
	// while (dbRow.next()){
	// String countE = dbRow.getString(1);
	// result = Integer.parseInt(countE);
	// }
	// }
	// catch(SQLException se) {
	// //show error in console with error message
	// log.error(se.getMessage(),se);
	// //throw EventException
	// throw new DAOException(new ErrorHandler(se).getMessage());
	// } catch(Exception ex) {
	// //show error in console with error message
	// log.error(ex.getMessage(),ex);
	// //throw EventException
	// throw new DAOException(new ErrorHandler(ex).getMessage());
	// }
	//
	//
	// return result;
	// }

}