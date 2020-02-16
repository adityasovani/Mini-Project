package com.cg.asset;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.cg.bean.Asset;
import com.cg.dao.AssetDAO;
import com.cg.dao.AssetDAOImpl;
import com.cg.exception.AssetException;

public class AssetTest {

	AssetDAO assetdao = new AssetDAOImpl();

	@Test
	public void testUPDATE() throws Exception {

		Asset asset;

		asset = assetdao.updateAsset(101, "Desktop", "for high speed computing", "unallocated");
		assertEquals(asset.getAssetName(), "Desktop");
		assetdao.viewAllAssets();
	}

	@Test
	public void testExport() throws IOException {

		assetdao.export("aditya");

		File file = new File("aditya.csv");
		assertEquals(file.exists(), true);

	}

	@Test
	public void testViewAll() {
		assetdao.viewAllAssets();
	}

	@Test
	public void testAdd() {
		Asset ast = assetdao.addAsset(158, "Routers", "for faster speed", "unallocated");
		assertEquals(ast.getAssetName(), "Routers");
	}

	@Test
	public void testGetAssetbyId() throws AssetException {
		assertEquals("Laptops", assetdao.getAssetById(101).getAssetName());
	}

	@Test(expected = AssetException.class)
	public void testGetAssetByIdException() throws AssetException {
		assetdao.getAssetById(12033);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAssetUpdateStatusException() throws Exception {
		assetdao.updateAsset(101, "Mixer", "To mix", "available");
	}

	@Test(expected = AssetException.class)
	public void testAssetIdUpdateException() throws  Exception {
		assetdao.updateAsset(1008, "Mixer", "To mix", "allocated");
	}
}
