package com.example.administrator.shadowapplication.map;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.crash_log.LogUtil;
import com.example.administrator.shadowapplication.crash_log.ToastUtil;
import com.example.administrator.shadowapplication.databinding.ActivityAndroidLocationBinding;
import com.example.administrator.shadowapplication.map.databinding.LocationModel;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * GPS，精度高，速度慢，耗电对，而且可能因为天气原因或者障碍物而无法获取卫星信息，另外设备可能没有GPS模块（比如说一些平板）
 * 通过网络获取定位信息，精度低，网络通畅时速度快，耗电少，不依赖GPS模块。
 */
public class AndroidLocationActivity extends AppCompatActivity {

    private ActivityAndroidLocationBinding mLocationBinding;
    private LocationManager locationManager;
    private String provider = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationBinding = DataBindingUtil.setContentView(AndroidLocationActivity.this, R.layout.activity_android_location);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //获取定位服务
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager == null)
            return;
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        }

        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            provider = LocationManager.NETWORK_PROVIDER;
        }

        if (provider != null) {
            locationManager.requestLocationUpdates(provider, 10 * 1000, 10, requestLocationUpdates);
        } else {
            //无法定位：1、提示用户打开定位服务；2、跳转到设置界面
            ToastUtil.showMsg("无法定位，请打开定位服务");
            Intent i = new Intent();
            i.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(i);
        }
    }

    LocationListener requestLocationUpdates = new LocationListener() {
        //当坐标改变时触发此函数
        @Override
        public void onLocationChanged(Location location) {
            //得到纬度
            double latitude = location.getLatitude();
            //得到经度
            double longitude = location.getLongitude();
            //Geocoder经纬度解码者可用于将经纬度转为具体位置信息：国家，城市，街道名称等
            Geocoder geocoder = new Geocoder(AndroidLocationActivity.this, Locale.getDefault());
            List<Address> locationList = null;
            try {
                locationList = geocoder.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address = locationList.get(0);
            StringBuffer builder = new StringBuffer();
            builder.append(address.getCountryName()); //得到国家名称，比方：中国
            builder.append(address.getLocality()); //得到城市名称，比方：北京市
            for (int i = 0; address.getAddressLine(i) != null; i++) {
                builder.append(address.getAddressLine(i));//得到周边信息。包含街道等。i=0，得到街道名称
            }

            LocationModel locationModel = new LocationModel(latitude, longitude, builder.toString());
            mLocationBinding.setModel(locationModel);
        }

        // Provider的在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            LogUtil.d("hh", "LocationListener onStatusChanged()");
        }

        // //  Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String s) {
            LogUtil.d("hh", "LocationListener onProviderEnabled()");
        }

        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String s) {
            LogUtil.d("hh", "LocationListener onProviderDisabled()");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(requestLocationUpdates);
    }
}
