/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : ClvTrn0001Event.java
 *@FileTitle : Error Message Management
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.06.13
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.06.13 
 * 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.event;

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.vo.ErrMsgVO;

/**
 * CLV_TRN_0001 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * - CLV_TRN_0001HTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author Lam
 * @see CLV_TRN_0001HTMLAction 참조
 * @since J2EE 1.6
 */

public class ClvTrn0001Event extends EventSupport {

	private static final long serialVersionUID = 1L;

	/** Table Value Object 조회 조건 및 단건 처리 */
	ErrMsgVO errMsgVO = null;

	/** Table Value Object Multi Data 처리 */
	
	ErrMsgVO[] errMsgVOs = null;

	public ClvTrn0001Event() {
	}

	public void setErrMsgVO(ErrMsgVO errMsgVO) {
		this.errMsgVO = errMsgVO;
	}

	public void setErrMsgVOS(ErrMsgVO[] errMsgVOs) {
		this.errMsgVOs = errMsgVOs;
	}

	public ErrMsgVO getErrMsgVO() {
		return errMsgVO;
	}

	public ErrMsgVO[] getErrMsgVOS() {
		return errMsgVOs;
	}

}