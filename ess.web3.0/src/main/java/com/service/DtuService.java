package com.service;

import java.util.List;

import com.hibernate.entity.DTU;
import com.hibernate.entity.LMU;

public interface DtuService {
	public List<DTU> DTUlist();
	public List<DTU> DTUlistByStationId(int stationId);
	public List<DTU> DTUlistByStationIds(String stationIds);
 	public DTU dtu(int id);
 	public DTU dtu(String code);
 	public void saveDTU(DTU dtu);
 	public void updateDTU(DTU dtu);
 	public void deleteDTU(int id);
 	public void DeleteDTUAndLMUS(int dtuId);
 	public void SaveDTUAndLMUS(DTU dtu,List<LMU> listLmu);
 	public void UpdateDTUAndLMUS(DTU dtu,List<LMU> listLmu);
	public int findIndexCode(String id);
}
