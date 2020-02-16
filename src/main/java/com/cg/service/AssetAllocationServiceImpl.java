package com.cg.service;

import java.util.List;

import com.cg.bean.AssetAllocation;
import com.cg.dao.AssetAllocationDAO;
import com.cg.dao.AssetAllocationDAOImpl;
import com.cg.exception.AllocationException;

public class AssetAllocationServiceImpl implements AssetAllocationService{

	AssetAllocationDAO assetAllocationDao = new AssetAllocationDAOImpl();
	
	public void changeStatus(int assetId, String status, String remark) throws AllocationException {
		assetAllocationDao.changeStatus(assetId, status, remark);
	}

	public void request(AssetAllocation assetAllocation) {
		assetAllocationDao.request(assetAllocation);
	}

	public AssetAllocation findById(int allocationId) {
		return assetAllocationDao.findById(allocationId);
	}

	public List<AssetAllocation> findAll() {
		return assetAllocationDao.findAll();
	}

	public List<AssetAllocation> findPending() {
		return assetAllocationDao.findPending();
	}

}
