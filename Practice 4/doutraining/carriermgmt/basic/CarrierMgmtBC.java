package com.clt.apps.opus.dou.doutraining.carriermgmt.basic;

import java.util.List;

import org.slf4j.ext.EventException;

import com.clt.apps.opus.dou.doutraining.carriermgmt.vo.CarrierCdVO;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public interface CarrierMgmtBC {
	public List<CarrierCdVO> searchCarrierVO(CarrierCdVO carrierVO) throws EventException;
	public List<CarrierCdVO> searchCust(CarrierCdVO carrierVO) throws EventException;
	public void manageCarrierVO(CarrierCdVO[] carrierVO,SignOnUserAccount account) throws EventException;
}