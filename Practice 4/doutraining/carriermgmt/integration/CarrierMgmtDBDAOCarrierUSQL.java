/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : CarrierMgmtDBDAOCarrierUSQL.java
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

public class CarrierMgmtDBDAOCarrierUSQL implements ISQLTemplate {

	private StringBuffer query = new StringBuffer();

	Logger log = Logger.getLogger(this.getClass());

	/** Parameters definition in params/param elements */
	private HashMap<String, String[]> params = null;

	/**
	 * <pre>
	 * Update
	 * </pre>
	 */
	public CarrierMgmtDBDAOCarrierUSQL() {
		setQuery();
		params = new HashMap<String, String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.NUMERIC + ",N";
		arrTmp = tmp.split(",");
		if (arrTmp.length != 2) {
			throw new IllegalArgumentException();
		}
		params.put("vndr_seq", new String[] { arrTmp[0], arrTmp[1] });

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
		params.put("jo_stl_opt_cd", new String[] { arrTmp[0], arrTmp[1] });

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if (arrTmp.length != 2) {
			throw new IllegalArgumentException();
		}
		params.put("modi_cost_ctr_cd", new String[] { arrTmp[0], arrTmp[1] });

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if (arrTmp.length != 2) {
			throw new IllegalArgumentException();
		}
		params.put("rlane_cd", new String[] { arrTmp[0], arrTmp[1] });

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if (arrTmp.length != 2) {
			throw new IllegalArgumentException();
		}
		params.put("trd_cd", new String[] { arrTmp[0], arrTmp[1] });

		tmp = java.sql.Types.NUMERIC + ",N";
		arrTmp = tmp.split(",");
		if (arrTmp.length != 2) {
			throw new IllegalArgumentException();
		}
		params.put("cust_seq", new String[] { arrTmp[0], arrTmp[1] });

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if (arrTmp.length != 2) {
			throw new IllegalArgumentException();
		}
		params.put("delt_flg", new String[] { arrTmp[0], arrTmp[1] });

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if (arrTmp.length != 2) {
			throw new IllegalArgumentException();
		}
		params.put("cre_usr_id", new String[] { arrTmp[0], arrTmp[1] });

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if (arrTmp.length != 2) {
			throw new IllegalArgumentException();
		}
		params.put("cust_cnt_cd", new String[] { arrTmp[0], arrTmp[1] });

		query.append("/*").append("\n");
		query.append(
				"Path : com.clt.apps.opus.dou.doutraining.carriermgmt.integration")
				.append("\n");
		query.append("FileName : CarrierMgmtDBDAOCarrierUSQL").append("\n");
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
		query.append("UPDATE JOO_CARRIER SET ").append("\n");
		query.append("	MODI_COST_CTR_CD = @[modi_cost_ctr_cd]").append("\n");
		query.append(",	sysdate").append("\n");
		query.append(",	UPD_USR_ID = @[cre_usr_id]").append("\n");
		query.append(",	sysdate").append("\n");
		query.append(",	CRE_USR_ID = @[cre_usr_id]").append("\n");
		query.append(",	sysdate").append("\n");
		query.append(",	DELT_FLG = @[delt_flg]").append("\n");
		query.append(",	JO_STL_OPT_CD = @[jo_stl_opt_cd]").append("\n");
		query.append(",	TRD_CD = @[trd_cd]").append("\n");
		query.append(",	CUST_SEQ = @[cust_seq]").append("\n");
		query.append(",	CUST_CNT_CD = @[cust_cnt_cd]").append("\n");
		query.append(",	VNDR_SEQ = @[vndr_seq]").append("\n");
		query.append(",	RLANE_CD = @[rlane_cd]").append("\n");
		query.append("WHERE	JO_CRR_CD = @[jo_crr_cd]").append("\n");

	}
}