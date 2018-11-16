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
		var inputRollNumber = $("#inputRollNumber").val();
	    if (inputRollNumber == "") 
	    {
	        alert("Please enter roll number.");
	    } else if ( isNaN(inputRollNumber) || inputRollNumber < 0) {
	    	alert("Please roll number as a number greater than 0.")
	    }
	    else 
	    {
	    	var jersey_url = "http://localhost:8080/restServices/webapi/student/get/" + inputRollNumber;
		    $.ajax({
		        url: jersey_url
		    }).then(function(data) {
		    	if(data != undefined) {
			    	console.log(data);
			    	$('#Name').empty().append(data.name);
			       	$('#rollNumber').empty().append(data.rollNumber);
	                var date = data.DOB;
			       	var year = date.substr(0,4);
	                var month = date.substr(5,2);
	                var day = date.substr(8,2)
			       	$('#DOB').empty().append(day + "/" + month + "/" + year);
			       	$('#age').empty().append(data.age);
			       	$('#chemistryMarks').empty().append(data.chemistryMarks);
			       	$('#mathematicsMarks').empty().append(data.mathematicsMarks);
			       	$('#totalMarks').empty().append(data.totalMarks);
			       	$('#physicsMarks').empty().append(data.physicsMarks);
			       	$('#avgMarks').empty().append(data.avgMarks);
			       	$('#grade').empty().append(data.grade);
			       	$('#information').show("slow");
			       	var imgUrl = "http://localhost:8080/restServices/webapi/student/download/image/"+data.rollNumber;
			       	$("#imageUrl").attr("src", imgUrl);
			       	$("#imageUrl").show();
		    	} else {
		    		$('#invalid').show("slow");
		    	}
		    });
		}
	});
});