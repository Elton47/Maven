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
		$('.tap-target').tapTarget('open'); // Open Feature-Discovery for FAB.
		document.getElementById('fab').setAttribute("onclick", "fabOnClick()"); // FAB onclick init.
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
	var fab = document.getElementById('fab');
	var fabIcon = document.getElementById('fabIcon');
	if(fabIcon.style.transform != "rotate(45deg)") { // Add.
		if(!editing) {
			adding = true;
			document.getElementById('removeFabAction').style.display = "none";
			$('#add' + currentTableName + 'Div').show('fast');
		}
		else {
			$('#add' + currentTableName + 'Div').hide();
			document.getElementById('removeFabAction').style.display = "";
		}
		fab.classList.remove("indigo");
		fab.classList.add("red");
		fabIcon.style.webkitTransform = "rotate(45deg)";
		fabIcon.style.MozTransform = "rotate(45deg)";
		fabIcon.style.msTransform = "rotate(45deg)";
		fabIcon.style.OTransform = "rotate(45deg)";
		fabIcon.style.transform = "rotate(45deg)";
	}
	else { // Cancel.
		fab.classList.remove("red");
		fab.classList.add("indigo");
		fabIcon.style.webkitTransform = "";
		fabIcon.style.MozTransform = "";
		fabIcon.style.msTransform = "";
		fabIcon.style.OTransform = "";
		fabIcon.style.transform = "";
		if(adding) { // If was adding.
			document.getElementsByClassName('add' + currentTableName + 'Form')[0].reset();
			$('#add' + currentTableName + 'Div').hide('fast');
			adding = false;
		}
		if(editing) { // If was editing.
			document.getElementsByClassName('cancel' + currentTableName + 'HiddenButton')[0].click();
			editing = false;
		}
	}
}
function editOnClick() {
	if(adding) {
		adding = false;
		$('#fab').click();
	}
	if(editing)
		$('#fab').click();
	editing = true;
	$('#fab').click();
}
function doneButtonClicked() {
	if(adding) {
		document.getElementsByClassName('add' + currentTableName + 'HiddenButton')[0].click();
		notify("add");
		adding = false;
	}
	else if(editing) {
		document.getElementsByClassName('edit' + currentTableName + 'HiddenButton')[0].click();
		notify("updat"); // not update.
		editing = false;
	}
	$('#fab').click();
}
function removeButtonClicked() {
	document.getElementsByClassName('remove' + currentTableName + 'HiddenButton')[0].click();
	var toastContent = $('<span>' + currentTableName + ' removed successfully!</span>').add($('<button class="btn-flat toast-action" style="color: #c5cae9;" onclick="restoreButtonClicked(\'' + currentTableName + '\')">Undo</button>'));
	Materialize.toast(toastContent, 4000);
	$('#fab').click();
}
function restoreButtonClicked() {
	document.getElementsByClassName('restore' + currentTableName + 'HiddenButton')[0].click();
	$('.toast').first()[0].M_Toast.remove();
	Materialize.toast(currentTableName + " restored successfully!", 4000);
}
function notify(actionName) {
	if($('#succeeded' + currentTableName).val() == 'true')
		Materialize.toast(currentTableName + " " + actionName + "ed successfully!", 4000);
	else
		Materialize.toast("Error " + actionName + "ing " + currentTableName + "!", 4000);
}
function logout() {
	document.getElementsByClassName('logoutHiddenButton')[0].click();
}