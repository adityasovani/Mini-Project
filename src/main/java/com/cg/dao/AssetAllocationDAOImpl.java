package com.cg.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.bean.Asset;
import com.cg.bean.AssetAllocation;
import com.cg.bean.Employee;
import com.cg.exception.AssetException;

public class AssetAllocationDAOImpl implements AssetAllocationDAO {

	private Map<Integer, AssetAllocation> allocations = new HashMap<Integer, AssetAllocation>();

	AssetAllocation assetAllocation ;

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

		//Record no 2
		
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

	public void changeStatus(int assetId, String status, String remark) {

		assetAllocation = allocations.get(assetId);
		
		Asset asset = assetAllocation.getAsset();
		
		if (status.equals("approved") ) {
			asset.setStatus("allocated");
		} else if (status.equals("rejected")) {
			asset.setStatus("unallocated");
		}
		
		assetAllocation.setAsset(asset);
		assetAllocation.setStatus(status);
		assetAllocation.setRemark(remark);
		
		allocations.replace(assetId, assetAllocation);
		
	}

	public void request(AssetAllocation assetAllocation) {
		allocations.put(assetAllocation.getAllocationId(), assetAllocation);
	}

	public AssetAllocation findById(int allocationId) {
		return allocations.get(allocationId);
	}

	public List<AssetAllocation> findAll() {

		List<AssetAllocation> assetAllocations = new ArrayList<AssetAllocation>();

		for (int key : allocations.keySet()) {

			assetAllocations.add(allocations.get(key));
		}

		return assetAllocations;
	}

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
