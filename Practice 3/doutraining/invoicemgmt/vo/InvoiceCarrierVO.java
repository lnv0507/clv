/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : InvoiceCarrierVO.java
 *@FileTitle : InvoiceCarrierVO
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.06.30
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.06.30  
 * 1.0 Creation
=========================================================*/

package com.clt.apps.opus.dou.doutraining.invoicemgmt.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.clt.framework.component.common.AbstractValueObject;
import com.clt.framework.component.util.JSPUtil;

/**
 * Table Value Ojbect<br>
 * 관련 Event 에서 생성, 서버실행요청시 Data 전달역할을 수행하는 Value Object
 *
 * @author
 * @since J2EE 1.6
 * @see AbstractValueObject
 */

public class InvoiceCarrierVO extends AbstractValueObject {

	private static final long serialVersionUID = 1L;

	private Collection<InvoiceCarrierVO> models = new ArrayList<InvoiceCarrierVO>();

	/* Column Info */
	private String ibflag = null;
	/* Column Info */
	private String joCrrCd = null;
	/* Column Info */
	private String trdCd = null;
	/* Column Info */
	private String rlaneCd = null;
	/* Column Info */
	private String frAcctYrmon = null;
	/* Column Info */
	private String toAcctYrmon = null;
	/* Page Number */
	private String pagerows = null;

	/* 테이블 컬럼의 값을 저장하는 Hashtable */
	private HashMap<String, String> hashColumns = new LinkedHashMap<String, String>();

	/* 테이블 컬럼에 대응되는 멤버변수를 저장하는 Hashtable */
	private HashMap<String, String> hashFields = new LinkedHashMap<String, String>();

	public InvoiceCarrierVO() {
	}

	public InvoiceCarrierVO(String ibflag, String pagerows, String trdCd,
			String rlaneCd, String joCrrCd, String toAcctYrmon,
			String frAcctYrmon) {
		this.ibflag = ibflag;
		this.joCrrCd = joCrrCd;
		this.trdCd = trdCd;
		this.rlaneCd = rlaneCd;
		this.frAcctYrmon = frAcctYrmon;
		this.toAcctYrmon = toAcctYrmon;
		this.pagerows = pagerows;
	}

	/**
	 * 테이블 컬럼에 저장할 값을 Hashtable<"column_name", "value"> 로 반환
	 * 
	 * @return HashMap
	 */
	public HashMap<String, String> getColumnValues() {
		this.hashColumns.put("ibflag", getIbflag());
		this.hashColumns.put("jo_crr_cd", getJoCrrCd());
		this.hashColumns.put("trd_cd", getTrdCd());
		this.hashColumns.put("rlane_cd", getRlaneCd());
		this.hashColumns.put("fr_acct_yrmon", getFrAcctYrmon());
		this.hashColumns.put("to_acct_yrmon", getToAcctYrmon());
		this.hashColumns.put("pagerows", getPagerows());
		return this.hashColumns;
	}

	/**
	 * 컬럼명에 대응되는 멤버변수명을 저장하여 Hashtable<"column_name", "variable"> 로 반환
	 * 
	 * @return
	 */
	public HashMap<String, String> getFieldNames() {
		this.hashFields.put("ibflag", "ibflag");
		this.hashFields.put("jo_crr_cd", "joCrrCd");
		this.hashFields.put("trd_cd", "trdCd");
		this.hashFields.put("rlane_cd", "rlaneCd");
		this.hashFields.put("fr_acct_yrmon", "frAcctYrmon");
		this.hashFields.put("to_acct_yrmon", "toAcctYrmon");
		this.hashFields.put("pagerows", "pagerows");
		return this.hashFields;
	}

	public String getIbflag() {
		return ibflag;
	}

	public void setIbflag(String ibflag) {
		this.ibflag = ibflag;
	}

	public String getJoCrrCd() {
		return joCrrCd;
	}

	public void setJoCrrCd(String joCrrCd) {
		this.joCrrCd = joCrrCd;
	}

	public String getTrdCd() {
		return trdCd;
	}

	public void setTrdCd(String trdCd) {
		this.trdCd = trdCd;
	}

	public String getRlaneCd() {
		return rlaneCd;
	}

	public void setRlaneCd(String rlaneCd) {
		this.rlaneCd = rlaneCd;
	}

	public String getFrAcctYrmon() {
		return frAcctYrmon;
	}

	public void setFrAcctYrmon(String frAcctYrmon) {
		this.frAcctYrmon = frAcctYrmon;
	}

	public String getToAcctYrmon() {
		return toAcctYrmon;
	}

	public void setToAcctYrmon(String toAcctYrmon) {
		this.toAcctYrmon = toAcctYrmon;
	}

	public String getPagerows() {
		return pagerows;
	}

	public void setPagerows(String pagerows) {
		this.pagerows = pagerows;
	}

	/**
	 * Request 의 데이터를 추출하여 VO 의 멤버변수에 설정.
	 * 
	 * @param request
	 */
	public void fromRequest(HttpServletRequest request) {
		fromRequest(request, "");
	}

	/**
	 * Request 의 데이터를 추출하여 VO 의 멤버변수에 설정.
	 * 
	 * @param request
	 */
	public void fromRequest(HttpServletRequest request, String prefix) {
		setIbflag(JSPUtil.getParameter(request, prefix + "ibflag", ""));
		setJoCrrCd(JSPUtil.getParameter(request, prefix + "s_jo_crr_cd", ""));
		setTrdCd(JSPUtil.getParameter(request, prefix + "s_trd_cd", ""));
		setRlaneCd(JSPUtil.getParameter(request, prefix + "s_rlane_cd", ""));
		setFrAcctYrmon(JSPUtil.getParameter(request, prefix + "fr_acct_yrmon",
				""));
		setToAcctYrmon(JSPUtil.getParameter(request, prefix + "to_acct_yrmon",
				""));
		setPagerows(JSPUtil.getParameter(request, prefix + "pagerows", ""));
	}

	/**
	 * Request 의 데이터를 VO 배열로 변환하여 반환.
	 * 
	 * @param request
	 * @return InvoiceCarrierVO[]
	 */
	public InvoiceCarrierVO[] fromRequestGrid(HttpServletRequest request) {
		return fromRequestGrid(request, "");
	}

	/**
	 * Request 넘어온 여러 건 DATA를 VO Class 에 담는다.
	 * 
	 * @param request
	 * @param prefix
	 * @return InvoiceCarrierVO[]
	 */
	public InvoiceCarrierVO[] fromRequestGrid(HttpServletRequest request,
			String prefix) {
		InvoiceCarrierVO model = null;

		String[] tmp = request.getParameterValues(prefix + "ibflag");
		if (tmp == null)
			return null;

		int length = request.getParameterValues(prefix + "ibflag").length;

		try {
			String[] ibflag = (JSPUtil.getParameter(request, prefix + "ibflag",
					length));
			String[] joCrrCd = (JSPUtil.getParameter(request, prefix
					+ "jo_crr_cd", length));
			String[] trdCd = (JSPUtil.getParameter(request, prefix + "trd_cd",
					length));
			String[] rlaneCd = (JSPUtil.getParameter(request, prefix
					+ "rlane_cd", length));
			String[] frAcctYrmon = (JSPUtil.getParameter(request, prefix
					+ "fr_acct_yrmon", length));
			String[] toAcctYrmon = (JSPUtil.getParameter(request, prefix
					+ "to_acct_yrmon", length));
			String[] pagerows = (JSPUtil.getParameter(request, prefix
					+ "pagerows", length));

			for (int i = 0; i < length; i++) {
				model = new InvoiceCarrierVO();
				if (ibflag[i] != null)
					model.setIbflag(ibflag[i]);
				if (joCrrCd[i] != null)
					model.setJoCrrCd(joCrrCd[i]);
				if (trdCd[i] != null)
					model.setTrdCd(trdCd[i]);
				if (rlaneCd[i] != null)
					model.setRlaneCd(rlaneCd[i]);
				if (frAcctYrmon[i] != null)
					model.setFrAcctYrmon(frAcctYrmon[i]);
				if (toAcctYrmon[i] != null)
					model.setToAcctYrmon(toAcctYrmon[i]);
				if (pagerows[i] != null)
					model.setPagerows(pagerows[i]);
				models.add(model);
			}

		} catch (Exception e) {
			return null;
		}
		return getInvoiceCarrierVOs();
	}

	/**
	 * VO 배열을 반환
	 * 
	 * @return InvoiceCarrierVO[]
	 */
	public InvoiceCarrierVO[] getInvoiceCarrierVOs() {
		InvoiceCarrierVO[] vos = (InvoiceCarrierVO[]) models
				.toArray(new InvoiceCarrierVO[models.size()]);
		return vos;
	}

	/**
	 * VO Class의 내용을 String으로 변환
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * 포맷팅된 문자열에서 특수문자 제거("-","/",",",":")
	 */
	public void unDataFormat() {
		this.ibflag = this.ibflag.replaceAll(",", "").replaceAll("-", "")
				.replaceAll("/", "").replaceAll(":", "");
		this.joCrrCd = this.joCrrCd.replaceAll(",", "").replaceAll("-", "")
				.replaceAll("/", "").replaceAll(":", "");
		this.trdCd = this.trdCd.replaceAll(",", "").replaceAll("-", "")
				.replaceAll("/", "").replaceAll(":", "");
		this.rlaneCd = this.rlaneCd.replaceAll(",", "").replaceAll("-", "")
				.replaceAll("/", "").replaceAll(":", "");
		this.frAcctYrmon = this.frAcctYrmon.replaceAll(",", "")
				.replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.toAcctYrmon = this.toAcctYrmon.replaceAll(",", "")
				.replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.pagerows = this.pagerows.replaceAll(",", "").replaceAll("-", "")
				.replaceAll("/", "").replaceAll(":", "");
	}
}
