package com.restaurant.foodItems;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.sql.Connection;
import java.sql.SQLException;

public class JsoupParser {

	public static void parse1(Connection con) {
		Document doc;
		try {
			doc = Jsoup
					.connect(
							"http://www.foodtimeline.org/foodmexican.html#fajitas")
					.timeout(10000).get();
			Element table = doc.getElementsByTag("table").first();
			Element tbody = table.getElementsByTag("tbody").first();
			Element tr = tbody.getElementsByTag("tr").first();
			Element td = tr.getElementsByTag("td").first();
			Element ue = td.getElementsByTag("ul").first();
			Elements newsHeadlines = ue.getElementsByTag("li");

			for (Element ele : newsHeadlines) {
				System.out.println(ele.text());
				//Store.store(ele.text(), "", con);
			}
			con.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void parse2(Connection con) {
		Document doc;
		String tableName="food_items_food_time_line";
		try {
			doc = Jsoup
					.connect("http://www.foodtimeline.org/foodfaqindex.html")
					.timeout(10000).get();
			// Element table = doc.getElementsByTag("table").first();
			// Element tbody = table.getElementsByTag("tbody").first();
			// Element tr = tbody.getElementsByTag("tr").first();
			// Element td = tr.getElementsByTag("td").first();
			// Element ue = td.getElementsByTag("ul").first();
			// Elements foodnames = ue.getElementsByTag("li");
			con.prepareStatement("DELETE FROM food_items").executeUpdate();
			Element body = doc.getElementsByTag("body").first();
			body.getElementsByTag("center").get(0).remove();
			body.getElementsByTag("center").get(1).remove();
			body.getElementsByTag("center").get(2).remove();
			body.getElementsByTag("center").get(0).remove();
			body.getElementsByTag("p").get(0).remove();
			body.getElementsByTag("p").get(1).remove();
			body.getElementsByTag("p").get(2).remove();
			body.getElementsByTag("hr").get(0).remove();
			body.getElementsByTag("a").get(0).remove();
			Elements foodNames = body.getElementsByTag("a");

			int x = 0;
			if (x < 50)
				for (Element ele : foodNames) {
					x++;
					System.out.println(ele.text());
					String temp = ele.getElementsByTag("b").text();
					if (!temp.equals(null) && temp != "" && temp.length() > 0)
						Store.store(temp, "",tableName, con);
				}
			con.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void parse3(Connection con) {
		Document doc;
		String tableName = "food_items_oxford";
		try {

			String s1 = "http://www.oxfordreference.com/view/10.1093/acref/9780192803511.001.0001/acref-9780192803511?hide=true&page=";
			String s2 = "&pageSize=100&sort=titlesort&source=%2F10.1093%2Facref%2F9780192803511.001.0001%2Facref-9780192803511";

			for (int i = 1; i < 15; i++) {
				String s = s1 + i + s2;
				doc = Jsoup.connect(s).timeout(10000).get();

				Element contentWrapper = doc.getElementById("contentWrapper");
				// Element columnWrapper =
				// contentWrapper.getElementById("columnWrapper");
				// Element pageBody = contentWrapper.getElementById("pageBody");
				// Element mainContent =
				// contentWrapper.getElementById("mainContent");
				// Element readPanel =
				// contentWrapper.getElementById("readPanel");
				// Element tabEntries =
				// contentWrapper.getElementById("tabEntries");
				Element searchContent = contentWrapper
						.getElementById("searchContent");

				Elements foodnames = searchContent.getElementsByTag("a");

				int x = 0;
				// if (x < 50)
				for (Element ele : foodnames) {
					// x++;
					// System.out.println(ele.text());
					String temp = ele.text();
					if (!temp.equals(null) && temp != "" && temp.length() > 0)
						Store.store(temp, "", tableName, con);
				}

			}

			con.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void parse4(Connection con) {
		Document doc;
		String tableName = "";
		try {

			String s1 = "";
			String s2 = "";

			for (int i = 1; i < 15; i++) {
				String s = s1 + i + s2;
				doc = Jsoup.connect(s).timeout(10000).get();

				Element contentWrapper = doc.getElementById("contentWrapper");
				
				Element searchContent = contentWrapper
						.getElementById("searchContent");

				Elements foodnames = searchContent.getElementsByTag("a");

				int x = 0;
				
				for (Element ele : foodnames) {
					
					String temp = ele.text();
					if (!temp.equals(null) && temp != "" && temp.length() > 0)
						Store.store(temp, "", tableName, con);
				}

			}

			con.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
