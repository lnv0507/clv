package com.clt.apps.opus.dou.doutraining.carriermgmt.basic;

import java.util.List;

import org.slf4j.ext.EventException;

import com.clt.apps.opus.dou.doutraining.carriermgmt.vo.CarrierVO;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public interface CarrierMgmtBC {
	public List<CarrierVO> getCrrCds() throws EventException;

	public List<CarrierVO> searchCus(CarrierVO carrierVO) throws EventException;

	public List<CarrierVO> getLnCds() throws EventException;

	public List<CarrierVO> searchCarrierVO(CarrierVO carrierVO)
			throws EventException;

	public void manageCarrierVO(CarrierVO[] carrierVO, SignOnUserAccount account)
			throws EventException;

	public boolean checkDuplicate(CarrierVO carrierVO);
}