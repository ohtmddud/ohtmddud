package MyTest;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MySelenium {

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "C:/Users/sy/Desktop/Coding/library/chromedriver_win32/chromedriver.exe"); 
        // 드라이버 위치 본인이 저장한 위치에 알맞게 넣는다
		
		WebDriver driver = new ChromeDriver();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.get("https://www.hd-inter.co.kr/member/login.php");
		
		driver.findElement(By.id("loginId")).sendKeys("keesa7199");
		driver.findElement(By.id("loginPwd")).sendKeys("aa!1037916");
		
		driver.findElement(By.className("member_login_order_btn")).click();
		
		Thread.sleep(5000);
		driver.findElement(By.partialLinkText("장바구니")).click();
		String html = driver.getPageSource();
		Document doc = Jsoup.parse(html);
		
		for(int i=1; i<3; i++) {
		String Name = doc.select("#frmCart > div > div.order_table_type > table > tbody > tr:nth-child(" + i + ") > td.td_left > div > div > em > a").text();
		String Num = doc.select("#frmCart > div > div.order_table_type > table > tbody > tr:nth-child(" + i + ") > td.td_order_amount > div > strong").text().replaceAll("[^0-9]", "");
		Num.replaceAll("[^0-9]", "");
		String Price = doc.select("#frmCart > div > div.order_table_type > table > tbody > tr:nth-child(" + i + ") > td:nth-child(4) > strong").text().replaceAll("[^0-9]", "");;
		System.out.println(Name);
		System.out.println(Num);
		System.out.println(Price);
		}
		
		
		
		
		
		

	}

}
