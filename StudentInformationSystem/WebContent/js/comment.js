$(document).ready(function() {
	
	$('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });

	$('.msg').hide();
});
console.log("here");
var request;  
function sendInfo()  
{  
var v=document.getElementById("comment").value;
console.log(v);
var b=localStorage.getItem("roll");
console.log("current user rollno= "+b);
var url="http://localhost:8080/restServices/webapi/student/comment/"+v+"/"+b;  
  
if(window.XMLHttpRequest){  
request=new XMLHttpRequest();  
}  
else if(window.ActiveXObject){  
request=new ActiveXObject("Microsoft.XMLHTTP");  
}  
  
try  
{  
request.onreadystatechange=getInfo;  
request.open("GET",url,true);  
request.send();  
}  
catch(e)  
{  
alert("Unable to connect to server");  
}  
}  
  
function getInfo(){  
if(request.readyState==4){  
var val=request.responseText;  
document.getElementById('amit').innerHTML=val;  
}  
} 