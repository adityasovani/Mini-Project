package com.cg.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.junit.Test;

import com.cg.bean.Asset;

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

		Map<Integer, Asset> assets = new HashMap<Integer, Asset>();

		if (!status.equals("allocated") && !status.equals("unallocated"))
			throw new IllegalArgumentException("status can be allocated and unallocated.");

		asset.setAssetDes(assetDes);
		asset.setAssetId(assetId);
		asset.setAssetName(assetName);
		// asset.setQuantity(quantity);
		asset.setStatus(status);

		assets.put(assetId, asset);

		return asset;
	}

	public Asset updateAsset(int assetId, String assetName, String assetDes, String status)
			throws IllegalArgumentException {

		if (!status.equals("allocated") && !status.equals("unallocated"))
			throw new IllegalArgumentException("status can be allocated and unallocated.");

		asset.setAssetId(assetId);
		asset.setAssetName(assetName);
		asset.setAssetDes(assetDes);
		// asset.setQuantity(quantity);
		asset.setStatus(status);

		assets.replace(assetId, asset);

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
		  System.out.println("AssetID\tAssetName\tAssetDes\tAssetStatus"); for (Integer
		  key1 : assets.keySet()) {
		  
		  asset = assets.get(key1); 
		   if (asset.getStatus().equals("unallocated"))
		  System.out.println(asset.getAssetId() + "\t" + asset.getAssetName() + "\t\t"
		 + asset.getAssetDes() + "\t" + asset.getStatus());
		  
		  }
		  
		
	}

	public void export(String fileName) throws IOException {
		Writer write = new FileWriter(fileName + ".csv", true);
		write.write("AssetId,AssetName,AssetDes,AssetQty,AssetType\n");
		for (Integer key : assets.keySet()) {
			asset = assets.get(key);
			write.write(key + "," + asset.getAssetName() + "," + asset.getAssetDes() + "," + asset.getStatus() + "\n");
		}

		write.close();

	}

}
