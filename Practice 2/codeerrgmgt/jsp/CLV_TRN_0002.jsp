<%@page import="com.clt.apps.opus.dou.doutraining.codeerrgmgt.event.ClvTrn0002Event"%>
    <%@ page contentType="text/html; charset=UTF-8"%>
        <%@ page import="com.clt.framework.component.util.JSPUtil"%>
            <%@ page import="com.clt.framework.component.util.DateTime"%>
                <%@ page import="com.clt.framework.component.message.ErrorHandler"%>
                    <%@ page import="com.clt.framework.core.layer.event.GeneralEventResponse"%>
                        <%@ page import="com.clt.framework.support.controller.html.CommonWebKeys"%>
                            <%@ page import="com.clt.framework.support.view.signon.SignOnUserAccount"%>
                                <%@ page import="org.apache.log4j.Logger"%>

                                    <%
	ClvTrn0002Event event = null; 
	Exception serverException = null; 
	String strErrMsg = ""; 
	int rowCount = 0; 

	String successFlag = "";
	String codeList = "";
	String pageRows = "100";

	String strUsr_id = "";
	String strUsr_nm = "";
	Logger log = Logger.getLogger("com.clt.apps.CLVTraining.ErrMsgMgmt");

		try {
	   	SignOnUserAccount account=(SignOnUserAccount)session.getAttribute(CommonWebKeys.SIGN_ON_USER_ACCOUNT);
		strUsr_id =	account.getUsr_id();
		strUsr_nm = account.getUsr_nm();


		event = (ClvTrn0002Event)request.getAttribute("Event");
		serverException = (Exception)request.getAttribute(CommonWebKeys.EXCEPTION_OBJECT);

		if (serverException != null) {
			strErrMsg = new ErrorHandler(serverException).loadPopupMessage();
		}

		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");

	}catch(Exception e) {
		out.println(e.toString());
	}
%>
                                        <script language="javascript">
                                            function setupPage() {
                                                var errMessage = "<%=strErrMsg%>";
                                                if (errMessage.length >= 1) {
                                                    ComShowMessage(errMessage);
                                                } // end if
                                                loadPage();
                                            }
                                        </script>
                                        <form name="form">
                                            <input type="hidden" name="f_cmd">
                                            <div class="page_title_area clear">
                                                <div class="opus_design_btn">
                                                    <button type="button" class="btn_accent" name="btn_Save" id="btn_Save">Save</button>
                                                    <!--
		   -->
                                                    <button type="button" class="btn_accent" name="btn_Retrieve" id="btn_Retrieve">Retrieve</button>
                                                    <!--
		   -->
                                                </div>
                                            </div>

                                            <div class="wrap_search_tab">
                                                <div class="opus_design_inquiry wFit">
                                                    <table>
                                                        <tbody>
                                                            <tr>
                                                                <th width="40">Subsystem</th>
                                                                <td width="120"><input type="text" style="width:100px;" class="input" value="" name="s_ownr_sub_sys_cd" id="s_ownr_sub_sys_cd"></td>
                                                                <th width="40">Cd ID</th>
                                                                <td><input type="text" style="width:100px;" class="input" value="" name="s_intg_cd_id" id="s_intg_cd_id"></td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>

                                            <div class="wrap_result">
                                                <div class="opus_design_tab">
                                                    <div class="opus_design_inquiry wFit"></div>
                                                    <div id="tabLayer"></div>
                                                    <h3 class="title_design">Master</h3>
                                                    <div class="opus_design_btn">
                                                        <button type="button" class="btn_normal" name="btn_rowadd_ms" id="btn_rowadd_ms">Row Add</button>
                                                        <button type="button" class="btn_normal" name="btn_rowdelete_ms" id="btn_rowdelete_ms">Row Delete</button>
                                                    </div>
                                                </div>
                                                <script language="javascript">
                                                    ComSheetObject('sheet1');
                                                </script>

                                                <div class="opus_design_inquiry">
                                                    <table class="line_bluedot">
                                                        <tr>
                                                            <td></td>
                                                        </tr>
                                                    </table>
                                                </div>

                                                <div class="opus_design_grid">
                                                    <h3 class="title_design">Detail</h3>
                                                    <div class="opus_design_btn">
                                                        <button type="button" class="btn_normal" name="btn_rowadd_dl" id="btn_rowadd_dl">Row Add</button>
                                                        <button type="button" class="btn_normal" name="btn_rowdelete_dl" id="btn_rowdelete_dl">Row Delete</button>
                                                    </div>
                                                </div>
                                                <script language="javascript">
                                                    ComSheetObject('sheet2');
                                                </script>
                                            </div>
                                        </form>