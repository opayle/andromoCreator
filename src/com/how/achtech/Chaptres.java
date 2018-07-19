package com.how.achtech;

import java.util.List;

public class Chaptres {
	String subtitle;
	String src;
	String text;
	boolean video;
	List<String> li;
	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}
	/**
	 * @param subtitle the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	/**
	 * @return the src
	 */
	public String getSrc() {
		return src;
	}
	/**
	 * @param src the src to set
	 */
	public void setSrc(String src) {
		this.src = src;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the li
	 */
	public List<String> getLi() {
		return li;
	}
	/**
	 * @param li the li to set
	 */
	public void setLi(List<String> li) {
		this.li = li;
	}
	public Chaptres(String subtitle, String src,boolean video, String text, List<String> li) {
		super();
		this.subtitle = subtitle;
		this.src = src;
		this.video = video;
		this.text = text;
		this.li = li;
	}
	/**
	 * @return the video
	 */
	public boolean isVideo() {
		return video;
	}
	/**
	 * @param video the video to set
	 */
	public void setVideo(boolean video) {
		this.video = video;
	}
	
	
	
}
