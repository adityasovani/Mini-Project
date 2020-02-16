package com.cg.service;

import java.io.IOException;

import com.cg.bean.Asset;
import com.cg.dao.AssetDAO;
import com.cg.dao.AssetDAOImpl;
import com.cg.exception.AssetException;

public class AssetServiceImpl implements AssetService{

	AssetDAO assetDao = new AssetDAOImpl();
	
	public Asset addAsset(int assetId, String assetName, String assetDes, String status) throws IllegalArgumentException {
		return assetDao.addAsset(assetId, assetName, assetDes, status);
	}

	public Asset updateAsset(int assetId, String assetName, String assetDes, String status)	throws Exception {
		return assetDao.updateAsset(assetId, assetName, assetDes, status);
	}

	public void viewAllAssets() {
		assetDao.viewAllAssets();
	}

	public void export(String fileName) throws IOException {
		assetDao.export(fileName);
	}

	public Asset getAssetById(int assetId) throws AssetException {
		return assetDao.getAssetById(assetId);
	}
}
