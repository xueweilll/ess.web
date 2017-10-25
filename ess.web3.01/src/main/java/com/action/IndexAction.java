package com.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

//import com.qf.contral.CurrHrItem;
import com.common.contral.CurrHrItem;
import com.common.contral.CurrIrItem;
import com.common.contral.CurrStore;
import com.common.contral.MapMarkers;
import com.common.contral.MarkersTable;
import com.common.contral.StationTree;
import com.common.contral.TreeNode;
import com.hibernate.entity.DTU;
import com.hibernate.entity.LMU;
import com.hibernate.entity.Menu;
import com.hibernate.entity.Rmlink;
import com.service.CurrentIrDataService;
import com.service.HisHrDataService;
import com.service.LmuService;

public class IndexAction extends ActionBase {
	/**
	 * Page
	 */
	private static final long serialVersionUID = 1L;

	public String execute() {
		return "success";
	}

	public String map() {
		return "success";
	}
	
	private int reloadValue;

	/**
	 * Asyn
	 */
	// private String stationId;

	// public void setStationId(String stationId) {
	// this.stationId = stationId;
	// }
	
	/*private MenuService menuService;

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}*/
	
	private List<Menu> listMenu;

	public List<Menu> getListMenu() {
		return listMenu;
	}

	public String MenuTree(){
		@SuppressWarnings("unchecked")
		List<Rmlink> listRmlink= (List<Rmlink>)session.get("rmlink");
		listMenu = new ArrayList<>();
		for(Rmlink rm : listRmlink){
			listMenu.add(rm.getMenu());
		}
		return "success";
	}

	

	/*public void setStationService(StationService stationService) {
		this.stationService = stationService;
	}*/

	

	/*public void setDtuService(DtuService dtuService) {
		this.dtuService = dtuService;
	}*/

	private ArrayList<TreeNode> arrList;

	@JSON(name = "children")
	public ArrayList<TreeNode> getArrList() {
		return arrList;
	}

	@Resource
	private StationTree stationTree;
	@Resource
	private CurrStore currStore;
	
	public String StationTree() {
		//stationTree = new StationTree(stationService);
		arrList = stationTree.CreateStationTree(reloadValue);
		// jsonob = JSONArray.fromObject(arrList);
		// jsonString = jsonob.toString();
		// this.setJsonString("{children:"+JSONArray.fromObject(arrList).toString()+"}");
		/*
		 * HttpServletResponse response=ServletActionContext.getResponse();
		 * response.setContentType("text/html;charset=gb2312"); PrintStream out
		 * = new PrintStream(response.getOutputStream());
		 * out.println(this.jsonString);
		 */
		return "success";
	}

	private ArrayList<DTU> dtuList;

	public ArrayList<DTU> getDtuList() {
		return dtuList;
	}

	@Resource
	private MarkersTable markersTable;

	public String CreateDtus() {
		//markersTable = new MarkersTable(stationService, dtuService);
		dtuList = markersTable.CreateDtus(pareId);
		return "success";
	}

	public ArrayList<MapMarkers> arrMarkers;

	@JSON(name = "markers")
	public ArrayList<MapMarkers> getArrMarkers() {
		return arrMarkers;
	}

	private int pareId;

	public void setPareId(int pareId) {
		this.pareId = pareId;
	}

	/*@Autowired
	private MessageQueue messageQueue;*/
	
	public String MapMarkers() {
		// System.out.println(pareId);
		//markersTable = new MarkersTable(stationService, dtuService);
		arrMarkers = markersTable.CreateMapMarkers(pareId);
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
		return "success";
	}

	private List<LMU> lmuList;

	public List<LMU> getLmuList() {
		return lmuList;
	}

	/*
	 * private List<CurrIrItem> currIrData;
	 * 
	 * private List<CurrHrItem> currHrData;
	 * 
	 * public List<CurrIrItem> getCurrIrData() { return currIrData; }
	 * 
	 * public List<CurrHrItem> getCurrHrData() { return currHrData; }
	 */

	public void CreateLmus() {
		//markersTable = new MarkersTable(stationService, dtuService);
		arrMarkers = markersTable.CreateMapMarkers(pareId);
		String dtuIdsStr = "";
		for (MapMarkers mapMarker : arrMarkers) {
			dtuIdsStr += mapMarker.getId() + ",";
		}
		if (dtuIdsStr == "") {
			return;
		}
		String[] dtuIdsStrs = dtuIdsStr.split(",");
		Integer[] dtuIdsInt = new Integer[dtuIdsStrs.length];
		int i = 0;
		for (String dtuId : dtuIdsStrs) {
			dtuIdsInt[i] = Integer.parseInt(dtuId);
			i++;
		}
		lmuList = lmuService.LMUlistbyDtuId(dtuIdsInt);
	}

	public String CreateLmusInfo() {
		CreateLmus();
		if (lmuList != null) {
			for (LMU lmu : lmuList) {
				lmu.setName(lmu.getDtu().getName() + "-" + lmu.getName());
			}
		}
		return "success";
	}

	/*
	 * public String CreateLmusIRHRData() { CreateLmus(); currIrData = new
	 * ArrayList<>(); CurrIrItem curriritem; currHrData = new ArrayList<>();
	 * CurrHrItem currhritem; for (LMU lmu : lmuList) { curriritem = new
	 * CurrIrItem(); curriritem.setName(lmu.getDtu().getName() + "-" +
	 * lmu.getName()); currIrData.add(curriritem); currhritem = new
	 * CurrHrItem(); currhritem.setName(lmu.getDtu().getName() + "-" +
	 * lmu.getName()); currHrData.add(currhritem); } return "success"; }
	 */

	private int dtuId;

	public void setDtuId(int dtuId) {
		this.dtuId = dtuId;
	}

	ArrayList<CurrIrItem> arrIrStore;

	public ArrayList<CurrIrItem> getArrIrStore() {
		return arrIrStore;
	}

	ArrayList<CurrHrItem> arrHrStore;

	public ArrayList<CurrHrItem> getArrHrStore() {
		return arrHrStore;
	}
	

	public int getReloadValue() {
		return reloadValue;
	}

	public void setReloadValue(int reloadValue) {
		this.reloadValue = reloadValue;
	}

	@Autowired
	private CurrentIrDataService currentIrDataService;
	@Autowired
	private HisHrDataService hisHrDataService;
	@Autowired
	private LmuService lmuService;

	/*public void setCurrentIrDataService(
			CurrentIrDataService currentIrDataService) {
		this.currentIrDataService = currentIrDataService;
	}

	public void setLmuService(LmuService lmuService) {
		this.lmuService = lmuService;
	}

	public void setHisHrDataService(HisHrDataService hisHrDataService) {
		this.hisHrDataService = hisHrDataService;
	}*/

	private Integer[] DtuIdsInt() {
		//markersTable = new MarkersTable(stationService, dtuService);
		arrMarkers = markersTable.CreateMapMarkers(pareId);
		String dtuIdsStr = "";
		for (MapMarkers mapMarker : arrMarkers) {
			dtuIdsStr += mapMarker.getId() + ",";
		}
		if (dtuIdsStr == "") {
			return new Integer[]{};
		}
		String[] dtuIdsStrs = dtuIdsStr.split(",");
		Integer[] dtuIdsInt = new Integer[dtuIdsStrs.length];
		int i = 0;
		for (String dtuId : dtuIdsStrs) {
			dtuIdsInt[i] = Integer.parseInt(dtuId);
			i++;
		}
		return dtuIdsInt;
	}

	public String CurrentIrStoreByDtus() {
		// System.out.println("------------------------------------------------------------------------------------------Ir");
		arrIrStore = currStore.CreateCurrIrData(DtuIdsInt());
		return "success";
	}

	public String CurrentHrStoreByDtus() {
		// System.out.println("------------------------------------------------------------------------------------------Hr");
		arrHrStore = currStore.CreateCurrHrData(DtuIdsInt());
		return "success";
	}

	public String CurrentIrStore() {
		arrIrStore = currStore.CreateCurrIrData(dtuId);
		return "success";
	}
	
	
}
