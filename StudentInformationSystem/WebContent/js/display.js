function hide_tw() {
	if($( '#invalid' ).is(":visible"))
		$('#invalid').hide();
	if($( '#information' ).is(":visible"))
		$('#information').hide();
}

$(document).ready(function() {
	hide_tw();
	$('#submit').click(function()
	{
		hide_tw();
		rollNumber = document.getElementById("rollNumber").value;
	    if (rollNumber == "") 
	    {
	        alert("Please enter roll number.");
	    } 
	    else 
	    {
	    	var jersey_url = "http://localhost:8080/student_api/webapi/Student/students/" + rollNumber;
		    $.ajax({
		        url: jersey_url
		    }).then(function(data) {
		    	if(data != undefined) {
			    	console.log(data);
			    	$('#name').empty().append(data.Name);
			       	$('#rollNumber').empty().append(data.rollNo);
			       	$('#DOB').empty().append(data.DOB);
			       	$('#age').empty().append(data.age);
			       	$('#chemistryMarks').empty().append(data.chemistryMarks);
			       	$('#mathematicsMarks').empty().append(data.mathematicsMarks);
			       	$('#physicsMarks').empty().append(data.physicsMarks);
			       	$('#information').show("slow");
			       	var imgUrl = "http://localhost:8080/student_api/webapi/Student/"+"download/image/"+data.rollno;
			       	$("#imageUrl").attr("src", imgUrl);
			       	$("#imageUrl").show();
		    	} else {
		    		$('#invalid').show("slow");
		    	}
		    });
		}
	});
});