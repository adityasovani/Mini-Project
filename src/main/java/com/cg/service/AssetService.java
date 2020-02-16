package com.cg.service;

import java.io.IOException;

import com.cg.bean.Asset;
import com.cg.exception.AssetException;

public interface AssetService {
	
	public Asset addAsset(int assetId, String assetName, String assetDes, String status) throws IllegalArgumentException;
	public Asset updateAsset(int assetId, String assetName, String assetDes, String status)	throws  Exception;
	public void viewAllAssets();
	public void export(String fileName) throws IOException;
	public Asset getAssetById(int assetId) throws AssetException;
}
