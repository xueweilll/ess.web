package com.action.system;

import org.springframework.beans.factory.annotation.Autowired;

import com.action.ActionBase;
import com.hibernate.entity.Station;
import com.service.StationService;

public class StationAction extends ActionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ҳ��action
	 */
	public String execute() {
		return "success";
	}

	/**
	 * asyn method
	 */
	private boolean result;

	public boolean getResult() {
		return result;
	}

	@Autowired
	private StationService stationService;
/*
	public void setStationService(StationService stationService) {
		this.stationService = stationService;
	}*/

	private Station station;
	private int status;
	private int id;
	private String stationname;
	private String stationaddress;
	private double stationx;
	private double stationy;
	private int pareid;
	private int level;

	public void setStatus(int status) {
		this.status = status;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	public void setStationaddress(String stationaddress) {
		this.stationaddress = stationaddress;
	}

	public void setStationx(double stationx) {
		this.stationx = stationx;
	}

	public void setStationy(double stationy) {
		this.stationy = stationy;
	}

	public void setPareid(int pareid) {
		this.pareid = pareid;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String StationEdit() {
		result = false;
		if (status == 0) {
			try {
				station = stationService.station(id);
				stationService.deletestation(id);
				SaveSysLogByUser(1,
						this.getText("PUBLIC_DL") + this.getText("AREA_T")
								+ ":" + station.getStationname());
				result = true;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {

			station = new Station();
			station.setId(id);
			station.setLevel(level);
			station.setPareid(pareid);
			station.setStationaddress(stationaddress);
			// station.setStationmap(stationmap);
			station.setStationname(stationname);
			station.setStationx(stationx);
			station.setStationy(stationy);
			if (status == 1) {
				try {
					stationService.savestation(station);
					SaveSysLogByUser(1,
							this.getText("PUBLIC_IS") + this.getText("AREA_T")
									+ ":" + station.getStationname());
					result = true;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else if (status == 2) {
				try {
					stationService.updatestation(station);
					SaveSysLogByUser(1,
							this.getText("PUBLIC_UP") + this.getText("AREA_T")
									+ ":" + station.getStationname());
					result = true;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return "success";
	}
}
