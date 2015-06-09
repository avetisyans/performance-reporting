var jq = jQuery.noConflict();
var TABLE_ID = "mainTable";
var SUITE_ID = "suiteID";
var ENVIRONMENT_NAME = "environmentName";

// TODO change to id
// TODO add start end date
function testHistoryButton_onClick(testName, testSuiteName) {
	console.log(testName)
	console.log(testSuiteName)
	jq.ajax({
		type : "POST",
		url : "./TestSuites/history",
		data : {
			testName : testName,
			testSuiteName : testSuiteName
		},
		success : function(data) {
			console.log(data);
			var parsedData = $.parseJSON(data);
			showTestHistory(parsedData, testName);
		},
		dataType : "application/json", // expected return value type
		error : function(data, status, error) {
			console.log("testPost with errors!");
		}
	});
}

function envCheckBox_onClick(thisElement) {
	console.log(thisElement);
	var parentDiv = thisElement.parentNode.parentNode;
	var environmentName = thisElement.getAttribute("id");
	console.log(environmentName);
	var tableId = parentDiv.getAttribute("tableId");
	if (tableId === undefined) {
		tableId = TABLE_ID;
	}
	var table = document.getElementById(tableId);
	console.log(table);
	var environments = table.rows[0].cells;

	var columnCount = environments.length;
	var indexOfParentColumn = 0;
	for (indexOfParentColumn; indexOfParentColumn < columnCount; indexOfParentColumn++) {
		if (environments[indexOfParentColumn].innerHTML === environmentName)
			break;
	}
	var hideShow = "";
	if (thisElement.checked) {
		hideShow = "";
	} else {
		hideShow = "none";
	}
	console.log(hideShow);
	var rowIndex = 0;
	// hide environment row
	var th = table.rows[rowIndex++].cells[indexOfParentColumn];
	th.style.display = hideShow;

	// hide the rest
	var numberOfSubColumns = table.rows[0].cells[1].colSpan;
	var numberOfRows = table.rows.length;
	for (rowIndex; rowIndex < numberOfRows; rowIndex++) {
		for ( var cellIndex = (indexOfParentColumn - 1) * numberOfSubColumns
				+ 1; cellIndex < (indexOfParentColumn - 1) * numberOfSubColumns
				+ 1 + numberOfSubColumns; cellIndex++) {
			if (table.rows[rowIndex].cells[cellIndex] != undefined) {
				table.rows[rowIndex].cells[cellIndex].style.display = hideShow;
			}
		}
	}

}

function environmentName_onClick(environmentName) {
	jq.ajax({
		type : "POST",
		url : "./TestSuites/testDailyRuns",
		data : environmentName,
		success : function(data) {
			console.log(environmentName);
			showDailyRunsForEnv(data, environmentName);
		},
		dataType : "application/json", // expected return value type
		error : function(data, status, error) {
			console.log("testPost with errors!");
		}
	});
}

function suitName_onClick(testSuiteName) {
	var map = new Object();
	jq("#" + testSuiteName).find("td").each(function() {
		var suiteID = jq(this).find("#" + SUITE_ID).val();
		var environmentName = jq(this).find("#" + ENVIRONMENT_NAME).val();
		map[suiteID] = environmentName;
	});

	jq.ajax({
		type : "POST",
		url : "./TestSuites/tests",
		data : map,
		success : function(data) {
			var datepicker = document.getElementById("datepicker");
			datepicker.setAttribute("style", "visibility:hidden");
			showTableWithTestListRunInfos(data, testSuiteName);
		},
		dataType : "application/json", // expected return value type
		error : function(data, status, error) {
			console.log("testPost with errors!");
		}
	});
}

function testName_onClick(testName) {
	jq.ajax({
		type : "POST",
		url : "./TestSuites/test",
		data : testName,
		success : function(data) {
			console.log(data);
			showTestData(data, testName);
		},
		dataType : "application/json", // expected return value type
		error : function(data, status, error) {
			console.log("testPost with errors!");
		}
	});
}

function showTestHistory(data, testName) {
	console.log("function showTestHistory start");

	var closeDiv = document.createElement("div");
	closeDiv.setAttribute("align", "right");
	var closeBtn = document.createElement("button");
	closeBtn.appendChild(document.createTextNode("X"));
	closeBtn.setAttribute("onclick",
			"testRunPopUPClose('showTestHistoryDialog')");
	closeBtn.setAttribute("id", "testHistoryPopUPCloseButton");
	closeDiv.appendChild(closeBtn);

	var div = document.createElement("div");
	div.setAttribute("id", "testHistoryPopUpContent");

	div.appendChild(document.createTextNode(testName));
	div.appendChild(document.createElement("br"));
	var testHistoryTable = document.createElement("table");
	testHistoryTable.setAttribute("id", "historyTable");
	testHistoryTable.setAttribute("class",
			"table table-striped table-bordered table-hover");
	populateTestRunInfoHistoryTableHeader(testHistoryTable);
	populateTestRunInfoHistoryTableBody(testHistoryTable, data);

	var tableControllerCheckboxes = document.getElementById("tableController")
			.cloneNode(true);
	tableControllerCheckboxes.setAttribute("tableId", "historyTable");
	tableControllerCheckboxes.setAttribute("id", "history_tableController");
	div.appendChild(tableControllerCheckboxes);
	div.appendChild(testHistoryTable);

	var dialog = document.createElement("dialog");
	dialog.setAttribute("id", "showTestHistoryDialog");
	dialog.setAttribute("style", "border:0;");
	document.body.appendChild(dialog);

	dialog.appendChild(closeDiv);
	dialog.appendChild(div);

	dialog.showModal();

	console.log("function showTestHistory end");
}

function testHistoryTableBodyPopulate(testTable, numberOfCellsPerEnv, data) {

	var tblBody = testTable.createTBody();

	for ( var j = 0; j < numberOfCellsPerEnv; j++) {
		var row = document.createElement("tr");
		var cellN = document.createElement("td");
		var cellText = document.createTextNode(j);
		cellN.appendChild(cellText);
		row.appendChild(cellN);
	}
	testTable.appendChild(tblBody);
	testTable.setAttribute("class",
			"table table-striped table-bordered table-hover");

}

function testTableWithHeaderCreate(numberOfCellsPerEnv) {
	var tbl = document.createElement("table");
	tbl.setAttribute("id", "testTable");
	tbl.setAttribute("class", "table table-striped table-bordered table-hover");
	var tableHead = tbl.createTHead();
	var table = document.getElementById(TABLE_ID);
	headerRow = table.tHead.rows[0].cloneNode(true);
	var cells = headerRow.cells;
	for ( var i = 1, cell; cell = cells[i]; i++) {
		cell.setAttribute("colspan", "4");
	}

	tableHead.appendChild(headerRow);

	var rowIndex = 1;
	var columnIndex = 0;

	tableHead.insertRow(rowIndex);
	var row = tableHead.rows[rowIndex];
	var cell = row.insertCell(columnIndex);
	cell.innerHTML = "N".bold();

	columnIndex = 1;
	for ( var index = 1; index < headerRow.cells.length; index++) {
		columnIndex = 1 + numberOfCellsPerEnv * (index - 1);

		var row = tableHead.rows[rowIndex];

		var cell0 = row.insertCell(columnIndex);
		cell0.innerHTML = "Start Time".bold();
		cell0.setAttribute("id", "startTime");
		var cell1 = row.insertCell(columnIndex + 1);
		cell1.innerHTML = "End Time".bold();
		cell1.setAttribute("id", "endTime");
		var cell2 = row.insertCell(columnIndex + 2);
		cell2.innerHTML = "Duration".bold();
		cell2.setAttribute("id", "duration");
		var cell3 = row.insertCell(columnIndex + 3);
		cell3.innerHTML = "P/F".bold();
		cell3.setAttribute("id", "testResult");
	}
	return tbl;
}

function showDailyRunsForEnv(data, environmentName) {
	var parsedData = $.parseJSON(data);
	var testRunsButton = document.getElementById("TestRunsButton");
	testRunsButton.innerHTML = environmentName;
	var testRunsTable = document.getElementById("testRunsTable");
	testRunsTable.tBodies[0].remove();
	var tableBody = testRunsTable.createTBody();
	var numberOfRows = parsedData.length;
	for ( var index = 0; index < numberOfRows; index++) {
		var row = document.createElement("tr");
		var cell0 = row.insertCell(0);
		cell0.innerHTML = new Date(
				parsedData[index].testSuiteListPerEnv.dailyRunInfo.date)
				.toDateString();
		var cell1 = row.insertCell(1);
		cell1.innerHTML = parsedData[index].buildNumber;
		var cell2 = row.insertCell(2);

		// TODO must be calculated
		cell2.innerHTML = "100%";
		var cell3 = row.insertCell(3);
		cell3.innerHTML = "90%";
		tableBody.appendChild(row);

	}

}

function testRunInfoTableCreate(data) {
	var numberOfCellsPerEnv = 4;
	tbl = testTableWithHeaderCreate(numberOfCellsPerEnv);

	// table body population
	var parsedData = $.parseJSON(data);
	var tblBody = tbl.createTBody();

	var maxLength = 0;
	for (dataRow in parsedData) {
		console.log(dataRow);
		for (envTest in dataRow) {
			console.log(envTest.length);
			if (envTest.length > maxLength) {
				maxLength = envTest;
			}
		}
	}
	for ( var j = 0; j < 2; j++) {
		var row = document.createElement("tr");

		var cellN = document.createElement("td");
		var cellText = document.createTextNode(j);
		cellN.appendChild(cellText);
		row.appendChild(cellN);

		for ( var i = 1; i < (headerRow.cells.length - 1) * numberOfCellsPerEnv
				+ 1; i++) {

			var envIndex = Math.floor((i - 1) / numberOfCellsPerEnv) + 1;
			var envName = headerRow.cells[envIndex].innerHTML;

			var envTests = parsedData[envName];
			var cell = document.createElement("td");
			if (envTests.length > j) {
				var cellEntityName = tbl.tHead.rows[1].cells[i]
						.getAttribute("id");
				var cellText = document
						.createTextNode(envTests[j][cellEntityName]);
				// TODO
				// there can be cases when j is not present for all envs, change
				// this
				cell.appendChild(cellText);
			}
			row.appendChild(cell);
		}

		tblBody.appendChild(row);
	}

	// append the <tbody> inside the <table>
	tbl.appendChild(tblBody);
	return tbl;
}

function showTestData(data, testName) {
	var closeDiv = document.createElement("div");
	closeDiv.setAttribute("align", "right");
	var closeBtn = document.createElement("button");
	closeBtn.appendChild(document.createTextNode("X"));
	closeBtn.setAttribute("onclick", "testRunPopUPClose('showTestDataDialog')");
	closeBtn.setAttribute("id", "testRunPopUPCloseButton");
	closeDiv.appendChild(closeBtn);

	var div = document.createElement("div");
	div.setAttribute("id", "testRunPopContent");
	div.appendChild(document.createTextNode(testName));

	var tableControllerCheckboxes = document.getElementById("tableController")
			.cloneNode(true);
	tableControllerCheckboxes.setAttribute("tableId", "testTable");
	tableControllerCheckboxes.setAttribute("id", "test_tableController");
	div.appendChild(tableControllerCheckboxes);

	var testRunInfosTable = testRunInfoTableCreate(data);
	div.appendChild(testRunInfosTable);

	var dialog = document.createElement("dialog");
	dialog.appendChild(closeDiv);
	dialog.appendChild(div);
	dialog.setAttribute("id", "showTestDataDialog");
	dialog.setAttribute("style", "border:0");
	document.body.appendChild(dialog);
	dialog.showModal();

}

function populateTestRunInfoHistoryTableHeader(table) {
	var tableHeader = document.getElementById(TABLE_ID).tHead.cloneNode(true);
	var td = tableHeader.rows[1].cells[0];
	td.innerHTML = "Date".bold();
	console.log(td);
	table.appendChild(tableHeader);
}

function getEnvIndexInTable(envName, table) {
	// starting with index 1
	console.log("table " + table);
	console.log("envName " + envName);
	colspan = table.tHead.rows[0].cells[1].colSpan;
	console.log("colspan " + colspan);
	environments = table.rows[0];
	console.log("environments " + environments);
	for (envIndex = 1; i < environments.cells.length; envIndex++) {
		if (envName == environments.cells[envIndex].innerText)
			return (envIndex - 1) * colspan + 1;
	}
	return null;
}

function populateTestRunInfoHistoryTableBody(testHistoryTable, data) {
	console.log("start populateTestRunInfoHistoryTableBody");
	var tableBody = document.createElement("tbody");
	testHistoryTable.appendChild(tableBody);
	tableRowIndex = -1;
	currentDate = null;
	console.log(data.length);
	for (i=0; i < data.length; i++) {
		console.log("i "+i);
		row = data[i];
		console.log(row);

		// change 3
		if (currentDate != row[3]) {
			console.log("currentDate "+currentDate);
			currentDate = row[3];
			tableRowIndex++;
			tableBody.insertRow(tableRowIndex);
			for(j=0; j< testHistoryTable.rows[1].cells.length;j++ ){
				tableBody.rows[tableRowIndex].insertCell(j);
			}

			tableBody.rows[tableRowIndex].cells[0].innerHTML = new Date(currentDate).toDateString();
		}
		console.log("currentDate "+currentDate);
		console.log("currentDate "+row[3]);
		// change 2
		if (currentDate == row[3]) {
			index = getEnvIndexInTable(row[2], testHistoryTable);
			console.log(index);
			if (index != null) {
				tableBody.rows[tableRowIndex].cells[index].innerHTML = row[8];
				tableBody.rows[tableRowIndex].cells[index + 1].innerHTML = row[5];
				tableBody.rows[tableRowIndex].cells[index + 2].innerHTML = row[6];
				tableBody.rows[tableRowIndex].cells[index + 3].innerHTML = row[7];
				tableBody.rows[tableRowIndex].cells[index + 4].innerHTML = row[9];
			}
		}
	}

	console.log(tableBody);
}

function showTableWithTestListRunInfos(data, testSuiteName) {
	populateTableHeadForTestListRunInfos();
	var parsedData = $.parseJSON(data);
	populateTableBodyForTestListRunInfos(parsedData, testSuiteName);
}

function populateTableHeadForTestListRunInfos() {
	var table = document.getElementById(TABLE_ID);
	var headerFirstRowCells = environmentsCount = table.rows[0].cells;
	var environmentsCount = headerFirstRowCells.length;
	var tableHead = table.tHead;
	var rowIndex = 1;
	var columnIndex = 0;

	for(var i = 0 ; i < tableHead.rows[rowIndex].cells.length; i++)
		tableHead.rows[rowIndex].cells[i].remove();
	var row = tableHead.rows[rowIndex];
	var cell = row.insertCell(columnIndex);
	cell.innerHTML = "Test Name".bold();

	columnIndex = 1;
	var numberOfCellsPerEnv = 5;

	for ( var index = 1; index < environmentsCount; index++) {
		headerFirstRowCells[index].setAttribute("colspan", numberOfCellsPerEnv);
		columnIndex = 1 + numberOfCellsPerEnv * (index - 1);
		tableHead.rows[rowIndex].cells[columnIndex].remove();
		var row = tableHead.rows[rowIndex];

		var cell0 = row.insertCell(columnIndex);

		cell0.innerHTML = "N of Runs".bold();
		var cell1 = row.insertCell(columnIndex + 1);

		cell1.innerHTML = "Min".bold();
		var cell2 = row.insertCell(columnIndex + 2);

		cell2.innerHTML = "Max".bold();
		var cell3 = row.insertCell(columnIndex + 3);

		cell3.innerHTML = "Avg".bold();
		var cell4 = row.insertCell(columnIndex + 4);

		cell4.innerHTML = "P".bold();


	}
}

function populateTableBodyForTestListRunInfos(data, testSuiteName) {
	console.log(data);
	var table = document.getElementById(TABLE_ID);
	var environments = table.rows[0].cells;
	var tests = data["testDef"];
	var map = data["map"];
	var columnIndex = 0;
	var rowIndex = 0;
	var testListRunInfosTableBody = document.createElement('tbody');
	var numberOfCellsPerEnv = 5;

	for ( var testIndex in tests) {
		var row = testListRunInfosTableBody.insertRow(rowIndex);
		var cell = row.insertCell(columnIndex);

		var historyButton = document.createElement('input');
		historyButton.setAttribute("type", "image");
		historyButton.setAttribute("src", "resources/icons/testHistory.png");
		historyButton.setAttribute("style", "width:20px;");
		// change to id
		historyButton.setAttribute("id", tests[testIndex].name);
		historyButton.setAttribute("onclick", "testHistoryButton_onClick('"
				+ tests[testIndex].name + "','" + testSuiteName + "')");

		cell.appendChild(historyButton);

		var link = document.createElement('a');
		link.setAttribute("href", "#");
		link.setAttribute("onClick", "testName_onClick('"
				+ tests[testIndex].name + "')");
		link.innerHTML = tests[testIndex].name;
		cell.appendChild(link);

		for ( var environmentIndex = 1; environmentIndex < environments.length; environmentIndex++) {
			var cell0, cell1,cell2,cell3,cell4;
			var noData = true;
			var index = (environmentIndex - 1) * numberOfCellsPerEnv + 1;
			var envName = environments[environmentIndex].innerHTML;
			var testsPerEnv = map[envName];
			if (typeof testsPerEnv != 'undefined') {
				for ( var l = 0; l < testsPerEnv.length; l++) {
					if (tests[testIndex].name == testsPerEnv[l].testDef.name) {
						noData = false;
						var appropriateTestList = testsPerEnv[l];
						cell0 = row.insertCell(index);
						cell0.innerHTML = appropriateTestList.numberOfRuns;
						cell1 = row.insertCell(index + 1);
						cell1.innerHTML = appropriateTestList.max;
						cell2 = row.insertCell(index + 2);
						cell2.innerHTML = appropriateTestList.min;
						cell3 = row.insertCell(index + 3);
						cell3.innerHTML = appropriateTestList.avg;
						cell4 = row.insertCell(index + 4);
						cell4.innerHTML = appropriateTestList.totalPass;

					}
				}
				if (noData == true) {
					for ( var l = 0; l < numberOfCellsPerEnv; l++) {
						var cell0 = row.insertCell(index++);
						cell0.innerHTML = "";
					}
				}
			}

			//TODO this mayght be changed, style may be more then only  "display: none;"
			if(environments[environmentIndex].getAttribute("style") == "display: none;"){
				cell0.setAttribute("style","display: none;");
				cell1.setAttribute("style","display: none;");
				cell2.setAttribute("style","display: none;");
				cell3.setAttribute("style","display: none;");
				cell4.setAttribute("style","display: none;");
			}
		}
		rowIndex++;
	}

	var oldTestListRunInfosTableBody = table.tBodies[0];
	oldTestListRunInfosTableBody.parentNode.replaceChild(
			testListRunInfosTableBody, oldTestListRunInfosTableBody);

}

function testRunPopUPClose(id) {
	console.log("function testRunPopUPClose start");
	console.log(id);

	var dialog = document.getElementById(id);
	dialog.close();
	dialog.remove();

	console.log("function testRunPopUPClose end");
}
