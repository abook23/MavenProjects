package com.grp.ln.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 07 读取properties配置文件 08
 * 
 * @author using 09
 */

public class properties extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static properties instance;

	public static properties getInstance() {

		if (null != instance) {

			return instance;

		} else {

			makeInstance();

			return instance;
		}

	}

	private static synchronized void makeInstance() {

		if (instance == null) {

			instance = new properties();
		}
	}

	private properties() {

		InputStream is = getClass().getResourceAsStream("/groupline.properties");

		try {

			load(is);

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	
	public static void main(String[] args) {
		
		String physicalPath = properties.getInstance().getProperty("res.path");

		System.out.println(physicalPath);
	}

}
