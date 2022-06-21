package com.clt.apps.opus.dou.doutraining.codeerrgmgt.basic;

import java.util.ArrayList;
import java.util.List;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.integration.INTGCdDtlMgmtDBDAO;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.integration.INTGCdMgMtDBDAO;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.vo.IntgCdDtlVO;
import com.clt.apps.opus.dou.doutraining.codeerrgmgt.vo.IntgCdVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public class INTGCdMgmtBCImpl extends BasicCommandSupport implements
		INTGCdMgmtBC {

	private transient INTGCdMgMtDBDAO dbDao = null;
	private transient INTGCdDtlMgmtDBDAO dbDaoDetail = null;

	public INTGCdMgmtBCImpl() {
		dbDao = new INTGCdMgMtDBDAO();
		dbDaoDetail = new INTGCdDtlMgmtDBDAO();
	}

	// public int checkDuplicated(ErrMsgVO errMsgVO) throws EventException {
	// try {
	// // return count
	// return dbDao.duplicated(errMsgVO);
	// } catch (DAOException ex) {
	// throw new EventException(new ErrorHandler(ex).getMessage(), ex);
	// } catch (Exception ex) {
	// throw new EventException(new ErrorHandler(ex).getMessage(), ex);
	// }
	//
	// }
	//
	// public boolean checkDuplicateMsgCd(ErrMsgVO errMsg, List<ErrMsgVO>
	// errList) {
	// for (ErrMsgVO err : errList) {
	// if (err.getErrMsgCd().equals(errMsg.getErrMsgCd())) {
	// return false;
	// }
	// }
	// return true;
	// }

	@Override
	public List<IntgCdVO> searchMaster(IntgCdVO intgCdVO) throws EventException {
		try {
			return dbDao.searchErrMsg(intgCdVO);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}

	@Override
	public List<IntgCdDtlVO> searchIntgCdDtl(IntgCdDtlVO intgCdDtlVO)
			throws EventException {
		try {
			return dbDaoDetail.searchDetail(intgCdDtlVO);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}

	@Override
	public void manageMaster(IntgCdVO[] intgCdVO, SignOnUserAccount account)
			throws EventException {
		try {
			List<IntgCdVO> insertVoList = new ArrayList<IntgCdVO>();
			List<IntgCdVO> updateVoList = new ArrayList<IntgCdVO>();
			List<IntgCdVO> deleteVoList = new ArrayList<IntgCdVO>();
			List<IntgCdDtlVO> deleteDetailVoList = new ArrayList<IntgCdDtlVO>();

			for (int i = 0; i < intgCdVO.length; i++) {
				if (intgCdVO[i].getIbflag().equals("I")) {
					 insertVoList.add(intgCdVO[i]);

				} else if (intgCdVO[i].getIbflag().equals("U")) {
					updateVoList.add(intgCdVO[i]);
				} else if (intgCdVO[i].getIbflag().equals("D")) {
					IntgCdDtlVO detailVO = new IntgCdDtlVO();
					detailVO.setIntgCdId(intgCdVO[i].getIntgCdId());
					deleteDetailVoList.add(detailVO);
					deleteVoList.add(intgCdVO[i]);
				}
				intgCdVO[i].setCreUsrId(account.getUsr_id());
				intgCdVO[i].setUpdUsrId(account.getUsr_id());
			}

			if (insertVoList.size() > 0) {
				dbDao.addmanageErrMsgS(insertVoList);
			}

			if (updateVoList.size() > 0) {
				dbDao.modifymanageErrMsgS(updateVoList);
			}

			if (deleteVoList.size() > 0) {
				dbDao.removemanageErrMsgS(deleteVoList);
				dbDaoDetail.removemanageDetailS(deleteDetailVoList);
			}
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}

	@Override
	public void manageDetail(IntgCdDtlVO[] detailVO, SignOnUserAccount account)
			throws EventException {
		try {
			List<IntgCdDtlVO> insertVoList = new ArrayList<IntgCdDtlVO>();
			List<IntgCdDtlVO> updateVoList = new ArrayList<IntgCdDtlVO>();
			List<IntgCdDtlVO> deleteVoList = new ArrayList<IntgCdDtlVO>();

			for (int i = 0; i < detailVO.length; i++) {
				if (detailVO[i].getIbflag().equals("I")) {
					insertVoList.add(detailVO[i]);
				} else if (detailVO[i].getIbflag().equals("U")) {
					updateVoList.add(detailVO[i]);
				} else if (detailVO[i].getIbflag().equals("D")) {
					deleteVoList.add(detailVO[i]);
				}
				detailVO[i].setCreUsrId(account.getUsr_id());
				detailVO[i].setUpdUsrId(account.getUsr_id());
			}

			if (insertVoList.size() > 0) {
				dbDaoDetail.addmanageDetailS(insertVoList);
			}

			if (updateVoList.size() > 0) {
				dbDaoDetail.modifymanageDetailS(updateVoList);
			}

			if (deleteVoList.size() > 0) {
				dbDaoDetail.removemanageDetailS(deleteVoList);
			}
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}
}