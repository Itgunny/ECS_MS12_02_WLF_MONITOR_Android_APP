package taeha.wheelloader.fseries_monitor.main;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class LanguageDB {
	private static final String TAG = "LanguageDB";
	
	public static String LanguageVersion1;
	public static String LanguageVersion2;
	public static String LanguageVersion3;
	public static String LanguageVersion4;
	protected Home ParentActivity;
	boolean OpenState = false;
	Workbook workbook = null;
	WorkbookSettings workbookSettings;
	Sheet sheet = null;
	File excelStringFile = null;
	public LanguageDB(Context context) {
		// TODO Auto-generated constructor stub
		ParentActivity = (Home)context;
		excelStringFile = new File(Environment.getExternalStorageDirectory() + "/Language/string.xls");
		workbookSettings = new WorkbookSettings();
		workbookSettings.setEncoding("Cp1252");
		if(excelStringFile != null){
			try {
				workbook = Workbook.getWorkbook(excelStringFile, workbookSettings);
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(workbook != null){
				sheet = workbook.getSheet(0);
				String str = workbook.getSheet(0).getName();
				Log.d(TAG, "Sheet Name : "  + str);
				LanguageVersion1 = sheet.getCell(5, 0).getContents();
				LanguageVersion2 = sheet.getCell(6, 0).getContents();
				LanguageVersion3 = sheet.getCell(7, 0).getContents();
				LanguageVersion4 = sheet.getCell(8, 0).getContents();
				OpenState = true;
			}else{
				OpenState = false;
			}
		}else {
			Log.d(TAG, "excel file is null!");
			OpenState = false;
		}
	}
	public boolean isFileExist(){
		boolean result;
		if(excelStringFile != null && excelStringFile.exists()){
			result = true;
		}
		else{
			result = false;
		}
		return result;
	}
	public String findStrGetString(int index, int language){
		//Log.w(TAG, "findStrGetString");
		String name = "";
		if(sheet != null){
			int nColumnStartIndex = 1;
			try {
				name = new String(sheet.getCell(nColumnStartIndex + language + 1, index+2).getContents());
				if(name.equals(""))
				{
					return null;
				}else{
					//Log.d(TAG, "name : " + name);
					return name;
				}
			}catch(ArrayIndexOutOfBoundsException e){
				return null;
			}
		}else {
			return null;
		}
	}
	public String findStrGetString(int index){
		String name = "";
		if(sheet != null){
			int nRowIndex = 0;
			try {
				name = new String(sheet.getCell(index, nRowIndex +1).getContents());
				if(name.equals(""))
				{
					return null;
				}else{
					return name;
				}
			}catch(ArrayIndexOutOfBoundsException e){
				return null;
			}
		}else {
			return null;
		}
	}
	public int getCountLanguage(){
		int nRowIndex = 0;
		String name = "";
		int count = 0;
		if(sheet != null){
			for(int i = 1;  ; i++){
				try{
					if(sheet.getCell(i, 0) != null){
						name = new String(sheet.getCell(i+1, nRowIndex+1).getContents());
						if(name.equals(""))
						{
							count += 0;
						}else {
							count += 1;
						}
					} else {
						break;
					}
				}catch(ArrayIndexOutOfBoundsException e){
					e.printStackTrace();
					break;
				}
			}
			if(count > 18){
				return 18;	
			}else{
				return count;
			}
		}else {
			return 0;
		}
	}
	public boolean getOpenState(){
		return OpenState;
	}
}
