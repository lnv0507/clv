/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : INTGCdDtlMgmtDBDAOIntgCdDtlRSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.20
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.20 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.doutraining.codeerrgmgt.integration;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Lam
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class INTGCdDtlMgmtDBDAOIntgCdDtlRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * Select
	  * </pre>
	  */
	public INTGCdDtlMgmtDBDAOIntgCdDtlRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("intg_cd_id",new String[]{arrTmp[0],arrTmp[1]});

		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.dou.doutraining.codeerrgmgt.integration").append("\n"); 
		query.append("FileName : INTGCdDtlMgmtDBDAOIntgCdDtlRSQL").append("\n"); 
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
		query.append("	INTG_CD_VAL_DP_SEQ" ).append("\n"); 
		query.append(",	INTG_CD_VAL_DESC" ).append("\n"); 
		query.append(",	INTG_CD_VAL_DP_DESC" ).append("\n"); 
		query.append(",	INTG_CD_VAL_CTNT" ).append("\n"); 
		query.append(",	INTG_CD_ID" ).append("\n"); 
		query.append("FROM COM_INTG_CD_DTL" ).append("\n"); 
		query.append("WHERE INTG_CD_ID = @[intg_cd_id]" ).append("\n"); 

	}
}