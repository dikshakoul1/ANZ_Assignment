package com.anz.runner;

import io.cucumber.testng.*;

@CucumberOptions(
		features = "Feature",
		glue = {"com.anz.stepdefinition"},
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		monochrome = true,
		publish = true)
public class Runner extends AbstractTestNGCucumberTests {
}