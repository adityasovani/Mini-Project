package com.cg.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import com.cg.bean.Asset;
import com.cg.exception.AssetException;

public class AssetDAOImpl implements AssetDAO {

	public Map<Integer, Asset> assets = new HashMap<Integer, Asset>();
	private Asset asset;

	public AssetDAOImpl() {
		asset = new Asset();

		asset.setAssetId(101);
		asset.setAssetName("Laptops");
		asset.setAssetDes("abcdefghijkl");
		asset.setStatus("allocated");

		assets.put(asset.getAssetId(), asset);

		Asset asset1 = new Asset();

		asset1.setAssetId(102);
		asset1.setAssetName("PC");
		asset1.setAssetDes("mnopqrs");
		asset1.setStatus("unallocated");

		assets.put(asset1.getAssetId(), asset1);

		Asset asset2 = new Asset();

		asset2.setAssetId(103);
		asset2.setAssetName("WI-FI");
		asset2.setAssetDes("tuvwxyz");
		asset2.setStatus("allocated");

		assets.put(asset2.getAssetId(), asset2);

	}

	public Asset addAsset(int assetId, String assetName, String assetDes, String status)
			throws IllegalArgumentException {

		asset = new Asset();

		if (!status.equals("allocated") && !status.equals("unallocated"))
			throw new IllegalArgumentException("status can be allocated and unallocated.");

		asset.setAssetDes(assetDes);
		asset.setAssetId(assetId);
		asset.setAssetName(assetName);
		asset.setStatus(status);

		assets.put(assetId, asset);

		return asset;
	}

	public Asset updateAsset(int assetId, String assetName, String assetDes, String status) throws Exception {

		if (!assets.containsKey(assetId))
			throw new AssetException("Asset does not exist. Check assetId and try again.");

		if (!status.equals("allocated") && !status.equals("unallocated"))
			throw new IllegalArgumentException("status can be allocated and unallocated.");

		asset = new Asset();
		asset.setAssetId(assetId);
		asset.setAssetName(assetName);
		asset.setAssetDes(assetDes);
		asset.setStatus(status);

		assets.put(assetId, asset);

		return asset;

	}

	public void viewAllAssets() {
		asset = new Asset();

		System.out.println("ALLOCATED ASSETS\n");
		System.out.println("AssetID\tAssetName\tAssetDes\tAssetStatus");

		for (Integer key : assets.keySet()) {

			asset = assets.get(key);
			if (asset.getStatus().equals("allocated"))
				System.out.println(asset.getAssetId() + "\t" + asset.getAssetName() + "\t" + asset.getAssetDes() + "\t"
						+ asset.getStatus());

		}

		System.out.println("\nUNALLOCATED ASSETS\n");
		System.out.println("AssetID\tAssetName\tAssetDes\tAssetStatus");

		for (Integer key1 : assets.keySet()) {

			asset = assets.get(key1);
			if (asset.getStatus().equals("unallocated"))
				System.out.println(asset.getAssetId() + "\t" + asset.getAssetName() + "\t\t" + asset.getAssetDes()
						+ "\t" + asset.getStatus());

		}

	}

	public void export(String fileName) throws IOException {
		Writer write = new FileWriter(fileName + ".csv", true);
		File file = new File(fileName + ".csv");
		if (!file.exists())
			write.write("AssetId,AssetName,AssetDes,AssetStatus\n");
		for (Integer key : assets.keySet()) {
			asset = assets.get(key);
			write.write(key + "," + asset.getAssetName() + "," + asset.getAssetDes() + "," + asset.getStatus() + "\n");
		}

		write.close();

	}

	public Asset getAssetById(int assetId) throws AssetException {
		if (assets.get(assetId) instanceof Asset)
			return assets.get(assetId);
		else
			throw new AssetException("Asset Does not Exist");
	}

}
