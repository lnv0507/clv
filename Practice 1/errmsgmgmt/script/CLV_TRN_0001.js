/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : CLV_TRN_0001.js
 *@FileTitle : Error Message Management
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.06.13
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.06.13 
 * 1.0 Creation
=========================================================*/
/****************************************************************************************
 이벤트 구분 코드: [초기화]INIT=0; [입력]ADD=1; [조회]SEARCH=2; [리스트조회]SEARCHLIST=3;
 [수정]MODIFY=4; [삭제]REMOVE=5; [리스트삭제]REMOVELIST=6 [다중처리]MULTI=7
 기타 여분의 문자상수  COMMAND01=11; ~ COMMAND20=30;
 ***************************************************************************************/

/*------------------다음 코드는 JSDoc을 잘 만들기 위해서 추가된 코드임 ------------------*/
/**
 * @fileoverview 업무에서 공통으로 사용하는 자바스크립트파일로 달력 관련 함수가 정의되어 있다.
 * @author 한진해운
 */

/**
 * @extends
 * @class CLV_TRN_0001 : CLV_TRN_0001 생성을 위한 화면에서 사용하는 업무 스크립트를 정의한다.
 */
var sheetObjects = new Array();
var sheetCnt = 0;
var rowcount = 0;
document.onclick = processButtonClick;

function processButtonClick() {
    var sheetObj = sheetObjects[0];
    var formObj = document.form;
    try {
        var srcName = ComGetEvent("name");
        switch (srcName) {
            case "btn_Add":
                doActionIBSheet(sheetObj, formObj, IBINSERT);
                break;
            case "btn_Retrieve":
                doActionIBSheet(sheetObj, formObj, IBSEARCH);
                break;
            case "btn_Save":
                doActionIBSheet(sheetObj, formObj, IBSAVE);
                break;
            case "btn_DownExcel":
                doActionIBSheet(sheetObj, formObj, IBDOWNEXCEL);
                break;
        } // end switch
    } catch (e) {
        if (e == "[object Error]") {
            ComShowMessage(OBJECT_ERROR);
        } else {
            ComShowMessage(e);
        }
    }
}

function setSheetObject(sheetObj) {
    sheetObjects[sheetCnt++] = sheetObj;
}

function loadPage() {
    // generate Grid Layout
    for (i = 0; i < sheetObjects.length; i++) {
        ComConfigSheet(sheetObjects[i]);
        initSheet(sheetObjects[i], i + 1);
        ComEndConfigSheet(sheetObjects[i]);
        doActionIBSheet(sheetObjects[i], document.form, IBSEARCH);
    }

    // auto search data after loading finish.

}

function initSheet(sheetObj, sheetNo) {
    var cnt = 0;
    var sheetID = sheetObj.id;
    switch (sheetID) {
        case "sheet1":
            with(sheetObj) {
                var HeadTitle = "|Del|Msg Cd|Msg Type|Msg level|Message|Description";
                var headCount = ComCountHeadTitle(HeadTitle);
                SetConfig({ SearchMode: 2, MergeSheet: 5, Page: 20, FrozenCol: 0, DataRowMerge: 1 });
                var info = { Sort: 1, ColMove: 1, HeaderCheck: 0, ColResize: 1 };
                var headers = [{ Text: HeadTitle, Align: "Center" }];
                InitHeaders(headers, info);

                var cols = [{ Type: "Status", Hidden: 0, Width: 30, Align: "Center", ColMerge: 0, SaveName: "ibflag" },
                    { Type: "DelCheck", Hidden: 0, Width: 45, Align: "Center", ColMerge: 1, SaveName: "DEL", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Text", Hidden: 0, Width: 80, Align: "Center", ColMerge: 0, SaveName: "err_msg_cd", KeyField: 1, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 0, InsertEdit: 1, EditLen: 8 },
                    { Type: "Combo", Hidden: 0, Width: 80, Align: "Center", ColMerge: 0, SaveName: "err_tp_cd", KeyField: 1, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1, ComboText: "Server|UI|Both", ComboCode: "S|U|B" },
                    { Type: "Combo", Hidden: 0, Width: 80, Align: "Center", ColMerge: 0, SaveName: "err_lvl_cd", KeyField: 1, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1, ComboText: "ERR|WARNING|INFO", ComboCode: "E|W|I" },
                    { Type: "Text", Hidden: 0, Width: 400, Align: "Left", ColMerge: 0, SaveName: "err_msg", KeyField: 1, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1, MultiLineText: 1 },
                    { Type: "Text", Hidden: 0, Width: 250, Align: "Left", ColMerge: 0, SaveName: "err_desc", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 }
                ];
                InitColumns(cols);
                SetWaitImageVisible(0);
                resizeSheet();
            }
            break;
    }
}

function resizeSheet() {  
    ComResizeSheet(sheetObjects[0]);
}

function doActionIBSheet(sheetObj, formObj, sAction) {
    switch (sAction) {
        case IBSEARCH:
            if (!validateForm(sheetObj, formObj, sAction)) return
            ComOpenWait(true);
            formObj.f_cmd.value = SEARCH;
            sheetObj.DoSearch("CLV_TRN_0001GS.do", FormQueryString(formObj));
            break;
        case IBSAVE:
            if (!validateForm(sheetObj, formObj, sAction)) return;
            formObj.f_cmd.value = MULTI;
            var SaveStr = sheetObj.GetSaveString();
            sheetObj.DoSave("CLV_TRN_0001GS.do", FormQueryString(formObj));
            break;
        case IBINSERT:
            rowcount = sheetObj.RowCount();
            row = sheetObj.DataInsert();
            break;
        case IBDOWNEXCEL:
            if (sheetObj.RowCount() < 1) {
                ComShowCodeMessage("COM132501");
            } else {
                sheetObj.Down2Excel({ DownCols: makeHiddenSkipCol(sheetObj), SheetDesign: 1, Merge: 1 });
            }
            break;
    }
}

function validateForm(sheetObj, formObj, sAction) {
    with(formObj) {
        console.log(formObj.iPage);
        // if (!isNumber(formObj.iPage)) {
        // return false;
        // }
    }
    return true;
}
// handle duplicate code and check 8 char
function sheet1_OnChange(sheetObj, Row, Col) {
    var check = /([A-Z]{3}[0-9]{5})/g;
    if (sheetObj.ColSaveName(Col) === "err_msg_cd") {
        var code = sheetObj.GetCellValue(Row, Col);
//        check regexp
        var arrCodeTrue = code.match(check);
//        if haven't arrCodeTrue = null
        if (arrCodeTrue === null) {
            ComShowCodeMessage("COM12114");
            sheetObj.SetCellValue(Row, Col, "", 0);
            return;
        }
//        get List code now
//        for (var i = 1; i < sheetObj.RowCount(); i++) {
//            var codeCheck = sheetObj.GetCellValue(i, Col);
////            check code new = code now => duplicate
//            if (code === codeCheck) {
//                ComShowCodeMessage('COM131302', code);
//                sheetObj.SetCellValue(Row, Col, "", 0);
//                return;
//            }
//        }
    }


}
 function sheet1_OnSaveEnd(Code, Msg){
	 if(Code >= 0) {
		 ComShowCodeMessage("COM12150");// saving success message
		 mySheet.DoSearch("CLV_TRN_0001GS.do");
		 } else {
			 ComShowCodeMessage("COM12149"); // saving failure message
		}
 }

function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) {
    ComOpenWait(false);
}


function CLV_TRN_0001() {
	this.processButtonClick = processButtonClick;
	this.setSheetObject = setSheetObject;
	this.loadPage = loadPage;
	this.initSheet = initSheet;
	this.initControl = initControl;
	this.doActionIBSheet = doActionIBSheet;
	this.setTabObject = setTabObject;
	this.validateForm = validateForm;
}