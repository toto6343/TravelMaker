/**
 * ajaxdemo1.js
 */
//alert("Test"); //[��ũ�� ������ �� �Ǿ����� Ȯ�� �� �ּ�ó�� ��]
let xhr = null;
function sendRequest(url,param,callback,method){
   xhr = new XMLHttpRequest();
   //���۹���� �м�
   let httpMethod = (method !== 'POST' && method !== 'post')?'GET':'POST';
   
   //�Ķ���� �� 
   let httpParam = (param === null||param ==='')?null:param;
   
   var httpURL = url;
   
   //��û ��Ŀ� ���� ó�� test.jsp?idx=11
   if(httpMethod === 'GET' && httpParam !== null){
      httpURL = httpURL+"?"+httpParam;
   }
   xhr.onreadystatechange=callback;
   xhr.open(httpMethod,httpURL,true);
   //��û�� Ÿ�� 
	if(type == "json"){
		xhr.setRequestHeader("Content-Type","application/json");
	}else{
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	}
	console.log("httpMethod => "+httpMethod+" : httpParam => "+httpParam);
	xhr.send(httpMethod === 'POST'?httpParam:null);
}
