package com.cg.allocation;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.cg.bean.Asset;
import com.cg.bean.AssetAllocation;
import com.cg.bean.Employee;
import com.cg.dao.AssetAllocationDAO;
import com.cg.dao.AssetAllocationDAOImpl;

public class AssetAllocationTest {

	AssetAllocationDAO assetAllocationDAO;

	@Test
	public void testChangeStatus() {
		assetAllocationDAO = new AssetAllocationDAOImpl();
		assetAllocationDAO.changeStatus(101, "rejected", "Lai nataka hotayt");
		
	}

	@Test
	public void testFindAll() {
		assetAllocationDAO = new AssetAllocationDAOImpl();
		List<AssetAllocation> allocs = assetAllocationDAO.findAll();
		
		System.out.println("No of requests: "+allocs.size());
	}
	
	@Test
	public void testFindById() {
		assetAllocationDAO = new AssetAllocationDAOImpl();
		System.out.println(assetAllocationDAO.findById(488).getAsset().getAssetId());
		assertEquals(assetAllocationDAO.findById(488).getAllocationId(), 488);
	}
	
	@Test
	public void testFindAllPending() {
		assetAllocationDAO = new AssetAllocationDAOImpl();
		List<AssetAllocation> pending = assetAllocationDAO.findPending();
		System.out.println("Pendya: "+pending.size());
	}
	
	@Test
	public void testRequest() {
		assetAllocationDAO = new AssetAllocationDAOImpl();
		
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
		
		//Call request function of the AssetAllocationDAO
		assetAllocationDAO.request(assetAllocation);
		
		System.out.println("FindAll: "+assetAllocationDAO.findAll().size());
	}
	
	/*@Test
	void testAdminViewAll() {
		AdminDAO admin = new AdminDAOImpl();
	}*/
}
