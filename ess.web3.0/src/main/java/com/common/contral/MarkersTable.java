package com.common.contral;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hibernate.entity.DTU;
import com.hibernate.entity.PartSensor;
//import com.hibernate.entity.Sensor;
import com.hibernate.entity.Station;
import com.service.DtuService;
import com.service.StationService;
import com.unit.MessageQueue;

@Scope("singleton")
@DependsOn({"dtuService","stationService"})
@Service("markersTable")
public class MarkersTable{
	
	@Autowired
	DtuService dtuService;
	
	@Autowired
	StationService stationService;
	
	protected List<Station> listStation;
	
	/*public MarkersTableService() {
		listStation = stationService.stationlist();
		listDtu = dtuService.DTUlist();
		//this.messageQueue = (MessageQueue)MyApplicationContext.getInstance().getCtx().getBean("messageQueue");
	}*/
	@PostConstruct
	private void init(){
		listStation = stationService.stationlist();
		listDtu = dtuService.DTUlist();
	}
	
	private List<DTU> listDtu;
	/*private static MarkersTable instance;

	public static synchronized MarkersTable Instance(StationService stationService,
			DtuService dtuService) {
		while (instance == null) {
			instance = new MarkersTable(stationService, dtuService);
		}
		return instance;
	}*/
	
	/*
	@Override
	public TreeNode DtuNode(TreeNode tn) {
		//return super.DtuNode(tn);
		TreeNode treeNode;
		for(DTU dtu:listDtu){
			if(String.valueOf(dtu.getStationId()).equals(tn.getId())){
				tn.setLeaf(false);
				treeNode = new TreeNode();
				treeNode.setId(String.valueOf(dtu.getId()));
				treeNode.setText(dtu.getName());
				treeNode.setLeaf(true);
				treeNode.setLat(dtu.getDtuX());
				treeNode.setLon(dtu.getDtuY());
				treeNode.setLevel(dtu.getLevel());
				treeNode.setObjData(dtu);
				tn.getChildren().add(treeNode);
			}
		}
		return tn;
	}*/

	protected ArrayList<MapMarkers> arrMarkers;

	public ArrayList<Integer> FindIdsByPareId(ArrayList<Integer> ids,
			int currentId) {
		//listStation = stationService.stationlist();
		for (Station station : listStation) {
			if (station.getPareid() == currentId) {
				ids.add(station.getId());
				ids = FindIdsByPareId(ids, station.getId());
			}
		}
		return ids;
	}
	
	@Resource
	MessageQueue messageQueue;
	
	public synchronized ArrayList<MapMarkers> CreateMapMarkers(int pareId) {

		arrMarkers = new ArrayList<MapMarkers>();
		MapMarkers markers;
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ids.add(pareId);
		//listStation = stationService.stationlist();
		for (Station station : listStation) {
			if (station.getPareid() == pareId) {
				ids.add(station.getId());
				ids = FindIdsByPareId(ids, station.getId());
			}
		}

		/*ConcurrentHashMap<DTU, Sensor> cacheData = messageQueue.getCacheData();
		DTU dtu;
		for (Map.Entry<DTU, Sensor> e : cacheData.entrySet()) {
			dtu = e.getKey();
			for(MapMarkers mapMarkers : arrMarkers){
				if(mapMarkers.getId().equals(String.valueOf(dtu.getId()))){
					mapMarkers.setLine(true);
					break;
				}
			}
		}*/
		ConcurrentHashMap<DTU, PartSensor> cacheData = messageQueue.getCacheData();
		listDtu = dtuService.DTUlist();
		for (DTU dtu : listDtu) {
			if (ids.contains(dtu.getStationId())) {
				markers = new MapMarkers();
				markers.setId(String.valueOf(dtu.getId()));
				markers.setTitle(dtu.getName());
				markers.setLat(dtu.getDtuX());
				markers.setLon(dtu.getDtuY());
				markers.setNvrUsername(dtu.getNvrUsername());
				markers.setLine(false);
				markers.setImg("hs");
				for (Map.Entry<DTU, PartSensor> e : cacheData.entrySet()) {
					if(dtu.getId()==e.getKey().getId()){
						markers.setLine(true);
						markers.setType(e.getValue().getType());
						if(e.getValue().getType() == 0){
							markers.setImg("ls");
						}else if(e.getValue().getType() == 1){
							markers.setImg("res");
						}else if(e.getValue().getType() == 2){
							markers.setImg("hs");
						}
						break;
					}
				}
				/*markers.setLine(dtu.getIsActive());*/
				arrMarkers.add(markers);
			}
		}
		return arrMarkers;
	}

	protected ArrayList<DTU> arrDtu;
	
	public ArrayList<DTU> CreateDtus(int stationId){
		arrDtu  = new ArrayList<DTU>();
		//listDtu = dtuService.DTUlist();
		for(DTU dtu : dtuService.DTUlist()){
			if(dtu.getStationId()==stationId){
				arrDtu.add(dtu);
			}
		}
		return arrDtu;
	}
	
	/*public static void Dispose(){
		instance = null;
	}*/
}