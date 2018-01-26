package desafio.geo.tech.page;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Helper {

	public void Helper() {

	}

	public String generateRandomString(int length) {
		String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVXZ1234567890";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
		}

		return sb.toString();
	}

	public String generateActualDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());

		return date;
	}

	public String generateYesterdayDate() {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date yesterday = calendar.getTime();
		return formatDate.format(yesterday).toString();
	}
	
	public String generatePrice() {
		Random r = new Random();
		Locale ptBr = new Locale("pt", "BR");
		String newPrice = NumberFormat.getCurrencyInstance(ptBr).format(r.nextInt(9999));

		return newPrice;
	}

}
