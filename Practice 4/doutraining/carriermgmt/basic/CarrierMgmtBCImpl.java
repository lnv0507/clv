package com.clt.apps.opus.dou.doutraining.carriermgmt.basic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.ext.EventException;

import com.clt.apps.opus.dou.doutraining.carriermgmt.integration.CarrierMgmtDBDAO;
import com.clt.apps.opus.dou.doutraining.carriermgmt.vo.CarrierVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public class CarrierMgmtBCImpl extends BasicCommandSupport implements
		CarrierMgmtBC {

	private transient CarrierMgmtDBDAO dbDao = null;

	public CarrierMgmtBCImpl() {
		// create DAO object
		dbDao = new CarrierMgmtDBDAO();
	}

	/**
	 * call DBDAO select data
	 */
	@Override
	public List<CarrierVO> searchCarrierVO(CarrierVO carrierVO)
			throws EventException {
		try {
			return dbDao.searchCarrier(carrierVO);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}

	@Override
	public void manageCarrierVO(CarrierVO[] carrierVO, SignOnUserAccount account)
			throws EventException {
		try {
			// List needs to be inserted
			List<CarrierVO> insertVoList = new ArrayList<CarrierVO>();

			// List needs to be updated
			List<CarrierVO> updateVoList = new ArrayList<CarrierVO>();

			// List needs to be deleted
			List<CarrierVO> deleteVoList = new ArrayList<CarrierVO>();

			// Invalid code message
			StringBuilder invalidData = new StringBuilder();
			String listDuplicate = "";
			// loop through carrierVO and base on IbFlag to perform corresponding
			// action
			for (int i = 0; i < carrierVO.length; i++) {

				// Insert
				if (carrierVO[i].getIbflag().equals("I")) {
					carrierVO[i].setCreUsrId(account.getUsr_id());
					carrierVO[i].setUpdUsrId(account.getUsr_id());
					if (!dbDao.checkDuplicate(carrierVO[i])) {
						listDuplicate += carrierVO[i].getJoCrrCd()
								+ carrierVO[i].getRlaneCd();
						listDuplicate += ",";
					}
					insertVoList.add(carrierVO[i]);
				} else if (carrierVO[i].getIbflag().equals("U")) {
					// Update
					// set UpdUsrId is current user id
					carrierVO[i].setUpdUsrId(account.getUsr_id());

					// add to updating list
					updateVoList.add(carrierVO[i]);
				} else if (carrierVO[i].getIbflag().equals("D")) {
					deleteVoList.add(carrierVO[i]);
				}
			}

			// if we have invalid data( because message code already existed)
			if (invalidData.length() != 0) {
				// remove "|" at the end
				invalidData.deleteCharAt(invalidData.length() - 1);
				// throw new EventException
				throw new EventException(new ErrorHandler("ERR12356",
						new String[] { invalidData.toString() }).getMessage());
			}

			// if inserting list isn't empty
			if (insertVoList.size() > 0) {
				dbDao.addmanageCarrier(insertVoList);
			}

			// if updating list isn't empty
			if (updateVoList.size() > 0) {
				dbDao.modifymanageCarrier(updateVoList);
			}
			// if deleting list isn't empty
			if (deleteVoList.size() > 0) {
				dbDao.removemanageCarrier(deleteVoList);
			}
		} catch (DAOException ex) {
			// throw new EventException if we have DAOException
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			// other exception
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}

	}

	@Override
	public List<CarrierVO> getCrrCds() throws EventException {
		// TODO Auto-generated method stub
		try {
			return dbDao.getCrrCds();
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}

	@Override
	public List<CarrierVO> getLnCds() throws EventException {
		// TODO Auto-generated method stub
		try {
			return dbDao.getLnCds();
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}

	@Override
	public List<CarrierVO> searchCus(CarrierVO carrierVO) throws EventException {
		// TODO Auto-generated method stub
		try {
			return dbDao.searchCusPop(carrierVO);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}

	@Override
	public boolean checkDuplicate(CarrierVO carrierVO) {
		// if duplicate show message on client
		if (dbDao.checkDuplicate(carrierVO) == false) {
			throw new EventException(new ErrorHandler("ERR00001").getMessage());
		}
		return dbDao.checkDuplicate(carrierVO);
	}
}