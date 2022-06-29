var sheetObjects = new Array();
var sheetCnt = 0;
var comboObjects = new Array();
var comboCnt = 0;
document.onclick = processButtonClick;

function sheet1_OnPopupClick(sheetObj,Row, Col){
	var colName = sheetObj.ColSaveName(Col);
	
	switch(colName){
		case "cust_cnt_cd":
		case "cust_seq":
			ComOpenPopup('/opuscntr/CLV_POPUP.do', 750, 450, 'setCustCd', '1,0,1,1,1,1', true, Row,Col);
			break;
		case "vndr_seq":
			ComOpenPopup('/opuscntr/COM_COM_0007.do', 750, 450, 'setVndrCd', '1,0,1', true, Row, Col);
			break;
		case "trd_cd":
			ComOpenPopup('/opuscntr/COM_COM_0012.do', 750, 450, 'setTrdCd', '1,0,0,1,1,1,1,1', true, Row, Col);
			break;
		case "jo_crr_cd":
			ComOpenPopup('/opuscntr/COM_ENS_0N1.do', 750, 450, 'setCrrCd', '1,0,0,1,1,1,1,1', true, Row, Col);
			break;
			/**
			 * This function open the pop-up
			 * 
			 * @param: url: the url of the popup to be called
			 * @param: width: the width of the popup
			 * @param: height: the height of the popup
			 * @param: function: function return data to parent window
			 * @param: display: whether column of the grid in popup is hidden
			 *         (1: visible, 0: invisible) bModal: whether the popup is
			 *         modal (default: false)
			 */
	}
}

function setSheetObject(sheetObj) {
    sheetObjects[sheetCnt++] = sheetObj;
}

function setComboObject(comboObj) {
    comboObjects[comboCnt++] = comboObj;
}

function loadPage() {
    // generate Grid Layout
    for (let i = 0; i < sheetObjects.length; i++) {
        ComConfigSheet(sheetObjects[i]);
        // ComConfigSheet: set Button
        initSheet(sheetObjects[i], i + 1);
        ComEndConfigSheet(sheetObjects[i]);
        // ComEndConfigSheet: set text
    }
    for (let j = 0; j < comboObjects.length; j++) {
        initCombo(comboObjects[j], j + 1);
    }
    initControl();
    doActionIBSheet(sheetObjects[0], comboObjects[0], document.form, IBSEARCH);

    // auto search data after loading finish.

}
	// check input Vendor only number
function initControl() {
    document.getElementById('s_vndr_seq').addEventListener('keypress', function() { ComKeyOnlyNumber(this); });
}

function initCombo(comboObj) {
    comboObj.SetTitle("All")
    comboObj.SetTitleVisible(true);
    // get title show head combo box
    comboObj.SetEnableAllCheckBtn(true);
    // setEnableCheckAll: show check all check box
    comboObj.SetMultiSelect(1);
    /**
	 * @param: SetMultiSelect: 0 select only one 1 select much item
	 */ 
    addComboItem(comboObj, crrCds);
    checkAllItem(comboObj);
}

/**
 * 
 */
function initSheet(sheetObj) {
    var sheetID = sheetObj.id;
    switch (sheetID) {
        case "sheet1":
            with(sheetObj) {
                var headTitle = "|Chk|Carrier|Rev. Lane|Vendor Code|Customer Code|Customer Code|Trade|Delete Flag|Create Date|Create User ID|Update Date|Update User ID";
                SetConfig({ SearchMode: 2, MergeSheet: 5, Page: 20, FrozenCol: 0, DataRowMerge: 1 });
                /**
				 * @param: SetConFig: configure how to fetch initialized sheet,
				 *         location of frozen rows or columns and other basic
				 *         configurations.
				 * @param: SearchMode: 2 (is where you can configure search
				 *         mode) LazyLoad mode Search all data and display
				 *         search result data on the screen by page as set in
				 *         Page property value according to the scroll location
				 * @param: MergeSheet: 5 (is where you can configure merge
				 *         styles) Value: msHeaderOnly Allow merge in the header
				 *         rows only
				 * @param: FrozenCol: 0 (is where you can select the frozen
				 *         column count in the left)
				 * @param: DataRowMerge: 1 (Whether to allow horizontal merge of
				 *         the entire row.)
				 */
                var info = { Sort: 1, ColMove: 1, HeaderCheck: 0, ColResize: 1 };
                /**
				 * Define header functions such as sorting and column movement
				 * permissions in json format
				 * 
				 * @param: Sort: 1 (allow sorting by clicking on the header)
				 * @param: ColMove: 1 (allow column movement in header)
				 * @param: HeaderCheck : 0 (the CheckAll in the header is not
				 *         checked)
				 * @param: ColResize: 1 (allow resizing of column width)
				 */
                var headers = [{ Text: headTitle, Align: "Center" }];
                // Define header title and alignment in json format.
                InitHeaders(headers, info);
                // Define the header title and function using this method.
                var cols = [
                    { Type: "Status", Hidden: 1, Width: 50, Align: "Center", SaveName: "ibflag" },
                    { Type: "DelCheck", Hidden: 0, Width: 50, Align: "Center", SaveName: "del_chk" },
                    { Type: "PopupEdit", Hidden: 0, Width: 100, Align: "Center", SaveName: "jo_crr_cd", KeyField: 1, UpdateEdit: 0, InsertEdit: 1, AcceptKeys: "E", InputCaseSensitive: 1, EditLen: 3 },
                    { Type: "Combo", Hidden: 0, Width: 100, Align: "Center", SaveName: "rlane_cd", KeyField: 1, UpdateEdit: 0, InsertEdit: 1, ComboCode: lnCds, ComboText: lnCds },
                    { Type: "Popup", Hidden: 0, Width: 100, Align: "Center", SaveName: "vndr_seq", KeyField: 1, UpdateEdit: 1, InsertEdit: 1, AcceptKeys: "N", EditLen: 6 },
                    { Type: "Popup", Hidden: 0, Width: 50, Align: "Center", SaveName: "cust_cnt_cd", KeyField: 1, UpdateEdit: 1, InsertEdit: 1, AcceptKeys: "E", InputCaseSensitive: 1, EditLen: 2 },
                    { Type: "Popup", Hidden: 0, Width: 100, Align: "Center", SaveName: "cust_seq", KeyField: 1, UpdateEdit: 1, InsertEdit: 1, AcceptKeys: "N", EditLen: 6 },
                    { Type: "Popup", Hidden: 0, Width: 100, Align: "Center", SaveName: "trd_cd", KeyField: 0, UpdateEdit: 1, InsertEdit: 1, AcceptKeys: "E", InputCaseSensitive: 1, EditLen: 3 },
                    { Type: "Combo", Hidden: 0, Width: 70, Align: "Center", SaveName: "delt_flg", KeyField: 0, UpdateEdit: 1, InsertEdit: 1, ComboCode: "N|Y", ComboText: "N|Y" },
                    { Type: "Text", Hidden: 0, Width: 200, Align: "Center", SaveName: "cre_dt", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },
                    { Type: "Text", Hidden: 0, Width: 200, Align: "Left", SaveName: "cre_usr_id", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },
                    { Type: "Text", Hidden: 0, Width: 200, Align: "Center", SaveName: "upd_dt", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },
                    { Type: "Text", Hidden: 0, Width: 200, Align: "Left", SaveName: "upd_usr_id", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 }
                ];
                /**
				 * @param:Type (String) : Column data type
				 * @param: Hidden (Boolean): Whether a column is hidden
				 * @param: Width (Number) : Column width
				 * @param: Align (String) : Data alignment
				 * @param: ColMerge (Boolean): Whether to allow column merging
				 * @param: SaveName (String) : A parameter name used to save or
				 *         search data
				 * @param: KeyField (Boolean): Required fields
				 * @param: UpdateEdit (Boolean): Whether to allow data editing
				 *         when transaction is in "Search" state
				 * @param InsertEdit
				 *            (Boolean): Whether to allow data editing when
				 *            transaction is in "Insert" state
				 * @param: EditLen (Number) : Editable data legnth
				 * @param ComboText
				 *            (String) : Combo list text string group
				 * @param ComboCode
				 *            (String) : Combo list code group
				 * @param MultiLineText
				 *            (Boolean): Whether to use multilines
				 */ 
                InitColumns(cols);
                // InitColums:Configure data type, format and functionality of
				// each column.
                SetWaitImageVisible(0);
                // SetWaitImageVisible:Set not to display waiting image for
				// processing
                SetSheetHeight(300);
            }
            break;
    }
}
/**
 * Using Button equivalent button name
 */
function processButtonClick() {
    // get sheet1
    var sheetObject1 = sheetObjects[0];
    var comboObject1 = comboObjects[0];
    // get form object
    var formObj = document.form;
    // get name
    var srcName = ComGetEvent("name");
    // do nothing if srcName is null
    if (srcName == null) {
        return;
    }
    // base on name to handle event onclick
    switch (srcName) {
        // button Retrieve
        case "btn_Retrieve":
            // call doActionIBSheet with action is IBSEARCH
            doActionIBSheet(sheetObject1, comboObject1, formObj, IBSEARCH);
            // console.log(FormQueryString(formObj));
            break;
        case "btn_calendar_dt_fr":
            var calendar = new ComCalendar();
            calendar.select(formObj.s_cre_dt_fr, "yyyy-MM-dd");
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
            doActionIBSheet(sheetObject1, comboObject1, formObj, IBDOWNEXCEL);
            break;
        case "btn_Save":
            doActionIBSheet(sheetObject1, comboObject1, formObj, IBSAVE);
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
function doActionIBSheet(sheetObj, comboObj, formObj, sAction) {
    // Check or configure whether to display debugging message
    // -1: Start system popup debugging
    // 0: End all debugging
    sheetObj.ShowDebugMsg(0);

    // Handle base on sAction
    switch (sAction) {
        case IBSEARCH: // retrieve
            ComOpenWait(true);
            formObj.f_cmd.value = SEARCH;
            sheetObj.DoSearch("CLV_TRN_0004GS.do", FormQueryString(formObj));
            // DoSearch: Connect to search page to read search XML, and then
			// load XML data internally in IBSheet
            // ObjId.DoSearch(PageUrl, [Param], [Opt])
            // FormQueryString: change data of input -> string
            break;
        case IBSAVE: // save
        	 ComOpenWait(true);
            formObj.f_cmd.value = MULTI;
            // call server to save
            sheetObj.DoSave("CLV_TRN_0004GS.do", FormQueryString(formObj));
            break;
        case IBDELETE: // Delete
            if (sheetObj.RowCount("I") != 0 || sheetObj.RowCount("U") != 0) {
                ComShowMessage("Please save your data before deleting");
                break;
            }
            if (getCellValue(sheetObj, "ibflag") == "I") { 
            	/*
				 * Add Row(add data or not) then Delete Row
				 */
                sheetObj.RowDelete();
                break;
            }
            // set value for f_cmd input which is hidden on UI
            formObj.f_cmd.value = MULTI;
            // call server to delete
            sheetObj.SetCellValue(sheetObj.GetSelectRow(), "ibflag", "D");
            sheetObj.DoSave("CLV_TRN_0004GS.do", FormQueryString(formObj));
            break;

        case IBRESET: // retrieve
            formObj.reset();
            sheetObj.RemoveAll();
            checkAllItem(comboObj);
            doActionIBSheet(sheetObj, comboObj, formObj, IBSEARCH);
            break;

        case IBDOWNEXCEL:
            // if sheet don't have data
            if (sheetObj.RowCount() < 1) {
                // show message base on code message
                ComShowCodeMessage("COM132501");
            } else {
                // download sheet
                // DownCols: DownCols
                // makeHiddenSkipCol: ignore hidden column
                sheetObj.Down2Excel({ DownCols: makeHiddenSkipCol(sheetObj) });
            }
            break;
        default:
            break;
    }
}
// auto select all item and set default head combobox = All
function checkAllItem(comboObj) {
    var size = comboObj.GetItemCount();
    for (var i = 0; i < size; i++) {
        comboObj.SetItemCheck(i, true);
    }
    document.form.s_carrier_text.value = "All";
    document.form.s_carrier.value = "All";
}
// show item get combo box
function addComboItem(comboObj, comboItems) {
    comboItems = comboItems.split("|");
    for (var i = 0; i < comboItems.length; i++) {
        comboObj.InsertItem(i, comboItems[i], comboItems[i]);
    }
}
// configure POP UP CustCD
function setCustCd(aryPopupData){
	sheetObjects[0].SetCellValue(sheetObjects[0].GetSelectRow(), "cust_cnt_cd", aryPopupData[0][2]);
	sheetObjects[0].SetCellValue(sheetObjects[0].GetSelectRow(), "cust_seq",    aryPopupData[0][3]);
}
//configure POP UP VndrCD
function setVndrCd(aryPopupData){
	sheetObjects[0].SetCellValue(sheetObjects[0].GetSelectRow(), "vndr_seq", aryPopupData[0][2]);
}
//configure POP UP TrdCD
function setTrdCd(aryPopupData){
	sheetObjects[0].SetCellValue(sheetObjects[0].GetSelectRow(), "trd_cd", aryPopupData[0][3]);
}
//configure POP UP CrrCD
function setCrrCd(aryPopupData){
	sheetObjects[0].SetCellValue(sheetObjects[0].GetSelectRow(), "jo_crr_cd", aryPopupData[0][3]);
}
// get value at location select
function getCellValue(sheetObj,colName){
	return sheetObj.GetCellValue(sheetObj.GetSelectRow(), colName);
}


/**
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
 * @param sheetObj
 * @param Code
 * @param Msg
 * @param StCode
 * @param StMsg
 */
function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) {
    ComOpenWait(false);
}


