
document.querySelector("#tekst").addEventListener("keyup", function(){
	window.localStorage.setItem("tekst", document.querySelector("#tekst").value);
	console.log(window.localStorage);
});


