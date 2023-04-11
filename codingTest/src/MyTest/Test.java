package MyTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
	public static String Path = "C:/Users/sy/Desktop/Coding";
	public static String File = java.time.LocalDate.now() + " 장바구니.xlsx";

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "C:/Users/sy/Desktop/Coding/library/chromedriver_win32/chromedriver.exe"); 
        // 드라이버 위치 본인이 저장한 위치에 알맞게 넣는다

		WebDriver driver = new ChromeDriver();

		driver.get("Site");

		driver.findElement(By.id("loginId")).sendKeys("id");
		driver.findElement(By.id("loginPwd")).sendKeys("password");
		driver.findElement(By.className("member_login_order_btn")).click();
		Thread.sleep(5000);
		driver.findElement(By.partialLinkText("장바구니")).click();

		String html = driver.getPageSource();
		Document doc = Jsoup.parse(html);

		String Name = doc.select("#frmCart > div > div.order_table_type > table > tbody > tr:nth-child(" + 1 + ") > td.td_left > div > div > em > a").text();
		int j = 2;

		XSSFWorkbook work = new XSSFWorkbook();
		XSSFSheet sheet = work.createSheet("cart");

		Map<String, Object[]> data = new TreeMap<>();
		data.put("1", new Object[] {"TABLE", "NAME", "NUMBER", "PRICE"});


		for(int i = 1; i < 3; i++) {
		Name = doc.select("#frmCart > div > div.order_table_type > table > tbody > tr:nth-child(" + i + ") > td.td_left > div > div > em > a").text();
		String Num = doc.select("#frmCart > div > div.order_table_type > table > tbody > tr:nth-child(" + i + ") > td.td_order_amount > div > strong").text().replaceAll("[^0-9]", "");
		String Price = doc.select("#frmCart > div > div.order_table_type > table > tbody > tr:nth-child(" + i + ") > td:nth-child(4) > strong").text().replaceAll("[^0-9]", "");;
		data.put(String.valueOf(j), new Object[] {String.valueOf(i), Name, Num, Price});

        j++;
		}

        // data에서 keySet를 가져온다. 이 Set 값들을 조회하면서 데이터들을 sheet에 입력한다.
        Set<String> keyset = data.keySet();
        int rownum = 0;

        // 알아야할 점, TreeMap을 통해 생성된 keySet는 for를 조회시, 키값이 오름차순으로 조회된다.
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String)obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer)obj);
                }
            }
        }

        try {
            FileOutputStream out = new FileOutputStream(new File(Path, File));
            work.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}