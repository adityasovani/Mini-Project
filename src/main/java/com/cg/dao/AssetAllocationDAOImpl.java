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

	AssetAllocation assetAllocation ;
	Asset asset;

	public AssetAllocationDAOImpl() throws Exception {

		AssetDAO assetDao = new AssetDAOImpl();
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		
		assetAllocation = new AssetAllocation();
		
		assetAllocation.setAllocationId(1205);

		assetAllocation.setAssetId(assetDao.getAssetById(101).getAssetId());
		assetAllocation.setAsset(assetDao.getAssetById(101));

		assetAllocation.setEmployee(employeeDao.getEmployeeById(101));

		assetAllocation.setStatus("pending");
		assetAllocation.setRemark("Work in progress");

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

		assetAllocation = new AssetAllocation();
		
		assetAllocation = allocations.get(assetId);
		assetAllocation.setStatus(status);
		assetAllocation.setRemark(remark);
		
		allocations.replace(assetId, assetAllocation);
		
		/*for (int key : allocations.keySet()) {
			if (assetId == key) {
				assetAllocation = allocations.get(assetId);
				assetAllocation.setStatus(status);
				assetAllocation.setRemark(remark);
				allocations.put(assetId, assetAllocation);
			}
		}*/
		//System.out.println("Status = "+allocations.get(assetId).getStatus());

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
