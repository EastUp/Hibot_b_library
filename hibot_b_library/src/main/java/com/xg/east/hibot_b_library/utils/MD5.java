package com.xg.east.hibot_b_library.utils;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {  
	
	private static final String TAG = "MD5";
	
    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
'A', 'B', 'C', 'D', 'E', 'F' };  
  
    public static String toHexString(byte[] b) {  
        StringBuilder sb = new StringBuilder(b.length * 2);  
        for (int i = 0; i < b.length; i++) {  
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);  
            sb.append(HEX_DIGITS[b[i] & 0x0f]);  
        }  
        return sb.toString();  
    }  
  
    /**  
     * �����ַ�����md5У��ֵ  
     *   
     * @param s  
     * @return  
     */   
    public static String getMD5String(byte[] buffer) throws NoSuchAlgorithmException {  
        MessageDigest md5;  
        md5 = MessageDigest.getInstance("MD5");  
        md5.update(buffer); 
        return toHexString(md5.digest());     
    }  
    
    /**  
     * �ж��ַ�����md5У�����Ƿ���һ����֪��md5����ƥ��  
     *   
     * @param buffer    ҪУ���byte����
     * @param md5PwdStr ��֪��md5У����  
     * @return  
     */   
    public static boolean checkMD5(byte[] buffer, String md5PwdStr) {  
        String s = null;
		try {
			s = getMD5String(buffer);
			Log.i(TAG,s+"  ");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}  
        return  s.equals(md5PwdStr);  
    }  
}  