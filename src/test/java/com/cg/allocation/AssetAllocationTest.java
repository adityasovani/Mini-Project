package com.cg.allocation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.cg.bean.Asset;
import com.cg.bean.AssetAllocation;
import com.cg.bean.Employee;
import com.cg.dao.AssetDAO;
import com.cg.dao.AssetDAOImpl;
import com.cg.dao.EmployeeDao;
import com.cg.dao.EmployeeDaoImpl;
import com.cg.exception.AllocationException;
import com.cg.service.AssetAllocationService;
import com.cg.service.AssetAllocationServiceImpl;

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
		AssetAllocation assetAllocation = new AssetAllocation();
		AssetDAO assetDAO = new AssetDAOImpl();
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		assetAllocation.setAllocationId(501);

		assetAllocation.setAsset(assetDAO.getAssetById(102));
		assetAllocation.setAssetId(assetDAO.getAssetById(102).getAssetId());

		assetAllocation.setEmployee(employeeDao.getEmployeeById(101));

		assetAllocation.setStatus("pending");
		assetAllocation.setRemark("Alright");
		assetAllocationService.request(assetAllocation);

		assertEquals(assetAllocationService.findById(501).getAllocationId(), 501);
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
	public void testRequest() throws AllocationException {

		AssetAllocation assetAllocation = new AssetAllocation();

		assetAllocation.setAllocationId(100);

		Asset asset = new Asset();
		asset.setAssetId(533);
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

		assetAllocation.setStatus("rejected");
		assetAllocation.setRemark("scrambling resources");

		// Call request function of the AssetAllocationDAO
		assetAllocationService.request(assetAllocation);

		assertEquals(assetAllocationService.findById(100) instanceof AssetAllocation, true);
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

	@Test(expected=IllegalArgumentException.class)
	//Check exception thrown for invalid status
	public void testAllocationStatusException() throws AllocationException {
		assetAllocationService.changeStatus(1205, "accept", "This is for record");
	}

	@Test(expected=AllocationException.class)
	//Check exception thrown for invalid allocationId
	public void testChangeStatusException() throws AllocationException {
		assetAllocationService.changeStatus(11205, "approved", "This is for record");
	}
	
	@Test(expected=AllocationException.class)
	public void findByIdAllocationException() throws AllocationException{
		assetAllocationService.findById(788);
	}
}