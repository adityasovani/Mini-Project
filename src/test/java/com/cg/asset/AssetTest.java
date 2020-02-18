package com.cg.asset;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.bean.Asset;
import com.cg.exception.AssetException;
import com.cg.service.AssetService;
import com.cg.service.AssetServiceImpl;

public class AssetTest {

	AssetService assetService;
	File file;
	@Before
	//Initialize
	public void initialize() {
		assetService = new AssetServiceImpl();
		file = new File("aditya.csv");
	}

	@Test
	//Test update function
	public void testUpdate() throws Exception {

		Asset asset;

		asset = assetService.updateAsset(101, "Desktop", "for high speed computing", "unallocated");
		assertEquals(asset.getAssetName(), "Desktop");
		assetService.viewAllAssets();
	}

	@Test
	//Test export
	public void testExport() throws IOException {

		assetService.export("aditya");

		assertEquals(file.exists(), true);

	}

	@Test
	//Test viewAll
	public void testViewAll() {
		assetService.viewAllAssets();
	}

	@Test
	//Test add asset
	public void testAdd() {
		Asset ast = assetService.addAsset(158, "Routers", "for faster speed", "unallocated");
		assertEquals(ast.getAssetName(), "Routers");
	}

	@Test
	//Test get assetById
	public void testGetAssetbyId() throws AssetException {
		assertEquals("Laptops", assetService.getAssetById(101).getAssetName());
	}

	@Test(expected = AssetException.class)
	//Check exception thrown after entering wrong id during  retrieving asset
	public void testGetAssetByIdException() throws AssetException {
		assetService.getAssetById(12033);
	}

	@Test(expected = IllegalArgumentException.class)
	//Test to check validation of update status
	public void testAssetUpdateStatusException() throws Exception {
		assetService.updateAsset(101, "Mixer", "To mix", "available");
	}

	@Test(expected = AssetException.class)
	//Test exception thrown after entering wrong id.
	public void testAssetIdUpdateException() throws Exception {
		assetService.updateAsset(1008, "Mixer", "To mix", "allocated");
	}
	
	@After
	//Delete file created for test
	public void clean() {
		file.delete();
	}
}
