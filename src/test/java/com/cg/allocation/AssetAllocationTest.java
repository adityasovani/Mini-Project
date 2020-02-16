package com.cg.allocation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.cg.bean.Asset;
import com.cg.bean.AssetAllocation;
import com.cg.bean.Employee;
import com.cg.dao.AssetAllocationDAO;
import com.cg.dao.AssetAllocationDAOImpl;
import com.cg.dao.AssetDAO;
import com.cg.dao.AssetDAOImpl;
import com.cg.dao.EmployeeDao;
import com.cg.dao.EmployeeDaoImpl;

public class AssetAllocationTest {

	private AssetAllocationDAO assetAllocationDAO;
	
	public AssetAllocationTest() throws Exception {
		assetAllocationDAO = new AssetAllocationDAOImpl();
	}

	@Ignore
	@Test
	public void testChangeStatus() {
		assetAllocationDAO.changeStatus(1205, "rejected", "Lai nataka hotayt");

	}

	@Ignore
	@Test
	public void testFindAll() {
		List<AssetAllocation> allocs = assetAllocationDAO.findAll();

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
		assetAllocationDAO.request(assetAllocation);

		assertEquals(assetAllocationDAO.findById(501).getAllocationId(), 501);
	}

	@Ignore
	@Test
	public void testFindAllPending() {
		List<AssetAllocation> pending = new ArrayList<AssetAllocation>();
		for(int i=0; i<assetAllocationDAO.findPending().size(); i++) {
			pending.add(assetAllocationDAO.findPending().get(i));
		}
	}

	@Ignore
	@Test
	public void testRequest() {

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
		assetAllocationDAO.request(assetAllocation);

		System.out.println("FindAll: " + assetAllocationDAO.findAll().size());
	}

	@Test
	public void testViewAll() {
		List<AssetAllocation> allocationList = new ArrayList<AssetAllocation>();
		
		for (int i = 0; i < assetAllocationDAO.findAll().size(); i++) {
			allocationList.add(assetAllocationDAO.findAll().get(i));
			System.out.println(allocationList.get(i).getAllocationId()+ " "+ allocationList.get(i).getAsset().getAssetName());
		}
	}

}
