package com.hoonimom.realreal.api;

import com.hoonimom.realreal.entity.Show;
import com.hoonimom.realreal.parser.XmlParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiService {
	@Value("${openapi.kpikey}")
	private String kpikey;
	@Value("${openapi.datakey}")
	private String datakey;

	final private XmlParser xmlParser;

	public List<Show> getPerform() throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://kopis.or.kr/openApi/restful/pblprfr"); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("service","UTF-8") + "=" + URLEncoder.encode(kpikey, "UTF-8")); /*Service Key*/
		urlBuilder.append("&" + URLEncoder.encode("stdate","UTF-8") + "=" + URLEncoder.encode("20230501", "UTF-8")); /*페이지번호*/
		urlBuilder.append("&" + URLEncoder.encode("eddate","UTF-8") + "=" + URLEncoder.encode("20230530", "UTF-8")); /*한 페이지 결과 수*/
		urlBuilder.append("&" + URLEncoder.encode("rows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*지역코드*/
		urlBuilder.append("&" + URLEncoder.encode("cpage","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*계약월*/
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		List<Show> showList = null;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//			rd = new BufferedReader(new InputStreamReader());
			showList = xmlParser.getShow(conn.getInputStream());
		} else {
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
		}
		conn.disconnect();
		return showList;
	}
	public String getPerformAndExhibition() throws IOException {
		StringBuilder urlBuilder = new StringBuilder(
				"http://www.culture.go.kr/openapi/rest/publicperformancedisplays/period"); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + URLEncoder.encode(datakey,
				"UTF-8")); /*Service Key*/
		urlBuilder.append("&" + URLEncoder.encode("keyword", "UTF-8") + "=" + URLEncoder.encode("",
				"UTF-8")); /**/
		urlBuilder.append(
				"&" + URLEncoder.encode("sortStdr", "UTF-8") + "=" + URLEncoder.encode("1",
						"UTF-8")); /*1:등록일, 2:공연명, 3:지역*/
		urlBuilder.append(
				"&" + URLEncoder.encode("ComMsgHeader", "UTF-8") + "=" + URLEncoder.encode("",
						"UTF-8")); /**/
		urlBuilder.append("&" + URLEncoder.encode("RequestTime", "UTF-8") + "=" + URLEncoder.encode(
				"20100810:23003422", "UTF-8")); /*Optional 필드*/
		urlBuilder.append(
				"&" + URLEncoder.encode("CallBackURI", "UTF-8") + "=" + URLEncoder.encode("",
						"UTF-8")); /*Optional 필드*/
		urlBuilder.append("&" + URLEncoder.encode("MsgBody", "UTF-8") + "=" + URLEncoder.encode("",
				"UTF-8")); /**/
		urlBuilder.append(
				"&" + URLEncoder.encode("from", "UTF-8") + "=" + URLEncoder.encode("20100101",
						"UTF-8")); /**/
		urlBuilder.append(
				"&" + URLEncoder.encode("to", "UTF-8") + "=" + URLEncoder.encode("20101201",
						"UTF-8")); /**/
		urlBuilder.append("&" + URLEncoder.encode("cPage", "UTF-8") + "=" + URLEncoder.encode("1",
				"UTF-8")); /**/
		urlBuilder.append("&" + URLEncoder.encode("rows", "UTF-8") + "=" + URLEncoder.encode("10",
				"UTF-8")); /*3~100*/
		urlBuilder.append("&" + URLEncoder.encode("place", "UTF-8") + "=" + URLEncoder.encode("1",
				"UTF-8")); /**/
		urlBuilder.append(
				"&" + URLEncoder.encode("gpsxfrom", "UTF-8") + "=" + URLEncoder.encode("129.101",
						"UTF-8")); /*경도 범위검색 중 하한*/
		urlBuilder.append(
				"&" + URLEncoder.encode("gpsyfrom", "UTF-8") + "=" + URLEncoder.encode("35.142",
						"UTF-8")); /*위도 범위검색 중 하한*/
		urlBuilder.append(
				"&" + URLEncoder.encode("gpsxto", "UTF-8") + "=" + URLEncoder.encode("129.101",
						"UTF-8")); /*경도 범위검색 중 상한*/
		urlBuilder.append(
				"&" + URLEncoder.encode("gpsyto", "UTF-8") + "=" + URLEncoder.encode("35.142",
						"UTF-8")); /*위도 범위검색 중 상한*/
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
	public String getRealEstate() throws IOException {

		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" +URLEncoder.encode(datakey,"UTF-8")); /*Service Key*/
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1",
				"UTF-8")); /*페이지번호*/
		urlBuilder.append(
				"&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10",
						"UTF-8")); /*한 페이지 결과 수*/
		urlBuilder.append(
				"&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode("11110",
						"UTF-8")); /*지역코드*/
		urlBuilder.append(
				"&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode("202212",
						"UTF-8")); /*계약월*/
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
