package com.clt.apps.opus.dou.doutraining.carriermgmt.basic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.ext.EventException;

import com.clt.apps.opus.dou.doutraining.carriermgmt.integration.CarrierMgmtDBDAO;
import com.clt.apps.opus.dou.doutraining.carriermgmt.vo.CarrierCdVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public class CarrierMgmtBCImpl extends BasicCommandSupport implements CarrierMgmtBC {
	
	private transient CarrierMgmtDBDAO dbDao = null;
	
	public CarrierMgmtBCImpl() {
		//create DAO object
		dbDao = new CarrierMgmtDBDAO();
	}

	@Override
	public List<CarrierCdVO> searchCarrierVO(CarrierCdVO carrierVO) throws EventException {
		try {
			return dbDao.searchMaster(carrierVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
	@Override
	public List<CarrierCdVO> searchCust(CarrierCdVO carrierVO) throws EventException {
		try {
			return dbDao.searchMaster(carrierVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}

	@Override
	public void manageCarrierVO(CarrierCdVO[] carrierVO, SignOnUserAccount account) throws EventException {
		try {
			//List needs to be inserted
			List<CarrierCdVO> insertVoList = new ArrayList<CarrierCdVO>();
			
			//List needs to be updated
			List<CarrierCdVO> updateVoList = new ArrayList<CarrierCdVO>();
			
			//List needs to be deleted
			List<CarrierCdVO> deleteVoList = new ArrayList<CarrierCdVO>();
			
			//Invalid code message
			StringBuilder invalidData=new StringBuilder();
			
			//loop through errMsgVO and base on IbFlag to perform corresponding action
			for ( int i=0; i<carrierVO.length; i++ ) {
				
				//Insert
				if ( carrierVO[i].getIbflag().equals("I")){
					//Condition need to check before inserting
					CarrierCdVO condition = new CarrierCdVO();
					//set message code for condition
//					condition.setIntgCdId(carrierVO[i].getIntgCdId());
					condition.setJoCrrCd(carrierVO[i].getJoCrrCd());
					condition.setRlaneCd(carrierVO[i].getRlaneCd());
					//if message code don't exist
					if(searchCarrierVO(condition).size()==0){
						//set CreUsrId is current user id
						carrierVO[i].setCreUsrId(account.getUsr_id());
						
						//set UpdUsrId is current user id
						carrierVO[i].setUpdUsrId(account.getUsr_id());
						
						//add to inserting list
						insertVoList.add(carrierVO[i]);
					}else{
						//if message code already existed
						//append invalid message code to invalidMsgCds variable
						invalidData.append(carrierVO[i].getJoCrrCd()+"-"+carrierVO[i].getRlaneCd()+"|");
					}
				} else if (carrierVO[i].getIbflag().equals("U")){
					//Update
					//set UpdUsrId is current user id
					carrierVO[i].setUpdUsrId(account.getUsr_id());
					
					//add to updating list
					updateVoList.add(carrierVO[i]);
				} else if ( carrierVO[i].getIbflag().equals("D")){
//					CodeDetailVO codeDetailVO = new CodeDetailVO();
//					codeDetailVO.setIntgCdId(carrierVO[i].getIntgCdId());
					deleteVoList.add(carrierVO[i]);
				}
			}
			
			//if we have invalid data( because message code already existed)
			if(invalidData.length()!=0){
				//remove "|" at the end
				invalidData.deleteCharAt(invalidData.length()-1);
				//throw new EventException 
				throw new EventException(new ErrorHandler("ERR12356", new String[]{invalidData.toString()}).getMessage());
			}
			
			//if inserting list isn't empty
			if ( insertVoList.size() > 0 ) {
				dbDao.addmanageMaster(insertVoList);
			}
			
			//if updating list isn't empty
			if ( updateVoList.size() > 0 ) {
				dbDao.modifymanageMaster(updateVoList);
			}
//			
//			//if deleting list isn't empty
			if ( deleteVoList.size() > 0 ) {
				dbDao.removemanageMaster(deleteVoList);
			}
		} catch(DAOException ex) {
			// throw new EventException if we have DAOException
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			//other exception
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}


}