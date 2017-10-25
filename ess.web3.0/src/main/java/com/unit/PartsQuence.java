package com.unit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service("partsQuence")
public class PartsQuence {
	private ConcurrentHashMap<Integer, Map<String, Object>> partsData;
	
	@PostConstruct
	public void init(){
		partsData = new ConcurrentHashMap<>();
	}
	public void put(Integer key,Map<String, Object> map){
		partsData.put(key, map);
	}
	
	public void remove(int key){
		if (partsData.containsKey(key)) {
			partsData.remove(key);
		}
	}
	
	public Map<String, Object> get(int key){
		return partsData.get(key);
	}
	
	public ConcurrentHashMap<Integer, Map<String, Object>> getPartsData() {
		return partsData;
	}
	
}
