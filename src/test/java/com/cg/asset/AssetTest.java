package com.cg.asset;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.cg.bean.Asset;
import com.cg.dao.AssetDAO;
import com.cg.dao.AssetDAOImpl;

public class AssetTest {

	AssetDAO assetdao;

	@Test
	public void testUPDATE() {

		assetdao = new AssetDAOImpl();
		Asset asset;

		asset = assetdao.updateAsset(101, "Desktop", "for high speed computing", "unallocated");
		assertEquals(asset.getAssetName(), "Desktop");
		assetdao.viewAllAssets();
	}

	@Test
	public void testExport() throws IOException {

		assetdao = new AssetDAOImpl();
		assetdao.export("aditya");

		File file = new File("aditya.csv");
		assertEquals(file.exists(), true);

	}

	@Test
	public void testViewAll() {
		assetdao = new AssetDAOImpl();
		assetdao.viewAllAssets();
	}

	@Test
	public void testAdd() {

		assetdao = new AssetDAOImpl();

		Asset ast;

		ast = assetdao.addAsset(158, "Routers", "for faster speed", "unallocated");
		assertEquals(ast.getAssetName(), "Routers");
	}
}
