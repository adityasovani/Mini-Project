package com.cg.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.bean.Asset;
import com.cg.bean.AssetAllocation;
import com.cg.bean.Employee;

public class AssetAllocationDAOImpl implements AssetAllocationDAO {

	private Map<Integer, AssetAllocation> allocations = new HashMap<Integer, AssetAllocation>();

	AssetAllocation assetAllocation = new AssetAllocation();
	Asset asset;

	public AssetAllocationDAOImpl() {

		assetAllocation.setAllocationId(1205);

		asset = new Asset();
		asset.setAssetId(101);
		asset.setAssetName("Laptops");
		asset.setAssetDes("laptops required for additional interns");
		asset.setStatus("allocated");

		assetAllocation.setAssetId(asset.getAssetId());
		assetAllocation.setAsset(asset);

		Employee employee = new Employee();
		employee.setDepartment("Networks");
		employee.setEmpName("Mayuresh");
		employee.setEmpNo(2344);
		assetAllocation.setEmployee(employee);

		assetAllocation.setStatus("pending");
		assetAllocation.setRemark("scrambling resources");

		AssetAllocation alloc2 = new AssetAllocation();

		 alloc2.setAllocationId(488);

		Asset asset1 = new Asset();
		asset1.setAssetId(101);
		asset1.setAssetName("Desktops");
		asset1.setAssetDes("laptops required for additional interns");
		asset1.setStatus("allocated");

		alloc2.setAssetId(asset1.getAssetId());
		alloc2.setAsset(asset1);

		Employee employee1 = new Employee();
		employee1.setDepartment("Networks");
		employee1.setEmpName("Mayuresh");
		employee1.setEmpNo(2344);
		alloc2.setEmployee(employee1);

		alloc2.setStatus("pending");
		alloc2.setRemark("scrambling resources");
		
		allocations.put(assetAllocation.getAllocationId(), assetAllocation);
		allocations.put(alloc2.getAllocationId(), alloc2);
	}

	public void changeStatus(int assetId, String status, String remark) {
		Asset asset;
		
		assetAllocation = allocations.get(assetId);
		
		assetAllocation.setStatus(status);
		assetAllocation.setRemark(remark);
		
		allocations.put(assetId, assetAllocation);
		System.out.println(allocations.get(assetId).getStatus());
	}

	public void request(AssetAllocation assetAllocation) {
		allocations.put(assetAllocation.getAsset().getAssetId(), assetAllocation);
	}

	public AssetAllocation findById(int allocationId) {
		return allocations.get(allocationId);
	}

	public List<AssetAllocation> findAll() {
		
		List<AssetAllocation> assetAllocations = new ArrayList<AssetAllocation>();
		
		for(int key: allocations.keySet()) {
			
			assetAllocations.add(allocations.get(key));
		}
		
		return assetAllocations;
	}

	public List<AssetAllocation> findPending() {
		List<AssetAllocation> pending = new ArrayList<AssetAllocation>();
		for(int key : allocations.keySet()) {
			if(allocations.get(key).getStatus().equals("pending")) {
				pending.add(allocations.get(key));
			}
		}
		return pending;
	}

}
