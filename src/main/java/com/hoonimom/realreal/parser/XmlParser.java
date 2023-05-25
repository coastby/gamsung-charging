package com.hoonimom.realreal.parser;

import com.hoonimom.realreal.entity.Show;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@Component
public class XmlParser {
	final private DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	public List<Show> getShow(InputStream in) {
		List<Show> showList = new ArrayList<>();
		try {
			// API의 XML 문서 Document 인스턴스에 저장
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = (Document) documentBuilder.parse(new InputSource(in));

			//Dom Tree를 XML 문서의 구조대로 만들기
			document.getDocumentElement().normalize();
			//데이터를 객체로 파싱하여 리스트에 담기
			NodeList nList = document.getElementsByTagName("db");
			for (int i = 0; i < nList.getLength(); i++) {
				Node dbNode = nList.item(i);
				Show show = getShows((Element) dbNode);
				showList.add(show);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return showList;
	}

	public static Map<String, Object> getNodeList(NodeList nodeList) {
		Map<String, Object> dataMap = new HashMap<>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			String tagName = nodeList.item(i).getNodeName();
			if (!"#text".equals(tagName)) {
				if (nodeList.item(i).getChildNodes().getLength() > 1) {
					dataMap.put(tagName, getNodeList(nodeList.item(i).getChildNodes()));
				} else {
					dataMap.put(tagName, nodeList.item(i).getTextContent());
				}
			}
		}
		return dataMap;
	}

	private Show getShows(Element el) {
		return Show.builder()
				.showId(el.getElementsByTagName("mt20id").item(0).getTextContent())
				.showName(el.getElementsByTagName("prfnm").item(0).getTextContent())
				.posterUrl(el.getElementsByTagName("poster").item(0).getTextContent())
				.hallName(el.getElementsByTagName("fcltynm").item(0).getTextContent())
				.build();
	}

}
