package com.BDD.GoAudits.runner;

import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.BDD.GoAudits.ExtentManager;
import com.BDD.GoAudits.ExtentTestManager;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;




@CucumberOptions(features={"src//test//java//com//BDD//GoAudits//features"}
					,glue={"com.BDD.GoAudits.stepdefinations","com.BDD.GoAudits.utility"}
					,plugin = {"pretty", "html:target/cucumber"}
					
					
		)
@Test
public class RunTest extends AbstractTestNGCucumberTests{

}






