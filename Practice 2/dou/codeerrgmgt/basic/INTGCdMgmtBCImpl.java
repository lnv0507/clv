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
	/**
	 * 
	 * @author Nguyen Viet Lam
	 *
	 */
public class INTGCdMgmtBCImpl extends BasicCommandSupport implements
		INTGCdMgmtBC {

	private transient INTGCdMgMtDBDAO dbDao = null;
	private transient INTGCdDtlMgmtDBDAO dbDaoDetail = null;
	
	public INTGCdMgmtBCImpl() {
		dbDao = new INTGCdMgMtDBDAO();
		dbDaoDetail = new INTGCdDtlMgmtDBDAO();
	}
	/* SC call searchMaster() -> call to DB @param IntgCdVO (master)  
	 * (non-Javadoc)
	 * @see com.clt.apps.opus.dou.doutraining.codeerrgmgt.basic.INTGCdMgmtBC#searchMaster(com.clt.apps.opus.dou.doutraining.codeerrgmgt.vo.IntgCdVO)
	 */
	@Override
	public List<IntgCdVO> searchMaster(IntgCdVO intgCdVO) throws EventException {
		try {
			return dbDao.searchMaster(intgCdVO);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}
	/**
	 * 
	 */
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

	// manageMaster have 4 ArrayList
	// 3 list I, U, D master after user request and add list equivalent
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
			// if list > 0 DB handle request
			if (insertVoList.size() > 0) {
				dbDao.addmanageMaster(insertVoList);
			}

			if (updateVoList.size() > 0) {
				dbDao.modifymanageMaster(updateVoList);
			}

			if (deleteVoList.size() > 0) {
				dbDao.removemanageMaster(deleteVoList);
				dbDaoDetail.removemanageDetailS(deleteDetailVoList);
			}
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}
	/**
	 * 
	 */
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