/**
 * 
 */
var xhrObject;
function createXHR() {
	if(window.ActiveXObject) {
		xhrObject = new ActiveXObject("Microsoft.XMLHTTP");
		
	}else if(window.XMLHttpRequest) {
		xhrObject = new XMLHttpRequest();
		
	}
}
function startMethod() {
	createXHR();
	xhrObject.onreadystatechange = resultProcess;
	xhrObject.open("Get","ajaxEx02.xml","true");
	xhrObject.send(null);
}
function processXML() {
	var xmlDoc = xhrObject.responseXML;
	var subject ="";
	var trTag = "";
	var tdTag = "";
	var subjects = xmlDoc.getElementsByTagName("subject");
	
	for(var i = 0; i<subjects.length; i++) {
		trTag = document.createElement("tr");
		subject = subjects[i];
		var child = subject.childNodes;
		
		for(var j = 0; j<child.length; j++) {
			tdTag = document.createElement("td");
			var text = document.createTextNode(child[j].firstChild.nodeValue);
			tdTag.appendChild(text);
			trTag.appendChild(tdTag);
		}
		document.getElementById("resultDisplay").appendChild(trTag);
	}
}
function resultProcess() {
	if(xhrObject.readyState == 4) {
		if(xhrObject.status == 200) {
			processXML();
		}
	}
}