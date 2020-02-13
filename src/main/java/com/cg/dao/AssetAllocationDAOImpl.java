package com.cg.dao;

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
		
		System.out.println(assetAllocation.getAsset().getAssetName());
		
		allocations.put(assetAllocation.getAllocationId(), assetAllocation);
	}

	@Override
	public void changeStatus(int assetId, String status, String remark) {
		Asset asset;
		for(int key : allocations.keySet()) {
			assetAllocation = allocations.get(key);
			asset = assetAllocation.getAsset();
			if(asset.getAssetId() == assetId) {
				assetAllocation.setStatus(status);
				assetAllocation.setRemark(remark);
				if(remark.equals("rejected"))
					asset.setStatus("unallocatepd");
				assetAllocation.setAsset(asset);
				allocations.replace(key, assetAllocation);
			}
		}
		
		System.out.println(this.assetAllocation.getStatus());
		System.out.println(assetAllocation.getAsset().getStatus());
	}

	@Override
	public AssetAllocation request(AssetAllocation assetAllocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssetAllocation findById(int allocationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssetAllocation> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssetAllocation> findPending() {
		// TODO Auto-generated method stub
		return null;
	}

}
