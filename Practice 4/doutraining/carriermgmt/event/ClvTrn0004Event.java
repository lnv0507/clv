package com.clt.apps.opus.dou.doutraining.carriermgmt.event;

import com.clt.apps.opus.dou.doutraining.carriermgmt.vo.CarrierVO;
import com.clt.framework.support.layer.event.EventSupport;

public class ClvTrn0004Event extends EventSupport {
	/**
	 * The serialVersionUID attribute is an identifier that is used to //
	 * serialize/deserialize an object
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Table Value Object search condition and single event processing 
	 * VO for single data
	 * VOs for handle multi function
	 */
	CarrierVO carrierVO = null;

	CarrierVO[] carrierVOs = null;

	public CarrierVO[] getCarrierVOs() {
		return carrierVOs;
	}

	public void setCarrierVOs(CarrierVO[] carrierVOs) {
		this.carrierVOs = carrierVOs;
	}

	public ClvTrn0004Event() {
	}

	public CarrierVO getCarrierVO() {
		return carrierVO;
	}

	public void setCarrierVO(CarrierVO carrierVO) {
		this.carrierVO = carrierVO;
	}

}