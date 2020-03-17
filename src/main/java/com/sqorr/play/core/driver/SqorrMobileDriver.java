package com.sqorr.play.core.driver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sqorr.play.core.constants.TestConstants;
import com.sqorr.play.core.utils.Config;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * SqorrMobileDriver extends implements WebDriver and uses RemoteWebdriver for
 * implementation, customized reusable functions are added along with regular
 * functions
 */

public class SqorrMobileDriver implements SqorrDriver, WebDriver {

	private static final Logger logger = LogManager.getLogger(SqorrMobileDriver.class.getName());
	private static final long DEFAULT_TIMEOUT = 0;
	static DesiredCapabilities capabilities = new DesiredCapabilities();

	public static AndroidDriver<MobileElement> driver;
	public Config config;
	private long elementWaitTime;

	private static int MIN_DEFAULT_TIMEOUT = 5;
	private static String platformUsed;

	public SqorrMobileDriver(Config config) {
		this.config = config;
	}

	public SqorrMobileDriver() {

	}

	/**
	 * @throws MalformedURLException Prepares the environment that tests to be run
	 *                               on
	 */
	public void loadApplication() throws MalformedURLException {
		capabilities.setCapability("platformName", config.getProperty("platformName"));
		capabilities.setCapability("platformVersion", config.getProperty("platformVersion"));
		capabilities.setCapability("deviceName", config.getProperty("deviceName"));
		capabilities.setCapability("udid", config.getProperty("udid"));
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator1");
		capabilities.setCapability("newCommandTimeout", 20000);
		// capabilities.setCapability("autoWebview",true);

		capabilities.setCapability("appPackage", "com.sport.playsqorr");
		capabilities.setCapability("appActivity", "com.sport.playsqorr.views.OnBoarding");

		// capabilities.setCapability("autoDismissAlerts", true);

		URL remoteUrl = new URL("http://" + config.getProperty("host") + "/wd/hub");
		logger.debug("Remote URL is " + remoteUrl);

		driver = new AndroidDriver<MobileElement>(remoteUrl, capabilities);

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}

	public WebElement findElementBYID(String locator) {
		return driver.findElementById(locator);
	}

	public void selectValueByText(WebElement ele, String value) {
		Select dropdown = new Select(ele);
		dropdown.selectByVisibleText(value);
	}

	/*
	 * public void type(WebElement locator, String input) {
	 * findElement(locator).sendKeys(input); locator.sendKeys(input); }
	 */

	public String getCurrentWindowHandle() {
		return driver.getWindowHandle();
	}

	public void openLinkInNewWindow(WebElement element) {
		Actions newTab = new Actions(driver);
		newTab.keyDown(Keys.SHIFT).click(element).keyUp(Keys.SHIFT).build().perform();
	}

	public String getchildWindow(String mainHandle) {
		Set<String> allHandles = driver.getWindowHandles();
		Iterator<String> iter = allHandles.iterator();
		while (iter.hasNext()) {
			String setElement = iter.next();
			if (!setElement.equals(mainHandle)) {
				return setElement;
			}
		}
		return null;
	}

	public boolean switchToWindow(String originalWindowHandle) {
		driver.switchTo().window(originalWindowHandle);
		return true;
	}

	public void highlightObject(WebElement Element) {
		try {
			if (driver != null) {
				JavascriptExecutor javascript = (JavascriptExecutor) driver;
				javascript.executeScript("arguments[0].setAttribute('style', arguments[1]);", Element,
						"border: 3px solid yellow;");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getURL() {
		return TestConstants.KMS_BASEURL;
	}

	public boolean isElementPresent(By locator) {
		boolean IsPresent = false;
		turnOffImplicitWaits();
		if (driver.findElements(locator).size() > 0) {
			IsPresent = true;
		} else {
			IsPresent = false;
		}
		turnOnImplicitWaits();
		return IsPresent;
	}

	public void waitForElementPresent(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT, 100);
			logger.debug("waiting for locator " + locator);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			logger.debug("Found locator " + locator);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	/*
	 * public void waitForElementPresent(WebElement Element) {
	 * 
	 * long startTime = System.currentTimeMillis(); long totalWaitTime = 100L; try {
	 * startTime = System.currentTimeMillis(); new WebDriverWait(driver,
	 * this.elementWaitTime).ignoring(NoSuchElementException.class)
	 * .ignoring(TimeoutException.class).until(ExpectedConditions.visibilityOf(
	 * Element));
	 * 
	 * totalWaitTime = startTime - System.currentTimeMillis();
	 * System.out.println("Verified element is  present on page : Waited  " +
	 * Long.toString(totalWaitTime) + " mili seconds to be present "); } catch
	 * (TimeoutException e) { System.out.
	 * println("Failed: Exceeded timeout threshold! while waiting for element Timeout Threshold: "
	 * + Integer.toString(this.elementWaitTime)); } }
	 */

	public void verifyTextOnElement(WebElement Element, String verificationText) {

		String retrievedText = "";
		try {

			retrievedText = Element.getText().trim();
			if ((retrievedText.equalsIgnoreCase(verificationText.trim()) & (Element.isDisplayed()))) {

				System.out.println("PASS: verifyTextOnElement: The text verified successfully. Element-> " + " text: "
						+ verificationText);

			}
		} catch (Throwable t) {
			t.printStackTrace();
			System.out.println("FAIL: verifyTextOnElement: Exception while locating the element. Element-> "
					+ ", text: " + verificationText);
			Assert.fail();
		}
	}

	/**
	 * @param locator
	 * 
	 */

	public void moveToELement(By locator) {
		Actions build = new Actions(driver);
		build.moveToElement(driver.findElement(locator)).build().perform();
	}

	public void get(String Url) {
		driver.get(Url);
	}

	/*
	 * public void click(WebElement locator) { // highlightObject(locator); //
	 * waitForElementToBeVisible(locator); // highlightObject(locator);
	 * locator.click(); }
	 */

	public void type(By locator, Keys input) {
		findElement(locator).sendKeys(input);
	}

	public void quit() {
		driver.quit();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public List<WebElement> findElements(By by) { //
		/*
		 * if (getPlatformUsed().equalsIgnoreCase(TestConstants.IOS)) //
		 * movetToElementJavascript(by); return driver.findElements(by);
		 */
		return null;
	}

	public WebElement findElement(By by) { //
		if (getPlatformUsed().equalsIgnoreCase(TestConstants.IOS)) //
			movetToElementJavascript(by);
		return driver.findElement(by);
	}

	public WebElement findElementWithoutMove(By by) { //
		movetToElementJavascript(by);
		return driver.findElement(by);
	}

	public String getPageSource() {
		return driver.getPageSource();
	}

	public void close() {
		driver.close();
	}

	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	public TargetLocator switchTo() {
		return driver.switchTo();
	}

	public Navigation navigate() {
		return driver.navigate();

	}

	public Options manage() {
		return driver.manage();
	}

	public Select getSelect(By by) {
		return new Select(findElement(by));
	}

	public Select getSelect(WebElement element) {
		return new Select(element);
	}

	public void scrollToBottomJS() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript(
				"window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
	}

	public void movetToElementJavascript(By locator) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + findElement(locator).getLocation().y + ")");
	}

	public void movetToElementJavascript(WebElement webElement) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
	}

	// Need to be customized 
	public void fnDragAndDrop(By by) throws InterruptedException
	{
		WebElement Slider = driver.findElement(by);
		Actions moveSlider = new Actions(driver);
		moveSlider.clickAndHold(Slider);
		moveSlider.dragAndDropBy(Slider, 3, 0).build().perform();
	}

	public Actions action() {
		return new Actions(driver);
	}

	public TouchAction touchAction() {
		return new TouchAction(driver);
	}

	public void pauseExecutionFor(long lTimeInMilliSeconds) {
		try {
			Thread.sleep(lTimeInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean IsElementVisible(WebElement element) {
		return element.isDisplayed() ? true : false;
	}

	public void selectFromList(List<WebElement> lstElementList, String sValueToBeSelected) throws Exception {
		logger.debug("START OF FUNCTION->selectFromList");
		boolean flag = false;
		logger.debug("Total element found --> " + lstElementList.size());
		logger.debug("Value to be selected " + sValueToBeSelected + " from list" + lstElementList);
		for (WebElement e : lstElementList) {
			logger.debug(">>>>>>>>>>>>>" + e.getText() + "<<<<<<<<<<<<<<<");
			if (e.getText().equalsIgnoreCase(sValueToBeSelected)) {
				logger.debug("Value matched in list as->" + e.getText());
				clickByJS(e);

				flag = true;
				break;
			}
		}
		if (flag == false) {
			throw new Exception("No match found in the list for value->" + sValueToBeSelected);
		}
		logger.debug("END OF FUNCTION->selectFromList");
	}

	public boolean reTryClick(By by) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 3) {
			try {
				driver.findElement(by).click();
				result = true;
				break;
			} catch (Exception e) {
			}
			attempts++;
		}
		return result;
	}

	public boolean deleteAllCookies(WebDriver driver) {
		boolean IsDeleted = false;
		try {
			driver.manage().deleteAllCookies();
			IsDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return IsDeleted;
	}

	public void turnOffImplicitWaits() {
		driver.manage().timeouts().implicitlyWait(MIN_DEFAULT_TIMEOUT, TimeUnit.SECONDS);
	}

	public void turnOnImplicitWaits() {
		driver.manage().timeouts().implicitlyWait(MIN_DEFAULT_TIMEOUT, TimeUnit.SECONDS);
	}

	public void clickByJS(By by) {
		moveToELement(by);
		WebElement element = driver.findElement(by);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void clickByJS(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public String takeSnapShotAndRetPath(WebDriver driver) throws Exception {
		logger.debug("INTO METHOD->Fn_TakeSnapShotAndRetPath");
		String FullSnapShotFilePath = "";

		try {
			logger.debug("Take Screen shot started");
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String sFilename = null;
			sFilename = "Screenshot-" + getDateTime() + ".png";
			FullSnapShotFilePath = System.getProperty("user.dir") + "\\Output\\ScreenShots\\" + sFilename;
			FileUtils.copyFile(scrFile, new File(FullSnapShotFilePath));
		} catch (Exception e) {

		}

		return FullSnapShotFilePath;
	}
	
	/*public void swipe(int startx, int starty, int endx, int endy, int duration) {
		driver.swipe(startx, starty, endx, endy, duration);
	}*/

	/**
	 * Returns current Date Time
	 * 
	 * @return
	 */
	public static String getDateTime() {
		String sDateTime = "";
		try {
			SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
			Date now = new Date();
			String strDate = sdfDate.format(now);
			String strTime = sdfTime.format(now);
			strTime = strTime.replace(":", "-");
			sDateTime = "D" + strDate + "_T" + strTime;
		} catch (Exception e) {
			System.err.println(e);
		}
		return sDateTime;
	}

	public void verifyClick(By clickBy, By afterClickBy) {
		int iAttempts = 8;
		int iCount = 0;
		do {
			clickByJS(clickBy);
			pauseExecutionFor(2000);
			iCount++;
		} while (iCount < iAttempts && (!isElementPresent(afterClickBy)));
	}

	public boolean quickCheckForElementPresent(By locator) {
		turnOffImplicitWaits();
		boolean IsElementPresent = false;
		if (driver.findElements(locator).size() > 0) {
			IsElementPresent = true;
		}
		turnOnImplicitWaits();
		return IsElementPresent;
	}

	/*
	 * public List<WebElement> quickFindElements(By by) { turnOffImplicitWaits();
	 * List<WebElement> list = driver.findElements(by); turnOnImplicitWaits();
	 * return list; }
	 */

	public boolean IsLocatorVisible(By by) {
		boolean IsVisible = false;
		try {
			turnOffImplicitWaits();
			IsVisible = findElement(by).isDisplayed();
		} catch (Exception e) {

		} finally {
			turnOnImplicitWaits();
		}

		return IsVisible;
	}

	public void scrollToTopJS() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
	}

	public void takeScreenShot(String fileName) throws Exception {
		String folder = "logs/screen/";
		WebDriver driver1 = new Augmenter().augment(driver);
		File file = ((TakesScreenshot) driver1).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(folder + fileName + ".jpg"));
	}

	public String getPlatformUsed() {
		return platformUsed;
	}

	public void fnDragAndDrop(By by, int x, int y) throws InterruptedException {
		WebElement Slider = driver.findElement(by);
		Actions moveSlider = new Actions(driver);
		moveSlider.clickAndHold(Slider);
		moveSlider.dragAndDropBy(Slider, x, y).build().perform();
	}

	public String getCSSValue(By locator, String propertyName) {
		return driver.findElement(locator).getCssValue(propertyName);
	}

	public void killChrome() throws Exception {
		Runtime.getRuntime().exec("cmd /c adb shell am force-stop com.android.chrome");
	}

	public void openChrome() throws Exception {
		Runtime.getRuntime()
				.exec("cmd /c adb adb shell am start -n com.android.chrome/com.google.android.apps.chrome.Main");
	}

	/*public void authenticateUsing(String username, String password) {
		driver.switchTo().alert().authenticateUsing(new UserAndPassword(username, password));
	}*/

	public boolean IsLocatorEnable(By by) {
		return driver.findElement(by).isEnabled();
	}

	public int getIntegerValueFromString(String value) {
		int returnValue = Integer.parseInt(value.replaceAll("[^0-9]", ""));
		return returnValue;
	}

	public String getTextFromElement(WebElement element) {
		String text = element.getText().trim();
		return text;
	}

	public void selectByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public String getSelectedValue(WebElement element) {
		Select select = new Select(element);
		String text = getTextFromElement(select.getFirstSelectedOption());
		return text;
	}

	public void moveToElementAndClick(By by) {
		WebElement element = findElement(by);
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}

	public String getAttribute(WebElement element, String name) {
		return element.getAttribute(name);
	}

	public int getCurrentYear(){ 
		Calendar now = Calendar.getInstance(); 
		//Gets the current date and time 
		int year = now.get(Calendar.YEAR); 
		return year; 
	}

	public String getTextColorHexFormat(By by) {
		String color = driver.findElement(by).getCssValue("color");
		logger.info("color: " + color);
		String[] numbers = color.replace("rgba(", "").replace(")", "").split(",");
		int r = Integer.parseInt(numbers[0].trim());
		int g = Integer.parseInt(numbers[1].trim());
		int b = Integer.parseInt(numbers[2].trim());
		System.out.println("r: " + r + "g: " + g + "b: " + b);
		String hex = "#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
		System.out.println(hex);
		return hex;
	}

	public String getDiscountApplied(String total, String discount) {
		DecimalFormat f = new DecimalFormat("##.00");
		double merch;
		if (total.contains(TestConstants.CANADIAN_PRICE))
			merch = Double.parseDouble(total.replace(TestConstants.CANADIAN_PRICE, ""));
		else
			merch = Double.parseDouble(total.replace(TestConstants.DOLLAR, ""));
		double off = Double.parseDouble(discount);
		return f.format((merch * off) / 100);
	}

	public String getMonthForInt(int num) {
		String month = "wrong";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (num >= 0 && num <= 11) {
			month = months[num];
		}
		return month;
	}

	public boolean isStringAExistingMonth(String str) {
		String[] months = new DateFormatSymbols().getMonths();
		for (int i = 0; i < months.length; i++) {
			if (str != null && str.equalsIgnoreCase(months[i])) {
				return true;
			}
		}
		return false;
	}

	public int getIntForMonth(String month) throws ParseException {
		if (isStringAExistingMonth(month)) {
			Date date = new SimpleDateFormat("MMMM").parse(month);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int number = cal.get(Calendar.MONTH);
			return number + 1;
		}
		return 0;
	}

	public void clickAtPointsJS(int x, int y) {
		JavascriptExecutor().executeScript("document.elementFromPoint(" + x + ", " + y + ").click()");
	}

	public JavascriptExecutor JavascriptExecutor() {
		return ((JavascriptExecutor) driver);
	}

	public String getTagName(By locator) {
		return findElement(locator).getTagName();
	}

	public boolean isElementDisplayed(By locator) {
		try {
			return findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementVisible(WebElement element) {
		try {
			return element.isDisplayed() ? true : false;
		} catch (Exception e) {
			return false;
		}
	}

}
