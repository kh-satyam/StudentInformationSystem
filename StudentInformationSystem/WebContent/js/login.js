var request;  

function func()
{
	var a = document.getElementById("rollno").value;
	var b = document.getElementById("pass").value;
	localStorage.setItem("roll", a);
	var url="http://localhost:8080/restServices/webapi/student/login/"+ a + "/"+b;  
	if(window.XMLHttpRequest){  
		request=new XMLHttpRequest();  
	}  
	else if(window.ActiveXObject){  
		request=new ActiveXObject("Microsoft.XMLHTTP");  
	}  
	try {  
		request.onreadystatechange=getInfo;  
		request.open("GET",url,true);  
		request.send();  
	}  
	catch(e) {  
		alert("Unable to connect to server");  
	}  
}  
  
function getInfo(){  
	if(request.readyState==4){  
		var val=request.responseText;  
		console.log(val);
		if(val=="success") {
			location.replace("index2.html");
		}
		document.getElementById('amit').innerHTML=val;  
	} 
} 