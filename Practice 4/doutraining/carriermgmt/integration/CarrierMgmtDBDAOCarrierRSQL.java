/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierMgmtDBDAOCarrierRSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.23
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.23 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.doutraining.carriermgmt.integration ;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Lam
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class CarrierMgmtDBDAOCarrierRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * Select
	  * </pre>
	  */
	public CarrierMgmtDBDAOCarrierRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.dou.doutraining.carriermgmt.integration ").append("\n"); 
		query.append("FileName : CarrierMgmtDBDAOCarrierRSQL").append("\n"); 
		query.append("*/").append("\n"); 
	}
	
	public String getSQL(){
		return query.toString();
	}
	
	public HashMap<String,String[]> getParams() {
		return params;
	}

	/**
	 * Query 생성
	 */
	public void setQuery(){
		query.append("SELECT " ).append("\n"); 
		query.append("	MODI_COST_CTR_CD" ).append("\n"); 
		query.append(",	EDW_UPD_DT" ).append("\n"); 
		query.append(",	UPD_USR_ID" ).append("\n"); 
		query.append(",	UPD_DT" ).append("\n"); 
		query.append(",	CRE_USR_ID" ).append("\n"); 
		query.append(",	CRE_DT" ).append("\n"); 
		query.append(",	DELT_FLG" ).append("\n"); 
		query.append(",	JO_STL_OPT_CD" ).append("\n"); 
		query.append(",	TRD_CD" ).append("\n"); 
		query.append(",	CUST_SEQ" ).append("\n"); 
		query.append(",	CUST_CNT_CD" ).append("\n"); 
		query.append(",	VNDR_SEQ" ).append("\n"); 
		query.append(",	RLANE_CD" ).append("\n"); 
		query.append(",	JO_CRR_CD" ).append("\n"); 
		query.append("FROM JOO_CARRIER" ).append("\n"); 

	}
}