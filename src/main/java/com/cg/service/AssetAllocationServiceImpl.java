package com.cg.service;

import java.util.List;

import com.cg.bean.AssetAllocation;
import com.cg.dao.AssetAllocationDAO;
import com.cg.dao.AssetAllocationDAOImpl;

public class AssetAllocationServiceImpl implements AssetAllocationService{

	AssetAllocationDAO assetAllocationDao = new AssetAllocationDAOImpl();
	
	@Override
	public void changeStatus(int assetId, String status, String remark) {
		assetAllocationDao.changeStatus(assetId, status, remark);
	}

	@Override
	public void request(AssetAllocation assetAllocation) {
		assetAllocationDao.request(assetAllocation);
	}

	@Override
	public AssetAllocation findById(int allocationId) {
		return assetAllocationDao.findById(allocationId);
	}

	@Override
	public List<AssetAllocation> findAll() {
		return assetAllocationDao.findAll();
	}

	@Override
	public List<AssetAllocation> findPending() {
		return assetAllocationDao.findPending();
	}

}
