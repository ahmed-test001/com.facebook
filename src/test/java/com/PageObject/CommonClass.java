package com.PageObject;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.Utilities.ReadConfig;

public class CommonClass {

	ReadConfig readconfig = new ReadConfig();

	public static File file;
	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	public static JavascriptExecutor js;

	@BeforeMethod
	@Parameters("browser")
	public void setup(String browser) {
		logger = Logger.getLogger("Facebook");
		PropertyConfigurator.configure("log4j.properties");

		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			driver = new ChromeDriver();
			logger.info("Initializing ChromeDriver..");
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readconfig.getFireFoxPath());
			driver = new FirefoxDriver();
			logger.info("Initializing FireFoxDriver..");
		}
		js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);

	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}

	public void AddPhotos() throws Exception {

		Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection sc = new StringSelection(System.getProperty("user.dir") + "\\Image\\jordan.jpg");
		c.setContents(sc, null);
		Robot rb = new Robot();
		Thread.sleep(3000);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(3000);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
	}

	public static void WindowHandle() {
		String parent = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		System.out.println("window available :: " + s1.size());
		Iterator<String> I1 = s1.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
				//System.out.println(driver.switchTo().window(child_window).getTitle());
			}
		}
	}

	public static void collectCookies(String FileName) {
		file = new File(FileName);
		try {
			file.delete();
			file.createNewFile();
			FileWriter fileWr = new FileWriter(file);
			BufferedWriter Bwritecookie = new BufferedWriter(fileWr); // Getting the cookie information
			for (Cookie ck : driver.manage().getCookies()) {
				System.out.println(ck.toString());
				Bwritecookie.write((ck.getName() + ";" + ck.getValue() + ";" + ck.getDomain() + ";" + ck.getPath() + ";"
						+ ck.getExpiry() + ";" + ck.isSecure()));
				Bwritecookie.newLine();
			}
			Bwritecookie.close();
			fileWr.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings({ "resource", "deprecation" })
	public static void injectCookies(String FileName) {
		file = new File(FileName);
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader Buffreader = new BufferedReader(fileReader);
			String strline;
			while ((strline = Buffreader.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(strline, ";");
				while (token.hasMoreTokens()) {
					String name = token.nextToken();
					String value = token.nextToken();
					String domain = token.nextToken();
					String path = token.nextToken();
					Date expiry = null;
					String val;
					if (!(val = token.nextToken()).equals("null")) {
						expiry = new Date(val);
					}
					Boolean isSecure = new Boolean(token.nextToken()).booleanValue();
					Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);
					driver.manage().addCookie(ck); // This will add the stored cookie to your current session
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
