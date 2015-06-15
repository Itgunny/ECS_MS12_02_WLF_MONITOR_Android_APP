package taeha.wheelloader.fseries_monitor.main;

interface ICAN1CommManager
{
	void OpenComport();
	void CloseComport();

	int LineOutfromJNI(int spk);
	
	int native_system_sync_Native();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	////////////////////////////////////////////////////////////////////
	// CALLBACK METHOD
	///////////////////////////////////////////////////////////////////
	void Callback_KeyButton(int Data);
	void Callback_EEPRomTest(int Data);
	void Callback_FlashTest(int Data);
	void Callback_CID();
	void Callback_AS();
	void Callback_StopCommService();
	
}