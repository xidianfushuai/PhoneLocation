package com.example.mobilelocation;

import java.util.List;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tvLocation;
	private LocationManager lm;
	private MyLocationListener listener;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvLocation = (TextView) findViewById(R.id.tv_location);
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		listener = new MyLocationListener();
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60, 50, listener);//位置提供者  最短更新时间    最短更新距离
		
	}
	class MyLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			//位置发生变化
			String j = "经度： " + location.getLongitude();
			String w = "纬度： " + location.getLatitude();
			String accuracy = "精确度： " + location.getAccuracy();
			String altitude = "海拔： " + location.getAltitude();
			tvLocation.setText(j + "\n" + w + "\n" + accuracy + "\n" + altitude);
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			//位置提供者的状态发生变化
		}

		@Override
		public void onProviderEnabled(String provider) {
			//打开GPS时
		}

		@Override
		public void onProviderDisabled(String provider) {
			//关闭GPS时
		}
		
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		lm.removeUpdates(listener);//当activity销毁时   停止更新坐标
	}
	
}
