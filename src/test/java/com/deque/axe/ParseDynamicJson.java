package com.deque.axe;
 
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class ParseDynamicJson {

	// How to parse dynamic JSON?
	// How to parse nested JSON?

	public static void parseObject(JSONObject json, String key) {
		//System.out.println(json.has(key));
		System.out.println(json.get(key));
	}

	public static void getKey(JSONObject json, String key) {

		boolean exists = json.has(key);
		Iterator<?> keys;
		String nextKeys;

		if (!exists) {
			keys = json.keys();
			while (keys.hasNext()) {
				nextKeys = (String) keys.next();
				try {

					if (json.get(nextKeys) instanceof JSONObject) {

						if (exists == false) {
							getKey(json.getJSONObject(nextKeys), key);
						}

					} else if (json.get(nextKeys) instanceof JSONArray) {
						JSONArray jsonarray = json.getJSONArray(nextKeys);
						for (int i = 0; i < jsonarray.length(); i++) {
							String jsonarrayString = jsonarray.get(i).toString();
							JSONObject innerJSOn = new JSONObject(jsonarrayString);

							if (exists == false) {
								getKey(innerJSOn, key);
							}

						}

					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			}

		} else {
			parseObject(json, key);
		}

	}

	public static void main(String[] args) {


		String exampleRequest = null;
		try {
			exampleRequest = FileUtils.readFileToString(new File("./testAccessibility.json"), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject inputJSONOBject = new JSONObject(exampleRequest);
		
		
		getKey(inputJSONOBject, "id");
		getKey(inputJSONOBject, "message");
		getKey(inputJSONOBject, "impact");
		
	}
	
	public static void getData() {
		
		String exampleRequest = null;
		try {
			exampleRequest = FileUtils.readFileToString(new File("./testAccessibility.json"), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject inputJSONOBject = new JSONObject(exampleRequest);
		
		
		getKey(inputJSONOBject, "id");
		getKey(inputJSONOBject, "message");
		getKey(inputJSONOBject, "impact");
		
	}

}


