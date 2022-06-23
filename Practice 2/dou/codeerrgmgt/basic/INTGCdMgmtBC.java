package com.clt.apps.opus.dou.doutraining.codeerrgmgt.basic;

import java.util.List;

import com.clt.apps.opus.dou.doutraining.codeerrgmgt.vo.IntgCdDtlVO;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.vo.IntgCdVO;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public interface INTGCdMgmtBC {
	public List<IntgCdVO> searchMaster(IntgCdVO intgCdVO) throws EventException;

	public void manageMaster(IntgCdVO[] intgCdVOS, SignOnUserAccount account)
			throws EventException;

	public void manageDetail(IntgCdDtlVO[] detailVOS, SignOnUserAccount account)
			throws EventException;

	public List<IntgCdDtlVO> searchIntgCdDtl(IntgCdDtlVO intgCđtlVO)
			throws EventException;

}