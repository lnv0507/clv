package com.clt.apps.opus.dou.doutraining.codeerrgmgt.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.clt.apps.opus.dou.doutraining.codeerrgmgt.vo.IntgCdVO;
import com.clt.framework.component.common.AbstractValueObject;
import com.clt.framework.component.util.JSPUtil;

public class IntgCdDtlVO extends AbstractValueObject {
	private static final long serialVersionUID = 1L;

	private Collection<IntgCdDtlVO> models = new ArrayList<IntgCdDtlVO>();

	/* Column Info */
	private String intgCdId = null;
	/* Column Info */
	private String creUsrId = null;
	/* Column Info */
	private String intgCdValDesc = null;
	/* VO Data Value( C:Creation, U:Update, D:Delete ) */
	private String ibflag = null;
	/* Column Info */
	private String intgCdValDpSeq = null;
	/* Column Info */
	private String intgCdValDpDesc = null;
	/* Column Info */
	private String intgCdValCtnt = null;
	/* Column Info */
	private String updUsrId = null;
	/* Page Number */
	private String pagerows = null;

	/* 테이블 컬럼의 값을 저장하는 Hashtable */
	private HashMap<String, String> hashColumns = new LinkedHashMap<String, String>();

	/* 테이블 컬럼에 대응되는 멤버변수를 저장하는 Hashtable */
	private HashMap<String, String> hashFields = new LinkedHashMap<String, String>();

	public IntgCdDtlVO() {
	}

	public IntgCdDtlVO(String ibflag, String pagerows, String intgCdId,
			String intgCdValCtnt, String intgCdValDpDesc, String intgCdValDesc,
			String intgCdValDpSeq, String creUsrId, String updUsrId) {
		this.intgCdId = intgCdId;
		this.creUsrId = creUsrId;
		this.intgCdValDesc = intgCdValDesc;
		this.ibflag = ibflag;
		this.intgCdValDpSeq = intgCdValDpSeq;
		this.intgCdValDpDesc = intgCdValDpDesc;
		this.intgCdValCtnt = intgCdValCtnt;
		this.updUsrId = updUsrId;
		this.pagerows = pagerows;
	}

	/**
	 * 테이블 컬럼에 저장할 값을 Hashtable<"column_name", "value"> 로 반환
	 * 
	 * @return HashMap
	 */
	public HashMap<String, String> getColumnValues() {
		this.hashColumns.put("intg_cd_id", getIntgCdId());
		this.hashColumns.put("cre_usr_id", getCreUsrId());
		this.hashColumns.put("intg_cd_val_desc", getIntgCdValDesc());
		this.hashColumns.put("ibflag", getIbflag());
		this.hashColumns.put("intg_cd_val_dp_seq", getIntgCdValDpSeq());
		this.hashColumns.put("intg_cd_val_dp_desc", getIntgCdValDpDesc());
		this.hashColumns.put("intg_cd_val_ctnt", getIntgCdValCtnt());
		this.hashColumns.put("upd_usr_id", getUpdUsrId());
		this.hashColumns.put("pagerows", getPagerows());
		return this.hashColumns;
	}

	/**
	 * 컬럼명에 대응되는 멤버변수명을 저장하여 Hashtable<"column_name", "variable"> 로 반환
	 * 
	 * @return
	 */
	public HashMap<String, String> getFieldNames() {
		this.hashFields.put("intg_cd_id", "intgCdId");
		this.hashFields.put("cre_usr_id", "creUsrId");
		this.hashFields.put("intg_cd_val_desc", "intgCdValDesc");
		this.hashFields.put("ibflag", "ibflag");
		this.hashFields.put("intg_cd_val_dp_seq", "intgCdValDpSeq");
		this.hashFields.put("intg_cd_val_dp_desc", "intgCdValDpDesc");
		this.hashFields.put("intg_cd_val_ctnt", "intgCdValCtnt");
		this.hashFields.put("upd_usr_id", "updUsrId");
		this.hashFields.put("pagerows", "pagerows");
		return this.hashFields;
	}

	/**
	 * Column Info
	 * 
	 * @return intgCdId
	 */
	public String getIntgCdId() {
		return this.intgCdId;
	}

	/**
	 * Column Info
	 * 
	 * @return creUsrId
	 */
	public String getCreUsrId() {
		return this.creUsrId;
	}

	/**
	 * Column Info
	 * 
	 * @return intgCdValDesc
	 */
	public String getIntgCdValDesc() {
		return this.intgCdValDesc;
	}

	/**
	 * VO Data Value( C:Creation, U:Update, D:Delete )
	 * 
	 * @return ibflag
	 */
	public String getIbflag() {
		return this.ibflag;
	}

	/**
	 * Column Info
	 * 
	 * @return intgCdValDpSeq
	 */
	public String getIntgCdValDpSeq() {
		return this.intgCdValDpSeq;
	}

	/**
	 * Column Info
	 * 
	 * @return intgCdValDpDesc
	 */
	public String getIntgCdValDpDesc() {
		return this.intgCdValDpDesc;
	}

	/**
	 * Column Info
	 * 
	 * @return intgCdValCtnt
	 */
	public String getIntgCdValCtnt() {
		return this.intgCdValCtnt;
	}

	/**
	 * Column Info
	 * 
	 * @return updUsrId
	 */
	public String getUpdUsrId() {
		return this.updUsrId;
	}

	/**
	 * Page Number
	 * 
	 * @return pagerows
	 */
	public String getPagerows() {
		return this.pagerows;
	}

	/**
	 * Column Info
	 * 
	 * @param intgCdId
	 */
	public void setIntgCdId(String intgCdId) {
		this.intgCdId = intgCdId;
	}

	/**
	 * Column Info
	 * 
	 * @param creUsrId
	 */
	public void setCreUsrId(String creUsrId) {
		this.creUsrId = creUsrId;
	}

	/**
	 * Column Info
	 * 
	 * @param intgCdValDesc
	 */
	public void setIntgCdValDesc(String intgCdValDesc) {
		this.intgCdValDesc = intgCdValDesc;
	}

	/**
	 * VO Data Value( C:Creation, U:Update, D:Delete )
	 * 
	 * @param ibflag
	 */
	public void setIbflag(String ibflag) {
		this.ibflag = ibflag;
	}

	/**
	 * Column Info
	 * 
	 * @param intgCdValDpSeq
	 */
	public void setIntgCdValDpSeq(String intgCdValDpSeq) {
		this.intgCdValDpSeq = intgCdValDpSeq;
	}

	/**
	 * Column Info
	 * 
	 * @param intgCdValDpDesc
	 */
	public void setIntgCdValDpDesc(String intgCdValDpDesc) {
		this.intgCdValDpDesc = intgCdValDpDesc;
	}

	/**
	 * Column Info
	 * 
	 * @param intgCdValCtnt
	 */
	public void setIntgCdValCtnt(String intgCdValCtnt) {
		this.intgCdValCtnt = intgCdValCtnt;
	}

	/**
	 * Column Info
	 * 
	 * @param updUsrId
	 */
	public void setUpdUsrId(String updUsrId) {
		this.updUsrId = updUsrId;
	}

	/**
	 * Page Number
	 * 
	 * @param pagerows
	 */
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
		setIntgCdId(JSPUtil.getParameter(request, prefix + "intg_cd_id", ""));
		setCreUsrId(JSPUtil.getParameter(request, prefix + "cre_usr_id", ""));
		setIntgCdValDesc(JSPUtil.getParameter(request, prefix
				+ "intg_cd_val_desc", ""));
		setIbflag(JSPUtil.getParameter(request, prefix + "ibflag", ""));
		setIntgCdValDpSeq(JSPUtil.getParameter(request, prefix
				+ "intg_cd_val_dp_seq", ""));
		setIntgCdValDpDesc(JSPUtil.getParameter(request, prefix
				+ "intg_cd_val_dp_desc", ""));
		setIntgCdValCtnt(JSPUtil.getParameter(request, prefix
				+ "intg_cd_val_ctnt", ""));
		setUpdUsrId(JSPUtil.getParameter(request, prefix + "upd_usr_id", ""));
		setPagerows(JSPUtil.getParameter(request, prefix + "pagerows", ""));
	}

	/**
	 * Request 의 데이터를 VO 배열로 변환하여 반환.
	 * 
	 * @param request
	 * @return DetailVO[]
	 */
	public IntgCdDtlVO[] fromRequestGrid(HttpServletRequest request) {
		return fromRequestGrid(request, "");
	}

	/**
	 * Request 넘어온 여러 건 DATA를 VO Class 에 담는다.
	 * 
	 * @param request
	 * @param prefix
	 * @return DetailVO[]
	 */
	public IntgCdDtlVO[] fromRequestGrid(HttpServletRequest request,
			String prefix) {
		IntgCdDtlVO model = null;

		String[] tmp = request.getParameterValues(prefix + "ibflag");
		if (tmp == null)
			return null;

		int length = request.getParameterValues(prefix + "ibflag").length;

		try {
			String[] intgCdId = (JSPUtil.getParameter(request, prefix
					+ "intg_cd_id", length));
			String[] creUsrId = (JSPUtil.getParameter(request, prefix
					+ "cre_usr_id", length));
			String[] intgCdValDesc = (JSPUtil.getParameter(request, prefix
					+ "intg_cd_val_desc", length));
			String[] ibflag = (JSPUtil.getParameter(request, prefix + "ibflag",
					length));
			String[] intgCdValDpSeq = (JSPUtil.getParameter(request, prefix
					+ "intg_cd_val_dp_seq", length));
			String[] intgCdValDpDesc = (JSPUtil.getParameter(request, prefix
					+ "intg_cd_val_dp_desc", length));
			String[] intgCdValCtnt = (JSPUtil.getParameter(request, prefix
					+ "intg_cd_val_ctnt", length));
			String[] updUsrId = (JSPUtil.getParameter(request, prefix
					+ "upd_usr_id", length));
			String[] pagerows = (JSPUtil.getParameter(request, prefix
					+ "pagerows", length));

			for (int i = 0; i < length; i++) {
				model = new IntgCdDtlVO();
				if (intgCdId[i] != null)
					model.setIntgCdId(intgCdId[i]);
				if (creUsrId[i] != null)
					model.setCreUsrId(creUsrId[i]);
				if (intgCdValDesc[i] != null)
					model.setIntgCdValDesc(intgCdValDesc[i]);
				if (ibflag[i] != null)
					model.setIbflag(ibflag[i]);
				if (intgCdValDpSeq[i] != null)
					model.setIntgCdValDpSeq(intgCdValDpSeq[i]);
				if (intgCdValDpDesc[i] != null)
					model.setIntgCdValDpDesc(intgCdValDpDesc[i]);
				if (intgCdValCtnt[i] != null)
					model.setIntgCdValCtnt(intgCdValCtnt[i]);
				if (updUsrId[i] != null)
					model.setUpdUsrId(updUsrId[i]);
				if (pagerows[i] != null)
					model.setPagerows(pagerows[i]);
				models.add(model);
			}

		} catch (Exception e) {
			return null;
		}
		return getDetailVOs();
	}

	/**
	 * VO 배열을 반환
	 * 
	 * @return DetailVO[]
	 */
	public IntgCdDtlVO[] getDetailVOs() {
		IntgCdDtlVO[] vos = (IntgCdDtlVO[]) models
				.toArray(new IntgCdDtlVO[models.size()]);
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
		this.intgCdId = this.intgCdId.replaceAll(",", "").replaceAll("-", "")
				.replaceAll("/", "").replaceAll(":", "");
		this.creUsrId = this.creUsrId.replaceAll(",", "").replaceAll("-", "")
				.replaceAll("/", "").replaceAll(":", "");
		this.intgCdValDesc = this.intgCdValDesc.replaceAll(",", "")
				.replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.ibflag = this.ibflag.replaceAll(",", "").replaceAll("-", "")
				.replaceAll("/", "").replaceAll(":", "");
		this.intgCdValDpSeq = this.intgCdValDpSeq.replaceAll(",", "")
				.replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdValDpDesc = this.intgCdValDpDesc.replaceAll(",", "")
				.replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdValCtnt = this.intgCdValCtnt.replaceAll(",", "")
				.replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.updUsrId = this.updUsrId.replaceAll(",", "").replaceAll("-", "")
				.replaceAll("/", "").replaceAll(":", "");
		this.pagerows = this.pagerows.replaceAll(",", "").replaceAll("-", "")
				.replaceAll("/", "").replaceAll(":", "");
	}
}