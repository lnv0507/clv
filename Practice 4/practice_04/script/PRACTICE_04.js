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
        	var headTitle="STS|Chk|Carrier|Rev. Lane|Vendor Code|Customer Code|Customer Code|Trade|Delete Flag|Create Date|Create User ID|Update Date|Update User ID";
        	SetConfig( { SearchMode:2, MergeSheet:5, Page:20, FrozenCol:0, DataRowMerge:1 } ); 
                //SetConFig: configure how to fetch initialized sheet, location of frozen rows or columns and other basic configurations.
                	// SearchMode: 2 (is where you can configure search mode)
    				// LazyLoad mode
    				// Search all data and display search result data on the screen by page as set in Page property value according to the scroll location
                	// MergeSheet: 5 (is where you can configure merge styles)
    				// Value: msHeaderOnly
    				// Allow merge in the header rows only
                	// FrozenCol: 0 (is where you can select the frozen column count in the left)
                	// DataRowMerge: 1 (Whether to allow horizontal merge of the entire row.)
        	var info    = { Sort:1, ColMove:1, HeaderCheck:0, ColResize:1 };
                //Define header functions such as sorting and column movement permissions in json format
            		// Sort: 1 (allow sorting by clicking on the header)
            		// ColMove: 1 (allow column movement in header)
            		// HeaderCheck : 0 (the CheckAll in the header is not checked)
            		// ColResize: 1 (allow resizing of column width)
                var headers = [{ Text: headTitle, Align: "Center" }];
                // Define header title and alignment in json format.
                InitHeaders(headers, info);
                // Define the header title and function using this method.
                var cols = [
                             {Type:"Status",    Hidden:1, Width:50,  Align:"Center",  SaveName:"ibflag"}, 
			            {Type:"DelCheck",  Hidden:0, Width:50,  Align:"Center",  SaveName:"del_chk"},
				        {Type:"PopupEdit",     Hidden:0, Width:100, Align:"Center",  SaveName:"jo_crr_cd",     KeyField:1, UpdateEdit:0, InsertEdit:1, AcceptKeys:"E", InputCaseSensitive:1, EditLen:3},
				        {Type:"Combo",     Hidden:0, Width:100, Align:"Center",  SaveName:"rlane_cd",      KeyField:1, UpdateEdit:0, InsertEdit:1, ComboCode:lnCds, ComboText: lnCds},
				        {Type:"PopupEdit", Hidden:0, Width:100, Align:"Center",  SaveName:"vndr_seq",      KeyField:1, UpdateEdit:1, InsertEdit:1, AcceptKeys:"N", EditLen:6},
				        {Type:"Popup",     Hidden:0, Width:50,  Align:"Center",  SaveName:"cust_cnt_cd",   KeyField:1, UpdateEdit:1, InsertEdit:1, AcceptKeys:"E", InputCaseSensitive:1, EditLen:2}, 
					    {Type:"Popup",     Hidden:0, Width:100, Align:"Center",  SaveName:"cust_seq",      KeyField:1, UpdateEdit:1, InsertEdit:1, AcceptKeys:"N", EditLen: 6}, 
					    {Type:"PopupEdit", Hidden:0, Width:100, Align:"Center",  SaveName:"trd_cd",        KeyField:0, UpdateEdit:1, InsertEdit:1, AcceptKeys:"E", InputCaseSensitive:1, EditLen:3},
					    {Type:"Combo",     Hidden:0, Width:70,  Align:"Center",  SaveName:"delt_flg",      KeyField:0, UpdateEdit:1, InsertEdit:1, ComboCode:"N|Y",  ComboText:"N|Y"}, 
					    {Type:"Text",      Hidden:0, Width:200, Align:"Center",  SaveName:"cre_dt",        KeyField:0, UpdateEdit:0, InsertEdit:0}, 
					    {Type:"Text",      Hidden:0, Width:200, Align:"Left",    SaveName:"cre_usr_id",    KeyField:0, UpdateEdit:0, InsertEdit:0}, 
					    {Type:"Text",      Hidden:0, Width:200, Align:"Center",  SaveName:"upd_dt",        KeyField:0, UpdateEdit:0, InsertEdit:0}, 
					    {Type:"Text",      Hidden:0, Width:200, Align:"Left",    SaveName:"upd_usr_id",    KeyField:0, UpdateEdit:0, InsertEdit:0}
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
    }
}
/**
 * Using Button equivalent button name
 */
function processButtonClick() {
	//get sheet1
	var sheetObject1 = sheetObjects[0];
	var comboObject1 = comboObjects[0];
    //get form object
    var formObj = document.form;
    //get name 
    var srcName = ComGetEvent("name");
    //do nothing if srcName is null
    if (srcName == null) {
        return;
    }
    //base on name to handle event onclick
    switch (srcName) {
    	//button Retrieve
        case "btn_Retrieve":
        	//call doActionIBSheet with action is IBSEARCH
            doActionIBSheet(sheetObject1,comboObject1, formObj, IBSEARCH);
//        	console.log(FormQueryString(formObj));
            break;
        case "btn_calendar_dt_fr":
			 var calendar = new ComCalendar();
			 calendar.select(formObj.s_cre_dt_fm, "yyyy-MM-dd");
			 break;
        case "btn_calendar_dt_to":
			 var calendar = new ComCalendar();
			 calendar.select(formObj.s_cre_dt_to, "yyyy-MM-dd");
			 break;
        case "btn_Add":
	        sheetObject1.DataInsert();
	        break;
        case "btn_New":
        	doActionIBSheet(sheetObject1, comboObject1, formObj, IBRESET);
	        break;	
        case "btn_Delete":
			doActionIBSheet(sheetObject1, comboObject1, formObj, IBDELETE);
            break;
        case "btn_DownExcel":
        	doActionIBSheet(sheetObject1,comboObject1, formObj, IBDOWNEXCEL);
        	break;
        case "btn_Save":
        	// form need to be validated
//            if (validateForm(sheetObject1)) {
            	//call doActionIBSheet with action is IBSAVE
                doActionIBSheet(sheetObject1, comboObject1, formObj, IBSAVE);
//            }
            break;
        default:
            break;
    }
}
/**
 * 
 * @param sheetObj
 * @param formObj
 * @param sAction
 */
function doActionIBSheet(sheetObj, formObj, sAction) {
	//Check or configure whether to display debugging message
	//-1: Start system popup debugging
	//0: End all debugging
sheetObj.ShowDebugMsg(0);

//Handle base on sAction
switch (sAction) {
    case IBSEARCH: // retrieve
        formObj.f_cmd.value = SEARCH;
        if(!isValidDate(formObj.s_cre_dt_fr.value,formObj.s_cre_dt_to.value)){
        	ComShowMessage("Start date must be earlier than end date");
        	break;
        }
        sheetObj.DoSearch("PRACTICE_0004GS.do", FormQueryString(formObj));
        break;
    case IBSAVE: // save
    	//set value for f_cmd input which is hidden on UI
        formObj.f_cmd.value = MULTI;
        
        //call server to save
        //ObjId.DoSearch(PageUrl, [Param], [Opt])
        sheetObj.DoSave("PRACTICE_0004GS.do", FormQueryString(formObj));
        console.log("SAVE");
        break;
    
    case IBDELETE: // save
    	if(sheetObj.RowCount("I")!=0||sheetObj.RowCount("U")!=0){
			ComShowMessage("Please save your data before deleting");
			break;
		}
    	if(getCellValue(sheetObj,"ibflag")=="I"){//Add Row(add data or not) then Delete Row
			sheetObj.RowDelete();
			break;
		}
    	//set value for f_cmd input which is hidden on UI
        formObj.f_cmd.value = MULTI;
        
        //call server to save
        //ObjId.DoSearch(PageUrl, [Param], [Opt])
        sheetObj.SetCellValue(sheetObj.GetSelectRow(), "ibflag", "D");
        sheetObj.DoSave("PRACTICE_0004GS.do", FormQueryString(formObj));
        break;
        
    case IBRESET: // retrieve
    	formObj.reset();
		sheetObj.RemoveAll();
		checkAllItem(comboObj);
		doActionIBSheet(sheetObj, comboObj, formObj, IBSEARCH);
        break;
        
    case IBDOWNEXCEL:
    	//if sheet don't have data
        if (sheetObj.RowCount() < 1) {
        	//show message base on code message
            ComShowCodeMessage("COM132501");
        } else {
        	//download sheet
        		//DownCols: DownCols
        		//makeHiddenSkipCol: ignore hidden column
        	sheetObj.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj)});
        }
        break;
    default:
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
        sheet1.DoSearch("CLV_TRN_0004GS.do");
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
 * @param Row
 * @param Col
 */
function sheet1_OnDblClick(sheetObj, Row, Col) {
    // Set to move to another page when a row is double-clicked
    document.form.s_intg_cd_id.value = sheetObjects[0].GetCellValue(Row, "intg_cd_id");
    doActionIBSheet(sheetObjects[1], document.form, IBSEARCH);

}