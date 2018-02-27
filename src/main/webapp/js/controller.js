var adding = false;
var editing = false;
function showTable(div) {
	if($(div).is(":visible"))
		$(div).hide("fast");
	else {
		hideTables();
		$(div).show("fast");
	}
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
function toggleAddEditInputDiv(tableName) {
	$('#input' + tableName + 'Div').toggle('fast');
	$('#done' + tableName + 'Button').toggle();
	$('#cancel' + tableName + 'Button').toggle();
	$('#add' + tableName + 'Button').toggle();
}
function addButtonClicked(tableName) { // To Add Records for ANY Table.
	adding = true;
	editing = false;
	toggleAddEditInputDiv(tableName);
}
function editButtonClicked(tableName) { // To Edit Records of ANY Table.
	editing = true;
	adding = false;
	toggleAddEditInputDiv(tableName);
}
function doneButtonClicked(tableName) {
	if(adding) {
		document.getElementsByClassName('add' + tableName + 'HiddenButton')[0].click()	
		Materialize.toast(tableName + " added successfully!", 4000);
	}
	else if(editing) {
		document.getElementsByClassName('edit' + tableName + 'HiddenButton')[0].click();
		Materialize.toast(tableName + " updated successfully!", 4000);
	}
	cancelButtonClicked(tableName); 
}
function cancelButtonClicked(tableName) {
	adding = false;
	editing = false;
	document.getElementsByClassName('input' + tableName + 'Form')[0].reset();
	toggleAddEditInputDiv(tableName);
}
function removeButtonClicked(tableName) {
	var $toastContent = $('<span>' + tableName + ' removed successfully!</span>').add($('<button class="btn-flat toast-action" onclick="restoreButtonClicked(\'' + tableName + '\')">Undo</button>'));
	Materialize.toast($toastContent, 4000);
}
function restoreButtonClicked(tableName) {
	document.getElementsByClassName('restore' + tableName + 'HiddenButton')[0].click();
	Materialize.toast(tableName + " restored successfully!", 4000);
}