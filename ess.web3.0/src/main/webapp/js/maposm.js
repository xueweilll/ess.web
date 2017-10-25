var currentLat, currentLon, currentLevel;
//关键代码在这里了 
OpenLayers.Control.Click = OpenLayers.Class(OpenLayers.Control, {
	defaultHandlerOptions : {
		'single' : true,
		'double' : false,
		'pixelTolerance' : 0,
		'stopSingle' : false,
		'stopDouble' : false
	},

	initialize : function(options) {
		this.handlerOptions = OpenLayers.Util.extend({},
				this.defaultHandlerOptions);
		OpenLayers.Control.prototype.initialize.apply(this, arguments);
		this.handler = new OpenLayers.Handler.Click(this, {
			'click' : this.trigger
		//'dblclick': this.trigger
		}, this.handlerOptions);
	},

	trigger : function(e) {
		var lonlat = map.getLonLatFromViewPortPx(e.xy).transform('EPSG:3857',
				'EPSG:4326');
		currentLat = lonlat.lat;
		currentLon = lonlat.lon;
		currentLevel = map.getZoom();
		BindXY(currentLat,currentLon);
		/*if (Ext.getCmp("stationy") != null) {
			Ext.getCmp("stationy").setValue(currentLat);
			Ext.getCmp("stationx").setValue(currentLon);
		}
		if (Ext.getCmp("stationy") != null) {
			Ext.getCmp("dtuY").setValue(currentLat);
			Ext.getCmp("dtuX").setValue(currentLon);
		}*/
		//                    alert("You clicked near " + lonlat.lat + " N, " +
		//                                              + lonlat.lon + " E");
		//$("#stationY").val(lonlat.lat);//经度
		//$("#stationX").val(lonlat.lon);//纬度
		//alert(map.getZoom());
		//$("#level").val(map.getZoom());
	}

});
var map,layer;
//var layer;
var markers;
var zoom = 2;//0-17;
function CreateMap() {
	/*map = new OpenLayers.Map('mapview', {
		projection : 'EPSG:3857',
		layers : [ new OpenLayers.Layer.Google("Google Streets", // the default
		{
			numZoomLevels : 20
		}), new OpenLayers.Layer.Google("Google Physical", {
			type : google.maps.MapTypeId.TERRAIN
		}), new OpenLayers.Layer.Google("Google Hybrid", {
			type : google.maps.MapTypeId.HYBRID,
			numZoomLevels : 20
		}), new OpenLayers.Layer.Google("Google Satellite", {
			type : google.maps.MapTypeId.SATELLITE,
			numZoomLevels : 22
		}) ],
		center : new OpenLayers.LonLat(10.2, 48.9).transform('EPSG:4326',
				'EPSG:3857'),
		zoom : zoom
	});*/
	/*map = new OpenLayers.Map("mapview", {  
        maxExtent : new OpenLayers.Bounds(-20037508.3427892,  
        -20037508.3427892, 20037508.3427892, 20037508.3427892),  
		//maxExtent : new OpenLayers.Bounds(119.667110,  
        //31.334278, 120.197161, 32.062260),  
        numZoomLevels : 18,  
        maxResolution : 156543.0339,
		//maxResolution : auto,  
        units : 'm',  
        projection : "EPSG:900913",  
        displayProjection : new OpenLayers.Projection("EPSG:4326")  
    });  */
	
	
	
    layer = new OpenLayers.Layer.TMS("Name",  
            "http://192.168.1.26:8079/china/changzhou/", {  
                'type' : 'png',  
                'getURL' : get_my_url  
            });  

    map.addLayer(layer);  

    map.addControl(new OpenLayers.Control.Scale());  
    map.addControl(new OpenLayers.Control.MousePosition());  
    map.addControl(new OpenLayers.Control.LayerSwitcher());  

    var lonLat = new OpenLayers.LonLat(119.94892, 31.81328);  
    lonLat.transform(map.displayProjection, map.getProjectionObject());  
    map.setCenter(lonLat, 12);
    
	map.addControl(new OpenLayers.Control.LayerSwitcher());
	// add behavior to html
	for (var i = map.layers.length - 1; i >= 0; --i) {
		map.layers[i].animationEnabled = true;
	}
	var click = new OpenLayers.Control.Click();
	map.addControl(click);
	click.activate();
}

function get_my_url(bounds) {  
    var res = this.map.getResolution();  
    var x = Math.round((bounds.left - this.maxExtent.left)  
            / (res * this.tileSize.w));  
    var y = Math.round((this.maxExtent.top - bounds.top)  
            / (res * this.tileSize.h));  
    var z = this.map.getZoom();  
    //alert(z);
    //var path = "" + z + "/" + x + "/gmcn_" + x + "_" + y + "_" + z + "." + this.type;  
	var path = "" + z + "/" + x + "/" + y + "." + this.type;  
	//alert(path);
    var url = this.url;  
    if (url instanceof Array) {
        url = this.selectUrl(path, url);
    }  
	//alert(url+path);
    return url + path;  

}  

/*
 function initbak() {
 // 使用指定的文档元素创建地图
 //var map = new OpenLayers.Map("rcp1_map");
 // 创建一个 OpenStreeMap raster layer
 // 把这个图层添加到map中
 //var osm = new OpenLayers.Layer.OSM();
 //map.addLayer(osm);
 // 设定视图缩放地图程度为最大
 //map.zoomToMaxExtent();
 //map.zoomToExtent(newOpenLayers.Bounds(-3.922119,44.335327,4.866943,49.553833));
 //var lon=5;
 //var lat=40;
 //var zoom=5;
 //markers = new OpenLayers.Layer.Markers("Markers");
 //zoom=$("#level").val();
 //alert(zoom);
 map=new OpenLayers.Map("rcp1_map");
 map.numZoomLevels=null;
 //layer = new OpenLayers.Layer.WMS("OpenLayers WMS","http://labs.metacarta.com/wms/vmap0", {layers: 'basic'});
 //layer = new OpenLayers.Layer.OSM();
 //layer = new OpenLayers.Layer.WMS( "OpenLayers WMS", "http://labs.metacarta.com/wms/vmap0", {layers: 'basic'} );
 //map.addLayer(layer);
 //map.zooToExtent(new OpenLayers.Bounds(-3.922119,44.335327,4.866943,49.553833));
 //map.addLayer(new OpenLayers.Layer.GML("GML","gml/polygon.xml"));
 //var popup = new OpenLayers.Popup("chicken",new OpenLayers.LonLat(5,40),new OpenLayers.Size(200,200),"example popup",true);
 //map.addPopup(popup);

 //map = new OpenLayers.Map('map');
 map.addControl(new OpenLayers.Control.LayerSwitcher());
 var gmap = new OpenLayers.Layer.Google(
 "Google Streets", // the default
 {numZoomLevels: 18}
 );
 var gphy = new OpenLayers.Layer.Google(
 "Google Physical",
 {type: G_PHYSICAL_MAP}
 );
 var ghyb = new OpenLayers.Layer.Google(
 "Google Hybrid",
 {type: G_HYBRID_MAP, numZoomLevels: 18}
 );
 var gsat = new OpenLayers.Layer.Google(
 "Google Satellite",
 {type: G_SATELLITE_MAP, numZoomLevels: 18}
 );
 map.addLayers([gmap, gphy, ghyb, gsat]);

 map.setCenter(new OpenLayers.LonLat(0,0), zoom);

 //map.setCenter(new OpenLayers.LonLat(0, 0), 0);
 //var newl = new OpenLayers.Layer.Text( "text", { location:"./textfile.txt"} );
 //map.addLayer(newl);

 //map.addLayer(markers);  


 //BindMarkers();
 //markers.addMarker(new OpenLayers.Marker(new OpenLayers.LonLat(0,0),icon.clone()));

 //map.zoomToMaxExtent();
 var click = new OpenLayers.Control.Click();
 map.addControl(click);
 click.activate();
 //BindMarkers();				
 }
 */

function BindMarker(lat, lon, level, src, id, callbackFunc) {

	var size = new OpenLayers.Size(21, 25);
	var offset = new OpenLayers.Pixel(-(size.w / 2), -size.h);
	var icon = new OpenLayers.Icon('js/olapi/img/' + src, size, offset);
	map.setCenter(new OpenLayers.LonLat(lat, lon).transform('EPSG:4326',
			'EPSG:3857'), level);
	if (markers != null) {
		map.removeLayer(markers);
	}
	markers = new OpenLayers.Layer.Markers("Markers");
	map.addLayer(markers);
	var marker = new OpenLayers.Marker(new OpenLayers.LonLat(lat, lon)
			.transform('EPSG:4326', 'EPSG:3857'), icon.clone());
	marker.text = "aaaa";
	markers.addMarker(marker);

	marker.events.register('click', marker, function(evt) {
		//alert('aa');
		callbackFunc(id);
	});
}

function SetCenter(lat, lon, level) {
	currentLevel = level;
	map.setCenter(new OpenLayers.LonLat(lat, lon).transform('EPSG:4326',
			'EPSG:3857'), level);
}

function BindMarkers(title, lat, lon, src, id, callbackFunc) {
	var stuts = "";
	if (src.indexOf("green") != -1) {
		stuts = "在线";
	} else {
		stuts = "离线";
	}
	var size = new OpenLayers.Size(21, 25);
	var offset = new OpenLayers.Pixel(-(size.w / 2), -size.h);
	var icon = new OpenLayers.Icon('js/olapi/img/' + src, size, offset);
	//map.setCenter(new OpenLayers.LonLat(lat,lon), level);

	var marker = new OpenLayers.Marker(new OpenLayers.LonLat(lat, lon)
			.transform('EPSG:4326', 'EPSG:3857'), icon.clone());
	//marker.title="aaaa";
	markers.addMarker(marker);

	marker.events.register('click', marker, function(evt) {
		panel.hide();
		callbackFunc(id);
	});
	var panel;
	marker.events.register('mouseover', marker, function(evt) {
		panel = Ext.create('Ext.window.Window', {
			title : '当前设备',
			height : 100,
			width : 200,
			layout : 'fit',
			html : '<p>设备名称：' + title + '</p><p>当前状态：' + stuts + '</p>'
		});
		panel.show();
	});

	marker.events.register('mouseout', marker, function(evt) {
		panel.hide();
	});
}

//function BindMarkers(){
//	map.setCenter(new OpenLayers.LonLat(119.973991394043,31.811389923096), 8);
//	var size = new OpenLayers.Size(21,25);
//	var offset = new OpenLayers.Pixel(-(size.w/2), -size.h); 
//	var icon = new OpenLayers.Icon('js/olapi/img/marker.png', size, offset); 
//	for(var i=0;i<2;i++){
//		markers.addMarker(new OpenLayers.Marker(new OpenLayers.LonLat(119.97+i,31.81+i),icon.clone())); 
//	}
//}