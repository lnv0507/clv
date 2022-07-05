/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : InvoiceDBDAOInvoiceTradeRSQL.java
 *@FileTitle : 
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.06.30
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.06.30 
 * 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.doutraining.invoicemgmt.integration;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.clt.framework.core.layer.integration.DAO;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Lam
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class InvoiceDBDAOInvoiceTradeRSQL implements ISQLTemplate {

	private StringBuffer query = new StringBuffer();

	Logger log = Logger.getLogger(this.getClass());

	/** Parameters definition in params/param elements */
	private HashMap<String, String[]> params = null;

	/**
	 * <pre>
	 * SelectTrade
	 * </pre>
	 */
	public InvoiceDBDAOInvoiceTradeRSQL() {
		setQuery();
		params = new HashMap<String, String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if (arrTmp.length != 2) {
			throw new IllegalArgumentException();
		}
		params.put("rlane_cd", new String[] { arrTmp[0], arrTmp[1] });

		query.append("/*").append("\n");
		query.append(
				"Path : com.clt.apps.opus.dou.doutraining.invoicemgmt.integration")
				.append("\n");
		query.append("FileName : InvoiceDBDAOInvoiceTradeRSQL").append("\n");
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
		query.append("SELECT DISTINCT(TRD_CD)").append("\n");
		query.append("FROM JOO_CARRIER").append("\n");
		query.append(
				"WHERE JO_CRR_CD IN (#foreach($key IN ${jo_crr_cd})#if($velocityCount < $jo_crr_cd.size()) '$key', #else '$key' #end #end)")
				.append("\n");
		query.append("AND RLANE_CD = @[rlane_cd]	").append("\n");

	}
}