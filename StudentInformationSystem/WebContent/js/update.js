$(document).ready(function() {
	var sbutton=document.getElementById('updateStudent');
	sbutton.addEventListener('click',updateRecord);
	function updateRecord(){
		var x = document.forms.namedItem("form3");
		var i;
		var arr = {};
		var form = new FormData();
		for(i=0;i<x.length;i++){
				arr[x.elements[i].name]=x.elements[i].value;
		}
		console.log(arr);
		form.append("json",arr);
		var val1=arr["rollno"];
		var val2=arr["parameter"];
		var val3=arr["new_record"];
		var val1=val1+"/"+val2+"/"+val3;
		console.log(val1);
		var xhr = new XMLHttpRequest();
		xhr.open('PUT', 'http://localhost:8080/student_api/webapi/Student/update/'+val1, true);
		xhr.setRequestHeader("Content-Type", "application/json");
		data=JSON.stringify(arr);
		console.log(data);
		xhr.send(data);
		xhr.onreadystatechange = function () {
		    if (xhr.readyState === 4 && xhr.status === 200) {
		        var json_data = xhr.responseText;
		        var dis=document.getElementById("display");
		        if(json_data==1){
		        	dis.innerHTML="Student Record Updated";
		        }else{
		        	dis.innerHTML="There is an Error while Updating";
		        }
		    }
		};
		
	}

});