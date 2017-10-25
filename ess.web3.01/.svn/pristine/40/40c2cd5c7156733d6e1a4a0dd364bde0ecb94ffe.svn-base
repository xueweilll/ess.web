package com.common.contral;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hibernate.entity.Station;
import com.service.StationService;

@Scope("singleton")
@DependsOn({"stationService"})
@Service("stationTree")
public class StationTree {
	private ArrayList<TreeNode> arrList;
	protected List<Station> listStation;
	
	@Autowired
	StationService stationService;
	
	/*public StationTree(){
		listStation = stationService.stationlist();
	}*/
	
	@PostConstruct
	private void init(){
		listStation = stationService.stationlist();
	}

	/*private static StationTree instance;
	public static synchronized StationTree Instance(StationService stationService){
		while(instance==null){
			instance = new StationTree(stationService);
		}
		return instance;
	}*/

	public ArrayList<TreeNode> CreateStationTree(int reloadValue) {
		
		arrList = new ArrayList<TreeNode>();
		TreeNode treeNode;
		//listStation = stationService.stationlist();
		for (Station station : listStation) {
			if (station.getPareid() == 0) {
				treeNode = new TreeNode();
				treeNode.setId(String.valueOf(station.getId()));
				treeNode.setText(station.getStationname());
				treeNode.setLeaf(true);
				treeNode.setLat(station.getStationx());
				treeNode.setLon(station.getStationy());
				treeNode.setLevel(station.getLevel());
				treeNode.setObjData(station);
				treeNode.setExpanded(true);
				//treeNode=DtuNode(treeNode);
				// arrList.add(treeNode);
				arrList.add(StationNode(treeNode,reloadValue));
			}
		}
		return arrList;
	}
	/*
	public TreeNode DtuNode(TreeNode tn){
		return tn;
	}*/
	
	private TreeNode StationNode(TreeNode tn,int reloadValue) {
		TreeNode treeNode;
		listStation = stationService.stationlist();
		for (Station station : listStation) {
			if (String.valueOf(station.getPareid()).equals(tn.getId())) {
				tn.setLeaf(false);
				treeNode = new TreeNode();
				treeNode.setId(String.valueOf(station.getId()));
				treeNode.setText(station.getStationname());
				treeNode.setLeaf(true);
				treeNode.setLat(station.getStationx());
				treeNode.setLon(station.getStationy());
				treeNode.setLevel(station.getLevel());
				treeNode.setObjData(station);
				//treeNode=DtuNode(treeNode);
				if(reloadValue == station.getId()){
					tn.setExpanded(true);
				}
				tn.getChildren().add(StationNode(treeNode,reloadValue));
			}
		}
		return tn;
	}
	
	/*public static void Dispose(){
		instance=null;
	}*/
}
