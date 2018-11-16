$(document).ready(function() {
	$('#sidebarCollapse').on('click', function () {
		$('#sidebar').toggleClass('active');
	});
	$('.msg').hide();
	// var modal=document.getElementById('simpleModal');
	// var modalBtn=document.getElementById('modalBtn');
	// var closeBtn=document.getElementsByClassName('closeBtn')[0];

	var sbutton=document.getElementById('submitstudent');

	// modalBtn.addEventListener('click',openModal);
	// closeBtn.addEventListener('click',closeModal);
	// window.addEventListener('click',clickOutside);
	sbutton.addEventListener('click',registerSeller);

	// function openModal(){
	// 	modal.style.display='block';
	// }
	// function closeModal(){
	// 	modal.style.display='none';
	// }
	// function clickOutside(e){
	// 	if(e.target==modal){
	// 		modal.style.display='none';
	// 		document.getElementById("display").innerHTML="";
	// 	}
	// }

	function registerSeller(){
		$('.msg').hide();
		var x = document.forms.namedItem("form1");
		var y = document.forms.namedItem("form2");
		console.log(y);
		var i;
		var arr = {};
		var arr1={}
		var form = new FormData(y);
		for(i=0;i<x.length;i++){
			if(x.elements[i].name!="file"){
				if ( x.elements[i].name == "rollNumber" ) {
					var rollNumber = parseInt(x.elements[i].value);
					if ( isNaN(rollNumber) || rollNumber < 1) {alert("Please roll number as a number greater than 0.")}
				}
				if ( x.elements[i].name == "physicsMarks" || x.elements[i].name == "chemistryMarks" 
				|| x.elements[i].name == "mathematicsMarks"  ) {
					var marks = parseInt(x.elements[i].value);
					var field = x.elements[i].name;
					if ( isNaN(marks) ) {alert("Marks must be a number from 0 to 100. Check " + field); return;}
					if (marks < 0 || marks > 100) { alert("Enter valid marks of " + field + " from 0 to 100."); return;}
				}
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
//		        var dis=document.getElementById("display");
		        // document.getElementById("f1").reset();
		        // document.getElementById("f2").reset();
		        if(json_data == 1) $('#success-msg').show("slow");
				else if(json_data == 2) $('#eexist-msg').show("slow");
				else $('#error-msg').show("slow");
//		        if(json_data==1){
//		        	$.
////		        	dis.innerHTML="Student Registration Successfull";
//		        }else{
//		        	dis.innerHTML="There is an Error while registering";
//		        }
		    }
		};
	}
});