package com.clt.apps.opus.dou.doutraining.carriermgmt.event;


import com.clt.apps.opus.dou.doutraining.carriermgmt.vo.CarrierCdVO;
import com.clt.framework.support.layer.event.EventSupport;

public class ClvTrn0004Event extends EventSupport {
	//The serialVersionUID attribute is an identifier that is used to serialize/deserialize an object
	private static final long serialVersionUID = 1L;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	//VO for single data
	CarrierCdVO carrierVO = null;
	
	CarrierCdVO[] carrierVOs = null;
	
	public CarrierCdVO[] getCarrierVOs() {
		return carrierVOs;
	}

	public void setCarrierVOs(CarrierCdVO[] carrierVOs) {
		this.carrierVOs = carrierVOs;
	}

	public ClvTrn0004Event(){}

	public CarrierCdVO getCarrierVO() {
		return carrierVO;
	}


	public void setCarrierCdVO(CarrierCdVO carrierVO) {
		this.carrierVO = carrierVO;
	}


	

}