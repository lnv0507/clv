var sheetObjects = new Array();
var sheetCnt = 0;
var rowcount = 0;
document.onclick = processButtonClick;


function setSheetObject(sheetObj) {
    sheetObjects[sheetCnt++] = sheetObj;
}

function loadPage() {
    // generate Grid Layout
    for (i = 0; i < sheetObjects.length; i++) {
        ComConfigSheet(sheetObjects[i]);
        initSheet(sheetObjects[i], i + 1);
        ComEndConfigSheet(sheetObjects[i]);
    }
    doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
    doActionIBSheet(sheetObjects[1], document.form, IBSEARCH);

    // auto search data after loading finish.

}

function initSheet(sheetObj, sheetNo) {
    var cnt = 0;
    var sheetID = sheetObj.id;
    switch (sheetID) {
        case "sheet1":
            with(sheetObj) {
                var HeadTitle = "|System|Cd ID|Cd Name|Length|Cd Type|Table Name|Description Remark|Flag|Create User|Create Date|Update User|Update Date";
                var headCount = ComCountHeadTitle(HeadTitle);
                SetConfig({ SearchMode: 2, MergeSheet: 5, Page: 20, FrozenCol: 0, DataRowMerge: 1 });
                var info = { Sort: 1, ColMove: 1, HeaderCheck: 0, ColResize: 1 };
                var headers = [{ Text: HeadTitle, Align: "Center" }];
                InitHeaders(headers, info);

                var cols = [{ Type: "Status", Hidden: 0, Width: 30, Align: "Center", ColMerge: 0, SaveName: "ibflag" },
                    { Type: "Text", Hidden: 0, Width: 80, Align: "Center", ColMerge: 1, SaveName: "ownr_sub_sys_cd", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Text", Hidden: 0, Width: 80, Align: "Center", ColMerge: 0, SaveName: "intg_cd_id", KeyField: 1, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 0, InsertEdit: 1, EditLen: 3 },
                    { Type: "Text", Hidden: 0, Width: 80, Align: "Center", ColMerge: 0, SaveName: "intg_cd_nm", KeyField: 1, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Text", Hidden: 0, Width: 80, Align: "Center", ColMerge: 0, SaveName: "intg_cd_len", KeyField: 1, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Text", Hidden: 0, Width: 400, Align: "Left", ColMerge: 0, SaveName: "intg_cd_tp_cd", KeyField: 1, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1, MultiLineText: 1 },
                    { Type: "Text", Hidden: 0, Width: 250, Align: "Left", ColMerge: 0, SaveName: "mng_tbl_nm", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Text", Hidden: 0, Width: 250, Align: "Left", ColMerge: 0, SaveName: "intg_cd_desc", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Combo", Hidden: 0, Width: 250, Align: "Left", ColMerge: 0, SaveName: "intg_cd_use_flg", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1, ComboCode: "N|Y", ComboText: "N|Y" },
                    { Type: "Text", Hidden: 0, Width: 250, Align: "Left", ColMerge: 0, SaveName: "cre_usr_id", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Date", Hidden: 0, Width: 250, Align: "Left", ColMerge: 0, SaveName: "cre_dt", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Text", Hidden: 0, Width: 250, Align: "Left", ColMerge: 0, SaveName: "upd_usr_id", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Date", Hidden: 0, Width: 250, Align: "Left", ColMerge: 0, SaveName: "upd_dt", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                ];
                InitColumns(cols);
                SetWaitImageVisible(0);
                SetSheetHeight(300);
            }
            break;
        case "sheet2":
            with(sheetObj) {
                var HeadTitle = "|CD ID|Cd Val|Display Name|Description Remark|Order";
                var headCount = ComCountHeadTitle(HeadTitle);
                SetConfig({ SearchMode: 2, MergeSheet: 5, Page: 20, FrozenCol: 0, DataRowMerge: 1 });
                var info = { Sort: 1, ColMove: 1, HeaderCheck: 0, ColResize: 1 };
                var headers = [{ Text: HeadTitle, Align: "Center" }];
                InitHeaders(headers, info);

                var cols = [{ Type: "Status", Hidden: 0, Width: 30, Align: "Center", ColMerge: 0, SaveName: "ibflag" },
                    { Type: "Text", Hidden: 0, Width: 80, Align: "Center", ColMerge: 0, SaveName: "intg_cd_id", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 0, InsertEdit: 1 },
                    { Type: "Text", Hidden: 0, Width: 80, Align: "Center", ColMerge: 0, SaveName: "intg_cd_val_ctnt", KeyField: 1, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 0, InsertEdit: 1, EditLen: 8 },
                    { Type: "Text", Hidden: 0, Width: 80, Align: "Center", ColMerge: 0, SaveName: "intg_cd_val_dp_desc", KeyField: 1, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Text", Hidden: 0, Width: 80, Align: "Center", ColMerge: 0, SaveName: "intg_cd_val_desc", KeyField: 1, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Text", Hidden: 0, Width: 80, Align: "Center", ColMerge: 0, SaveName: "intg_cd_val_dp_seq", KeyField: 1, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 }
                ];
                InitColumns(cols);
                SetWaitImageVisible(0);
                SetSheetHeight(300);
            }
            break;
    }
}

function resizeSheet() {  
    ComResizeSheet(sheetObjects[0]);
}

function processButtonClick() {
    var sheetObj = sheetObjects[0];
    var sheetObjDtl = sheetObjects[1];
    var formObj = document.form;
    try {
        var srcName = ComGetEvent("name");
        switch (srcName) {
            case "btn_rowadd_ms":
                doActionIBSheet(sheetObj, formObj, IBINSERT);
                break;
            case "btn_rowadd_dl":
                doActionIBSheet(sheetObjDtl, formObj, IBINSERT);
                break;
            case "btn_rowdelete_ms":
                doActionIBSheet(sheetObj, formObj, IBDELETE);
                break;
            case "btn_rowdelete_dl":
                doActionIBSheet(sheetObjDtl, formObj, IBDELETE);
                break;
            case "btn_Retrieve":
                doActionIBSheet(sheetObj, formObj, IBSEARCH);
                break;
            case "btn_Save":
                doActionIBSheet(sheetObj, formObj, IBSAVE);
                doActionIBSheet(sheetObjDtl, formObj, IBSAVE);
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

function doActionIBSheet(sheetObj, formObj, sAction) {
    switch (sAction) {
        case IBSEARCH:
            // ComOpenWait(true);
            if (sheetObj.id == 'sheet1') {
                formObj.f_cmd.value = SEARCH;
            } else if (sheetObj.id == 'sheet2') {
                formObj.f_cmd.value = SEARCH01;
            }
            var loadData = sheetObj.DoSearch("CLV_TRN_0002GS.do", FormQueryString(formObj));
            sheetObj.LoadSearchData(loadData, { Sync: 1 });
            break;
        case IBSAVE:
            if (sheetObj.id == 'sheet1') {
                formObj.f_cmd.value = MULTI;
            } else if (sheetObj.id == 'sheet2') {
                formObj.f_cmd.value = MULTI01;
            }
            var saveString = sheetObj.GetSaveString();
            var loadSave = sheetObj.GetSaveData("CLV_TRN_0002GS.do", saveString, FormQueryString(formObj));
   	     	sheetObj.LoadSaveData(loadSave);
// sheetObj.DoSave("CLV_TRN_0002GS.do", FormQueryString(formObj));
            break;
        case IBINSERT:
            var cdId = sheetObjects[0].GetCellValue(sheetObjects[0].GetSelectRow(), "intg_cd_id");
            if (sheetObj.id == 'sheet2') {
                sheetObj.DataInsert();
                sheetObj.SetCellValue(sheetObj.GetSelectRow(), "intg_cd_id", cdId);
            } else {
                rowcount = sheetObj.RowCount();
                row = sheetObj.DataInsert();
            }

            break;
        case IBDELETE:
            var rowNow = sheetObj.GetSelectRow();
            sheetObj.SetCellValue(rowNow, "ibflag", "D");
            sheetObj.SetRowHidden(rowNow, 1);
            break;
    }
}


// handle duplicate code and check 3 char
function sheet1_OnChange(sheetObj, Row, Col) {
    var check = /([A-Z]{3})/g;
    if (sheetObj.ColSaveName(Col) === "intg_cd_id") {
        var code = sheetObj.GetCellValue(Row, Col);
        // check regexp
        var arrCodeTrue = code.match(check);
        // if haven't arrCodeTrue = null
        if (arrCodeTrue === null) {
            ComShowCodeMessage("COM12114");
            sheetObj.SetCellValue(Row, Col, "", 0);
            return;
        }
        // get List code now
        // for (var i = 1; i < sheetObj.RowCount(); i++) {
        // var codeCheck = sheetObj.GetCellValue(i, Col);
        // // check code new = code now => duplicate
        // if (code === codeCheck) {
        // ComShowCodeMessage('COM131302', code);
        // sheetObj.SetCellValue(Row, Col, "", 0);
        // return;
        // }
        // }
    }


}

function sheet1_OnSaveEnd(Code, Msg) {
    if (Code >= 0) {
        ComShowCodeMessage("COM12150"); // saving success message
        sheet1.DoSearch("CLV_TRN_0002GS.do");
    } else {
        ComShowCodeMessage("COM12149"); // saving failure message
    }
}

function sheet2_OnSaveEnd(Code, Msg) {
    if (Code >= 0) {
        ComShowCodeMessage("COM12150"); // saving success message
        sheet1.DoSearch("CLV_TRN_0002GS.do");
    } else {
        ComShowCodeMessage("COM12149"); // saving failure message
    }
}

function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) {
    ComOpenWait(false);
}

function sheet2_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) {
    ComOpenWait(false);
    document.form.s_intg_cd_id.value = "";
}

function sheet1_OnDblClick(sheetObj, Row, Col) {
    // Set to move to another page when a row is double-clicked

    document.form.s_intg_cd_id.value = sheetObjects[0].GetCellValue(Row, "intg_cd_val_ctnt");
    doActionIBSheet(sheetObjects[1], document.form, IBSEARCH);

}