package taeha.wheelloader.fseries_monitor.main;

oneway interface ICAN1CommManagerCallback
{
	void CallbackFunc(int Data);
	void KeyButtonCallBack(int Data);
	void EEPRomCallBack(int Data);
	void FlashCallBack(int Data);
	void CIDCallBack();
	void ASCallBack();
	void StopCommServiceCallBack();
}