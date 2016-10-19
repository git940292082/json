package com.news.news.entity;

/**
 * 1.FunnyFragment ʵ���� 2.���� һ�� JSON ���� 3.ͨ�� ���� ��Ӧ���ݰ� ���
 * 
 * @author Shark Z
 * 
 */
public class Funny  {

	private int error_code;
	private String reason;

	private FunnyData result;

	public Funny() {
		super();
	}

	public Funny(int error_code, String reason, FunnyData result) {
		super();
		this.error_code = error_code;
		this.reason = reason;
		this.result = result;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public FunnyData getResult() {
		return result;
	}

	public void setResult(FunnyData result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Funny [error_code=" + error_code + ", reason=" + reason
				+ ", result=" + result + "]";
	}

}
