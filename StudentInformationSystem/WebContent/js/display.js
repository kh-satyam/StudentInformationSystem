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
	    	var jersey_url = "http://localhost:8080/restServices/webapi/student/get/" + rollNumber;
		    $.ajax({
		        url: jersey_url
		    }).then(function(data) {
		    	if(data != undefined) {
			    	console.log(data);
			    	$('#Name').empty().append(data.name);
			       	$('#rollNumber').empty().append(data.rollNumber);
			       	$('#DOB').empty().append(data.DOB);
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