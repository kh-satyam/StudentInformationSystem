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
		var i;
		var arr = {};
		var arr1={}
		var form = new FormData(y);
		for(i=0;i<x.length;i++){
			if(x.elements[i].name!="file"){
				// from validation
				// validating roll number
				if ( x.elements[i].name == "rollNumber" ) {
					var rollNumber = parseInt(x.elements[i].value);
					if ( isNaN(rollNumber) || rollNumber < 1) {$("#rollNumber-msg").show("slow"); return;}
				}
				
				// validating dob
				else if ( x.elements[i].name == "DOB" ) { 
					var entDOB = new Date(x.elements[i].value);
					var yage = new Date("2015-01-01");
					var old = new Date("1890-01-01");
					if ( entDOB > yage ) {
						$("#dob-msg").show("slow"); 
						return;
					} else if ( entDOB < old ) {
						$("#dob-old-msg").show("slow");
						return;
					}
				}
				
				// validating marks 
				else if ( x.elements[i].name == "physicsMarks" || x.elements[i].name == "chemistryMarks" 
				|| x.elements[i].name == "mathematicsMarks"  ) {
					var marks = parseInt(x.elements[i].value);
					var field = x.elements[i].name;
					if ( isNaN(marks) || (marks < 0 || marks > 100)) {$("#" + field + "-msg").show("slow"); return;}
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
		        if(json_data == 0) $('#eexist-msg').show("slow");
		        else if(json_data == 1) {
		        	$('#success-msg').show("slow");
		        	x.reset();
		        	y.reset();
		        }
				else $('#error-msg').show("slow");
//		        if(json_data==1){
//		        	$.
////		        	dis.innerHTML="Student Registration Successful";
//		        }else{
//		        	dis.innerHTML="There is an Error while registering";
//		        }
		    }
		};
	}
});