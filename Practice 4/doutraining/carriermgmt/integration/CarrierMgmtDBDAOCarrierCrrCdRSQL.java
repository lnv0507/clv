/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : CarrierMgmtDBDAOCarrierCrrCdRSQL.java
 *@FileTitle : 
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.06.27
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.06.27 
 * 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.doutraining.carriermgmt.integration;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Lam
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class CarrierMgmtDBDAOCarrierCrrCdRSQL implements ISQLTemplate {

	private StringBuffer query = new StringBuffer();

	Logger log = Logger.getLogger(this.getClass());

	/** Parameters definition in params/param elements */
	private HashMap<String, String[]> params = null;

	/**
	 * <pre>
	 * Select Search
	 * </pre>
	 */
	public CarrierMgmtDBDAOCarrierCrrCdRSQL() {
		setQuery();
		params = new HashMap<String, String[]>();
		query.append("/*").append("\n");
		query.append(
				"Path : com.clt.apps.opus.dou.doutraining.carriermgmt.integration ")
				.append("\n");
		query.append("FileName : CarrierMgmtDBDAOCarrierCrrCdRSQL")
				.append("\n");
		query.append("*/").append("\n");
	}

	public String getSQL() {
		return query.toString();
	}

	public HashMap<String, String[]> getParams() {
		return params;
	}

	/**
	 * Query 생성
	 */
	public void setQuery() {
		query.append("SELECT CRR_CD AS JO_CRR_CD").append("\n");
		query.append("FROM MDM_CARRIER").append("\n");
		query.append("WHERE delt_flg = 'N'").append("\n");

	}
}