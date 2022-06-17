/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : ErrMsgMgmtBC.java
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

import java.util.List;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.vo.ErrMsgVO;

/**
 * ALPS-Clvtraining Business Logic Command Interface<br>
 * - ALPS-Clvtraining에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Lam
 * @since J2EE 1.6
 */

public interface ErrMsgMgmtBC {

	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param ErrMsgVO
	 *            errMsgVO
	 * @return List<ErrMsgVO>
	 * @exception EventException
	 */
	public List<ErrMsgVO> searchErrMsg(ErrMsgVO errMsgVO) throws EventException;

	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param ErrMsgVO
	 *            [] errMsgVO
	 * @param account
	 *            SignOnUserAccount
	 * @exception EventException
	 */
	public void manageErrMsg(ErrMsgVO[] errMsgVO, SignOnUserAccount account)
			throws EventException;
	public int checkDuplicated(ErrMsgVO errMsgVO) throws EventException;
}