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
console.log("v="+v);
var b=localStorage.getItem("roll");
console.log("current user rollno= "+b);
if(v=="")
	{
		v="aal";
	}
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
	if(request.readyState == 4){  
		var val = JSON.parse(request.responseText);  
		console.log("comments array=");
		console.log(val);
		// document.getElementById('amit').innerHTML="";
		// document.getElementById('amit').innerHTML+="<b>rollno</b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<b>comment</b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<b>name</b><br/>";
		// for (i in val) {
		// 	document.getElementById('amit').innerHTML+=val[i]['rollno']+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
		// 	document.getElementById('amit').innerHTML+=val[i]['comment']+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
		// 	document.getElementById('amit').innerHTML+=val[i]['name']+"<br/>";
		// }
		var populate = "<table> <tr> <th>Roll Number   </th><th>Name    </th><th>Comment  </th></tr>";
		for ( var i in val ) {
			populate += "<tr>";
			populate += ("<td height='50' width='1000' >" + val[i]['rollno'] + "</td>");
			populate += ("<td height='50' width='1000' >" + val[i]['name'] + "</td>");
			populate += ("<td height='50' width='1000' >" + val[i]['comment'] + "</td>");
			populate += ("</tr>");
		};
		populate += ("</table>");
		$("#displayBody").html(populate);

	}  
} 