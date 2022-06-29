/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : CarrierMgmtDBDAOCarrierDuplicateRSQL.java
 *@FileTitle : 
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.06.28
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.06.28 
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

public class CarrierMgmtDBDAOCarrierDuplicateRSQL implements ISQLTemplate {

	private StringBuffer query = new StringBuffer();

	Logger log = Logger.getLogger(this.getClass());

	/** Parameters definition in params/param elements */
	private HashMap<String, String[]> params = null;

	/**
	 * <pre>
	 * Check Duplicate
	 * </pre>
	 */
	public CarrierMgmtDBDAOCarrierDuplicateRSQL() {
		setQuery();
		params = new HashMap<String, String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if (arrTmp.length != 2) {
			throw new IllegalArgumentException();
		}
		params.put("jo_crr_cd", new String[] { arrTmp[0], arrTmp[1] });

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if (arrTmp.length != 2) {
			throw new IllegalArgumentException();
		}
		params.put("rlane_cd", new String[] { arrTmp[0], arrTmp[1] });

		query.append("/*").append("\n");
		query.append(
				"Path : com.clt.apps.opus.dou.doutraining.carriermgmt.integration ")
				.append("\n");
		query.append("FileName : CarrierMgmtDBDAOCarrierDuplicateRSQL").append(
				"\n");
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
		query.append("SELECT COUNT(*)").append("\n");
		query.append("FROM JOO_CARRIER").append("\n");
		query.append("WHERE 1=1").append("\n");
		query.append("AND	JO_CRR_CD = @[jo_crr_cd]").append("\n");
		query.append("AND RLANE_CD = @[rlane_cd]").append("\n");

	}
}