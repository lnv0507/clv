package com.clt.apps.opus.dou.doutraining.codeerrgmgt.event;

import com.clt.apps.opus.dou.doutraining.codeerrgmgt.vo.IntgCdDtlVO;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.vo.IntgCdVO;
import com.clt.framework.support.layer.event.EventSupport;

public class ClvTrn0002Event extends EventSupport {
	private static final long serialVersionUID = 1L;
	IntgCdVO intgCdVO = null;
	IntgCdVO[] intgCdVOs = null;
	IntgCdDtlVO intgCdDtlVO = null;

	public IntgCdDtlVO getIntgCdDtlVO() {
		return intgCdDtlVO;
	}

	public void setIntgCdDtlVO(IntgCdDtlVO intgCdDtlVO) {
		this.intgCdDtlVO = intgCdDtlVO;
	}

	public IntgCdDtlVO[] getIntgCdDtlVOs() {
		return intgCdDtlVOs;
	}

	public void setIntgCdDtlVOs(IntgCdDtlVO[] intgCdDtlVOs) {
		this.intgCdDtlVOs = intgCdDtlVOs;
	}

	IntgCdDtlVO[] intgCdDtlVOs = null;

	public ClvTrn0002Event() {
	}

	public IntgCdVO getIntgCdVO() {
		return intgCdVO;
	}

	public void setIntgCdVO(IntgCdVO intgCdVO) {
		this.intgCdVO = intgCdVO;
	}

	public IntgCdVO[] getIntgCdVOs() {
		return intgCdVOs;
	}

	public void setIntgCdVOs(IntgCdVO[] intgCdVOs) {
		this.intgCdVOs = intgCdVOs;
	}

}