function myIntervalFunction() {
	var tempvalue = document.querySelector("#txtArea").value;
	if(txtAreaValue != tempvalue){
		txtAreaValue = tempvalue;
		console.log("Text veranderd naar: " + txtAreaValue);
	}
}
txtAreaValue = document.querySelector("#txtArea").value;
var intervalID = setInterval(myIntervalFunction, 5000);