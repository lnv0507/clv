
let sheetObjects = new Array();
let sheetCnt = 0;
let comboObjects = new Array();
let comboCnt = 0;
let searchSummary = "";
let searchDetail = "";
let tabObjects = new Array();
let tabCnt = 0;
let beforetab = 0;

let isTrueType = (OldText, OldIndex, OldCode, NewText, NewIndex, NewCode) => {
	
	let arrAssign = [OldText, OldIndex, OldCode, NewText, NewIndex, NewCode];
	
	if(arrAssign.some(value => typeof value != "string" )) return;
}

/**
 * Registering IBCombo Object as list parameter : combo_obj adding process for
 * list in case of needing batch processing with other items defining list on
 * the top of source.
 * 
 * @param combo_obj:
 *            String - combo object name.
 */
let setComboObject = (combo_obj) => {
	comboObjects[comboCnt++] = combo_obj;
}
/**
 * Registering IBSheet Object as list adding process for list in case of needing
 * batch processing with other items defining list on the top of source.
 * 
 * @param sheet_obj:
 *            String - sheet name.
 */
let setSheetObject = (sheet_obj) => {
	sheetObjects[sheetCnt++] = sheet_obj;
}
/**
 * Sharing Object into tab
 * 
 * @param tab_obj:
 *            String - tab name.
 */
let setTabObject = (tab_obj) => {
	tabObjects[tabCnt++] = tab_obj;
}
/**
 * format date
 */
let getDateFormat = (obj, sFormat) => {
	
	
	let sVal = String(getArgValue(obj));
	sVal = sVal.replace(/\/|\-|\.|\:|\ /g, "");
	if (ComIsEmpty(sVal))
		return "";
console.log(sVal);
	let retValue = "";
	switch (sFormat) {
	case "ym":
		retValue = sVal.substring(0, 6);
		console.log(retValue);
		break;
	}
	retValue = ComGetMaskedValue(retValue, sFormat);
	console.log(retValue);
	return retValue;
}
// Get month and year to present
// if from month = month to - 2 because avoid over 3month
let initCalendar = () => {
	let formObj = document.form;
	let ymTo = ComGetNowInfo("ym", "-");
	
	formObj.to_acct_yrmon.value = ymTo;
	let ymFrom = ComGetDateAdd(formObj.to_acct_yrmon.value + "-01", "M", -2);
	formObj.fr_acct_yrmon.value = getDateFormat(ymFrom, "ym");
}
// if to date - from date > 88days -> 3month -> overDate
let checkOver3Month = () => {
	let formObj = document.form;
	let fromDate = formObj.fr_acct_yrmon.value.replaceStr("-", "") + "01";
	let toDate = formObj.to_acct_yrmon.value.replaceStr("-", "") + "01";
	
	if (ComGetDaysBetween(fromDate, toDate) > 88)
		return false;
	return true;
}

// check from date always smaller than to date
let isValidDate = () => {
	let from = new Date(document.form.fr_acct_yrmon.value);
	let to = new Date(document.form.to_acct_yrmon.value);
	return from < to;
}

// handle month when press previos or next -> +- 1 value month
let changeMonth = (obj, month) => {
	
	if(obj.value === "") return;
	
	if(obj.value != "") {
		obj.value = ComGetDateAdd(obj.value + "-01", "M", month).substr(0, 7);
	}
}

/**
 * get data = data display all input support check changeTab
 */
let getSearchOption = () => {
	let formObject = document.form;
	let result = formObject.fr_acct_yrmon.value
			+ formObject.to_acct_yrmon.value 
			+ formObject.s_jo_crr_cd.value
			+ formObject.s_rlane_cd.value 
			+ formObject.s_trd_cd.value
	return result;
}

let doActionIBSheet = (sheetObj, formObj, sAction) => {
	
	if(typeof sheetObj != "object") return;
	
	// ComOpenWait(true);
	switch (sAction) {
	// Retrieve button event.
	case IBSEARCH:
		if(sheetObj.id === "sheet1") {
			 searchSummary = getSearchOption();
			formObj.f_cmd.value = SEARCH;
			sheetObj.DoSearch("CLV_TRN_0003GS.do", FormQueryString(formObj),{Sync : 1});
		} 
		if(sheetObj.id === "sheet2"){
			 searchDetail = getSearchOption();
			formObj.f_cmd.value = SEARCH03;
			let xml = sheetObjects[1].GetSearchData("CLV_TRN_0003GS.do", FormQueryString(formObj));
			sheetObjects[1].LoadSearchData(xml,{Sync:1});
		}
		break;
	 case IBDOWNEXCEL:
		 let sizeSheet = sheetObj.RowCount();
		 let indexSheet = sheetObj.id;
		 if(sizeSheet < 1 && indexSheet === "sheet1") {
		 ComShowCodeMessage("COM132501");
		 } 
		 if(sizeSheet >= 1 && indexSheet === "sheet1"){
		 sheetObj.Down2ExcelBuffer(true);
		 sheetObj.Down2Excel({ FileName: 'excel1', SheetName: 'sheet1', DownCols:makeHiddenSkipCol(sheetObj), SheetDesign: 1, Merge: 1 });
		 sheetObjects[1].Down2Excel({ SheetName: 'sheet2', DownCols:makeHiddenSkipCol(sheetObjects[1]), Merge: 1 });
		 sheetObj.Down2ExcelBuffer(false);			
		 }
		 if( indexSheet === "sheet2"){
			formObj.f_cmd.value = COMMAND01;
			/**
			 * @URL: parameter is used to mark the page path where excel display
			 *       data is populated
			 * @ExtendParam: parameter is used to create a get method
			 *               QueryString of search conditions to send to the
			 *               server, which can be retrieved using
			 *               request.getParameter() method from the page set in
			 *               URL parameter
			 * @Filename: parameter is used to set the downloaded excel file
			 *            name. If file extension is set as xls, excel 2003
			 *            format file is downloaded. If xlsx, excel 2007 format
			 *            file is downloaded. If no value is set, an xls file is
			 *            downloaded.
			 * @DownCols: parameter is a string connecting all downloading
			 *            columns using "|". You can use either SaveName or
			 *            column index. If null, all columns are downloaded
			 * @Merge: parameter determines whether to merge columns if adjacent
			 *         header data cells contain same letters. The default is 0
			 * @SheetDesign: parameter determines whether to download header
			 *               color. The default is 0.
			 * @KeyFiledMark: parameter is used to download KeyFIeld mark (*) in
			 *                KeyField column.
			 * @SheetName: name sheet of excel
			 */
			let param ={
					URL:"/opuscntr/Down2ExcelGS.do", // Business logic page
					ExtendParam:FormQueryString(formObj),
					FileName:"Details.xls",
					DownCols: makeHiddenSkipCol(sheetObjects[1]),
					Merge:1,
					SheetDesign:1,
					KeyFieldMark:0,
					SheetName:'Details'
			};
			sheetObjects[1].DirectDown2Excel(param);
			ComOpenWait(false);
		 }
		 break;
	}
}

// reset all sheet and input
let onclickButtonNew = () => {
	ComResetAll();
	initCalendar();
	s_jo_crr_cd.SetItemCheck(0, 1, 1);
	s_rlane_cd.SetEnable(false);
	s_trd_cd.SetEnable(false);
}

/**
 * check present on sheet ? support check changeTab
 * 
 * @returns
 */
let getCurrentSheet = () => {
	return sheetObjects[beforetab];
}

/**
 * click event with name button and handle for button
 */
let processButtonClick = () => {
	let formObj = document.form;
	let fromDate = "From Date";
	let toDate = "To Date"
	try {
		let srcName = ComGetEvent("name");
		// Get event by name which corresponding to button.
		switch (srcName) {
		case "btn_Retrieve":
			// asking user want to retrieve data by month
			// if over 3 months
			if (!checkOver3Month()) {
				// the variable to store user's choose
				ComShowCodeConfirm("COM12173", "Date", "3 Month");
			}
		doActionIBSheet(getCurrentSheet(), formObj, IBSEARCH);
		break;
		// For from date
		// Event fires when user press previous month button
		// month -1
		case "btn_from_back":
			changeMonth(formObj.fr_acct_yrmon, -1);
			break;
		// Event fires when user press next month button
			// month + 1
		case "btn_from_next":
			if (!isValidDate()) {
				ComShowCodeMessage("COM12133",toDate, fromDate);
				break;
			}
			changeMonth(formObj.fr_acct_yrmon, 1);
			break;
		// For to date
			// Event fires when user press previous month button
			// month -1
		case "btn_vvd_to_back":
			if (!isValidDate()) {
				ComShowCodeMessage("COM12133", fromDate, toDate);
				break;
			}
			changeMonth(formObj.to_acct_yrmon, -1);
			break;
			// Event fires when user press next month button
			// month + 1
		case "btn_vvd_to_next":
			changeMonth(formObj.to_acct_yrmon, 1);
			break;
		// Event fires when New button is clicked, reset form.
		case "btn_New":
			onclickButtonNew();
			break;
		// Event fires when DownExcel button is clicked, down sheet to excel
		case "btn_DownExcel":
			doActionIBSheet(sheetObjects[0], formObj, IBDOWNEXCEL);
			break;
		// Event fires when DownExcel button is clicked, down sheet to excel
			// Just down detail excel from server
		case "btn_Down":
			doActionIBSheet(sheetObjects[1], formObj, IBDOWNEXCEL);
			break;

		}
	} catch (e) {
		if (e == "[object Error]") {
			ComShowCodeMessage(OBJECT_ERROR);
		} else {
			ComShowMessage(e.message);
		}
	}
}
// ONCLICK BUTTON
// EVENT
document.onclick = processButtonClick;

/**
 * function add item to combo box
 * 
 * @param comboObj
 * @param comboItems
 */
let addComboItem = (comboObj, comboItems) => {
	
	if(typeof comboObj === 'undefined' || !Array.isArray(comboItems)) return;
	
	let assignComboItems = [...comboItems];
	console.log(assignComboItems);
	assignComboItems.forEach((item, index)=>{
		let comboItem = assignComboItems[index].split(",");
// console.log(comboItem);
		if(comboItem.length === 1) {
			comboObj.InsertItem(index, comboItem[0], comboItem[0]);
		} 
		if(comboItem.length != 1){
			comboObj.InsertItem(index, comboItem[0] + "|" + comboItem[1], comboItem[1]);
		}
	});
	
}
/**
 * List data of partner and split by | doing AHK|POP -> [AHK, POP]
 * 
 * @param comboObj:
 *            IBSheet Object.
 * @param comboNo :
 *            Number of IBMultiCombo Object.
 */
let initCombo = (comboObj, comboNo) => {
	
	if(typeof comboObj === 'undefined') return;
	
	let formObj = document.form;
	switch (comboNo) {
	case 1:
		with (comboObj) {
			SetMultiSelect(1);
			// Show box check and default check ALL because ALL ahead
			SetDropHeight(200);
			// set high size Drop
		}
		partnerList = "ALL|" + partnerList;
		let comboItems = partnerList.split("|");
		addComboItem(comboObj, comboItems);
		comboObj.SetSelectIndex(0, 1, 0, 0);
		break;
	}
}
let resizeSheet = () => {
	ComResizeSheet(sheetObjects[0]);
	ComResizeSheet(sheetObjects[1]);
}
/**
 * This function initSheet define the basic properties of the sheet on the
 * screen.
 * 
 * @param sheetObj:
 *            IBSheet Object.
 * @param sheetNo:
 *            Number of IBSheet Object.
 */
let initSheet = (sheetObj) => {
	let cnt = 0;
	switch (sheetObj.id) {
		case "sheet1": // sheet1 init
			with (sheetObj) {
				let HeadTitle1 = "|Partner|Lane|Invoice No|Slip No|Approved|Curr.|Revenue|Expense|Customer/S.Provider|Customer/S.Provider|cust_vndr_cnt_cd|cust_vndr_seq";
				let HeadTitle2 = "|Partner|Lane|Invoice No|Slip No|Approved|Curr.|Revenue|Expense|Code|Name|cust_vndr_cnt_cd|cust_vndr_seq";		
				SetConfig({ SearchMode: 2, MergeSheet: 5, Page: 20, FrozenCol:0, DataRowMerge: 1 });
				let info = { Sort: 0, ColMove: 0, HeaderCheck: 0, ColResize: 1 };
				let headers = [{ Text: HeadTitle1, Align: "Center" }, { Text: HeadTitle2, Align: "Center" }];
				InitHeaders(headers, info);
				let cols = [
					{ Type: "Status",Hidden: 1, Width: 0,   Align: "Center", ColMerge: 0, SaveName: "ibflag" },
					{ Type: "Text",  Hidden: 0, Width: 50,  Align: "Center", ColMerge: 0, SaveName: "jo_crr_cd", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },
					{ Type: "Text",  Hidden: 0, Width: 70,  Align: "Center", ColMerge: 0, SaveName: "rlane_cd", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },
					{ Type: "Text",  Hidden: 0, Width: 90,  Align: "Center", ColMerge: 0, SaveName: "inv_no", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },
					{ Type: "Text",  Hidden: 0, Width: 120, Align: "Center", ColMerge: 0, SaveName: "csr_no", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },
					{ Type: "Text",  Hidden: 0, Width: 90,  Align: "Center", ColMerge: 0, SaveName: "apro_flg", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },
					{ Type: "Text",  Hidden: 0, Width: 50,  Align: "Center", ColMerge: 0, SaveName: "locl_curr_cd", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },
					{ Type: "Float", Hidden: 0, Width: 120, Align: "Right",  ColMerge: 0, SaveName: "inv_rev_act_amt", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },
					{ Type: "Float", Hidden: 0, Width: 120, Align: "Right",  ColMerge: 0, SaveName: "inv_exp_act_amt", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },
					{ Type: "Text",  Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "prnr_ref_no", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },
					{ Type: "Text",  Hidden: 0, Width: 40,  Align: "Center", ColMerge: 0, SaveName: "cust_vndr_eng_nm", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 }, 
					{ Type: "Text",  Hidden: 1, Width: 90,  Align: "Left",   ColMerge: 0, SaveName: "cust_vndr_cnt_cd", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },
					{ Type: "Text",  Hidden: 1, Width: 90,  Align: "Left",   ColMerge: 0, SaveName: "cust_vndr_seq", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 }
				];
				 /**
					 * @param:Type (String) : Column data type
					 * @param: Hidden (Boolean): Whether a column is hidden
					 * @param: Width (Number) : Column width
					 * @param: Align (String) : Data alignment
					 * @param: ColMerge (Boolean): Whether to allow column
					 *         merging
					 * @param: SaveName (String) : A parameter name used to save
					 *         or search data
					 * @param: KeyField (Boolean): Required fields
					 * @param: UpdateEdit (Boolean): Whether to allow data
					 *         editing when transaction is in "Search" state
					 * @param InsertEdit
					 *            (Boolean): Whether to allow data editing when
					 *            transaction is in "Insert" state
					 */ 
				InitColumns(cols);
				 // InitColums:Configure data type, format and functionality
					// of
				// each column.
				SetWaitImageVisible(0);
				// SetWaitImageVisible:Set not to display waiting image for
				// processing
				SetEditable(1);
				resizeSheet();
				ShowSubSum([{StdCol:"inv_no" , SumCols:"7|8",ShowCumulate:0,CaptionText:"",CaptionCol:3}]);
				// show sumSubSu : Calculate sub sums and total sum for a
				// column.
				// This method should be called before data search method is
				// called. When configuration is
				// changed, you need to rerun a data search to apply it
				/*
				 * StdCol: Reference column index or SaveName SumCols: String of
				 * column indexes where sub sum should be calculated, adjoined
				 * by“|” ShowCumulate:Whether to display aggregate total of sub
				 * sums Default=0 CaptionText:Set sub sum caption text format
				 * Default=sub sum (aggregate total): + reference value String
				 * of column indexes where average should be calculated,
				 * adjoined by“|”. Default=”” CaptionCol:Column to set sub sum
				 * caption text as “Sub sum : “ + reference value Default=The
				 * first unhidden column
				 */
				
			}
			break;
		case "sheet2": 
			with (sheetObj) {
				let HeadTitle1 = "|Partner|Lane|Invoice No|Slip No|Approved|Rev / Exp|Item|Curr.|Revenue|Expense|Customer/S.Provider|Customer/S.Provider";
				let HeadTitle2 = "|Partner|Lane|Invoice No|Slip No|Approved|Rev / Exp|Item|Curr.|Revenue|Expense|Code|Name";
				SetConfig({ SearchMode: 2, MergeSheet: 5, Page: 20, FrozenCol:0, DataRowMerge: 1 });
				let info = { Sort: 0, HeaderCheck: 1, ColResize: 0 };
				let headers = [{ Text: HeadTitle1, Align: "Center" }, { Text: HeadTitle2, Align: "Center" }];
				InitHeaders(headers, info);
				let cols = [{ Type: "Status", Hidden: 1, Width: 10, Align: "Center", ColMerge: 0, SaveName: "ibflag", KeyField: 0, CalcLogic: "", Format: "", UpdateEdit: 0, InsertEdit: 0 },// 
				{ Type: "Text", Hidden: 0, Width: 50,  Align: "Center", ColMerge: 0, SaveName: "jo_crr_cd",	KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },// partner
				{ Type: "Text", Hidden: 0, Width: 50,  Align: "Center", ColMerge: 0, SaveName: "rlane_cd", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },// lane
				{ Type: "Text", Hidden: 0, Width: 150, Align: "Center", ColMerge: 0, SaveName: "inv_no", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },// invoice
																																								// no
				{ Type: "Text", Hidden: 0, Width: 150, Align: "Center", ColMerge: 0, SaveName: "csr_no", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },// slipno
				{ Type: "Text", Hidden: 0, Width: 70,  Align: "Center", ColMerge: 0, SaveName: "apro_flg", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },// approved
				{ Type: "Text", Hidden: 0, Width: 70,  Align: "Center", ColMerge: 0, SaveName: "rev_exp", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },// rev/exp
				{ Type: "Text", Hidden: 0, Width: 60,  Align: "Center", ColMerge: 0, SaveName: "item", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },// item
				{ Type: "Text", Hidden: 0, Width: 40,  Align: "Center", ColMerge: 0, SaveName: "locl_curr_cd", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },// currency
				{ Type: "Float",Hidden: 0, Width: 100, Align: "Right",  ColMerge: 0, SaveName: "inv_rev_act_amt", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },// revenue
				{ Type: "Float",Hidden: 0, Width: 100, Align: "Right",  ColMerge: 0, SaveName: "inv_exp_act_amt", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },// expense
				{ Type: "Text", Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "prnr_ref_no", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 },// code
				{ Type: "Text", Hidden: 0, Width: 40,  Align: "Center", ColMerge: 0, SaveName: "cust_vndr_eng_nm", KeyField: 0, UpdateEdit: 0, InsertEdit: 0 }// name
				];
				InitColumns(cols);
				SetColProperty(0, "rev_exp", { ComboText: "|Rev|Exp", ComboCode: "|R|E", DefaultValue: "R" });	
				SetEditable(1);
				ShowSubSum([{StdCol:"inv_no" , SumCols:"9|10",ShowCumulate:0,CaptionText:"",CaptionCol:3}]);
			}
			break;
	}
}
/**
 * split tab
 */
let initTab = (tabObj, tabNo) => {
	switch (tabNo) {
	case 1:
		with (tabObj) {
			let cnt = 0;
			InsertItem("Summary", "");
			InsertItem("Detail", "");
		}
		break;
	}
}

/**
 * This function calls a common function that sets the default settings of the
 * sheet, It is the first called area when file *jsp onload event.
 */
let loadPage = () => {
	let assignTabObject = [...tabObjects];
	let assignSheetObjects = [...sheetObjects];
	let assignComboObjects = [...comboObjects];
	
	let assignObject = [assignTabObject, assignSheetObjects, assignComboObjects];
	
	if(assignObject.some(value => !Array.isArray(value))) return;
	// generate Grid Layout
	
	assignTabObject.forEach((value, index)=>{
		initTab(value, index + 1);
		value.SetSelectedIndex(0);
	});
	assignSheetObjects.forEach((value, index)=>{
		ComConfigSheet(value);
		initSheet(value, index + 1);
		ComEndConfigSheet(value);
	});
	// initializing IBMultiCombo
	assignComboObjects.forEach((value, index) => {
		initCombo(value, index + 1);
	});
	// initializing tabobject
	
	s_rlane_cd.SetEnable(false);
	s_trd_cd.SetEnable(false);
	// call date to and date form defaul date to = date present
	 initCalendar();
	// auto search data after loading finish.
	doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
}


/**
 * This function is used to get data at row and append that data to string
 * 
 * @param sheetObj:
 *            Sheet Object
 * @param row:
 *            selected row
 * @param saveNames:
 *            array of save name
 * @returns data
 */
let getDataRow = (sheetObj, row, saveNames) => {
	
	if(typeof sheetObj != "object" || !Array.isArray(saveNames)) return;
	
	let result = "";
	let assignSaveNames = [...saveNames];
	
	assignSaveNames.forEach((value) => {
		result += sheetObj.GetCellValue(row, value);
	});
	
	return result;
}
/**
 * When change tab check partner, lane, trade have change data? If have change
 * data show message avoid retrieve Check input initial assign when load first
 * data and check formQuery present if != show message
 */
let changeTab = () => {
	let currentSheet = getCurrentSheet();
	let formQuery = getSearchOption();
	console.log(searchSummary);
	console.log(formQuery);
	console.log(searchDetail);
	
	if(searchSummary != formQuery && formQuery != searchDetail) {
		ComShowCodeConfirm("COM130504");
		doActionIBSheet(currentSheet, document.form, IBSEARCH);
		return;
	}
	if (currentSheet.id == "sheet1" && searchSummary != formQuery) {// in
																	// summary
		doActionIBSheet(currentSheet, document.form, IBSEARCH)
		return;
	} 
	doActionIBSheet(currentSheet, document.form, IBSEARCH)
		
}
var tab1_OnChange = (tabObj, nItem) => {
	// it handles the case where there are multiple elements with the same name
	// properly
	let objs = document.all.item("tabLayer");
	// set the style display for next tab
	objs[nItem].style.display = "Inline";
	// --------------- this is important! --------------------------//
	for (let i = 0; i < objs.length; i++) {
		if (i != nItem) {
			// hide the current tab
			objs[i].style.display = "none";
			// display next tab and replace the current tab
			objs[beforetab].style.zIndex = objs[nItem].style.zIndex - 1;
		}
	}
	// ------------------------------------------------------//
	beforetab = nItem;
	tab1.SetSelectedIndex(beforetab)
	changeTab();
	resizeSheet();
}
/**
 * function when double click on sheet 1 by select row will move on to detail
 * corresponding and select row detail
 * 
 * @param Row:
 *            Long - Row index of the cell.
 * @param Col:
 *            Long - Column index of the cell.
 */
var sheet1_OnDblClick = (sheetObj, Row, Col) => {
	
	if(typeof sheetObj != "object" ) return;
	
	if (sheetObjects[1].RowCount() == 0) {
		doActionIBSheet(sheetObjects[1], document.form, IBSEARCH);
	}
	// setTimeout(function(){
	if (sheetObj.GetCellValue(Row, "jo_crr_cd") != "") {
		let saveNames = [ "jo_crr_cd", "rlane_cd", "inv_no", "csr_no", "locl_curr_cd", "prnr_ref_no" ];
		let summaryData = getDataRow(sheet1, Row, saveNames);
		let size = sheet2.RowCount();
		// sheetObj.HeaderRows() = size Header
		for (let i = sheetObj.HeaderRows(); i <= size; i++) {
			let detailData = getDataRow(sheet2, i, saveNames);
// console.log(getDataRow(sheet2, i, saveNames));
			// getDataRow(sheet2, i, saveNames) = SUM String ROW
			if (summaryData === detailData) {
				tab1_OnChange(tabObjects[1], 1);
				sheetObjects[1].SetSelectRow(i);
				return;
			}
		}
		ComShowCodeMessage("COM132701");
	}
}

// Total Sum
let subsumTotalSum = (sheetObj) => {
	
	if(typeof sheetObj != "object") return;
	
	let rowLast = sheetObj.LastRow() -1; // but -1 because LastRow = total
											// last when hidden this return
											// rowLast
	if (sheetObj.RowCount() > 0) {
		sheetObj.SetRowHidden(sheetObj.LastRow(),1); // hide totalsum LAST
		/*
		 * Return the row index of the last row. Using this method will return
		 * the index of the very last row, not just last data row or the last
		 * row as displayed in the screen. Note that the last row may be a sum
		 * row, data row or even a header row.
		 */
	}
	console.log( sheetObj.HeaderRows());
	/*
	 * HeaderRows: Check header row count. This method returns the header row
	 * counts as set in InitHeaders() method.
	 */
	for (let i = sheetObj.HeaderRows(); i <= sheetObj.LastRow(); i++) {
		if (sheetObj.GetCellValue(i, "jo_crr_cd") === '') {
			sheetObj.SetCellValue(i, "inv_no", "");
			sheetObj.SetCellValue(i, "locl_curr_cd", 
			sheetObj.GetCellValue(i-1, "locl_curr_cd"));
		}
	}
	if (sheetObj.GetCellValue(rowLast, "inv_no") == '') {
		sheetObj.SetRangeFontBold(rowLast, 1, rowLast , 10, 1);
		sheetObj.SetRowBackColor(rowLast, "#FFDAB9");
	} 
}

var sheet1_OnSearchEnd = (sheetObj, Code, Msg, StCode, StMsg) => {
	ComOpenWait(false);
	subsumTotalSum(sheetObj);
}

var sheet2_OnSearchEnd = (sheetObj, Code, Msg, StCode, StMsg) => {
	ComOpenWait(false);
	subsumTotalSum(sheetObj);
}


let initComboBoxLane = (laneList) => {
	
	if(typeof laneList != "string") return;
	
	let formObj = document.form;
	with (comboObjects[1]) {
		RemoveAll();
		SetMultiSelect(0);
		SetDropHeight(200);
	}
	let comboItems = laneList.split("|");
	addComboItem(comboObjects[1], comboItems);
}

var s_jo_crr_cd_OnChange = (OldText, OldIndex, OldCode, NewText, NewIndex, NewCode) => {
	
	let newIndexArr = OldIndex != 0 ? NewIndex.split(",") : "";
	
	isTrueType(OldText, OldIndex, OldCode, NewText, NewIndex, NewCode);
	
	// handling events when user checks all item partner's combo
	if (OldIndex == 0) {
		s_jo_crr_cd.SetItemCheck(0, 0, 0);
	}
	if (NewIndex == -1) {
		s_jo_crr_cd.SetItemCheck(0, 1, 0);
		s_rlane_cd.RemoveAll();
		s_rlane_cd.SetEnable(false);
		s_trd_cd.RemoveAll();
		s_trd_cd.SetEnable(false);
	} 
	if (newIndexArr[newIndexArr.length - 1] == 0 && OldIndex != -1) {
		
		newIndexArr.forEach((value)=>{
		let location = parseInt(value);
		s_jo_crr_cd.SetItemCheck(location, 0, 0);
		});
		
		s_jo_crr_cd.SetItemCheck(0, 1, 0);
		s_rlane_cd.RemoveAll();
		s_rlane_cd.SetEnable(false);
		
	}
	// when user check item form combo , combo rlane will be loaded data
	let formObj = document.form;
	formObj.f_cmd.value = SEARCH01;
	let xml = sheetObjects[0].GetSearchData("CLV_TRN_0003GS.do", FormQueryString(formObj));
	let laneList = ComGetEtcData(xml, "lane");
	initComboBoxLane(laneList);
}
/**
 * 
 */
let initComboBoxTrade = (tradeList) => {
	
	if(typeof tradeList != "string") return;
	
	let formObj = document.form;

	with (comboObjects[2]) {
		RemoveAll();
		SetMultiSelect(0);
		SetDropHeight(200);
	}
	let comboItems = tradeList.split("|");
	addComboItem(comboObjects[2], comboItems);
}
/**
 * 
 */
var s_rlane_cd_OnChange = (OldText, OldIndex, OldCode, NewText, NewIndex, NewCode) => {

	isTrueType(OldText, OldIndex, OldCode, NewText, NewIndex, NewCode);
	
	if (NewCode.length === 0){
		s_trd_cd.SetEnable(false);
		return;
	}

	s_trd_cd.SetEnable(true);
	laneValue = NewCode;
	let formObj = document.form;
	formObj.f_cmd.value = SEARCH02;
	let xml = sheetObjects[0].GetSearchData("CLV_TRN_0003GS.do", FormQueryString(formObj));
	let tradeList = ComGetEtcData(xml, "trade");
	initComboBoxTrade(tradeList);
}



// when click partner all unable Lane end Trade. if code != all Lane enable and
// trade still unable
var s_jo_crr_cd_OnCheckClick = (sheetObj, Index, Code) => {
	
	if(typeof sheetObj != "object" || typeof Index != "number" || typeof Code != "string") return;
	
	if (Code != "ALL") {
		s_rlane_cd.SetEnable(true);
		return;
	}
	
	s_rlane_cd.RemoveAll();
	s_rlane_cd.SetEnable(false);
	s_trd_cd.RemoveAll();
	s_trd_cd.SetEnable(false);
	
}


/**
 * 
 * @param downloadType
 * @param result
 *            if Down sheet finish show message success
 */
var sheet1_OnDownFinish = (sheetObj, downloadType, result) => {
	
	if(typeof sheetObj != "object" || typeof downloadType != "string" && typeof result != "boolean") return;
	
	ComOpenWait(false);
	if(!result)
		return ComShowCodeMessage("COM131102", "Sheet");
	return ComShowCodeMessage("COM131101", "Sheet");
}
var sheet2_OnDownFinish = (sheetObj, downloadType, result) => {
	
	if(typeof sheetObj != "object" || typeof downloadType != "string" && typeof result != "boolean") return;
	
	ComOpenWait(false);
	if(!result)
		return ComShowCodeMessage("COM131102", "Sheet");
	return ComShowCodeMessage("COM131101", "Sheet");
}