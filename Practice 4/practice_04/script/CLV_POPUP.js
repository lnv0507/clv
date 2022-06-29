var sheetObjects = new Array();
var sheetCnt = 0;
document.onclick = processButtonClick;
function processButtonClick() {
	// get sheet1
	var sheetObj = sheetObjects[0];
	// get form object
	var formObj = document.form;
	// get name
	var srcName = ComGetEvent("name");
	if (srcName == null) {
		return;
	}
	try {
		// base on name to handle event onclick
		switch (srcName) {
		// button Retrieve
		case "btn_Retrieve":
			// call doActionIBSheet with action is IBSEARCH
			doActionIBSheet(sheetObj, formObj, IBSEARCH);
			break;
			//button OK choose item get on sheet
		case "btn_OK":
			comPopupOK();
			break;
			//button close -> close popup and cancel handle
		case "btn_Close":
			ComClosePopup();
			break;
		default:
			break;
		}
	} catch (e) {
		if (e == "[object Error]") {
			ComShowCodeMessage('JOO00001');
		} else {
			ComShowMessage(e.message);
		}
	}
}

/**
 * Function that is called after the JSP file is loaded
 */
function loadPage() {
	// generate Grid Layout
	for (i = 0; i < sheetObjects.length; i++) {
		ComConfigSheet(sheetObjects[i]);
		initSheet(sheetObjects[i], i + 1);
		ComEndConfigSheet(sheetObjects[i]);
	}

	// auto search data after loading finish.
	doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);

}

/**
 * Function that configure sheet
 */
function initSheet(sheetObj, sheetNo) {
	// get sheet id
	var sheetID = sheetObj.id;
	// configure sheet base on sheet id
	switch (sheetID) {
	// if sheet1
	case "sheet1":
		with (sheetObj) {
			var HeadTitle = "|Select|Country|Sequence|Legacy Customer Name|Short Name";

			SetConfig({
				SearchMode : 2,
				MergeSheet : 5,
				Page : 20,
				FrozenCol : 0,
				DataRowMerge : 1
			});

			var info = {
				Sort : 1,
				ColMove : 1,
				HeaderCheck : 0,
				ColResize : 1
			};
			var headers = [ {
				Text : HeadTitle,
				Align : "Center"
			} ];
			InitHeaders(headers, info);

			var cols = [ {
				Type : "Radio",
				Hidden : 0,
				Width : 50,
				Align : "Center",
				SaveName : "radio",
				KeyField : 0,
				UpdateEdit : 1,
				InsertEdit : 1
			}, {
				Type : "CheckBox",
				Hidden : 0,
				Width : 20,
				Align : "Center",
				SaveName : "checkbox",
				KeyField : 0,
				UpdateEdit : 1,
				InsertEdit : 1
			}, {
				Type : "Text",
				Hidden : 0,
				Width : 100,
				Align : "Center",
				SaveName : "cust_cnt_cd",
				KeyField : 1,
				UpdateEdit : 0,
				InsertEdit : 1
			}, {
				Type : "Text",
				Hidden : 0,
				Width : 100,
				Align : "Center",
				SaveName : "cust_seq",
				KeyField : 1,
				UpdateEdit : 0,
				InsertEdit : 1
			}, {
				Type : "Text",
				Hidden : 0,
				Width : 500,
				Align : "Left",
				SaveName : "cust_lgl_eng_nm",
				KeyField : 1,
				UpdateEdit : 0,
				InsertEdit : 1
			}, {
				Type : "Text",
				Hidden : 0,
				Width : 200,
				Align : "Left",
				SaveName : "cust_abbr_nm",
				KeyField : 0,
				UpdateEdit : 0,
				InsertEdit : 1
			} ];
			InitColumns(cols);
			SetEditable(1);
			SetWaitImageVisible(0);
			ComResizeSheet(sheetObjects[0]);
			resizeSheet();
		}
		break;
	}

}
function resizeSheet() {
	ComResizeSheet(sheetObjects[0]);
}

/**
 * Function that add sheet object to array
 */
function setSheetObject(sheetObj) {
	sheetObjects[sheetCnt++] = sheetObj;
}

function doActionIBSheet(sheetObj, formObj, sAction) {
	// Check or configure whether to display debugging message
	// -1: Start system popup debugging
	// 0: End all debugging
	sheetObj.ShowDebugMsg(0);
	// Handle base on sAction
	switch (sAction) {
	case IBSEARCH: // retrieve
		ComOpenWait(true);
		formObj.f_cmd.value = SEARCH01;
		sheetObj.DoSearch("CLV_POPUPGS.do", FormQueryString(formObj));
		break;
	}
}

// Handling event after searching
function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) {
	ComOpenWait(false);
}