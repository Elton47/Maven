var adding = false;
var editing = false;
function showTable(div) {
	if($(div).is(":visible"))
		$(div).hide("fast");
	else {
		hideTables();
		$(div).show("fast");
	}
	$('.tap-target').tapTarget('open');
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
function fabHandler(tableName) {
	var fab = document.getElementById('add' + tableName + 'Button');
	var fabIcon = document.getElementById('fab' + tableName + 'Icon');
	if(adding)
		document.getElementById('remove' + tableName + 'FABOption').style.display = "none";
	else
		document.getElementById('remove' + tableName + 'FABOption').style.display = "";
	if(fabIcon.style.transform != "rotate(45deg)") {
		fab.classList.remove("indigo");
		fab.classList.add("red");
		fabIcon.style.webkitTransform = "rotate(45deg)";
		fabIcon.style.MozTransform = "rotate(45deg)";
		fabIcon.style.msTransform = "rotate(45deg)";
		fabIcon.style.OTransform = "rotate(45deg)";
		fabIcon.style.transform = "rotate(45deg)";
		$('#input' + tableName + 'Div').show('fast');
	}
	else {
		fab.classList.remove("red");
		fab.classList.add("indigo");
		fabIcon.style.webkitTransform = "";
		fabIcon.style.MozTransform = "";
		fabIcon.style.msTransform = "";
		fabIcon.style.OTransform = "";
		fabIcon.style.transform = "";
		cancelButtonClicked(tableName);
	}
}
function addButtonClicked(tableName) { // To Add Records for ANY Table.
	if(!editing)
		adding = true;
	fabHandler(tableName);
}
function editButtonClicked(tableName) { // To Edit Records of ANY Table.
	editing = true;
	document.getElementById('add' + tableName + 'Button').click();
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
	$('#input' + tableName + 'Div').hide('fast');
}
function removeButtonClicked(tableName) {
	document.getElementsByClassName('remove' + tableName + 'HiddenButton')[0].click();
	var toastContent = $('<span>' + tableName + ' removed successfully!</span>').add($('<button class="btn-flat toast-action" style="color: #c5cae9;" onclick="restoreButtonClicked(\'' + tableName + '\')">Undo</button>'));
	Materialize.toast(toastContent, 4000);
}
function restoreButtonClicked(tableName) {
	document.getElementsByClassName('restore' + tableName + 'HiddenButton')[0].click();
	$('.toast').first()[0].M_Toast.remove();
	Materialize.toast(tableName + " restored successfully!", 4000);
}