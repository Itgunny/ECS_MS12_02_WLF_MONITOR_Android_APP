package taeha.wheelloader.fseries_monitor.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.acra.collector.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;

import android.os.PowerManager;
import android.util.Log;

public class ErrorReportSender implements ReportSender {
	public final static String TAG			= "ErrorReportSender";
	public final static String ROOT_PATH	= "/mnt/sdcard/alarms/";
	public final static String FILE_NAME	= "WheelLoader_Log"; 
	public final static String FILE_EXT		= ".txt";
	@Override
	public void send(CrashReportData Err) throws ReportSenderException {
		// TODO Auto-generated method stub
		Log.e(TAG, "Error");
		int LastIndex = CheckFileIndex();
		LastIndex++;
		WiteErrorLog(LastIndex,Err);
	}
	public void WiteErrorLog(int Index, CrashReportData Err){
		String strFileName;
		FileOutputStream fos = null;
		strFileName = FILE_NAME + Integer.toString(Index) + FILE_EXT;
		File file = new File(ROOT_PATH,strFileName);
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Log.e(TAG,"IOException WiteErrorLog");
		}
		try {
			fos = new FileOutputStream(file,true); //true 계속 추가 여부

		} catch (FileNotFoundException e) {
			// TODO: handle exception
			Log.e(TAG,"FileNotFoundException WiteErrorLog");
		}
		
		
		
		String strErr = Err.values().toString();
		byte[] cErr = new byte[strErr.length()];
		cErr = strErr.getBytes();
		try {
			fos.write(cErr);
		} catch (IOException e) {
			// TODO: handle exception
			Log.e(TAG,"IOException WiteErrorLog");
		}
	}
	
	public int CheckFileIndex(){
		int Num = 0;
		
		File rootDir = new File(ROOT_PATH);
		File[] files = rootDir.listFiles();
		
		if(files != null){
			for (int i = 0; i < files.length; i++) {
				String str = files[i].getPath();
				if (str.contains(FILE_NAME) == true) {
					if(str.endsWith(FILE_EXT)){
						Num++;
					}
				}
				else{
					Log.d(TAG, "Not Match Path : " + str);
					return 0;
				}
					
			}
			
			if(Num > 0){
				String[] strFileName = new String[Num];
				File[] Program = new File[Num];
				String strLast;
				
				int nLastIndex;

				Num = 0;
				for (int i = 0; i < files.length; i++) {
					String str = files[i].getPath();
					if (str.contains(FILE_NAME) == true) {
						if(str.endsWith(FILE_EXT)){
							strFileName[Num] = str;
							Program[Num] = files[i];
							Num++;
						}
					}
					else
						Log.d(TAG, "Not Match Path : " + str);
				}
				
				nLastIndex = GetIndex(ROOT_PATH,FILE_NAME,FILE_EXT,strFileName[0]);
				int Index;
				for(int i = 0; i < Num; i++){
					Index = GetIndex(ROOT_PATH,FILE_NAME,FILE_EXT,strFileName[i]);
					if(Index > nLastIndex){
						nLastIndex = Index;
					}
				}
				if(nLastIndex > 1000)
					nLastIndex = 1000;
				return nLastIndex;
			}
			
		}

		return 0;
	}
	
	public int GetIndex(String strRootPath, String strAppName, String strExtension, String strFileName){
		int[] nVersion;
		char[] cVersion;
		char[] cNumber;

		int IndexStartPosition;
		int result = 0;
		int cNumberLength;
		
		
		String str;
		nVersion = new int[4];
		cVersion = new char[4];
		
		IndexStartPosition = strRootPath.length() + strAppName.length();

		if(strFileName.length() <  IndexStartPosition + strExtension.length()){
			return 0;
		}else{
			cNumberLength =  strFileName.length() - (IndexStartPosition + strExtension.length());
			cNumber = new char[cNumberLength];
			int pow = cNumber.length;
			for(int i = 0; i < cNumber.length; i++){
				cNumber[i] = strFileName.charAt(IndexStartPosition + i);
				result *= 10;
				result += (cNumber[i] - 0x30);
			}			
			return result;
			
		}
	}
	public int Pow(int n1, int n2){
		int result = n1;
		for(int i = 1; i < n2; i++){
			result *= n1;
		}
		return result;
	}
	
	private File makeDirectory(String dir_path){        
		File dir = new File(dir_path);        
		if (!dir.exists())        
		{            
			dir.mkdirs();            
			Log.i( TAG , "!dir.exists" );        
		}else{            
			Log.i( TAG , "dir.exists" );        
		}         
		return dir;    
		}
}
