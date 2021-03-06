var adding = false;
var editing = false;
var isSearched = false;
var featureDiscoveryShownOnce = false;
var currentTableName;
function showTable(selectedTableName) {
	if($('#div' + selectedTableName).is(":visible")) {
		$('#div' + selectedTableName).hide("fast");
		document.getElementById('fabDiv').style.display = "none";
		document.getElementById('searchIcon').style.display = "none";
		$('#backFromSearch').hide('fast');
		$('#welcomeDiv').show();
	}
	else {
		hideAllTables();
		$('#div' + selectedTableName).show("fast");
		$('#welcomeDiv').hide("fast");
		document.getElementById('fabDiv').style.display = "";
		document.getElementById('searchIcon').style.display = "";
		if(isSearched)
			$('#backFromSearch').show('fast');
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
	if(fabIcon.style.transform != "rotate(45deg)") {
		if(!editing) { // Adding
			adding = true;
			document.getElementById('removeFabAction').style.display = "none";
			$('#add' + currentTableName + 'Div').show('fast');
		}
		else if(editing) {
			$('#add' + currentTableName + 'Div').hide();
			document.getElementById('removeFabAction').style.display = "";
		}
		fab.classList.remove("indigo");
		fab.classList.add("red");
		fab.title = "Cancel";
		fabIcon.style.webkitTransform = "rotate(45deg)";
		fabIcon.style.MozTransform = "rotate(45deg)";
		fabIcon.style.msTransform = "rotate(45deg)";
		fabIcon.style.OTransform = "rotate(45deg)";
		fabIcon.style.transform = "rotate(45deg)";
	}
	else { // Cancel.
		fab.classList.remove("red");
		fab.classList.add("indigo");
		fab.title = "Add";
		fabIcon.style.webkitTransform = "";
		fabIcon.style.MozTransform = "";
		fabIcon.style.msTransform = "";
		fabIcon.style.OTransform = "";
		fabIcon.style.transform = "";
		if(adding) {
			document.getElementsByClassName('add' + currentTableName + 'Form')[0].reset();
			$('#add' + currentTableName + 'Div').hide('fast');
			adding = false;
		}
		if(editing) {
			document.getElementsByClassName('cancel' + currentTableName + 'HiddenButton')[0].click();
			editing = false;
		}
	}
}
function editOnClick() {
	if(adding)
		$('#fab').click();
	if(editing)
		$('#fab').click();
	editing = true;
	$('#fab').click();
}
function searchOnClick() {
	if(adding || editing)
		$('#fab').click();
	$('#search' + currentTableName + 'Modal').modal(); // Init.
	$('#search' + currentTableName + 'Modal').modal('open');
}
function searchActionOnClick() {
	document.getElementsByClassName('search' + currentTableName + 'HiddenButton')[0].click();
	$('#search' + currentTableName + 'Modal').modal('close');
	$('#backFromSearch').show('fast');
	isSearched = true;
}
function backFromSearchOnClick() {
	if(adding || editing)
		$('#fab').click();
	document.getElementsByClassName('search' + currentTableName + 'HiddenButton')[0].click();
	$('#backFromSearch').hide('fast');
	isSearched = false;
}
function doneButtonClicked() {
	if(adding) {
		document.getElementsByClassName('add' + currentTableName + 'HiddenButton')[0].click();
		adding = false;
	}
	else if(editing) {
		document.getElementsByClassName('edit' + currentTableName + 'HiddenButton')[0].click();
		editing = false;
	}
	$('#fab').click();
}
function removeButtonClicked() {
	document.getElementsByClassName('remove' + currentTableName + 'HiddenButton')[0].click();
	var toastContent = $('<span>' + currentTableName + ' removed successfully!</span>').add($('<button class="btn-flat toast-action" style="color: #9fa8da;" onclick="restoreButtonClicked(\'' + currentTableName + '\')">Undo</button>'));
	Materialize.toast(toastContent, 4000);
	$('#fab').click();
}
function restoreButtonClicked() {
	document.getElementsByClassName('restore' + currentTableName + 'HiddenButton')[0].click();
	$('.toast').first()[0].M_Toast.remove();
	Materialize.toast(currentTableName + " restored successfully!", 4000);
}
function logout() {
	document.getElementsByClassName('logoutHiddenButton')[0].click();
}