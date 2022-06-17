/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : ErrMsgMgmtDBDAO.java
 *@FileTitle : Error Message Management
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.06.13
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.06.13 
 * 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.basic.ErrMsgMgmtBCImpl;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;
import com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.vo.ErrMsgVO;

/**
 * ALPS ErrMsgMgmtDBDAO <br>
 * - ALPS-CLVTraining system Business Logic을 처리하기 위한 JDBC 작업수행.<br>
 * 
 * @author Lam
 * @see ErrMsgMgmtBCImpl 참조
 * @since J2EE 1.6
 */
public class ErrMsgMgmtDBDAO extends DBDAOSupport {

	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param ErrMsgVO
	 *            errMsgVO
	 * @return List<ErrMsgVO>
	 * @exception DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<ErrMsgVO> searchErrMsg(ErrMsgVO errMsgVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<ErrMsgVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (errMsgVO != null) {
				Map<String, String> mapVO = errMsgVO.getColumnValues();

				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery(
					(ISQLTemplate) new ErrMsgMgmtDBDAOErrMsgVORSQL(), param,
					velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, ErrMsgVO.class);
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
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param ErrMsgVO
	 *            errMsgVO
	 * @exception DAOException
	 * @exception Exception
	 */
	public void addmanageErrMsg(ErrMsgVO errMsgVO) throws DAOException,
			Exception {
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		try {
			Map<String, String> mapVO = errMsgVO.getColumnValues();

			param.putAll(mapVO);
			velParam.putAll(mapVO);

			SQLExecuter sqlExe = new SQLExecuter("");
			int result = sqlExe.executeUpdate(
					(ISQLTemplate) new ErrMsgMgmtDBDAOErrMsgVOCSQL(), param,
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
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param ErrMsgVO
	 *            errMsgVO
	 * @return int
	 * @exception DAOException
	 * @exception Exception
	 */
	public int modifymanageErrMsg(ErrMsgVO errMsgVO) throws DAOException,
			Exception {
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		int result = 0;
		try {
			Map<String, String> mapVO = errMsgVO.getColumnValues();

			param.putAll(mapVO);
			velParam.putAll(mapVO);

			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate(
					(ISQLTemplate) new ErrMsgMgmtDBDAOErrMsgVOUSQL(), param,
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
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param ErrMsgVO
	 *            errMsgVO
	 * @return int
	 * @exception DAOException
	 * @exception Exception
	 */
	public int removemanageErrMsg(ErrMsgVO errMsgVO) throws DAOException,
			Exception {
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		int result = 0;
		try {
			Map<String, String> mapVO = errMsgVO.getColumnValues();

			param.putAll(mapVO);
			velParam.putAll(mapVO);

			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate(
					(ISQLTemplate) new ErrMsgMgmtDBDAOErrMsgVODSQL(), param,
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
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param List
	 *            <ErrMsgVO> errMsgVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] addmanageErrMsgS(List<ErrMsgVO> errMsgVO) throws DAOException,
			Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (errMsgVO.size() > 0) {
				insCnt = sqlExe.executeBatch(
						(ISQLTemplate) new ErrMsgMgmtDBDAOErrMsgVOCSQL(),
						errMsgVO, null);
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
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param List
	 *            <ErrMsgVO> errMsgVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] modifymanageErrMsgS(List<ErrMsgVO> errMsgVO)
			throws DAOException, Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (errMsgVO.size() > 0) {
				updCnt = sqlExe.executeBatch(
						(ISQLTemplate) new ErrMsgMgmtDBDAOErrMsgVOUSQL(),
						errMsgVO, null);
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
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param List
	 *            <ErrMsgVO> errMsgVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] removemanageErrMsgS(List<ErrMsgVO> errMsgVO)
			throws DAOException, Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (errMsgVO.size() > 0) {
				delCnt = sqlExe.executeBatch(
						(ISQLTemplate) new ErrMsgMgmtDBDAOErrMsgVODSQL(),
						errMsgVO, null);
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
	public int duplicated(ErrMsgVO errMsgVO) throws DAOException, Exception {
		DBRowSet dbRow = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		int result = 0;
		try {
			Map<String, String> mapVO = errMsgVO.getColumnValues();
			param.put("err_msg_cd",errMsgVO.getErrMsgCd());
			// put values in mapVO into velParam
			velParam.putAll(mapVO);
			// execute queries in ErrMsgMgmtDBDAOErrMsgDuplicateRSQL with param and velParam
			dbRow = new SQLExecuter("").executeQuery((ISQLTemplate)new ErrMsgMgmtDBDAOErrMsgDuplicatedVORSQL(), param, null);
			while (dbRow.next()){
				String countE = dbRow.getString(1);
				result = Integer.parseInt(countE);
			}
		}
		catch(SQLException se) {
			//show error in console with error message
			log.error(se.getMessage(),se);
			//throw EventException
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			//show error in console with error message
			log.error(ex.getMessage(),ex);
			//throw EventException
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		

		return result;
	}

}