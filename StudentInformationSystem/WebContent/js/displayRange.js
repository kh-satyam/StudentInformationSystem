var x = document.getElementById('getResults');
x.addEventListener('click',fetchResults);

function fetchResults(){
	console.log(document.getElementById('from').value);
	console.log(document.getElementById('to').value);
	console.log(document.getElementById('key').value);
	var from=document.getElementById('from').value;
	var to=document.getElementById('to').value;
	var key=document.getElementById('key').value;
	var val=from + "/" + to +"/" +key;
	console.log(val);
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/restServices/webapi/student/getAllRange/'+val, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send();
	
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        var data = JSON.parse(xhr.responseText);
	        console.log(data);
	        var populate=document.getElementById('display');
	        var html='<div id="myCarousel" style="width:400px;height:400px;" class="carousel slide" data-ride="carousel">';
	        html+='<ol class="carousel-indicators">';
	        var i;
	        var l1;
	        for(i=0;i<data.length;i++){
	        	if(i==0){
	        		l1='<li data-target="#myCarousel" data-slide-to="';
		        	l1+=i;
		        	l1+='" class="active"></li>';
		        	html+=l1;
	        	}else{
	        		l1='<li data-target="#myCarousel" data-slide-to="';
		        	l1+=i;
		        	l1+='"></li>';
		        	html+=l1;
	        	}
	        	
	        }
	        html+='</ol>';
	        html+='<div  style="width:100%;height:100%;" class="carousel-inner">';
	        
	        for(i=0;i<data.length;i++){
	        	if(i==0){
	        		l1='<div  style="width:100%;height:100%;"class="item active">';
	        		l1+='<img'+' style="width:100%;height:100%;" src=';
	        		var url='"http://localhost:8080/restServices/webapi/student/download/image/'+data[i]['rollNumber'];
	        		l1+=url;
	        		l1+= ' "alt="';
	        		l1+=data[i]['rollNumber'];
	        		l1+='">';
	        		l1+='<div class="carousel-caption">';
	        		l1+='<h3>'+data[i]['name']+'</h3><h4>'+data[i]['rollNumber']+'</h4>';
	        		l1+='<h3>'+data[i]['totalMarks']+'</h4><p>'+data[i]['grade']+'</h4></div>';
	        		l1+='</div>';
	        		html+=l1;
	        	}else{
	        		l1='<div style="width:100%;height:100%;" class="item">';
	        		l1+='<img'+' style="width:100%;height:100%;" src=';
	        		var url='"http://localhost:8080/restServices/webapi/student/download/image/'+data[i]['rollNumber'];
	        		l1+=url;
	        		l1+= ' "alt="';
	        		l1+=data[i]['rollNumber'];
	        		l1+='">';
	        		l1+='<div class="carousel-caption">';
	        		l1+='<h3>'+data[i]['name']+'</h3><h4>'+data[i]['rollNumber']+'</h4>';
	        		l1+='<h3>'+data[i]['totalMarks']+'</h4><p>'+data[i]['grade']+'</h4></div>';
	        		l1+='</div>';
	        		html+=l1;
	        	}
	        }
	        
	        html+='</div>';
	        
	        html+='<a class="left carousel-control" href="#myCarousel" data-slide="prev">';
	        html+='<span class="glyphicon glyphicon-chevron-left"></span>';
	        html+='<span class="sr-only">Previous</span>';
	        html+='</a>';
	        html+='<a class="right carousel-control" href="#myCarousel" data-slide="next">';
	        html+='<span class="glyphicon glyphicon-chevron-right"></span>';
	        html+='<span class="sr-only">Next</span>';
	        html+='</a>';
	        html+='</div>';
	        console.log(html);
	        populate.innerHTML=html;
	    }
	};
}