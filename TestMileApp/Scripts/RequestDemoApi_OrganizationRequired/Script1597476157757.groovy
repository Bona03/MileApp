import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.googlecode.javacv.cpp.postproc
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent

import internal.GlobalVariable as GlobalVariable

def request=(RequestObject)findTestObject('POST_requestDemo')

String RequestBody = '{ "full_name":"Bona","email":"bona@gmail.com","phone":"0829898998","organization_name":""}'


try {
	request.setBodyContent(new HttpTextBodyContent(RequestBody, "UTF-8", "application/json"))
} catch (Exception ex) {
	println (ex.detailMessage)
}

postDemo = WS.sendRequest(request)

String JSONResponse = postDemo.getResponseText()

println('Response: '+JSONResponse)

String statusCode = postDemo.getStatusCode()

WS.verifyElementPropertyValue(postDemo, 'status', 'false')

WS.verifyElementPropertyValue(postDemo, 'message', 'The organization name field is required.')

WS.verifyEqual(statusCode, '400')