package com.cg.service;

import java.util.List;

import com.cg.bean.AssetAllocation;

public interface AssetAllocationService {
	
	public void changeStatus(int assetId, String status, String remark);
	public void request(AssetAllocation assetAllocation);
	public AssetAllocation findById(int allocationId);
	public List<AssetAllocation> findAll();
	public List<AssetAllocation> findPending();

}
