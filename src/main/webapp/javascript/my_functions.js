function initPage(){
	$.get("http://ip-api.com/json", function(data) {
	    $("#landcode").text("Landcode: " + data.countryCode);
	    $("#land").text("Land: " + data.country);
	    $("#regio").text("Regio: " + data.regionName);
	    $("#stad").text("Stad: " + data.city);
	    $("#postcode").text("Postcode: " + data.zip);
	    $("#latitude").text("Latitude: " + data.lat);
	    $("#longitude").text("Longtitude: " + data.lon);
	    $("#ip").text("IP: " + data.query);
	    document.querySelector("#stad").addEventListener("click", function(){
	    	showWeather(data.lat, data.lon, data.city)
	    });
	    showWeather(data.lat, data.lon, data.city);
	    loadCountries();
	});
}
function showWeather(latitude, longitude, city){
	$("#weerheader").text("Het weer in " + city);
	var currenttime = new Date().getTime() /1000;
	
	if(JSON.parse(window.localStorage.getItem(city))){ //controleer of er al data van de stad is
		console.log("er is al data van " + city);
		
		var oldData = JSON.parse(window.localStorage.getItem(city)); //zet item van storage in variabele
		
		//als de 10minuten nog niet voorbij zijn, gebruik localstorage data.
		if(getTimestamp(currenttime)<getTimestamp(oldData.timestamp)){
			console.log("data is nog geen 10minuten oud, localStorage items pakken.");
			$("#temperatuur").text("Temperatuur: " + Math.round(oldData.temp-273.15)+"C");
			$("#luchtvochtigheid").text("Luchtvochtigheid: " + oldData.lucht);
			$("#windsnelheid").text("Windsnelheid: " + oldData.windsnel);
			$("#windrichting").text("Windrichting: " + getWindrichting(oldData.windrich));
			$("#zonsopgang").text("Zonsopgang: " + getTimestamp(oldData.zonsop));
			$("#zonsondergang").text("Zonsondergang: " + getTimestamp(oldData.zonsonder));
		} else{ //na 10minuten, gegevens opnieuw ophalen
			console.log("10minuten waren voorbij, opnieuw data halen.");
			getWeather(latitude, longitude, city);
		}
	} else { //als er nog geen gegevens van de stad zijn
		console.log("Nog geen gegevens bekend van "+ city);
		getWeather(latitude, longitude, city);
	}
}

function getWeather(latitude, longitude, city){
	$.get("http://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&APPID=1fb4c3cdb36c5e228ff4b9f1f8518905", function(data) {
		$("#temperatuur").text("Temperatuur: " + Math.round(data.main.temp-273.15)+"C");
		$("#luchtvochtigheid").text("Luchtvochtigheid: " + data.main.humidity);
		$("#windsnelheid").text("Windsnelheid: " + data.wind.speed);
		$("#windrichting").text("Windrichting: " + getWindrichting(data.wind.deg));
		$("#zonsopgang").text("Zonsopgang: " + getTimestamp(data.sys.sunrise));
		$("#zonsondergang").text("Zonsondergang: " + getTimestamp(data.sys.sunset));
		var weatherInfo = {"timestamp":(new Date().getTime() + 600000) /1000 ,"temp":data.main.temp, "lucht":data.main.humidity, "windsnel":data.wind.speed, "windrich":data.wind.deg, "zonsop":data.sys.sunrise, "zonsonder":data.sys.sunset};
		window.localStorage.setItem(city, JSON.stringify(weatherInfo));
	});
}
function loadCountries(){
	$.get("restservices/countries", function(data) {
		$.each(data, function(i, d) {
		    $("#datatable").append("<tr id='row"+i+"'><td>"+ d.name + "</td><td>"+ d.capital +"</td><td>"+
		    		d.region+"</td><td>"+d.surface+"</td><td>"+d.population+"</td></tr>");
		    document.querySelector("#row"+i).addEventListener("click", function(){
		    	showWeather(d.lat, d.lng, d.capital)
		    });
		});
	});
}

	
function getTimestamp(sec){
	var date = new Date(sec * 1000);
	return date.toLocaleTimeString();
}
function getWindrichting(degree){
    if (degree>337.5) return 'Noord';
    if (degree>292.5) return 'Noordwest';
    if(degree>247.5) return 'West';
    if(degree>202.5) return 'Zuidwest';
    if(degree>157.5) return 'Zuid';
    if(degree>122.5) return 'Zuidoost';
    if(degree>67.5) return 'Oost';
    if(degree>22.5) return 'Noordoost';
    return 'Noord';
}
initPage();