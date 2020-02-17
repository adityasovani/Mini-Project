package com.cg.allocation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.cg.bean.AssetAllocation;
import com.cg.exception.AllocationException;
import com.cg.service.AssetAllocationService;
import com.cg.service.AssetAllocationServiceImpl;
import com.cg.service.AssetServiceImpl;
import com.cg.service.EmployeeServiceImpl;

public class AssetAllocationTest {

	private AssetAllocationService assetAllocationService = new AssetAllocationServiceImpl();

	@Test
	public void testChangeStatus() throws AllocationException {
		assetAllocationService.changeStatus(1205, "rejected", "Insufficient  privilages");
		assertEquals("rejected", assetAllocationService.findById(1205).getStatus());
	}

	@Test
	public void testFindAll() {
		List<AssetAllocation> allocs = assetAllocationService.findAll();

		System.out.println("No of requests: " + allocs.size());
	}

	@Test
	public void testFindById() throws Exception {

		assertEquals(assetAllocationService.findById(1205).getAllocationId(), 1205);
	}

	@Test
	public void testFindAllPending() {
		List<AssetAllocation> pending = new ArrayList<AssetAllocation>();
		for (int i = 0; i < assetAllocationService.findPending().size(); i++) {
			pending.add(assetAllocationService.findPending().get(i));
		}
		// Check whether it is returning pending records or not
		assertEquals(assetAllocationService.findPending().get(0).getStatus(), "pending");
	}

	@Test
	public void testRequest() throws Exception {

		AssetAllocation assetAllocation = new AssetAllocation();

		assetAllocation.setAllocationId(5560);

		assetAllocation.setAssetId(101);
		assetAllocation.setAsset(new AssetServiceImpl().getAssetById(101));

		assetAllocation.setEmployee(new EmployeeServiceImpl().getEmployeeById(102));

		assetAllocation.setStatus("rejected");
		assetAllocation.setRemark("scrambling resources");

		// Call request function of the AssetAllocationDAO
		assetAllocationService.request(assetAllocation);

		assertEquals(assetAllocationService.findById(5560) instanceof AssetAllocation, true);
	}

	@Test
	public void testViewAll() {
		List<AssetAllocation> allocationList = new ArrayList<AssetAllocation>();

		for (int i = 0; i < assetAllocationService.findAll().size(); i++) {
			allocationList.add(assetAllocationService.findAll().get(i));
			System.out.println(
					allocationList.get(i).getAllocationId() + " " + allocationList.get(i).getAsset().getAssetName());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	// Check exception thrown for invalid status
	public void testAllocationStatusException() throws AllocationException {
		assetAllocationService.changeStatus(1205, "accept", "This is for record");
	}

	@Test(expected = AllocationException.class)
	// Check exception thrown for invalid allocationId
	public void testChangeStatusException() throws AllocationException {
		assetAllocationService.changeStatus(11205, "approved", "This is for record");
	}

	@Test(expected = AllocationException.class)
	// Check exception for findById
	public void findByIdAllocationException() throws AllocationException {
		assetAllocationService.findById(788);
	}

	@Test(expected = AllocationException.class)
	// Check if request already exists or not
	public void checkRequestExceptionAllocationExists() throws Exception {
		AssetAllocation assetAllocation = new AssetAllocation();
		assetAllocation.setAllocationId(1205);
		assetAllocation.setAsset(new AssetServiceImpl().getAssetById(102));
		assetAllocation.setAssetId(102);
		assetAllocation.setEmployee(new EmployeeServiceImpl().getEmployeeById(101));
		assetAllocation.setStatus("pending");
		assetAllocation.setRemark("hmmm");

		assetAllocationService.request(assetAllocation);
	}
}