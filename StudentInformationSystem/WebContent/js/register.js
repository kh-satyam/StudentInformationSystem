$(document).ready(function() {
	var modal=document.getElementById('simpleModal');
	var modalBtn=document.getElementById('modalBtn');
	var closeBtn=document.getElementsByClassName('closeBtn')[0];

	var sbutton=document.getElementById('submitstudent');

	modalBtn.addEventListener('click',openModal);
	closeBtn.addEventListener('click',closeModal);
	window.addEventListener('click',clickOutside);
	sbutton.addEventListener('click',registerSeller);

	function openModal(){
		modal.style.display='block';
	}
	function closeModal(){
		modal.style.display='none';
	}
	function clickOutside(e){
		if(e.target==modal){
			modal.style.display='none';
			document.getElementById("display").innerHTML="";
		}
	}

	function registerSeller(){
		var x = document.forms.namedItem("form1");
		var y = document.forms.namedItem("form2");
		console.log(y);
		var i;
		var arr = {};
		var arr1={}
		var form = new FormData(y);
		for(i=0;i<x.length;i++){
			if(x.elements[i].name!="file"){
				arr[x.elements[i].name]=x.elements[i].value;
			}
		}
		arr=JSON.stringify(arr)
		console.log(arr);
		form.append("json",arr);
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "http://localhost:8080/restServices/webapi/student/add");
		//xhr.setRequestHeader("Content-Type", "multipart/form-data");
		xhr.send(form);
		xhr.onreadystatechange = function () {
		    if (xhr.readyState === 4 && xhr.status === 200) {
		        var json_data = xhr.responseText;
		        var dis=document.getElementById("display");
		        document.getElementById("f1").reset();
		        document.getElementById("f2").reset();
		        if(json_data==1){
		        	dis.innerHTML="Student Registration Successfull";
		        }else{
		        	dis.innerHTML="There is an Error while registering";
		        }
		    }
		};
	}
});