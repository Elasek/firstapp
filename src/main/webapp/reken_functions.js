function numberHandler(event) {
    var splitted = event.target.id.split("_");
    if(document.querySelector("#display").innerHTML == 0 || finished == true){
    	document.querySelector("#display").innerHTML = splitted[1];
    	finished = false;
    } else{
    	document.querySelector("#display").innerHTML += splitted[1];
    }
}

function handler(event) {
	firstEntry = parseInt(document.querySelector("#display").innerHTML);
	document.querySelector("#display").innerHTML = 0;
	action = event.target.id;
}

function eqHandler(event) {
	secondEntry = parseInt(document.querySelector("#display").innerHTML);
	finished = true;
	if(action == "btn_plus"){
		document.querySelector("#display").innerHTML = firstEntry + secondEntry;
	} else if(action == "btn_min"){
		document.querySelector("#display").innerHTML = firstEntry - secondEntry;
	} else if(action == "btn_div"){
		document.querySelector("#display").innerHTML = firstEntry / secondEntry;
	} else if(action == "btn_prod"){
		document.querySelector("#display").innerHTML = firstEntry * secondEntry;
	} else if(action == "btn_clear"){
		document.querySelector("#display").innerHTML = 0;
		firstEntry = null;
	}
}


var btndiv = document.querySelector("#btn_div");
btndiv.addEventListener("click", handler);
var btnprod = document.querySelector("#btn_prod");
btnprod.addEventListener("click", handler);
var btnmin = document.querySelector("#btn_min");
btnmin.addEventListener("click", handler);
var btnclear = document.querySelector("#btn_clear");
btnclear.addEventListener("click", handler);
var btneq = document.querySelector("#btn_eq");
btneq.addEventListener("click", eqHandler);
var btnplus = document.querySelector("#btn_plus");
btnplus.addEventListener("click", handler);


var btn1 = document.querySelector("#btn_1");
btn1.addEventListener("click", numberHandler);
var btn2 = document.querySelector("#btn_2");
btn2.addEventListener("click", numberHandler);
var btn3 = document.querySelector("#btn_3");
btn3.addEventListener("click", numberHandler);
var btn4 = document.querySelector("#btn_4");
btn4.addEventListener("click", numberHandler);
var btn5 = document.querySelector("#btn_5");
btn5.addEventListener("click", numberHandler);
var btn6 = document.querySelector("#btn_6");
btn6.addEventListener("click", numberHandler);
var btn7 = document.querySelector("#btn_7");
btn7.addEventListener("click", numberHandler);
var btn8 = document.querySelector("#btn_8");
btn8.addEventListener("click", numberHandler);
var btn9 = document.querySelector("#btn_9");
btn9.addEventListener("click", numberHandler);
var btn0 = document.querySelector("#btn_0");
btn0.addEventListener("click", numberHandler);
