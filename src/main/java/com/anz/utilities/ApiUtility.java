package com.anz.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.testng.Assert;

public class ApiUtility {

	static int responseCode;

	public static String sendGetRequest(String url) {
		StringBuffer response= new StringBuffer();
		try {
			@SuppressWarnings("deprecation")
			URL obj= new URL(url);
			HttpURLConnection con=(HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			responseCode= con.getResponseCode();
			if(responseCode== HttpURLConnection.HTTP_OK) {
				BufferedReader in= new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				while((inputLine= in.readLine())!= null) {
					response.append(inputLine);
				}
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response.toString();
	}

	public static String sendPostRequest(String url, String payload) {
		StringBuffer response= new StringBuffer();
		try {
			@SuppressWarnings("deprecation")
			URL obj= new URL(url);
			HttpURLConnection con=(HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			OutputStream os= con.getOutputStream();
			os.write(payload.getBytes());
			os.flush();
			os.close();
			responseCode= con.getResponseCode();
			if(responseCode== HttpURLConnection.HTTP_OK) {
				BufferedReader in= new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				while((inputLine= in.readLine())!= null) {
					response.append(inputLine);
				}
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response.toString();
	}

	public static String sendTokenRequest(String url) {
		String postData="grant_type=client_credentials&client_id=5741f5d89f42b8c03527e64ebb0f5d4e&client_secret=10da697e7acf3f16860eec9e847050ed&scope=sb";
		StringBuffer response= new StringBuffer();
		try {
			@SuppressWarnings("deprecation")
			URL obj= new URL(url);
			HttpURLConnection con=(HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setDoOutput(true);
			OutputStream os= con.getOutputStream();
			os.write(postData.getBytes());
			os.flush();
			os.close();
			responseCode= con.getResponseCode();
			if(responseCode== HttpURLConnection.HTTP_OK) {
				BufferedReader in= new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				while((inputLine= in.readLine())!= null) {
					response.append(inputLine);
				}
				in.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return response.toString();
	}

	public static void validateResponseCode(Integer responseCode1) {
		if(responseCode1==200) {
			Assert.assertEquals(responseCode, HttpURLConnection.HTTP_OK);
		}else if(responseCode1==404) {
			Assert.assertEquals(responseCode, HttpURLConnection.HTTP_NOT_FOUND);
		}else if(responseCode1==401) {
			Assert.assertEquals(responseCode, HttpURLConnection.HTTP_UNAUTHORIZED);		
		}else if(responseCode1==201) {
			Assert.assertEquals(responseCode, HttpURLConnection.HTTP_CREATED);
		}
	}
}