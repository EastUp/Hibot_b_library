package com.ivsign.android.IDCReader;


import com.xg.east.hibot_b_library.id_card_discern.AppConfig;

public class IDCReaderSDK {
	
	private static final String TAG = "unpack";

	public IDCReaderSDK()
	{
		//if( 0==wltInit("") )
        	//Log.i(TAG,  "wltInit success");
	}
	public static int Init()
	{
		return wltInit(AppConfig.RootFile);
	}
	public static int unpack(byte[] wltdata, byte[] licdata)
	{
		return wltGetBMP(wltdata, licdata);
	}

	// native functin interface
    public static native int wltInit(String workPath);
    
    public static native int wltGetBMP(byte[] wltdata, byte[] licdata);
    
    /* this is used to load the 'wltdecode' library on application
     */
    static {
        System.loadLibrary("wltdecode");
    }
}
