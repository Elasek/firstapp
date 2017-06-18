function initPage(){
	loadCountries();
	$("#postSave").click(function(response) {
		var data = $("#dataform").serialize(); 
	    $.post("restservices/country", data, function(response) {
	    	console.log("testing");
	    	$("#response").text(JSON.stringify(response));
	    }); 
	});
}

function loadCountries(){
	$.get("restservices/countries", function(data) {
		$.each(data, function(i, d) {
		    $("#datatable").append("<tr id='row"+i+"'><td>"+ d.name + "</td><td>"+ d.capital +"</td><td>"+
		    		d.continent+"</td><td><button id='deleteBtn"+i+"'>Delete</button></td><td><button id='wijzigBtn"+i+"'>Wijzig</button></td></tr>");
		    document.querySelector("#deleteBtn"+i).addEventListener("click", function(){
		    	console.log("delete dit land");
		    });
		    document.querySelector("#wijzigBtn"+i).addEventListener("click", function(){
		    	console.log("wijzig dit land");
		    });
		});
	});
}


initPage();