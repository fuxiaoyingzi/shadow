package com.example.administrator.shadowapplication.map;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.databinding.ActivityGoogleMapBinding;
import com.example.administrator.shadowapplication.map.databinding.MapDataVBindingHandlle;

import java.util.List;

public class MapActivity extends AppCompatActivity {
    private ActivityGoogleMapBinding mapBinding;

    private BaiduMap mBaiduMap;
    private int currentMapType;
    private LocationClient locationClient;
    private BDAbstractLocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapBinding = DataBindingUtil.setContentView(this, R.layout.activity_google_map);
        //获取地图控件引用
        initView();
        initClick();
        mapBinding.setHandle(new MapDataVBindingHandlle() {
            @Override
            public void location() {
                getCurrentLocation();
            }

            @Override
            public void addMarker() {
                mBaiduMap.clear();
            }

            @Override
            public void poiSearch() {
                searchNearbyPoi();//附近搜索地铁站
            }

            @Override
            public void mapChange() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
                builder.setTitle(R.string.map_change);
                builder.setSingleChoiceItems(getResources().getStringArray(R.array.map_style), currentMapType - 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                setMapType(BaiduMap.MAP_TYPE_NORMAL);
                                break;
                            case 1:
                                setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                                break;
                            case 2:
                                //开启交通图
                                mBaiduMap.setTrafficEnabled(!mBaiduMap.isTrafficEnabled());
                                break;
                            case 3:
                                //热力图
                                mBaiduMap.setBaiduHeatMapEnabled(!mBaiduMap.isBaiduHeatMapEnabled());
                                break;
                        }
                        dialog.dismiss();
                    }
                });
                builder.show();

            }
        });
    }

    public void initView() {
        mBaiduMap = mapBinding.bmapView.getMap();
        currentMapType = BaiduMap.MAP_TYPE_NORMAL;
        mBaiduMap.setMyLocationEnabled(true);
        locationClient = new LocationClient(this);
        locationListener = new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
                //以下只列举部分获取经纬度相关（常用）的结果信息
                //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

                double latitude = bdLocation.getLatitude();    //获取纬度信息
                double longitude = bdLocation.getLongitude();    //获取经度信息
                float radius = bdLocation.getRadius();    //获取定位精度，默认值为0.0f

                String coorType = bdLocation.getCoorType();
                //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准

                int errorCode = bdLocation.getLocType();
                //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明

                String address = bdLocation.getAddrStr();

                //移动地图到当前位置
                LatLng latLng = new LatLng(latitude, longitude);
                MapStatusUpdate statusUpdate = MapStatusUpdateFactory.newLatLngZoom(latLng, 18);
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(18));
                mBaiduMap.animateMapStatus(statusUpdate);

                //在当前定位点添加marker
                addMarker(latLng);

                //显示定位图标
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(radius)
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(100).latitude(latitude)
                        .longitude(longitude)
                        .build();

                // 设置定位数据
                mBaiduMap.setMyLocationData(locData);

                // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
                BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.like);
                MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker);
                mBaiduMap.setMyLocationConfiguration(config);

                toast(address + "---" + latitude + "---" + longitude + "---" + radius + "---" + coorType + "----" + errorCode);
            }
        };
        locationClient.registerLocationListener(locationListener);
    }

    /**
     * 地图监听
     */
    public void initClick() {
        BaiduMap.OnMapClickListener onMapClickListener = new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                toast("onMapClick " + latLng.latitude + "----" + latLng.longitude);
                addMarker(latLng);

            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                toast("onMapPoiClick " + mapPoi.getName());
                return false;
            }
        };

        BaiduMap.OnMarkerClickListener onMarkerClickListener = new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String markInfo = marker.getExtraInfo().getString("markInfo");
                toast("onMarkerClick " + markInfo);
                return false;
            }
        };
        mBaiduMap.setOnMapClickListener(onMapClickListener);
        mBaiduMap.setOnMarkerClickListener(onMarkerClickListener);
    }

    public void getCurrentLocation() {
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，设置定位模式，默认高精度
        //LocationMode.Hight_Accuracy：高精度；
        //LocationMode. Battery_Saving：低功耗；
        //LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
        //可选，设置返回经纬度坐标类型，默认gcj02
        //gcj02：国测局坐标；
        //bd09ll：百度经纬度坐标；
        //bd09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标

        option.setScanSpan(1000);
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5 * 60 * 1000);
        //可选，7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位

        option.setEnableSimulateGps(false);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        option.setIsNeedAddress(true);
        //表示需要获得详细地址信息

        locationClient.setLocOption(option);
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        locationClient.start();
        //调用LocationClient的start()方法，便可发起定位请求
    }

    /**
     * 切换地图类型
     *
     * @param mapType
     */
    public void setMapType(int mapType) {
        if (currentMapType != mapType) {
            mBaiduMap.setMapType(mapType);
            currentMapType = mapType;
        }
    }

    /**
     * 添加marker点
     *
     * @param latLng
     */
    public void addMarker(LatLng latLng) {
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.ic_marke);
        Bundle bundle = new Bundle();
        bundle.putString("markInfo", "hello shadow" + latLng.latitude);
        OverlayOptions options = new MarkerOptions()
                .anchor(0.5f, 1f)
                .position(latLng)
                .icon(bitmapDescriptor)
                .extraInfo(bundle);
        mBaiduMap.addOverlay(options);
    }


    public void searchNearbyPoi() {
        PoiSearch poiSearch = PoiSearch.newInstance();
        OnGetPoiSearchResultListener poiSearchResultListener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult result) {
                //获取POI检索结果
                if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
                    return;
                }

                if (result.error == SearchResult.ERRORNO.NO_ERROR) {
                    mBaiduMap.clear();
                    List<PoiInfo> poiInfoList = result.getAllPoi();
                    for (PoiInfo poiInfo : poiInfoList) {
                        addMarker(poiInfo.location);
                    }
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
                //获取Place详情页检索结果
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        };
        PoiNearbySearchOption searchOption = new PoiNearbySearchOption().keyword("餐厅")
                .sortType(PoiSortType.distance_from_near_to_far)
                .radius(2000)
                .pageNum(10)
                .location(mBaiduMap.getMapStatus().target);//MapStatus中target为地图中心点坐标
        poiSearch.setOnGetPoiSearchResultListener(poiSearchResultListener);
        poiSearch.searchNearby(searchOption);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mBaiduMap.setMyLocationEnabled(false);
        locationClient.stop();
        mapBinding.bmapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapBinding.bmapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapBinding.bmapView.onPause();
    }

    public void toast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
}
