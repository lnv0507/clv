var sheetObjects = new Array();
var sheetCnt = 0;
document.onclick = processButtonClick;
function setSheetObject(sheetObj) {
    sheetObjects[sheetCnt++] = sheetObj;
}

function loadPage() {
    // generate Grid Layout
    for (i = 0; i < sheetObjects.length; i++) {
        ComConfigSheet(sheetObjects[i]);
        //ComConfigSheet: set Button
        initSheet(sheetObjects[i], i + 1);
        ComEndConfigSheet(sheetObjects[i]);
        //ComEndConfigSheet: set text
    }
    doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
    doActionIBSheet(sheetObjects[1], document.form, IBSEARCH);
    // auto search data after loading finish.

}
/**
 * 
 */
function initSheet(sheetObj) {
    var sheetID = sheetObj.id;
    switch (sheetID) {
        case "sheet1":
            with(sheetObj) {
                var headTitle = "|System|Cd ID|Cd Name|Length|Cd Type|Table Name|Description Remark|Flag|Create User|Create Date|Update User|Update Date";
                SetConfig({ SearchMode: 2, MergeSheet: 5, Page: 20, FrozenCol: 0, DataRowMerge: 1 }); 
                //SetConFig: configure how to fetch initialized sheet, location of frozen rows or columns and other basic configurations.
                	// SearchMode: 2 (is where you can configure search mode)
    				// LazyLoad mode
    				// Search all data and display search result data on the screen by page as set in Page property value according to the scroll location
                	// MergeSheet: 5 (is where you can configure merge styles)
    				// Value: msHeaderOnly
    				// Allow merge in the header rows only
                	// FrozenCol: 0 (is where you can select the frozen column count in the left)
                	// DataRowMerge: 1 (Whether to allow horizontal merge of the entire row.)
                var info = { Sort: 1, ColMove: 1, HeaderCheck: 0, ColResize: 1 };
                //Define header functions such as sorting and column movement permissions in json format
            		// Sort: 1 (allow sorting by clicking on the header)
            		// ColMove: 1 (allow column movement in header)
            		// HeaderCheck : 0 (the CheckAll in the header is not checked)
            		// ColResize: 1 (allow resizing of column width)
                var headers = [{ Text: headTitle, Align: "Center" }];
                // Define header title and alignment in json format.
                InitHeaders(headers, info);
                // Define the header title and function using this method.
                var cols = [{ Type: "Status", Hidden: 0, Width: 30, Align: "Center", ColMerge: 0, SaveName: "ibflag" },
                    { Type: "Text", Hidden: 0, Width: 80, Align: "Center", ColMerge: 1, SaveName: "ownr_sub_sys_cd", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Text", Hidden: 0, Width: 80, Align: "Center", ColMerge: 0, SaveName: "intg_cd_id", KeyField: 1, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 0, InsertEdit: 1, EditLen: 3 },
                    { Type: "Text", Hidden: 0, Width: 80, Align: "Center", ColMerge: 0, SaveName: "intg_cd_nm", KeyField: 1, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Int", Hidden: 0, Width: 80, Align: "Center", ColMerge: 0, SaveName: "intg_cd_len", KeyField: 1, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Text", Hidden: 0, Width: 400, Align: "Left", ColMerge: 0, SaveName: "intg_cd_tp_cd", KeyField: 1, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1, MultiLineText: 1 },
                    { Type: "Text", Hidden: 0, Width: 250, Align: "Left", ColMerge: 0, SaveName: "mng_tbl_nm", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Text", Hidden: 0, Width: 250, Align: "Left", ColMerge: 0, SaveName: "intg_cd_desc", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Combo", Hidden: 0, Width: 250, Align: "Left", ColMerge: 0, SaveName: "intg_cd_use_flg", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1, ComboCode: "N|Y", ComboText: "N|Y" },
                    { Type: "Text", Hidden: 0, Width: 250, Align: "Left", ColMerge: 0, SaveName: "cre_usr_id", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Date", Hidden: 0, Width: 250, Align: "Left", ColMerge: 0, SaveName: "cre_dt", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Text", Hidden: 0, Width: 250, Align: "Left", ColMerge: 0, SaveName: "upd_usr_id", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                    { Type: "Date", Hidden: 0, Width: 250, Align: "Left", ColMerge: 0, SaveName: "upd_dt", KeyField: 0, CalcLogic: "", Format: "", PointCount: 0, UpdateEdit: 1, InsertEdit: 1 },
                ];
                // Type			 (String) : Column data type
                // Hidden 		 (Boolean): Whether a column is hidden
                // Width		 (Number) : Column width
                // Align 		 (String) : Data alignment
                // ColMerge 	 (Boolean): Whether to allow column merging
                // SaveName		 (String) : A parameter name used to save or search data
                // KeyField 	 (Boolean): Required fields
                // UpdateEdit    (Boolean): Whether to allow data editing when transaction is in "Search" state
                // InsertEdit 	 (Boolean): Whether to allow data editing when transaction is in "Insert" state
                // EditLen 	     (Number) : Editable data legnth
                // ComboText 	 (String) : Combo list text string group
                // ComboCode 	 (String) : Combo list code group
                // MultiLineText (Boolean): Whether to use multilines
                InitColumns(cols);
                //InitColums:Configure data type, format and functionality of each column.
                SetWaitImageVisible(0);
                //SetWaitImageVisible:Set not to display waiting image for processing
                SetSheetHeight(300);
            }
            break;
        case "sheet2":
            with(sheetObj) {
                var headTitle = "|CD ID|Cd Val|Display Name|Description Remark|Order";
                SetConfig({ SearchMode: 2, MergeSheet: 5, Page: 20, FrozenCol: 0, DataRowMerge: 1 });
                var info = { Sort: 1, ColMove: 1, HeaderCheck: 0, ColResize: 1 };
                var headers = [{ Text: headTitle, Align: "Center" }];
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
/**
 * Using Button equivalent button name
 */
function processButtonClick() {
	var sheetObjMas = sheetObjects[0];
	var sheetObjDtl = sheetObjects[1];
    var formObj = document.form;
    try {
        var srcName = ComGetEvent("name");
        // ComGetEvent: get input value
        switch (srcName) {
            case "btn_rowadd_ms":
                doActionIBSheet(sheetObjMas, formObj, IBINSERT);
                break;
            case "btn_rowadd_dl":
                doActionIBSheet(sheetObjDtl, formObj, IBINSERT);
                break;
            case "btn_rowdelete_ms":
                doActionIBSheet(sheetObjMas, formObj, IBDELETE);
                break;
            case "btn_rowdelete_dl":
                doActionIBSheet(sheetObjDtl, formObj, IBDELETE);
                break;
            case "btn_Retrieve":
                doActionIBSheet(sheetObjMas, formObj, IBSEARCH);
                break;
            case "btn_Save":
            	if(confirm("Do you save selected codes?")){
            		if ((sheetObjMas.RowCount("I") + sheetObjMas.RowCount("U") + sheetObjMas.RowCount("D")) > 0) {
            			//RowCount: Check the total data row count.
                    doActionIBSheet(sheetObjMas, formObj, IBSAVE);
            		}
            		if ((sheetObjDtl.RowCount("I") + sheetObjDtl.RowCount("U") + sheetObjDtl.RowCount("D")) > 0) {
                    doActionIBSheet(sheetObjDtl, formObj, IBSAVE);
            		}
            	}
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
/**
 * 
 * @param sheetObj
 * @param formObj
 * @param sAction
 */
function doActionIBSheet(sheetObj, formObj, sAction) {
    switch (sAction) {
        case IBSEARCH:
             ComOpenWait(true);
            if (sheetObj.id == 'sheet1') {
                formObj.f_cmd.value = SEARCH;
            } else if (sheetObj.id == 'sheet2') {
                formObj.f_cmd.value = SEARCH01;
            }
            var loadData = sheetObj.DoSearch("CLV_TRN_0002GS.do", FormQueryString(formObj));
            //DoSearch: Connect to search page to read search XML, and then load XML data internally in IBSheet
            //FormQueryString: change data of input -> string
            sheetObj.LoadSearchData(loadData, { Sync: 1 });
            break;
        case IBSAVE:
        	 ComOpenWait(true);
            if (sheetObj.id == 'sheet1') {
                formObj.f_cmd.value = MULTI;
            } 
            if (sheetObj.id == 'sheet2') {
            	console.log("hello");
                formObj.f_cmd.value = MULTI01;
            }
            var saveString = sheetObj.GetSaveString();
            //GetSaveString: Return a string of data Query String used for saving
            var loadSave = sheetObj.GetSaveData("CLV_TRN_0002GS.do", saveString, FormQueryString(formObj));
            sheetObj.LoadSaveData(loadSave);
            break;
        case IBINSERT:
        	 ComOpenWait(true);
            var cdId = sheetObjects[0].GetCellValue(sheetObjects[0].GetSelectRow(), "intg_cd_id");
            //GetCellValue: get value cell have id intg_cd_id
            //GetSelectRow: get row now.
            if (sheetObj.id == 'sheet2') {
                sheetObj.DataInsert();
                sheetObj.SetCellValue(sheetObj.GetSelectRow(), "intg_cd_id", cdId);
                //SetCellValue set value of id sheet 2 === id sheet master
            } else {
                sheetObj.DataInsert();
            }
            ComOpenWait(false);

            break;
        case IBDELETE:
        	 ComOpenWait(true);
            var rowNow = sheetObj.GetSelectRow();
            sheetObj.SetCellValue(rowNow, "ibflag", "D");
            sheetObj.SetRowHidden(rowNow, 1);
            //SetRowHidden: hidden row select
            if (sheetObj.id == 'sheet1') {
                sheetObj.SetRowStatus(rowNow, "D");
                formObj.f_cmd.value = MULTI;
                var saveStr = sheetObj.GetSaveString();
                if (sheetObj.IsDataModified && saveStr == "") return;
                //IsDataModified: Check transaction status of data rows
                var rtnData = sheetObj.GetSaveData("CLV_TRN_0002GS.do", saveStr, FormQueryString(formObj));
                sheetObj.LoadSaveData(rtnData);
            }
            ComOpenWait(false);
            break;
    }
}

/*handle duplicate code and check 3 char
 * when change value and out line Col intg_cd_id if 3 char NOT upper and A-Z 
 * result comshowmessage
 */
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
    }
}
/**
 * 
 * @param Code
 * @param Msg
 */
function sheet1_OnSaveEnd(Code, Msg) {
	ComOpenWait(false);
    if (Code >= 0) {
        ComShowCodeMessage("COM12150"); // saving success message
        sheet1.DoSearch("CLV_TRN_0002GS.do");
    } else {
        ComShowCodeMessage("COM12149"); // saving failure message
    }
}
/**
 * 
 * @param Code
 * @param Msg
 */
function sheet2_OnSaveEnd(Code, Msg) {
	ComOpenWait(false);
    if (Code >= 0) {
        ComShowCodeMessage("COM12150"); // saving success message
        sheet1.DoSearch("CLV_TRN_0002GS.do");
    } else {
        ComShowCodeMessage("COM12149"); // saving failure message
    }
}
/**
 * 
 * @param sheetObj
 * @param Code
 * @param Msg
 * @param StCode
 * @param StMsg
 */
function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) {
    ComOpenWait(false);
}
/**
 * 
 * @param sheetObj
 * @param Code
 * @param Msg
 * @param StCode
 * @param StMsg
 */
function sheet2_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) {
    ComOpenWait(false);
    document.form.s_intg_cd_id.value = "";
}
/**
 * 
 * @param sheetObj
 * @param Row
 * @param Col
 */
function sheet1_OnDblClick(sheetObj, Row, Col) {
    // Set to move to another page when a row is double-clicked
    document.form.s_intg_cd_id.value = sheetObjects[0].GetCellValue(Row, "intg_cd_id");
    doActionIBSheet(sheetObjects[1], document.form, IBSEARCH);

}