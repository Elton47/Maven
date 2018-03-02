var adding = false;
var editing = false;
var featureDiscoveryShownOnce = false;
var currentTableName;
function showTable(selectedTableName) {
	if($('#div' + selectedTableName).is(":visible")) {
		$('#div' + selectedTableName).hide("fast");
		document.getElementById('fabDiv').style.display = "none";
	}
	else {
		hideAllTables();
		$('#div' + selectedTableName).show("fast");
		document.getElementById('fabDiv').style.display = "";
	}
	if(!featureDiscoveryShownOnce) {
		$('.button-collapse').sideNav('show'); // Hide sideNavigation for first time.
		$('.tap-target').tapTarget('open');
	}
	featureDiscoveryShownOnce = true;
	currentTableName = selectedTableName;
}
function hideAllTables() {
	$("#divDepartment").hide("fast");
	$("#divSector").hide("fast");
	$("#divRole").hide("fast");
	$("#divPermission").hide("fast");
	$("#divUser").hide("fast");
	$("#divCountry").hide("fast");
	$("#divEmployee").hide("fast");
	$("#divChild").hide("fast");
}
function fabOnClick() {
	if(!editing)
		adding = true;
	var fab = document.getElementById('fab');
	var fabIcon = document.getElementById('fabIcon');
	if(adding)
		document.getElementById('removeFabAction').style.display = "none";
	else
		document.getElementById('removeFabAction').style.display = "";
	if(fabIcon.style.transform != "rotate(45deg)") {
		fab.classList.remove("indigo");
		fab.classList.add("red");
		fabIcon.style.webkitTransform = "rotate(45deg)";
		fabIcon.style.MozTransform = "rotate(45deg)";
		fabIcon.style.msTransform = "rotate(45deg)";
		fabIcon.style.OTransform = "rotate(45deg)";
		fabIcon.style.transform = "rotate(45deg)";
		$('#input' + currentTableName + 'Div').show('fast');
	}
	else {
		fab.classList.remove("red");
		fab.classList.add("indigo");
		fabIcon.style.webkitTransform = "";
		fabIcon.style.MozTransform = "";
		fabIcon.style.msTransform = "";
		fabIcon.style.OTransform = "";
		fabIcon.style.transform = "";
		cancelButtonClicked();
	}
}
function editOnClick() { // To Edit Records of ANY Table.
	editing = true;
	$('#fab').click();
}
function doneButtonClicked() {
	if(adding) {
		document.getElementsByClassName('add' + currentTableName + 'HiddenButton')[0].click();
		notify("add");
	}
	else if(editing) {
		document.getElementsByClassName('edit' + currentTableName + 'HiddenButton')[0].click();
		notify("updat"); // not update.
	}
	$('#fab').click();
}
function cancelButtonClicked() {
	if(adding) {
		Materialize.toast('Adding ' + currentTableName + ' canceled!', 1000);
		adding = false;
	}
	else if(editing) {
		Materialize.toast('Editing ' + currentTableName + ' canceled!', 1000);
		editing = false;
	}
	document.getElementsByClassName('input' + currentTableName + 'Form')[0].reset();
	$('#input' + currentTableName + 'Div').hide('fast');
}
function removeButtonClicked() {
	document.getElementsByClassName('remove' + currentTableName + 'HiddenButton')[0].click();
	var toastContent = $('<span>' + currentTableName + ' removed successfully!</span>').add($('<button class="btn-flat toast-action" style="color: #c5cae9;" onclick="restoreButtonClicked(\'' + currentTableName + '\')">Undo</button>'));
	Materialize.toast(toastContent, 4000);
}
function restoreButtonClicked() {
	document.getElementsByClassName('restore' + currentTableName + 'HiddenButton')[0].click();
	$('.toast').first()[0].M_Toast.remove();
	Materialize.toast(currentTableName + " restored successfully!", 4000);
}
function notify(actionName) {
	reload('succeeded' + currentTableName);
	if($('#succeeded' + currentTableName).val() === 'true')
		Materialize.toast(currentTableName + " " + actionName + "ed successfully!", 4000);
	else
		Materialize.toast("Error " + actionName + "ing " + currentTableName + "!", 4000);
}
function reload(elementId) {
	var container = document.getElementById(elementId);
	var content = container.innerHTML;
	container.innerHTML = content;
}