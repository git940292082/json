package com.news.news.untils;

//Ц����װ
public class Joke {  
	private String content; //����
	private String updatetime;  //ʱ��
	private String url; //ͼƬ����
	public Joke(String content, String updatetime, String url) {
		super();
		this.content = content;
		this.updatetime = updatetime;
		this.url = url;
	}
	public Joke() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Joke [content=" + content + ", updatetime=" + updatetime + ", url=" + url + "]";
	}
	
}
