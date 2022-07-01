/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : InvoiceDBDAOInvoiceSearchPastnerRSQL.java
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
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Lam
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class InvoiceDBDAOInvoiceSearchPastnerRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * Select
	  * </pre>
	  */
	public InvoiceDBDAOInvoiceSearchPastnerRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.dou.doutraining.invoicemgmt.integration").append("\n"); 
		query.append("FileName : InvoiceDBDAOInvoiceSearchPastnerRSQL").append("\n"); 
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
		query.append("SELECT DISTINCT(JO_CRR_CD)" ).append("\n"); 
		query.append("FROM JOO_INVOICE" ).append("\n"); 

	}
}