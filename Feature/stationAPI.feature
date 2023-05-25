#Stations_API

Feature: To test functionality of adding a new resource in stations api

@API_post_without_api_key
	Scenario: Verify the error message when user tries to register a weather station without an API key
	Given User creates a new payload with 'SF_TEST001','San Francisco Test Station','37.76','-122.43','150'
	And   User set the URL "http://api.openweathermap.org/data/3.0/stations"
	When  User send the POST HTTP request
	Then  Verify the resonseCode as '401'
	

@API_post_station
	Scenario Outline: Verify the responsecodd when user tries to register a weather station with a valid API key
	Given User creates a new payload with "<external_id>","<name>","<latitude>","<longitude>","<altitude>"
	And   User set the URL "http://api.openweathermap.org/data/3.0/stations?appId=465716e0a0ec83e79709e1f99f623332"
	When  User send the POST HTTP request
	Then  Verify the resonseCode as '201'
	
	Examples:
	|external_id | name                       | latitude| longitude| altitude |
	|DEMO_TEST001| Team Demo Test Station 001 | 33.33   | -122.43  | 222      |
	|DEMO_TEST002| Team Demo Test Station 002 | 44.44   | -122.44  | 111      |


@API_get_station
	Scenario Outline: Verify the response when user hits the weather station API
	Given User set the URL "http://api.openweathermap.org/data/3.0/stations?appId=465716e0a0ec83e79709e1f99f623332"
	When  User send the Get HTTP request
	Then  Verify the resonseCode as '200'
	And   Verify the responseBody as "<external_id>","<name>","<latitude>","<longitude>","<altitude>"
	
	Examples:
	|external_id | name                       | latitude| longitude| altitude |
	|DEMO_TEST001| Team Demo Test Station 001 | 33.33   | -122.43  | 222      |
	|DEMO_TEST002| Team Demo Test Station 002 | 44.44   | -122.44  | 111      |