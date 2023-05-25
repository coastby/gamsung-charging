package com.hoonimom.realreal.entity;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Show {
	private String showId;	//공연ID	PF132236
	private	String showName;//공연명	우리연애할까
	private LocalDate startDate; //공연시작일	2016.05.12
	private	LocalDate endDate;//공연종료일	2016.07.31
	private	String hallName; //공연시설(공연장명)	피가로아트홀(구 훈아트홀)
//	private	String hallId; //공연시설ID(공연장명)	피가로아트홀(구 훈아트홀)
//	private String cast; //출연진
//	private String staff; //제작진
//	private String runtime; //런타임
//	private String ticketPrice; //티켓 가격
	private	String posterUrl;//포스터이미지경로	http://www.kopis.or.kr/upload/pfmPoster/PF_PF132236_160704_142630.gif
//	private	String genre;//장르
	private	String status;//공연상태
	private	boolean openRun;//오픈런

}
