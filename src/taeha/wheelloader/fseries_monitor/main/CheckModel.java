package taeha.wheelloader.fseries_monitor.main;

import android.util.Log;


public class CheckModel {
	/////////////////////CONSTANT/////////////////////////////////
	private static final String TAG = "CheckModel";
	
	private static final int LENGTH_COMPONENTBASICINFORMATION	= 37;
	
	public static final int NO_MODEL 	= 0x00000000;
	public static final int MODEL_935 	= 0x00000001;
	public static final int MODEL_940 	= 0x00000010;
	public static final int MODEL_955 	= 0x00000100;
	public static final int MODEL_960 	= 0x00001000;
	public static final int MODEL_970	= 0x00010000;
	public static final int MODEL_980	= 0x00100000;
	
	public static final int	TCU_4SPEED	= 0x00000000;
	public static final int	TCU_5SPEED	= 0x00000001;
	
	public static final int STATE_MANUFACTURERCODE_CUMMINS					= 112;
	public static final int STATE_MANUFACTURERCODE_SCANIA					= 116;
	/////////////////////////////////////////////////////////////////
	
	/////////////////////VARIABLE//////////////////////////////////

	/////////////////////////////////////////////////////////////////
	
	public int GetMCUVersion(byte[] BasicInfo){
		boolean DataCheckFlag = true;
		String strModel;
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
			
			if(MCUModelNum == 935)
				return MODEL_935;
			else if(MCUModelNum == 940)
				return MODEL_940;
			else if(MCUModelNum == 955)
				return MODEL_955;
			else if(MCUModelNum == 960)
				return MODEL_960;
			else if(MCUModelNum == 970)
				return MODEL_970;
			else if(MCUModelNum == 980)
				return MODEL_980;
			else
				return NO_MODEL;
						
		}
	}
	
	public String GetTCUEST37APartNumber(byte[] softwareID){
		int Index = 0;
		boolean bAsterisk = false;
		try {
			for(int i = 1; i < 13; i++){
				if(softwareID[i] == 0x2A){
					bAsterisk = true;
					break;
				}else{
					Index++;
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
		if(strModel.equals("6057018671") == true){
			return TCU_4SPEED;
		}else if(strModel.equals("6057018608") == true){
			return TCU_4SPEED;
		}else if(strModel.equals("6057018554") == true){
			return TCU_4SPEED;
		}else if(strModel.equals("6057018641") == true){
			return TCU_4SPEED;
		}else if(strModel.equals("6057018610") == true){
			return TCU_4SPEED;
		}else if(strModel.equals("6057018633") == true){
			return TCU_4SPEED;
		}else if(strModel.equals("6057018646") == true){
			return TCU_4SPEED;
		}else{
			return TCU_5SPEED;
		}
	
	}
	
	
}
