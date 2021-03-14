/**
 * 
 */
var xhrObject;
function createXHR() {
	if(window.ActiveXObject) {
		xhrObject = new ActionXObject("Microsoft.XMLHTTP");
	}else if(window.XMLHttpRequest) {
		xhrObject = new XMLHttpRequest();
	}
}
function startMethod() {
	var s = "안녕하세요";
	createXHR();
	xhrObject.onreadystatechange = resultProcess;
	xhrObject.open("GET","ajaxEx01.xml","ture");
	xhrObject.send(null);
	console.log(s);
}
function resultProcess() {
	if(xhrObject.readyState == 4){
		if(xhrObject.status == 200) {
			document.getElementById("displayArea").innerHTML = xhrObject.responseText; 
		}
	}
}