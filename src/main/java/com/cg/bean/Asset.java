package com.cg.bean;

import java.io.Serializable;

public class Asset implements Serializable{
	private int assetId;
	private String assetName;
	private String assetDes;
	private int quantity;
	private String status;
	
	public int getAssetId() {
		return assetId;
	}
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getAssetDes() {
		return assetDes;
	}
	public void setAssetDes(String assetDes) {
		this.assetDes = assetDes;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
