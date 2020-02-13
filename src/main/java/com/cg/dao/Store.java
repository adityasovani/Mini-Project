package com.cg.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.cg.bean.Asset;
import com.cg.bean.User;

public class Store implements Serializable {

	private Map<Integer, Asset> ast;
	private Map<Integer, User> users;

	private static Store store;
	public static final String STORE_PATH = "asset.dat";

	private Store() {
		this.ast = new HashMap<Integer, Asset>();
		this.users = new HashMap<Integer, User>();
	}

	public static Store getInstance() throws Exception // singleton

	{
		//Store st = new Store();
		if (null == store) {
			File file = new File(STORE_PATH);
			if (file.exists()) {
				// FileInputStream file=new FileInputStream(STORE_PATH);
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STORE_PATH));
				Object obj = ois.readObject();
				if (obj instanceof Store) {
					store = (Store) obj;
				} else {
					store = new Store();
				}

			}
		}
		return store;
	}

	public void save() throws IOException {
		// write store into a object output stream.

		// FileInputStream out= new FileInputStream("test.txt");
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STORE_PATH));
		oos.writeObject(store);
		oos.flush();
		oos.close();
	}

	public Map<Integer, Asset> getAst() {
		return ast;
	}

	public void setAst(Map<Integer, Asset> ast) {
		this.ast = ast;
	}

	public Map<Integer, User> getUsers() {
		return users;
	}

	public void setUsers(Map<Integer, User> users) {
		this.users = users;
	}

	public static Store getStore() {
		return store;
	}

	public static void setStore(Store st) {
		store = st;
	}

	public static String getStorePath() {
		return STORE_PATH;
	}

}
