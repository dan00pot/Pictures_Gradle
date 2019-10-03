package scripts.pictures;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import libs.clients.AADSWebKeywords;
import libs.common.DriverManagement;
import libs.common.Selenium;
import testData.aadsData;

public class AADS_Pictures_02 {
	static WebDriver webDriver, webDriver2;
	AADSWebKeywords AADSWebDriver = new AADSWebKeywords();
	Selenium selenium = new Selenium();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();
	final static Logger logger = LogManager.getLogger("AADS_Pictures_002");


	@Before
	public void beforetestAADS_Pictures_02() throws Exception {
		logger.info("beforetest AADS_Pictures_002 starting...\n");
		webDriver = driverMgnt.createChromeDriver();
		webDriver2 = driverMgnt.createChromeDriver();
		logger.info("beforetest AADS_Pictures_002 completed...\n");
	}
	
	@Test 
	public void testAADS_Pictures_02 () throws Exception {
		logger.info("AADS_Pictures_02 - starting...\n");
		
			//Login to AADS server
			webDriver.get("https://aads250136.aam1.com:8445/admin");
			AADSWebDriver.loginAADSMainPage(webDriver, "acaladmin01", "tma_12Tma");
			
			//navigate to Pictures
			AADSWebDriver.navigateToFeaturesPage(webDriver, "Pictures");
			
			//Allow user upload picture AADS server
			AADSWebDriver.selectAllowUserUploadPictureCheckbox(webDriver, true);
			
			//verify Allow user upload picture AADS server
			boolean n  = AADSWebDriver.verifyAllowUserUploadPictureCheckbox(webDriver, "Settings were published successfully");
			assertTrue(n);
			
			//login to portal
			webDriver2.get("https://msg-esg.tma.com.vn/portal/tenants/default");
			AADSWebDriver.loginEsgPortalMainPage(webDriver2, "sip_71441", "tma_12Tma");
			
			//verify Change pictures user portal
			boolean s = AADSWebDriver.verifyPortalSettingsPageChangeImg(webDriver2);	
			assertTrue(s);
	
			logger.info("AADS_Pictures_02 - completed...\n");
	}
	
	@After
	public void tearDown() throws Exception {
		logger.info("tearDown starting...\n");
		webDriver2.navigate().to("https://msg-esg.tma.com.vn/portal/tenants/default");
		AADSWebDriver.logoffEsgPortalMainPage(webDriver2);
		AADSWebDriver.logoutAADSMainPage(webDriver);
		webDriver.close();
		webDriver2.close();
		logger.info("tearDown completed...\n");
	}
}
