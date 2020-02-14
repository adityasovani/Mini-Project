package com.cg.service;

import java.io.IOException;

import com.cg.bean.Asset;
import com.cg.dao.AssetDAO;
import com.cg.dao.AssetDAOImpl;

public class AssetServiceImpl implements AssetService{

	AssetDAO assetDao = new AssetDAOImpl();
	
	@Override
	public Asset addAsset(int assetId, String assetName, String assetDes, String status) throws IllegalArgumentException {
		return assetDao.addAsset(assetId, assetName, assetDes, status);
	}

	@Override
	public Asset updateAsset(int assetId, String assetName, String assetDes, String status)	throws IllegalArgumentException {
		return assetDao.updateAsset(assetId, assetName, assetDes, status);
	}

	@Override
	public void viewAllAssets() {
		assetDao.viewAllAssets();
	}

	@Override
	public void export(String fileName) throws IOException {
		assetDao.export(fileName);
	}
}
