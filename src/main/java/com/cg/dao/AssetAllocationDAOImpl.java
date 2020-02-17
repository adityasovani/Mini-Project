package com.cg.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.bean.Asset;
import com.cg.bean.AssetAllocation;
import com.cg.exception.AllocationException;

public class AssetAllocationDAOImpl implements AssetAllocationDAO {

	private Map<Integer, AssetAllocation> allocations = new HashMap<Integer, AssetAllocation>();

	AssetAllocation assetAllocation;

	public AssetAllocationDAOImpl() {

		AssetDAO assetDao = new AssetDAOImpl();
		EmployeeDao employeeDao = new EmployeeDaoImpl();

		assetAllocation = new AssetAllocation();

		assetAllocation.setAllocationId(1205);

		try {
			assetAllocation.setAssetId(assetDao.getAssetById(101).getAssetId());
			assetAllocation.setAsset(assetDao.getAssetById(101));
			assetAllocation.setEmployee(employeeDao.getEmployeeById(101));
		} catch (Exception e) {
			e.printStackTrace();
		}

		assetAllocation.setStatus("pending");
		assetAllocation.setRemark("Work in progress");

		// Record no 2

		AssetAllocation alloc2 = new AssetAllocation();

		alloc2.setAllocationId(488);

		try {
			alloc2.setAssetId(assetDao.getAssetById(102).getAssetId());
			alloc2.setAsset(assetDao.getAssetById(102));
			alloc2.setEmployee(employeeDao.getEmployeeById(103));
		} catch (Exception e) {
			e.printStackTrace();
		}

		alloc2.setStatus("pending");
		alloc2.setRemark("scrambling resources");

		allocations.put(assetAllocation.getAllocationId(), assetAllocation);
		allocations.put(alloc2.getAllocationId(), alloc2);
	}

	// APPROVE or REJECT request
	public void changeStatus(int assetId, String status, String remark) throws AllocationException {

		if (!allocations.containsKey(assetId))
			throw new AllocationException("Requested asset Does not exist");
		
		assetAllocation = allocations.get(assetId);

		Asset asset = assetAllocation.getAsset();

		if (status.equals("approved")) {
			asset.setStatus("allocated");
		} else if (status.equals("rejected")) {
			asset.setStatus("unallocated");
		} else if (status.equals("pending")) {
			asset.setStatus("pending");
		} else {
			throw new IllegalArgumentException("Invalid status. Status must be approved, pending or denied");
		}

		assetAllocation.setAsset(asset);
		assetAllocation.setStatus(status);
		assetAllocation.setRemark(remark);

		allocations.replace(assetId, assetAllocation);

	}

	// REQUEST for asset
	public void request(AssetAllocation assetAllocation) {
		allocations.put(assetAllocation.getAllocationId(), assetAllocation);
	}

	// FIND ALOCATION by id
	public AssetAllocation findById(int allocationId) throws AllocationException {
		if (allocations.containsKey(allocationId))
			return allocations.get(allocationId);
		else
			throw new AllocationException("Requested allocation doesn't exist.");
	}

	// FIND ALL allocations
	public List<AssetAllocation> findAll() {

		List<AssetAllocation> assetAllocations = new ArrayList<AssetAllocation>();

		for (int key : allocations.keySet()) {

			assetAllocations.add(allocations.get(key));
		}

		return assetAllocations;
	}

	// Find all PENDING asset allocation requests
	public List<AssetAllocation> findPending() {
		List<AssetAllocation> pending = new ArrayList<AssetAllocation>();
		for (int key : allocations.keySet()) {
			if (allocations.get(key).getStatus().equals("pending")) {
				pending.add(allocations.get(key));
			}
		}
		return pending;
	}

}
