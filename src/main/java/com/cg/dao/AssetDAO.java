package com.cg.dao;

import java.io.IOException;

import com.cg.bean.Asset;

public interface AssetDAO {

	public Asset addAsset(int assetId, String assetName, String assetDes, String status) throws IllegalArgumentException;
	public Asset updateAsset(int assetId, String assetName, String assetDes, String status) throws IllegalArgumentException;
	public void viewAllAssets();
	void export(String fileName) throws IOException;
		
}
