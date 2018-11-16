$(document).ready(function() {
	$('#sidebarCollapse').on('click', function () {
		$('#sidebar').toggleClass('active');
	});
	$('.msg').hide();
	if($( '#displayHeader' ).is(":visible"))
		$('#displayHeader').hide();

	$('#submit').click(function()
	{
		if($( '#displayHeader' ).is(":visible"))
			$('#displayHeader').hide();
		$("#diplayBody").empty();
		console.log($("#displayBody div").val());
		var startingRollNumber = $("#startingRollNumber").val();
	    var endingRollNumber = $("#endingRollNumber").val();
	    var key = $("#select_option option:selected").val();
	    console.log(key + startingRollNumber + endingRollNumber);
	    if (startingRollNumber == "" || endingRollNumber == "") 
	    {
	        alert("Roll number can't be empty.");
	    } else if ( isNaN(startingRollNumber) || isNaN(endingRollNumber) 
	    	|| startingRollNumber < 1 || endingRollNumber < 1) {
	    	alert("Roll number must be a number and greater than 0.")
	    }
	    else 
	    {
	    	var jersey_url = "http://localhost:8080/restServices/webapi/student/getAllRange/" + startingRollNumber + "/" + endingRollNumber + "/" + key;
		    $.ajax({
		        url: jersey_url
		    }).then(function(data) {
		    	if(data != undefined) {
		    		console.log(data);
		    		var openingDiv = "<div class=\"col-sm-";
		    		var opdiv2 = "\" style=\"background-color:#bbb;\">";
		    		var closingDiv = "</div>";
		    		var populate = "";
		    		for ( var i in data ) {
		    			populate += "<div class=\"row\">";
	    				populate += (openingDiv + 2 + opdiv2 + data[i].rollNumber + closingDiv);
	    				populate += (openingDiv + 3 + opdiv2 + data[i].name + closingDiv);
//	    				populate = populate + (openingDiv + 2 + opdiv2 + data[i].DOB + closingDiv);
//	    				populate = populate + (openingDiv + 1 + opdiv2 + data[i].age + closingDiv);
	    				populate += (openingDiv + 3 + opdiv2 + data[i].totalMarks + closingDiv);
	    				populate += (openingDiv + 2 + opdiv2 + data[i].grade + closingDiv);
	    				var imgUrl = "http://localhost:8080/restServices/webapi/student/download/image/"+data[i].rollNumber;
	    				populate += (openingDiv + 2 + opdiv2 + "<img src=\"" + imgUrl + "\" style=\"width:50px;height:50px;\">" + closingDiv);
	    				populate += (closingDiv);
		    		};
		    		$("#displayBody").html(populate);
		    		if( !($( '#displayHeader' ).is(":visible")) ) $('#displayHeader').show();
		    	} else {
		    		// $('#invalid').show("slow");
		    		alert("error occured");
		    	}
		    });
		}
	});
	
});