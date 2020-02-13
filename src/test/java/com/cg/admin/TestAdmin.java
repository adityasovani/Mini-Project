package com.cg.admin;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.junit.Test;

import com.cg.bean.Asset;
import com.cg.dao.AdminDAO;
import com.cg.dao.AdminDAOImpl;
import com.cg.dao.ManagerDao;
import com.cg.dao.ManagerDaoImpl;

public class TestAdmin  {

	AdminDAO admin = new AdminDAOImpl();
	
	Asset asset = admin.addAsset(1200, "machine_1", "Pune", 50, "unallocated");
	
	//Test to check add asset
	@Test
	public void testAddAsset() {
		assertEquals(asset.getStatus(), "unallocated");
	}

	//Test to check update asset
	@Test
	public void testUpdateAsset() {
		asset = admin.updateAsset(1200, "machine_2", "Pune", 50, "allocated");
		assertEquals(asset.getAssetName(), "machine_2");
	}
	
	//Test to check EXCEPTION for wrong status option during adding asset
	@Test(expected = IllegalArgumentException.class)
	public void  testAddException() {
		asset = admin.addAsset(1500, "udemy subscription", "learning", 560, "available");
	}
	
	//Test to check EXCEPTION for wrong status option during updating asset
	@Test(expected = IllegalArgumentException.class)
	public void  testUpdateException() {
		asset = admin.addAsset(1600, "MS Office Licences", "official", 55, "unavailable");
	}
	
	//Test to check functionality of view allocated assets functionality
	@Test
	public void testViewAllAssets() {
		admin.addAsset(160, "Transformer", "ForUsageBear", 525, "allocated");
		admin.addAsset(150, "Capacitor", "ForCharging", 325, "allocated");
		admin.addAsset(1220, "Chipset", " Processing", 252, "allocated");
		
		admin.addAsset(1530, "SoC", "ForCharging", 325, "unallocated");
		admin.addAsset(1270, "Chipset", " Processing", 252, "unallocated");
		
		System.out.println("----------TEST FOR VIEW ALLOATED ASSETS------------\n");
		admin.viewAllAssets();
	}
	
	@Test
	public void testExport() throws IOException {
		
		admin.addAsset(160, "Transformer", "ForUsageBear", 525, "allocated");
		admin.addAsset(150, "Capacitor", "ForCharging", 325, "allocated");
		admin.addAsset(1220, "Chipset", " Processing", 252, "allocated");
		admin.addAsset(1530, "SoC", "ForCharging", 325, "unallocated");
		admin.addAsset(1270, "Chipset", " Processing", 252, "unallocated");
		
		admin.export("aditya");
		
		File file = new File("aditya.csv");
		assertEquals(file.exists(), true);
	}
	
	@Test
	public void testManagerView() throws Exception {
		ManagerDao manager = new ManagerDaoImpl();
		manager.viewAssets();
	}
}
