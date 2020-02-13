package com.cg.service;

import java.io.IOException;

import com.cg.bean.Asset;
import com.cg.dao.AdminDAO;
import com.cg.dao.AdminDAOImpl;

public class AdminServiceImpl implements AdminService {

	AdminDAO dao = new AdminDAOImpl();

	@Override
	public Asset addAsset(int assetId, String assetName, String assetDes, int quantity, String status) {
		return dao.addAsset(assetId, assetName, assetDes, quantity, status);
	}

	@Override
	public Asset updateAsset(int assetId, String assetName, String assetDes, int quantity, String status) {
		return dao.updateAsset(assetId, assetName, assetDes, quantity, status);
	}

	@Override
	public void viewAllocatedAssets() {
		dao.viewAllAssets();
	}

	@Override
	public void export(String fileName) throws IOException {
		dao.export(fileName);
	}
}
