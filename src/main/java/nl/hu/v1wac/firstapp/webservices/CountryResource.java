package nl.hu.v1wac.firstapp.webservices;

import javax.json.*;
import javax.ws.rs.*;
import nl.hu.v1wac.firstapp.model.*;

@Path("/country")
public class CountryResource {
	WorldService service = ServiceProvider.getWorldService();
	
	@POST
	@Produces("application/json")
	public String createCountry(@FormParam("name") String nm, @FormParam("capital") String cap, 
			@FormParam("code") String code, @FormParam("government") String gov, 
			@FormParam("continent") String con, @FormParam("region") String reg, 
			@FormParam("surface") double sur, @FormParam("population") int pop, 
			@FormParam("latitude") double lat, @FormParam("longitude") double lon) {
		
		System.out.println("Ik kom wel in de createCountry ");
		
		Country newCountry = new Country(code,code,nm,cap,con,reg,sur,pop,gov,lat,lon);
		service.createCountry(newCountry);
		
		JsonObjectBuilder job = Json.createObjectBuilder();
	    job.add("name", nm);
	    job.add("code", code);
		return job.build().toString();
	}
}
