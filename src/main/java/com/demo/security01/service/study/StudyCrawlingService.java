package com.demo.security01.service.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import com.demo.security01.model.StudySite;
import com.demo.security01.model.dto.crawling.StudyCrawlingResponeDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudyCrawlingService {

	// 크롤링 연습 예제 - soup, 인프런 가능
//	 public List<StudyCrawlingResponeDto> getStudyHtml() {
	public Map<Enum<StudySite>, List<StudyCrawlingResponeDto>> getScrapInflearn() {
		log.info("============ StudyCrawlingService ==================");
		log.info("\t\t getHtmlCalled.......");

		List<StudyCrawlingResponeDto> results = new ArrayList<>();

		Map<Enum<StudySite>, List<StudyCrawlingResponeDto>> result2 = new HashMap<Enum<StudySite>, List<StudyCrawlingResponeDto>>();

		try {
			String url = "https://www.inflearn.com/community/studies";
			Document doc = Jsoup.connect(url).get();
			Elements el2 = doc.select(".e-click-post");

//		 Elements el = doc.select(".title__text");

			for(int i=0; i<10; i++) {
				Element element = el2.get(i);
				String title = element.select(".title__text").text();
				String content = element.select(".question__body").text();
				String link = element.attr("abs:href");
				results.add(new StudyCrawlingResponeDto(title, content,link));
			}
			
//			for (Element element : el2) {
//				String title = element.select(".title__text").text();
//				String content = element.select(".question__body").text();
//				String link = element.attr("abs:href");
//				results.add(new StudyCrawlingResponeDto(title, content,link));
//				
//				log.info("title = " + title);
//				log.info("content = " + content);
//				log.info("link = " + link);
//			}

//		 String title = el.text();
//		 String link = el2.attr("abs:href");

//		 log.info("title = " + title);
//		 log.info("link = " + link);
			result2.put(StudySite.INFLEARN, results);

//		 return results;
			return result2;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	// soup 크롤링
	public Map<Enum<StudySite>, List<StudyCrawlingResponeDto>> getScrapSoup() {
		log.info("============ StudyCrawlingService ==================");
		log.info("\t\t getHtmlCalled.......");

		List<StudyCrawlingResponeDto> responseDto = new ArrayList<>();

		Map<Enum<StudySite>, List<StudyCrawlingResponeDto>> result2 = new HashMap<Enum<StudySite>, List<StudyCrawlingResponeDto>>();

		try {
			String url = "https://soup.pw/projects";
			Document doc = Jsoup.connect(url).get();
			Elements el2 = doc.select(".css-1flq8k1");
			
			for(int i=0; i<10; i++) {
				Element element = el2.get(i);
				String title = element.select(".css-1089e3a").text(); 
				String content = element.select(".css-2moiiq").text();
				String link = element.select("div.css-1flq8k1 > a").attr("abs:href");
				responseDto.add(new StudyCrawlingResponeDto(title, content,link));
			}

			
//			 for (Element element : el2) { 
//				 String title = element.select(".css-1089e3a").text(); 
//				 String content = element.select(".css-2moiiq").text();
//				 String link = element.select("div.css-1flq8k1 > a").attr("abs:href");
//				 responseDto.add(new StudyCrawlingResponeDto(title, content,link));
//			 }
//			 

//			log.info("el2 = " + el2);
			result2.put(StudySite.SOUP, responseDto);

//			 return results;
			return result2;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	// letspl -> 빼야 됨
	public Map<Enum<StudySite>, List<StudyCrawlingResponeDto>> getScrapLetspl() {
		log.info("============ StudyCrawlingService ==================");
		log.info("\t\t getHtmlCalled.......");

		List<StudyCrawlingResponeDto> results = new ArrayList<>();

		Map<Enum<StudySite>, List<StudyCrawlingResponeDto>> result2 = new HashMap<Enum<StudySite>, List<StudyCrawlingResponeDto>>();

		try {
			String url = "https://letspl.me/project";
			Document doc = Jsoup.connect(url).get();

			log.info("doc = " + doc.toString());
//			 Elements el2 = doc.select(".tit");
			Elements el2 = doc.select("a[href]");
			Elements resultDivs = doc.select("div.projectTopInfo");
			Elements resultAs = resultDivs.select("a[href]");

			Elements links = doc.select("div.projectTopInfo > a");

			for (Element link : links) {
				String href = link.attr("href");
				log.info("href = " + href);
			}

//			log.info("el2 = " + el2);
//			 System.out.println("resultDivs = " + resultDivs);
//			log.info("resultDivs = " + resultAs);
//			 Elements el = doc.select(".title__text");

//			 for(Element element : el2) {
//				 String title = element.select(".title__text").text();
//				 String link = el2.attr("abs:href");
//				 results.add(new StudyCrawlingResponeDto(title, link));
//			 }

//			 String title = el.text();
//			 String link = el2.attr("abs:href");

//			 log.info("title = " + title);
//			 log.info("link = " + link);
			result2.put(StudySite.SOUP, results);

//			 return results;
			return result2;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	// selenium 방식
	public Map<Enum<StudySite>, List<StudyCrawlingResponeDto>> getScrapHola11() {
		log.info("============ StudyCrawlingService ==================");
		log.info("\t\t getScrapHola11 called .......");

		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\ideaproject\\ChromeDriver\\chromedriver.exe");
		List<StudyCrawlingResponeDto> results = new ArrayList<>();

		Map<Enum<StudySite>, List<StudyCrawlingResponeDto>> result2 = new HashMap<Enum<StudySite>, List<StudyCrawlingResponeDto>>();

		// 크롬 옵션
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments(
				"--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36");
		options.addArguments("--disable-gpu");
		options.addArguments("--disable-dev-shm-usage");

		WebDriver dri = new ChromeDriver(options);
		try {
			String url = "https://letspl.me/project";
			dri.get(url);
			log.info("dri.getPageSource(); = " + dri.getPageSource());
//			 Thread.sleep(5000);
			List<WebElement> elements = dri.findElements(By.cssSelector(".tit"));
			log.info(elements.toString());

			for (WebElement el : elements) {
				String title = el.getText();
				String link = el.getAttribute("abs:href");
//				results.add(new StudyCrawlingResponeDto(title,link));
			}
			result2.put(StudySite.HOLA, results);

//			 return results;
			return result2;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}
}
