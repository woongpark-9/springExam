/**
 * 
 */

// 자바 스크립트 파일
// 파일명 script.js

function check() {
	//문서에 find_frm(폼)에 find_box의 value가 == "" 라면
	if(document.find_frm.find_box.value==""){
		//메세지 출력
		alert("검색어를 입력해 주세요^^;");
		return false;
	}
}