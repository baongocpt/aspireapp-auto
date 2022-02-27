package aspireapp.base;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestUtilities extends BaseTest {

	// STATIC SLEEP 
	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getCurrentTime() {
	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	   LocalDateTime now = LocalDateTime.now();  
	   return dtf.format(now);
	}
}
