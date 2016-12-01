package taeha.wheelloader.fseries_monitor.main;

import android.R.integer;
import android.util.Log;


public class CheckModel {
	/////////////////////CONSTANT/////////////////////////////////
	private static final String TAG = "CheckModel";
	
	private static final int LENGTH_COMPONENTBASICINFORMATION	= 37;
	
	// ++, --, 150329 bwk 965 추가
	// ++, --, 150330 bwk 975 추가
//	String[] MCUModelName = {"HL935", "HL940", "HL955", "HL960", "HL965", "HL970", "HL975", "HL980"};		
//	String[] MCUModelExtraName = {"_v", "TM", "XT"};
	
	public static final int NO_MODEL 	= 0x0;
	/*
	public static final int MODEL_935 	= 0x1;
	public static final int MODEL_940 	= 0x2;
	public static final int MODEL_955 	= 0x3;
	public static final int MODEL_960 	= 0x4;
	public static final int MODEL_965 	= 0x5;
	public static final int MODEL_970	= 0x6;
	public static final int MODEL_975	= 0x7;
	public static final int MODEL_980	= 0x8;
	
	public static final int MODEL_935TM 	= 0x11;
	public static final int MODEL_940TM 	= 0x12;
	public static final int MODEL_955TM 	= 0x13;
	public static final int MODEL_960TM 	= 0x14;
	public static final int MODEL_965TM 	= 0x15;
	public static final int MODEL_970TM		= 0x16;
	public static final int MODEL_975TM		= 0x17;
	public static final int MODEL_980TM		= 0x18;
	*/
	
	public static final int	TCU_4SPEED	= 0x00000000;
	public static final int	TCU_5SPEED	= 0x00000001;
	
	public static final int STATE_MANUFACTURERCODE_CUMMINS					= 112;
	public static final int STATE_MANUFACTURERCODE_SCANIA					= 116;
	/////////////////////////////////////////////////////////////////
	
	/////////////////////VARIABLE//////////////////////////////////

	/////////////////////////////////////////////////////////////////
	public boolean CheckMCUVersionHigh(byte[] BasicInfo, int nStandardModel){
		int Index = 4;
		int Index2 = 0;
		boolean bAsterisk = false;
		int n100,n10,n1;
		int MCUModelNum;
		char cSub1 = 0, cSub2 = 0;
		////////////// Find Serial Number/////////////
		for(int i = 4; i < 20; i++){
			if(BasicInfo[i] != 0x2A)
			{
				Index++;
				bAsterisk = false;
			}
			else{
				bAsterisk = true;
				break;
			}
		}		
		/////////////////////////////////////////////
		
		//////////// Find Model Name/////////////////
		for(int i = Index + 1; i < LENGTH_COMPONENTBASICINFORMATION; i++){
			if(BasicInfo[i] != 0x2A)
			{
				Index2++;
				bAsterisk = false;
			}
			else{
				bAsterisk = true;
				break;
			}
		}
		/////////////////////////////////////////////
		int[] Model;
		Model = new int [Index2];
	
		
		if(bAsterisk == false){
			return false;
		}else{
			for(int i = 0; i < Index2; i++){
				Model[i] = (int)BasicInfo[i+Index+1];
			}
			
			n100 = Model[2] - 48;
			n10 = Model[3] - 48;
			n1 = Model[4] - 48;
			
			
			MCUModelNum = n100 * 100 + n10 * 10 + n1;
			
			if(Index2 >= 7){
				cSub1 = (char) Model[5];
				cSub2 = (char) Model[6];
			}
			
			if(nStandardModel <= MCUModelNum)
				return true;
			else
				return false;
		}
	}
	
	public boolean CheckMCUVersionLow(byte[] BasicInfo, int nStandardModel){
		int Index = 4;
		int Index2 = 0;
		boolean bAsterisk = false;
		int n100,n10,n1;
		int MCUModelNum;
		char cSub1 = 0, cSub2 = 0;
		////////////// Find Serial Number/////////////
		for(int i = 4; i < 20; i++){
			if(BasicInfo[i] != 0x2A)
			{
				Index++;
				bAsterisk = false;
			}
			else{
				bAsterisk = true;
				break;
			}
		}		
		/////////////////////////////////////////////
		
		//////////// Find Model Name/////////////////
		for(int i = Index + 1; i < LENGTH_COMPONENTBASICINFORMATION; i++){
			if(BasicInfo[i] != 0x2A)
			{
				Index2++;
				bAsterisk = false;
			}
			else{
				bAsterisk = true;
				break;
			}
		}
		/////////////////////////////////////////////
		int[] Model;
		Model = new int [Index2];
	
		
		if(bAsterisk == false){
			return false;
		}else{
			for(int i = 0; i < Index2; i++){
				Model[i] = (int)BasicInfo[i+Index+1];
			}
			
			n100 = Model[2] - 48;
			n10 = Model[3] - 48;
			n1 = Model[4] - 48;
			
			
			MCUModelNum = n100 * 100 + n10 * 10 + n1;
			
			if(Index2 >= 7){
				cSub1 = (char) Model[5];
				cSub2 = (char) Model[6];
			}
			if(nStandardModel >= MCUModelNum)
				return true;
			else
				return false;
		}
	}	
	
	public int GetMCUModelNum(byte[] BasicInfo){
		int Index = 4;
		int Index2 = 0;
		boolean bAsterisk = false;
		int n100,n10,n1;
		int MCUModelNum;
		////////////// Find Serial Number/////////////
		for(int i = 4; i < 20; i++){
			if(BasicInfo[i] != 0x2A)
			{
				Index++;
				bAsterisk = false;
			}
			else{
				bAsterisk = true;
				break;
			}
		}		
		/////////////////////////////////////////////
		
		//////////// Find Model Name/////////////////
		for(int i = Index + 1; i < LENGTH_COMPONENTBASICINFORMATION; i++){
			if(BasicInfo[i] != 0x2A)
			{
				Index2++;
				bAsterisk = false;
			}
			else{
				bAsterisk = true;
				break;
			}
		}
		/////////////////////////////////////////////
		int[] Model;
		Model = new int [Index2];
	
		
		if(bAsterisk == false){
			return NO_MODEL;
		}else{
			for(int i = 0; i < Index2; i++){
				Model[i] = (int)BasicInfo[i+Index+1];
			}
			
			n100 = Model[2] - 48;
			n10 = Model[3] - 48;
			n1 = Model[4] - 48;
			
			
			MCUModelNum = n100 * 100 + n10 * 10 + n1;
			
			return MCUModelNum;
		}
	}

	public String GetMCUModelOption(byte[] BasicInfo){
		int Index = 4;
		int Index2 = 0;
		boolean bAsterisk = false;
		
		////////////// Find Serial Number/////////////
		for(int i = 4; i < 20; i++){
			if(BasicInfo[i] != 0x2A)
			{
				Index++;
				bAsterisk = false;
			}
			else{
				bAsterisk = true;
				break;
			}
		}		
		/////////////////////////////////////////////
		
		//////////// Find Model Name/////////////////
		for(int i = Index + 1; i < LENGTH_COMPONENTBASICINFORMATION; i++){
			if(BasicInfo[i] != 0x2A)
			{
				Index2++;
				bAsterisk = false;
			}
			else{
				bAsterisk = true;
				break;
			}
		}
		/////////////////////////////////////////////
		int[] Model;
		Model = new int [Index2];
		Log.d(TAG, "bAsterisk:"+Boolean.toString(bAsterisk));
		
		if(bAsterisk == false){
			return "-";
		}else{
			for(int i = 0; i < Index2; i++){
				Model[i] = (int)BasicInfo[i+Index+1];
			}
						
			Log.d(TAG, "Index2:"+Integer.toString(Index2));
			if(Index2 >= 6){
								
				char[] cString;
				String strString;
				cString = new char[Index2-5];
				for(int i = 5; i < Index2; i++){
					cString[i-5] = (char) Model[i];
				}
				strString = new String(cString,0,cString.length);
				Log.d(TAG, "GetMCUModelOption:"+strString);
				return strString;
			}else{
				return "-";
			}
		}
	}	
	
	public String GetTCUEST37APartNumber(byte[] softwareID){
		int Index = 0;
		boolean bAsterisk = false;
		try {
			for(int i = 1; i < 14; i++){
				if(softwareID[i] == 0x2A){
					bAsterisk = true;
					break;
				}else if(softwareID[i] == 0x20 || (softwareID[i] >= 0x30 && softwareID[i] <= 0x39)){
					Index++;
				}else{
					bAsterisk = true;
					break;
				}
			}
			if(Index > 1 && bAsterisk == true){
				char[] cString;
				String strString;
				cString = new char[Index];
				for(int i = 0; i < Index; i++){
					cString[i] = (char) softwareID[1 + i];
				}
				strString = new String(cString,0,cString.length);
				return strString;
			}else{
				return "";
			
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			Log.e(TAG,"ArrayIndexOutOfBoundsException");
		}
		return "";
	}
	
	public int GetTCUModel(byte[] softwareID){
		String strModel;
		strModel = GetTCUEST37APartNumber(softwareID);
		//Log.d(TAG, "before))strModel="+strModel);
		strModel = strModel.replaceAll(" ", "");
		//Log.d(TAG, "after))strModel="+strModel);
		if(strModel.equals("6057018671") == true){			// HL740-F
			return TCU_4SPEED;
		}else if(strModel.equals("6057018608") == true){	// HL757-F
			return TCU_4SPEED;
		}else if(strModel.equals("6057018554") == true){	// HL760-F
			return TCU_4SPEED;
		}else if(strModel.equals("6057018641") == true){	// HL767-F
			return TCU_4SPEED;
		}else if(strModel.equals("6057018610") == true){	// HL770-F
			return TCU_4SPEED;
		}else if(strModel.equals("6057018633") == true){	// HL777-F(old)
			return TCU_4SPEED;
		}else if(strModel.equals("6057018809") == true){	// HL777-F(new)
			return TCU_4SPEED;
		}else if(strModel.equals("6057018646") == true){	// HL780-F	
			return TCU_4SPEED;
		// ++, 150610 추가
		}else if(strModel.equals("6057018709") == true){	// HL730-F	
			return TCU_4SPEED;
		}else{
			return TCU_5SPEED;
		}
	}
	
	
}
