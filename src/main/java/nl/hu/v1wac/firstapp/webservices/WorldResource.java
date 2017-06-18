package nl.hu.v1wac.firstapp.webservices;

import javax.json.*;
import javax.ws.rs.*;
import nl.hu.v1wac.firstapp.model.*;

@Path("/countries")
public class WorldResource {

	@GET
	@Produces("application/json")
	public String getCountries() {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Country c : service.getAllCountries()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", c.getCode());
			job.add("name", c.getName());
			job.add("capital", c.getCapital());
			job.add("continent", c.getContinent());
			job.add("region", c.getRegion());
			job.add("surface", c.getSurface());
			job.add("population", c.getPopulation());
			job.add("lat", c.getLatitude());
			job.add("lng", c.getLongitude());

			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("/largestsurfaces")
	@Produces("application/json")
	public String getLargestSurfaces() {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Country c : service.get10LargestSurfaces()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", c.getCode());
			job.add("name", c.getName());
			job.add("capital", c.getCapital());
			job.add("surface", c.getSurface());
			job.add("government", c.getGovernment());
			job.add("lat", c.getLatitude());

			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("/largestpopulations")
	@Produces("application/json")
	public String getLargestPopulations() {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Country c : service.get10LargestPopulations()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", c.getCode());
			job.add("name", c.getName());
			job.add("capital", c.getCapital());
			job.add("surface", c.getSurface());
			job.add("government", c.getGovernment());
			job.add("lat", c.getLatitude());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("{landcode}")
	@Produces("application/json")
	public String getCountry(@PathParam("landcode") String landcode) {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		Country land = service.getCountryByCode(landcode);

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("id", land.getCode());
		job.add("name", land.getName());
		job.add("capital", land.getCapital());
		job.add("surface", land.getSurface());
		job.add("government", land.getGovernment());
		job.add("lat", land.getLatitude());
		jab.add(job);

		JsonArray array = jab.build();
		return array.toString();
	}
}
