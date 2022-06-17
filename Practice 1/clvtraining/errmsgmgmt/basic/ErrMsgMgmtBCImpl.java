/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ErrMsgMgmtBCImpl.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.13
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.13 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.basic;

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.integration.ErrMsgMgmtDBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.vo.ErrMsgVO;

/**
 * ALPS-CLVTraining Business Logic Command Interface<br>
 * - ALPS-CLVTraining에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Lam
 * @since J2EE 1.6
 */
public class ErrMsgMgmtBCImpl extends BasicCommandSupport implements ErrMsgMgmtBC {

	// Database Access Object
	private transient ErrMsgMgmtDBDAO dbDao = null;

	/**
	 * ErrMsgMgmtBCImpl 객체 생성<br>
	 * ErrMsgMgmtDBDAO를 생성한다.<br>
	 */
	public ErrMsgMgmtBCImpl() {
		dbDao = new ErrMsgMgmtDBDAO();
	}
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param ErrMsgVO errMsgVO
	 * @return List<ErrMsgVO>
	 * @exception EventException
	 */
	public int checkDuplicated(ErrMsgVO errMsgVO) throws EventException {
		try {
			// return count
			return dbDao.duplicated(errMsgVO);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}

	}

	public boolean checkDuplicateMsgCd(ErrMsgVO errMsg, List<ErrMsgVO> errList) {
		for (ErrMsgVO err : errList) {
			if (err.getErrMsgCd().equals(errMsg.getErrMsgCd())) {
				return false;
			}
		}
		return true;
	}
	public List<ErrMsgVO> searchErrMsg(ErrMsgVO errMsgVO) throws EventException {
		try {
			return dbDao.searchErrMsg(errMsgVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param ErrMsgVO[] errMsgVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void manageErrMsg(ErrMsgVO[] errMsgVO, SignOnUserAccount account) throws EventException{
		try {
			List<ErrMsgVO> insertVoList = new ArrayList<ErrMsgVO>();
			List<ErrMsgVO> updateVoList = new ArrayList<ErrMsgVO>();
			List<ErrMsgVO> deleteVoList = new ArrayList<ErrMsgVO>();
			StringBuilder sb = new StringBuilder();
			int result = 0;
			for ( int i=0; i<errMsgVO .length; i++ ) {
				if ( errMsgVO[i].getIbflag().equals("I")){
					if (checkDuplicated(errMsgVO[i]) >= 1) {
						sb.append("[" + errMsgVO[i].getErrMsgCd());
						if (i < errMsgVO.length - 1) {
							sb.append("],");
						}
						if (i == errMsgVO.length - 1) {
							sb.append("]");
						}
						result++;
						errMsgVO[i].setCreUsrId(account.getUsr_id());
						insertVoList.add(errMsgVO[i]);
				} else if ( errMsgVO[i].getIbflag().equals("U")){
					errMsgVO[i].setCreUsrId(account.getUsr_id());
					updateVoList.add(errMsgVO[i]);
				} else if ( errMsgVO[i].getIbflag().equals("D")){
					deleteVoList.add(errMsgVO[i]);
				}
			}
			
			if ( insertVoList.size() > 0 ) {
				dbDao.addmanageErrMsgS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifymanageErrMsgS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removemanageErrMsgS(deleteVoList);
			}
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
}
	
