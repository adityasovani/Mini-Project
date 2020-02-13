package com.cg.dao;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import com.cg.bean.Asset;

public class ManagerDaoImpl implements ManagerDao,Serializable {

	private static Map<Integer, Asset> asset;

	public ManagerDaoImpl() throws Exception {
		asset = Store.getInstance().getAst();
	}

	@Override
	public void viewAssets() throws Exception{
		System.out.println(asset);
		Store.getInstance().save();
	}

}
