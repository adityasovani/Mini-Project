package com.cg.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.cg.bean.Asset;

public class AdminDAOImpl implements AdminDAO {

	Asset asset;
	Map<Integer, Asset> ast = new HashMap<Integer, Asset>();

	@Override
	public Asset addAsset(int assetId, String assetName, String assetDes, int quantity, String status)
			throws IllegalArgumentException {

		asset = new Asset();
		if (!status.equals("allocated") && !status.equals("unallocated"))
			throw new IllegalArgumentException("status can be allocated and unallocated.");

		asset.setAssetDes(assetDes);
		asset.setAssetId(assetId);
		asset.setAssetName(assetName);
		asset.setQuantity(quantity);
		asset.setStatus(status);

		ast.put(assetId, asset);

		return asset;
	}

	@Override
	public Asset updateAsset(int assetId, String assetName, String assetDes, int quantity, String status)
			throws IllegalArgumentException {

		if (!status.equals("allocated") && !status.equals("unallocated"))
			throw new IllegalArgumentException("status can be allocated and unallocated.");

		asset = new Asset();
		
		asset.setAssetDes(assetDes);
		asset.setAssetId(assetId);
		asset.setAssetName(assetName);
		asset.setQuantity(quantity);
		asset.setStatus(status);

		ast.replace(assetId, asset);

		return asset;
	}

	@Override
	public void viewAllAssets() {
		int i = 0;

		System.out.println("ALLOCATED ASSETS\n");
		System.out.println("AssetID\tAssetName\tAssetDes\tAssetQty");
		for (Integer key : ast.keySet()) {

			asset = ast.get(key);
			if (asset.getStatus().equals("allocated")) {
				System.out.println(asset.getAssetId() + "\t" + asset.getAssetName() + "\t" + asset.getAssetDes() + "\t"
						+ Integer.toString(asset.getQuantity()));
			}

		}
		System.out.println("\nUNALLOCATED ASSETS\n");
		System.out.println("AssetID\tAssetName\tAssetDes\tAssetQty");
		for (Integer key1 : ast.keySet()) {

			asset = ast.get(key1);
			if (asset.getStatus().equals("unallocated")) {
				System.out.println(asset.getAssetId() + "\t" + asset.getAssetName() + "\t\t" + asset.getAssetDes()
						+ "\t" + Integer.toString(asset.getQuantity()));
			}
		}
	}

	@Override
	public void export(String fileName) throws IOException {
		Writer write = new FileWriter(fileName + ".csv", true);
		write.write("AssetId,AssetName,AssetDes,AssetQty,AssetType\n");
		for (Integer key : ast.keySet()) {
			asset = ast.get(key);
			write.write(key + "," + asset.getAssetName() + "," + asset.getAssetDes() + "," + asset.getQuantity() + ","
					+ asset.getStatus() + "\n");
		}

		write.close();
	}

}
