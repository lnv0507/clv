<%@page import="com.clt.apps.opus.dou.doutraining.carriermgmt.event.ClvTrn0004Event"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.clt.framework.component.util.JSPUtil"%>
<%@ page import="com.clt.framework.component.util.DateTime"%>
<%@ page import="com.clt.framework.component.message.ErrorHandler"%>
<%@ page import="com.clt.framework.core.layer.event.GeneralEventResponse"%>
<%@ page import="com.clt.framework.support.controller.html.CommonWebKeys"%>
<%@ page import="com.clt.framework.support.view.signon.SignOnUserAccount"%>
<%@ page import="org.apache.log4j.Logger"%>

<%
	ClvTrn0004Event event; 
	Exception serverException; 
	String crrCds = ""; 
	String lnCds = "";
	String strUsr_id;
	String strUsr_nm;

		try {
	   	SignOnUserAccount account=(SignOnUserAccount)session.getAttribute(CommonWebKeys.SIGN_ON_USER_ACCOUNT);
		strUsr_id =	account.getUsr_id();
		strUsr_nm = account.getUsr_nm();
		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");
		crrCds = eventResponse.getETCData("crrCds");
		lnCds = eventResponse.getETCData("lnCds");
		
		event = (ClvTrn0004Event)request.getAttribute("Event");
		serverException = (Exception)request.getAttribute(CommonWebKeys.EXCEPTION_OBJECT);


	}catch(Exception e) {
		out.println(e.toString());
	}
%>
<script language="javascript">
	var crrCds = "<%=crrCds%>";
	var lnCds = "<%=lnCds%>";
	function setupPage(){
		loadPage();
	}
</script>
<form name="form">
	<input type="hidden" name="f_cmd"> 
	<input type="hidden" name="crr_cd"> 
	<input type="hidden" name="code"> 
	<div class="page_title_area clear">
		<div class="opus_design_btn">
			<button type="button" class="btn_accent" name="btn_Retrieve" id="btn_Retrieve">Retrieve</button><!--
			--><button type="button" class="btn_normal" name="btn_New" id="btn_New">New</button><!--
			--><button type="button" class="btn_normal" name="btn_Save" id="btn_Save">Save</button><!--
			--><button type="button" class="btn_normal" name="btn_DownExcel" id="btn_DownExcel">Down Excel</button>
		</div>
	</div>

	<div class="wrap_search_tab">
		<div class="opus_design_inquiry wfit">
			<table>
				<tbody>
					<tr>
						<th width="50">Carrier</th>
						<td width="120">
							<script type="text/javascript">ComComboObject('s_carrier');</script>
						</td>

						<th width="50">Vendor</th>
						<td width="120">
							<input type="text" style="width: 120px;" class="input" value="" maxlength="6" name="s_vndr_seq" id="s_vndr_seq">
						</td>

						<th width="100">Create Date</th>
						<td>
							<input type="text" style="width: 120px; text-align: center;" placeholder="YYYY-MM-DD" name="s_cre_dt_fr" id="s_cre_dt_fr"><!--  
							--><button type="button" class="calendar ir" name="btn_calendar_dt_fr" id="btn_calendar_dt_fr"></button>
							<input type="text" style="width: 120px; text-align: center;" placeholder="YYYY-MM-DD" name="s_cre_dt_to" id="s_cre_dt_to"><!-- 
							--><button type="button" class="calendar ir" name="btn_calendar_dt_to" id="btn_calendar_dt_to"></button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="wrap_result">
		<div class="opus_design_tab">
		<div class="opus_design_inquiry wFit"></div>
			<div class="opus_design_btn">
				<button type="button" class="btn_normal" name="btn_Delete" id="btn_Delete">Row Delete</button><!-- 
				--><button type="button" class="btn_normal" name="btn_Add" id="btn_Add">Row Add</button>
			</div>
			<script language="javascript">
				ComSheetObject('sheet1');
			</script>
		</div>
	</div>
</form>