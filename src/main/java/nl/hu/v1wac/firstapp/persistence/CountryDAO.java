package nl.hu.v1wac.firstapp.persistence;

import java.sql.*;
import java.util.*;
import nl.hu.v1wac.firstapp.model.Country;

public class CountryDAO extends BaseDAO {

	private List<Country> selectCountries(String query) {
		List<Country> results = new ArrayList<Country>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				String name = dbResultSet.getString("name");
				String iso2Code = dbResultSet.getString("code2");
				String iso3Code = dbResultSet.getString("code");
				String capital = dbResultSet.getString("capital");
				String continent = dbResultSet.getString("continent");
				String region = dbResultSet.getString("region");
				double surface = dbResultSet.getDouble("surfacearea");
				int population = dbResultSet.getInt("population");
				String government = dbResultSet.getString("governmentform");
				double latitude = dbResultSet.getDouble("latitude");
				double longitude = dbResultSet.getDouble("longitude");
				Country newCountry = new Country(iso2Code, iso3Code, name, capital, continent, region, surface,
						population, government, latitude, longitude);
				results.add(newCountry);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}
	
	private Country selectCountry(String query) {
		Country newCountry = null;
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				String name = dbResultSet.getString("name");
				String iso2Code = dbResultSet.getString("code2");
				String iso3Code = dbResultSet.getString("code");
				String capital = dbResultSet.getString("capital");
				String continent = dbResultSet.getString("continent");
				String region = dbResultSet.getString("region");
				double surface = dbResultSet.getDouble("surfacearea");
				int population = dbResultSet.getInt("population");
				String government = dbResultSet.getString("governmentform");
				double latitude = dbResultSet.getDouble("latitude");
				double longitude = dbResultSet.getDouble("longitude");
				newCountry = new Country(iso2Code, iso3Code, name, capital, continent, region, surface,
						population, government, latitude, longitude);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return newCountry;
	}

	public Country save(Country country) {
		System.out.println("Ik kom wel in de save");
		return country;
	}

	public List<Country> findAll() {
		return selectCountries("select * from country");
	}

	public Country findByCode(String code) {
		return selectCountry("select * from country where code2 = "+code);
	}

	public List<Country> find10LargestPopulations() {
		List<Country> allCountries = findAll();
		Collections.sort(allCountries, new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				return c2.getPopulation() - c1.getPopulation();
			};
		});
		
		return allCountries.subList(0, 10);
	}

	public List<Country> find10LargestSurfaces() {
		List<Country> allCountries = findAll();
		Collections.sort(allCountries, new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				return (int)(c2.getSurface() - c1.getSurface());
			};
		});
		return allCountries.subList(0, 10);
	}

	public Country update(Country country) {
		return null;
	}

	public boolean delete(Country country) {
		return true;
	}
}
