package com.how.achtech;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class TraiterData {
	public static String URI = "destination.uri";
	public static String CATEGORIES = "categorie.list";
	
	public static List<Articles> getDataFromUrl(String url) {
		Document doc;
		List<Articles> list = new ArrayList<Articles>();
		try {
			doc = Jsoup.connect(url).get();
			Element rootNode = doc.getElementById("bodycontents");

			Elements firstDiv = rootNode
					.getElementsByClass("section steps   sticky  steps_first");

			list.addAll(getDataFromDiv(firstDiv));
			Elements otherDiv = rootNode
					.getElementsByClass("section steps   sticky ");

			list.addAll(getDataFromDiv(otherDiv));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static List<Articles> getDataFromDiv(Elements outerdiv) {
		List<Articles> list = new ArrayList<Articles>();
		for (Element bigDiv : outerdiv) {
			Articles article = new Articles();

			String title1 = bigDiv.getElementsByClass("mw-headline").text();
			article.setTitle(title1);

			List<Chaptres> datas = new ArrayList<Chaptres>();
			Elements div1 = bigDiv.getElementsByClass("section_text");
			for (Element div : div1) {
				Elements div2 = div.getElementsByClass("hasimage");
				for (Element smallDiv : div2) {
					boolean isVideo = false;
					// TODO : check if the image exist
					List<String> srcs = smallDiv
							.getElementsByClass("whcdn content-fill")
							.eachAttr("src");
					String src = srcs.size()>0?srcs.get(0):"";
					if(src.isEmpty()){
						srcs = smallDiv
								.getElementsByClass("m-video content-fill")
								.eachAttr("data-poster");
						if(srcs.size()>0) {
							src = srcs.get(0);
							isVideo = true;
						}
					}
					String subTitle = smallDiv.getElementsByClass("whb")
							.eachText().get(0);
					Elements divText = smallDiv.getElementsByClass("step");
					String text = divText.eachText().get(0);
					List<String> lis = divText.get(0).getElementsByTag("li")
							.eachText();
					for (String li : lis) {
						text = text.replace(li, "");
					}
					datas.add(new Chaptres(subTitle, src,isVideo, text
							.replace(subTitle, ""), lis));
				}
				article.setDatas(datas);
			}
			list.add(article);
		}
		return list;
	}


	public static StringBuilder fillModel(int index, String subtitle, String src, boolean isVideo,
			String text, List<String> lis) {
		StringBuilder res = new StringBuilder();
		res.append("<div style='max-width:100%'>");
		res.append("<p><b style='color: rgb(128, 0, 0);'>"+ index+". "+subtitle + "</b></p>");
		
		if(!src.isEmpty())
			res.append("<div><center><img src='"+ src	+ "'  style='width: 80%; margin-right:10%; border: 2px solid; border-radius: 25%; margin-bottom: 10px;' /></center></div>");
		else if(isVideo && !src.isEmpty())
			res.append("<div><center><img playsinline src='"+ src	+ "'  style='width: 80%; margin-right:10%; border: 2px solid; border-radius: 25%; margin-bottom: 10px;' /></center></div>");
		
		res.append("<div style='width: 90%; margin-left: 5%; text-align: justify'>");
		res.append(text);
		res.append("</div>");
		if (lis.size() > 0) {
			res.append("<div>");
			res.append("<ul style='padding-bottom: 8px;width: 80%;text-align: justify;'>");
			for (String li : lis) {
				res.append("<li>");
				res.append(li);
				res.append("</li>");
			}
			res.append("</ul>");
			res.append("</div>");
		}
		res.append("<hr style='height: 2px;color: rgb(128, 0, 0);background-color: rgb(128, 0, 0);border: none;'>");
		return res;
	}
	
	public static List<Articles> createHtmlFileFromList(List<Articles> list,String fileName) {
		List<Articles> res = new ArrayList<Articles>();
		try {
			for (int i = 0; i < list.size(); i++) {
				Articles a = list.get(i);
				BufferedWriter writer;
				//TODO : create file fileName+"\\"+(i+1)+". "+a.getTitle()+ ".txt"
				Paths.get(fileName.trim()+"\\"+(i+1)+". "+a.getTitle().trim()+ ".txt");
				writer = new BufferedWriter(new FileWriter(fileName.trim()+"\\"+(i+1)+". "+a.getTitle().trim()+ ".txt"));
				StringBuilder str = new StringBuilder();
				for (int j = 0;j<a.getDatas().size();j++) {
					Chaptres data = a.getDatas().get(j);
					str.append(fillModel(j+1,data.getSubtitle(), data.getSrc(),data.isVideo(), 
							data.getText(), data.getLi()));
				}
				a.setTxt(str.toString());
				res.add(a);
				writer.write(str.toString());
				writer.close();
			}
		} catch (IOException e) {
			System.err.print(e.getMessage());
		}
		return res;
	}
	
	public static Map<String,String> getPropoerties(){
		final Properties prop = new Properties();
		InputStream input = null;
		Map<String,String> map = new HashMap<String, String>();
		try {

			input = new FileInputStream("files/config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			map.put(URI,prop.getProperty(URI));
			map.put(CATEGORIES,prop.getProperty(CATEGORIES));
			

		} catch (final IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
	public static void changeValueOfProperties(String key,String newValue){
		Properties properties = new Properties();
		try {		
			String propertiesFilePath = ("files/config.properties");
			FileInputStream fis = new FileInputStream(propertiesFilePath);
			properties.load(fis);
			properties.setProperty(key,newValue);
			FileOutputStream fos = new FileOutputStream(propertiesFilePath);
			properties.store(fos,null);
			System.out.println("alert('Mise à jour effectuée.');");
		}
		catch(Exception e) {
			System.out.println("alert('Echec de la mise à jour.');");
		}
	}

}
