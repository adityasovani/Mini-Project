package com.cg.dao;

import java.util.List;

import com.cg.bean.AssetAllocation;
import com.cg.exception.AllocationException;

public interface AssetAllocationDAO {
	public void changeStatus(int assetId, String status, String remark) throws AllocationException;
	public void request(AssetAllocation assetAllocation);
	public AssetAllocation findById(int allocationId) throws AllocationException;
	public List<AssetAllocation> findAll();
	public List<AssetAllocation> findPending();
}
