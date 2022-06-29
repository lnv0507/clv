/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : CarrierMgmtDBDAOCarrierCustRSQL.java
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

public class CarrierMgmtDBDAOCarrierCustRSQL implements ISQLTemplate {

	private StringBuffer query = new StringBuffer();

	Logger log = Logger.getLogger(this.getClass());

	/** Parameters definition in params/param elements */
	private HashMap<String, String[]> params = null;

	/**
	 * <pre>
	 * Slect
	 * </pre>
	 */
	public CarrierMgmtDBDAOCarrierCustRSQL() {
		setQuery();
		params = new HashMap<String, String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
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
		params.put("cust_cnt_cd", new String[] { arrTmp[0], arrTmp[1] });

		query.append("/*").append("\n");
		query.append(
				"Path : com.clt.apps.opus.dou.doutraining.carriermgmt.integration")
				.append("\n");
		query.append("FileName : CarrierMgmtDBDAOCarrierCustRSQL").append("\n");
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
		query.append("select ").append("\n");
		query.append("	cust_cnt_cd,").append("\n");
		query.append("	cust_seq,").append("\n");
		query.append("	cust_lgl_eng_nm,").append("\n");
		query.append("	cust_abbr_nm").append("\n");
		query.append("from mdm_customer").append("\n");
		query.append("where 1 = 1").append("\n");
		query.append("#if (${cust_cnt_cd} != '')").append("\n");
		query.append("and UPPER(cust_cnt_cd) like UPPER(@[cust_cnt_cd])")
				.append("\n");
		query.append("#end").append("\n");
		query.append("#if (${cust_seq} != '')").append("\n");
		query.append("and UPPER(cust_seq) like UPPER('%'||@[cust_seq]||'%')")
				.append("\n");
		query.append("#end").append("\n");

	}
}