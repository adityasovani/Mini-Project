package com.cg.dao;

import java.util.List;

import com.cg.bean.AssetAllocation;

public interface AssetAllocationDAO {
	public void changeStatus(int assetId, String status, String remark);
	public AssetAllocation request(AssetAllocation assetAllocation);
	public AssetAllocation findById(int allocationId);
	public List<AssetAllocation> findAll();
	public List<AssetAllocation> findPending();
}
