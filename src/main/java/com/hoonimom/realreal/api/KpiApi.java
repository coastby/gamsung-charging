package com.hoonimom.realreal.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KpiApi {
	@Value("${openapi.kpikey}")
	private String key;

	public String getPerform() throws IOException {
		System.out.println(key);
		StringBuilder urlBuilder = new StringBuilder("http://kopis.or.kr/openApi/restful/pblprfr"); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("service","UTF-8") + "=" + URLEncoder.encode(key, "UTF-8")); /*Service Key*/
		urlBuilder.append("&" + URLEncoder.encode("stdate","UTF-8") + "=" + URLEncoder.encode("20160101", "UTF-8")); /*페이지번호*/
		urlBuilder.append("&" + URLEncoder.encode("eddate","UTF-8") + "=" + URLEncoder.encode("20160630", "UTF-8")); /*한 페이지 결과 수*/
		urlBuilder.append("&" + URLEncoder.encode("rows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*지역코드*/
		urlBuilder.append("&" + URLEncoder.encode("cpage","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*계약월*/
		URL url = new URL(urlBuilder.toString());
		System.out.println(url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb);
		return sb.toString();
	}

}
