$(document).ready(function() {
	
	$('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });
	
	var sbutton=document.getElementById('updateStudent');
	var toggler=document.getElementById('toggle');
	var z = document.getElementById("t2");
	z.style.visibility='hidden';
	sbutton.addEventListener('click',updateRecord);
	toggler.addEventListener('click',checkDateOrNot);
	
	function checkDateOrNot(){
		console.log(toggler.value);
		var x = document.getElementById("t1");
		var y = document.getElementById("t2");
		if(toggler.value=="DOB"){
			x.style.visibility='hidden';
			if(y.style.visibility==='hidden'){
				console.log(":hidden");
				y.style.visibility='visible';
			}
		}
		else{
			x.style.visibility='visible';
			y.style.visibility='hidden';
		}
	}

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
		var val1=arr["rollNumber"];
		var val2=arr["parameter"];
		var val3=arr["value1"];
		if(toggler.value=="DOB"){
			val3=arr["value2"]
		}
		var val1=val1+"/"+val2+"/"+val3;
		console.log(val1);
		var xhr = new XMLHttpRequest();
		xhr.open('PUT', 'http://localhost:8080/restServices/webapi/student/update/'+val1, true);
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