package com.anz.stepdefinition;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import com.anz.utilities.ApiUtility;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StationsAPI_StepDefinition {
	String payload,url,response= null;
	ArrayList<String> Arrexternal_id= new ArrayList<String>();
	ArrayList<String> Arrname= new ArrayList<String>();
	ArrayList<String> Arrlatitude= new ArrayList<String>();
	ArrayList<String> Arrlongitude= new ArrayList<String>();
	ArrayList<String> Arraltitude= new ArrayList<String>();
	
	@Given("User creates a new payload with {string},{string},{string},{string},{string}")
	public void user_creates_a_new_payload_with(String external_id, String name, String latitude, String longitude, String altitude) {
		try {
			JSONObject obj= new JSONObject();
			obj.put("external_id", external_id);
			obj.put("name", name);
			obj.put("latitude", Float.valueOf(latitude));
			obj.put("longitude", Float.valueOf(longitude));
			obj.put("altitude", Integer.parseInt(altitude));
			payload= obj.toString();
		}catch(Exception e) {
			e.printStackTrace();
			boolean condition = false;
			Assert.assertTrue(condition,e.getMessage());
		}
	}

	@And("User set the URL {string}")
	public void user_set_the_url(String inputUrl) {
		url=inputUrl;
	}
	
	@When("User send the POST HTTP request")
	public void User_send_the_POST_HTTP_request() {
		try {
			response=  ApiUtility.sendPostRequest(url,payload);
		}catch(Exception e) {
			e.printStackTrace();
			boolean condition = false;
			Assert.assertTrue(condition,e.getMessage());
		}
	}
		
	@Then("Verify the resonseCode as {string}")
	public void Verify_the_resonseCode_as(String code) {
		try {
			ApiUtility.validateResponseCode(Integer.parseInt(code));
		}catch(Exception e) {
			e.printStackTrace();
			boolean condition = false;
			Assert.assertTrue(condition,e.getMessage());
		}
	}
	
	@When("User send the Get HTTP request")
	public void User_send_the_Get_HTTP_request() {
		try {
			response= ApiUtility.sendGetRequest(url);
		}catch(Exception e) {
			e.printStackTrace();
			boolean condition = false;
			Assert.assertTrue(condition,e.getMessage());
		}
	}
	
	@Then("Verify the responseBody as {string},{string},{string},{string},{string}")
	public void verify_the_response_body_as(String external_id, String name, String latitude, String longitude, String altitude) {
		try {
			JSONArray jArray= new JSONArray(response);
			for(int i=0;i<jArray.length();i++) {		
				JSONObject jobj= jArray.getJSONObject(i);
				Arrexternal_id.add(jobj.get("external_id").toString()); 
				Arrname.add(jobj.get("name").toString()); 
				Arrlatitude.add(jobj.get("latitude").toString()); 
				Arrlongitude.add(jobj.get("longitude").toString()); 
				Arraltitude.add(jobj.get("altitude").toString());  
			}
			Assert.assertEquals(Arrexternal_id.contains(external_id), true);
			Assert.assertEquals(Arrname.contains(name), true);
			Assert.assertEquals(Arrlatitude.contains(latitude), true);
			Assert.assertEquals(Arrlongitude.contains(longitude), true);
			Assert.assertEquals(Arraltitude.contains(altitude), true);
		}catch(Exception e) {
			e.printStackTrace();
			boolean condition = false;
			Assert.assertTrue(condition,e.getMessage());
		}
	}

}
