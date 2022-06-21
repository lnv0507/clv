package com.clt.apps.opus.dou.doutraining.codeerrgmgt.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.dou.doutraining.codeerrgmgt.integration.INTGCdMgmtDBDAOIntgCdRSQL;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.integration.INTGCdMgmtDBDAOIntgCdCSQL;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.integration.INTGCdMgmtDBDAOIntgCdUSQL;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.integration.INTGCdMgmtDBDAOIntgCdDSQL;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.vo.IntgCdVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;

public class INTGCdMgMtDBDAO extends DBDAOSupport {
	@SuppressWarnings("unchecked")
	public List<IntgCdVO> searchErrMsg(IntgCdVO intgCdVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<IntgCdVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (intgCdVO != null) {
				Map<String, String> mapVO = intgCdVO.getColumnValues();

				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery(
					(ISQLTemplate) new INTGCdMgmtDBDAOIntgCdRSQL(), param,
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

	public void addmanageErrMsg(IntgCdVO intgCdVO) throws DAOException,
			Exception {
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		try {
			Map<String, String> mapVO = intgCdVO.getColumnValues();

			param.putAll(mapVO);
			velParam.putAll(mapVO);

			SQLExecuter sqlExe = new SQLExecuter("");
			int result = sqlExe.executeUpdate(
					(ISQLTemplate) new INTGCdMgmtDBDAOIntgCdCSQL(), param,
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

	public int modifymanageErrMsg(IntgCdVO intgCdVO) throws DAOException,
			Exception {
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		int result = 0;
		try {
			Map<String, String> mapVO = intgCdVO.getColumnValues();

			param.putAll(mapVO);
			velParam.putAll(mapVO);

			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate(
					(ISQLTemplate) new INTGCdMgmtDBDAOIntgCdUSQL(), param,
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

	public int removemanageErrMsg(IntgCdVO intgCdVO) throws DAOException,
			Exception {
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		int result = 0;
		try {
			Map<String, String> mapVO = intgCdVO.getColumnValues();

			param.putAll(mapVO);
			velParam.putAll(mapVO);

			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate(
					(ISQLTemplate) new INTGCdMgmtDBDAOIntgCdDSQL(), param,
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

	public int[] addmanageErrMsgS(List<IntgCdVO> intgCdVO) throws DAOException,
			Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (intgCdVO.size() > 0) {
				insCnt = sqlExe.executeBatch(
						(ISQLTemplate) new INTGCdMgmtDBDAOIntgCdCSQL(),
						intgCdVO, null);
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

	public int[] modifymanageErrMsgS(List<IntgCdVO> intgCdVO)
			throws DAOException, Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (intgCdVO.size() > 0) {
				updCnt = sqlExe.executeBatch(
						(ISQLTemplate) new INTGCdMgmtDBDAOIntgCdUSQL(),
						intgCdVO, null);
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

	public int[] removemanageErrMsgS(List<IntgCdVO> intgCdVO)
			throws DAOException, Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (intgCdVO.size() > 0) {
				delCnt = sqlExe.executeBatch(
						(ISQLTemplate) new INTGCdMgmtDBDAOIntgCdDSQL(),
						intgCdVO, null);
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