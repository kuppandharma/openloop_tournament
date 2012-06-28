package in.openloop.data;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkData  {

	private ConnectivityManager mConnectivityMgr;
	
	public NetworkData(ConnectivityManager cm){
		mConnectivityMgr = cm;
	}
	
	
	
	public boolean isNetworkConnected(){
	    NetworkInfo info = mConnectivityMgr.getActiveNetworkInfo();
	    if(info != null  && info.isConnected()){
	    	return true;

	    }else{
	    	return false;
	    }
	    
	    }
}
