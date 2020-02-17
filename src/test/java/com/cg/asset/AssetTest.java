package com.cg.asset;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.cg.bean.Asset;
import com.cg.exception.AssetException;
import com.cg.service.AssetService;
import com.cg.service.AssetServiceImpl;

public class AssetTest {

	AssetService assetService = new AssetServiceImpl();

	
	@Test
	public void testUPDATE() throws Exception {

		Asset asset;

		asset = assetService.updateAsset(101, "Desktop", "for high speed computing", "unallocated");
		assertEquals(asset.getAssetName(), "Desktop");
		assetService.viewAllAssets();
	}

	@Test
	
	public void testExport() throws IOException {

		assetService.export("aditya");

		File file = new File("aditya.csv");
		assertEquals(file.exists(), true);

	}

	@Test
	public void testViewAll() {
		assetService.viewAllAssets();
	}

	@Test
	
	public void testAdd() {
		Asset ast = assetService.addAsset(158, "Routers", "for faster speed", "unallocated");
		assertEquals(ast.getAssetName(), "Routers");
	}

	
	@Test
	public void testGetAssetbyId() throws AssetException {
		assertEquals("Laptops", assetService.getAssetById(101).getAssetName());
	}

	
	@Test(expected = AssetException.class)
	public void testGetAssetByIdException() throws AssetException {
		assetService.getAssetById(12033);
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void testAssetUpdateStatusException() throws Exception {
		assetService.updateAsset(101, "Mixer", "To mix", "available");
	}
	
	@Test(expected = AssetException.class)
	public void testAssetIdUpdateException() throws Exception {
		assetService.updateAsset(1008, "Mixer", "To mix", "allocated");
	}
}
