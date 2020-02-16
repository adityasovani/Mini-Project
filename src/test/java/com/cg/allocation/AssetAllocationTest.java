package com.cg.allocation;

import static org.junit.Assert.*;

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
import com.cg.exception.AssetException;

public class AssetAllocationTest {

	AssetAllocationDAO assetAllocationDAO = new AssetAllocationDAOImpl();

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
		Asset asset = new Asset();
		AssetDAO assetDAO = new AssetDAOImpl();
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		assetAllocation.setAllocationId(501);

		assetAllocation.setAsset(assetDAO.getAssetById(102));
		assetAllocation.setAssetId(assetDAO.getAssetById(102).getAssetId());

		assetAllocation.setEmployee(employeeDao.getEmployeeById(101));

		assetAllocation.setStatus("pending");
		assetAllocation.setRemark("Alright");
		assetAllocationDAO.request(assetAllocation);
		
		for (int i = 0; i < assetAllocationDAO.findAll().size(); i++) {
			System.out.println("AllocationID: "+assetAllocationDAO.findAll().get(i).getAllocationId());
			System.out.println("AssetID: "+assetAllocationDAO.findAll().get(i).getAsset().getAssetId());
		}
		
		//System.out.println("FindByID.501:"+assetAllocationDAO.findById(501));
		assertEquals(assetAllocationDAO.findById(501).getAllocationId(), 501);
	}

	@Ignore
	@Test
	public void testFindAllPending() {
		List<AssetAllocation> pending = assetAllocationDAO.findPending();
		System.out.println("Pendya: " + pending.size());
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

	/*
	 * @Test void testAdminViewAll() { AdminDAO admin = new AdminDAOImpl(); }
	 */
}
