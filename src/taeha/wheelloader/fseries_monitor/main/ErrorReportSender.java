package taeha.wheelloader.fseries_monitor.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.acra.collector.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;

import android.app.Application;
import android.os.PowerManager;
import android.util.Log;

public class ErrorReportSender implements ReportSender {
	public final static String TAG			= "ErrorReportSender";
	public final static String ROOT_PATH	= "/mnt/sdcard/alarms/";
	public final static String ROOT_PATH_USB = "/mnt/usb/alarms/";	// ++, --, 150313 cjg
	public final static String FILE_NAME	= "WheelLoader_Log"; 
	public final static String FILE_EXT		= ".txt";
	
	int Year;
	int Month;
	int Date;
	int Hour;
	int Min;
	int Sec;
	
	@Override
	public void send(CrashReportData Err) throws ReportSenderException {
		// TODO Auto-generated method stub
		Log.e(TAG, "Error");
		
		String strIndex;
		strIndex = Integer.toString(Year) + Integer.toString(Month) + Integer.toString(Date) + Integer.toString(Hour) + Integer.toString(Min) + Integer.toString(Sec);
		
		DeleteOldestFile();
		WiteErrorLog(strIndex,Err);
	}
	// ++, 150313 cjg
	public void copyErrorLogToUSB(){
		String inputFilePath = ROOT_PATH;
		String outputFilePath = ROOT_PATH_USB;
		
		List<File> dirList = getDirFileList(inputFilePath);
		
		for(int i = 0; i < dirList.size(); i++){
			String fileName = dirList.get(i).getName();
			fileCopy(inputFilePath + "\\" + fileName, outputFilePath + "\\" + fileName);
		}
	}
	public List<File> getDirFileList(String dirPath){
		List<File> dirFileList = null;
		
		File dir = new File(dirPath);
		
		if(dir.exists()){
			File[] files = dir.listFiles();
			
			dirFileList = Arrays.asList(files);
		}
		return dirFileList;
	}
	public void fileCopy(String inputFilePath, String outputFilePath){
		try{
			FileInputStream fis = new FileInputStream(inputFilePath);
			FileOutputStream fos = new FileOutputStream(outputFilePath);
			
			int data = 0;
			while((data = fis.read()) != -1){
				fos.write(data);
			}
			fis.close();
			fos.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	// --, 150313 cjg	
	
	public void WiteErrorLog(String Index, CrashReportData Err){
		String strFileName;
		FileOutputStream fos = null;
		strFileName = FILE_NAME + Index + FILE_EXT;
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
		
		Index += "\n";
		byte[] cDate = new byte[Index.length()];
		cDate = Index.getBytes();
		
		String strErr = Err.values().toString();
		byte[] cErr = new byte[strErr.length()];
		cErr = strErr.getBytes();
		try {
			fos.write(cDate);
			fos.write(cErr);
			fos.close();
		} catch (IOException e) {
			// TODO: handle exception
			Log.e(TAG,"IOException WiteErrorLog");
		}
	}
	public int DeleteOldestFile(){
		int Num = 0;
		
		File rootDir = new File(ROOT_PATH);
		File[] files = rootDir.listFiles();
		if(files.length > 100){
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
					int n;
					
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
					strLast = strFileName[0];
					n = 0;
					int Index;
					for(int i = 0; i < Num; i++){
						Index = GetIndex(ROOT_PATH,FILE_NAME,FILE_EXT,strFileName[i]);
						if(Index < nLastIndex){
							nLastIndex = Index;
							strLast = strFileName[i];
							n = i;
						}
					}
					files[n].delete();
					return nLastIndex;
				}
				
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
	public void SetYear(int Data){
		Year = Data;
	}
	public void SetMonth(int Data){
		Month = Data;
	}
	public void SetDate(int Data){
		Date = Data;
	}
	public void SetHour(int Data){
		Hour = Data;
	}
	public void SetMin(int Data){
		Min = Data;
	}
	public void SetSec(int Data){
		Sec = Data;
	}
	public int GetYear(){
		return Year;
	}
	public int GetMonth(){
		return Month;
	}
	public int GetDate(){
		return Date;
	}
	public int GetHour(){
		return Hour;
	}
	public int GetMin(){
		return Min;
	}
	public int GetSec(){
		return Sec;
	}
	
//	public void WiteErrorLog(int Index, CrashReportData Err){
//		String strFileName;
//		FileOutputStream fos = null;
//		strFileName = FILE_NAME + Integer.toString(Index) + FILE_EXT;
//		File file = new File(ROOT_PATH,strFileName);
//		try {
//			file.createNewFile();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//			Log.e(TAG,"IOException WiteErrorLog");
//		}
//		try {
//			fos = new FileOutputStream(file,true); //true 계속 추가 여부
//
//		} catch (FileNotFoundException e) {
//			// TODO: handle exception
//			Log.e(TAG,"FileNotFoundException WiteErrorLog");
//		}
//		
//		
//		
//		String strErr = Err.values().toString();
//		byte[] cErr = new byte[strErr.length()];
//		cErr = strErr.getBytes();
//		try {
//			fos.write(cErr);
//		} catch (IOException e) {
//			// TODO: handle exception
//			Log.e(TAG,"IOException WiteErrorLog");
//		}
//	}
//	
//	public int CheckFileIndex(){
//		int Num = 0;
//		
//		File rootDir = new File(ROOT_PATH);
//		File[] files = rootDir.listFiles();
//		
//		if(files != null){
//			for (int i = 0; i < files.length; i++) {
//				String str = files[i].getPath();
//				if (str.contains(FILE_NAME) == true) {
//					if(str.endsWith(FILE_EXT)){
//						Num++;
//					}
//				}
//				else{
//					Log.d(TAG, "Not Match Path : " + str);
//					return 0;
//				}
//					
//			}
//			
//			if(Num > 0){
//				String[] strFileName = new String[Num];
//				File[] Program = new File[Num];
//				String strLast;
//				
//				int nLastIndex;
//
//				Num = 0;
//				for (int i = 0; i < files.length; i++) {
//					String str = files[i].getPath();
//					if (str.contains(FILE_NAME) == true) {
//						if(str.endsWith(FILE_EXT)){
//							strFileName[Num] = str;
//							Program[Num] = files[i];
//							Num++;
//						}
//					}
//					else
//						Log.d(TAG, "Not Match Path : " + str);
//				}
//				
//				nLastIndex = GetIndex(ROOT_PATH,FILE_NAME,FILE_EXT,strFileName[0]);
//				int Index;
//				for(int i = 0; i < Num; i++){
//					Index = GetIndex(ROOT_PATH,FILE_NAME,FILE_EXT,strFileName[i]);
//					if(Index > nLastIndex){
//						nLastIndex = Index;
//					}
//				}
//				if(nLastIndex > 1000)
//					nLastIndex = 1000;
//				return nLastIndex;
//			}
//			
//		}
//
//		return 0;
//	}
//	
//	public int GetIndex(String strRootPath, String strAppName, String strExtension, String strFileName){
//		int[] nVersion;
//		char[] cVersion;
//		char[] cNumber;
//
//		int IndexStartPosition;
//		int result = 0;
//		int cNumberLength;
//		
//		
//		String str;
//		nVersion = new int[4];
//		cVersion = new char[4];
//		
//		IndexStartPosition = strRootPath.length() + strAppName.length();
//
//		if(strFileName.length() <  IndexStartPosition + strExtension.length()){
//			return 0;
//		}else{
//			cNumberLength =  strFileName.length() - (IndexStartPosition + strExtension.length());
//			cNumber = new char[cNumberLength];
//			int pow = cNumber.length;
//			for(int i = 0; i < cNumber.length; i++){
//				cNumber[i] = strFileName.charAt(IndexStartPosition + i);
//				result *= 10;
//				result += (cNumber[i] - 0x30);
//			}			
//			return result;
//			
//		}
//	}
//	public int Pow(int n1, int n2){
//		int result = n1;
//		for(int i = 1; i < n2; i++){
//			result *= n1;
//		}
//		return result;
//	}
//	
//	private File makeDirectory(String dir_path){        
//		File dir = new File(dir_path);        
//		if (!dir.exists())        
//		{            
//			dir.mkdirs();            
//			Log.i( TAG , "!dir.exists" );        
//		}else{            
//			Log.i( TAG , "dir.exists" );        
//		}         
//		return dir;    
//	}
	
}
