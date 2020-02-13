package com.cg.service;

import java.io.IOException;

import com.cg.bean.Asset;

public interface AdminService {
	
	/*
	 * This component will allow the admin to add new asset, update an existing asset details 
	 * and Display all request for an asset
	 */
	public Asset addAsset(int assetId, String assetName, String assetDes, int quantity, String status) throws IllegalArgumentException;
	public Asset updateAsset(int assetId, String assetName, String assetDes, int quantity, String status) throws IllegalArgumentException;
	public void viewAllocatedAssets();
	void export(String fileName) throws IOException;
}
