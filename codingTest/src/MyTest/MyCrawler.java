package MyTest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
public class MyCrawler {
	
	

	public static void main(String[] args) throws IOException {
		String loginUrl = "https://www.hd-inter.co.kr/member/login.php";
		Connection.Response loginPageResponse = Jsoup.connect(loginUrl).execute();

		// 로그인 페이지에서 얻은 쿠키
		Map<String, String> loginTryCookie = loginPageResponse.cookies();
		
		// 로그인 페이지에서 로그인에 함께 전송하는 토큰 얻어내기
		Document loginPageDocument = loginPageResponse.parse();
		
		String myId = "";
		String myPass = "";
		String returnUrl = loginPageDocument.select("#returnUrl").val();
		String secretKey = loginPageDocument.select("#secretKey").val();
		String encryptFl = loginPageDocument.select("#encryptFl").val();
		System.out.println(returnUrl);
		System.out.println(secretKey);
		System.out.println(encryptFl);
		
		Map<String, String> data = new HashMap<>();
		data.put("loginId", myId);
		data.put("loginPwd", myPass);
		// 로그인 페이지의 보안 이슈
		data.put("returnUrl", returnUrl);
		data.put("secretKey", secretKey);
		data.put("encryptFl", encryptFl); 
		
		
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36";
		
		Connection.Response response = Jsoup.connect(loginUrl)
				.userAgent(userAgent)
				.timeout(3000)
		        .header("Host", "www.hd-inter.co.kr")
                .header("Referer", "https://www.hd-inter.co.kr/member/login.php")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.cookies(loginTryCookie)
				.data(data)
				.method(Connection.Method.POST)
				.execute();
		
		Map<String, String> loginCookies = response.cookies();
		
		for(String key : loginCookies.keySet()) {
			String value = (String) loginCookies.get(key);
			System.out.println(key + " : " + value);
		}
		
		System.out.println(loginCookies);
		

//		String url = "https://www.hd-inter.co.kr/order/cart.php";
//		Document doc = Jsoup.connect(url).cookies(loginCookies).get();
//		System.out.println(doc);

	}

}
