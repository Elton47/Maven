var adding = false;
var editing = false;
var codeToEdit = "";
function setCodeToEdit(codeToEditFromCall) {
	codeToEdit = codeToEditFromCall;
}
function showTable(div) {
	hideTables();
	if($(div).is(":visible"))
		$(div).hide("fast");
	else
		$(div).show("fast");
}
function hideTables() {
	$("#departmentsDiv").hide("fast");
	$("#sectorsDiv").hide("fast");
	$("#rolesDiv").hide("fast");
	$("#permissionsDiv").hide("fast");
	$("#usersDiv").hide("fast");
	$("#countriesDiv").hide("fast");
	$("#employeesDiv").hide("fast");
	$("#childrenDiv").hide("fast");
}
function addButtonClicked(tableName) { // To Add Records for ANY Table.
	adding = true;
	$('#input' + tableName + 'Div').toggle('fast');
	$('#done' + tableName + 'Button').toggle();
	$('#cancel' + tableName + 'Button').toggle();
	$('#add' + tableName + 'Button').toggle();
}
function doneButtonClicked(tableName) {
	if(adding) { // ADD Record.
		document.getElementsByClassName('add' + tableName + 'HiddenButton')[0].click();
		Materialize.toast(tableName + " added successfully!", 4000);
	}
	else if(editing) { // Edit Record.
		document.getElementsByClassName('codeToEdit' + tableName + 'Field')[0].value = codeToEdit;
		document.getElementsByClassName('edit' + tableName + 'HiddenButton')[0].click();
		Materialize.toast(tableName + " updated successfully!", 4000);
	}
	adding = false;
	editing = false;
	codeToEdit = "";
	cancelButtonClicked(tableName); // So it reverts back to "Add" button only.
}
function cancelButtonClicked(tableName) {
	adding = false;
	editing = false;
	document.getElementsByClassName('input' + tableName + 'Form')[0].reset();
	addButtonClicked(tableName); // Toggles again.
}
function editButtonClicked(tableName) { // To Edit Records of ANY Table.
	editing = true;
	$('#input' + tableName + 'Div').toggle('fast');
	$('#done' + tableName + 'Button').toggle();
	$('#cancel' + tableName + 'Button').toggle();
	$('#add' + tableName + 'Button').toggle();
}