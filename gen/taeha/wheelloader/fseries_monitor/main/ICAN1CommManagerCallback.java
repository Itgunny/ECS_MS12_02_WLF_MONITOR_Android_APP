/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\Project\\WL9F\\SVN\\[SVN] ECS-RD12-16 - WL9F Monitor - Android_APP - UI_B\\src\\taeha\\wheelloader\\fseries_monitor\\main\\ICAN1CommManagerCallback.aidl
 */
package taeha.wheelloader.fseries_monitor.main;
public interface ICAN1CommManagerCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements taeha.wheelloader.fseries_monitor.main.ICAN1CommManagerCallback
{
private static final java.lang.String DESCRIPTOR = "taeha.wheelloader.fseries_monitor.main.ICAN1CommManagerCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an taeha.wheelloader.fseries_monitor.main.ICAN1CommManagerCallback interface,
 * generating a proxy if needed.
 */
public static taeha.wheelloader.fseries_monitor.main.ICAN1CommManagerCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof taeha.wheelloader.fseries_monitor.main.ICAN1CommManagerCallback))) {
return ((taeha.wheelloader.fseries_monitor.main.ICAN1CommManagerCallback)iin);
}
return new taeha.wheelloader.fseries_monitor.main.ICAN1CommManagerCallback.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_CallbackFunc:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.CallbackFunc(_arg0);
return true;
}
case TRANSACTION_KeyButtonCallBack:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.KeyButtonCallBack(_arg0);
return true;
}
case TRANSACTION_EEPRomCallBack:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.EEPRomCallBack(_arg0);
return true;
}
case TRANSACTION_FlashCallBack:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.FlashCallBack(_arg0);
return true;
}
case TRANSACTION_CIDCallBack:
{
data.enforceInterface(DESCRIPTOR);
this.CIDCallBack();
return true;
}
case TRANSACTION_ASCallBack:
{
data.enforceInterface(DESCRIPTOR);
this.ASCallBack();
return true;
}
case TRANSACTION_StopCommServiceCallBack:
{
data.enforceInterface(DESCRIPTOR);
this.StopCommServiceCallBack();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements taeha.wheelloader.fseries_monitor.main.ICAN1CommManagerCallback
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void CallbackFunc(int Data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(Data);
mRemote.transact(Stub.TRANSACTION_CallbackFunc, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void KeyButtonCallBack(int Data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(Data);
mRemote.transact(Stub.TRANSACTION_KeyButtonCallBack, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void EEPRomCallBack(int Data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(Data);
mRemote.transact(Stub.TRANSACTION_EEPRomCallBack, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void FlashCallBack(int Data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(Data);
mRemote.transact(Stub.TRANSACTION_FlashCallBack, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void CIDCallBack() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_CIDCallBack, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void ASCallBack() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_ASCallBack, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void StopCommServiceCallBack() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_StopCommServiceCallBack, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_CallbackFunc = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_KeyButtonCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_EEPRomCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_FlashCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_CIDCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_ASCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_StopCommServiceCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
}
public void CallbackFunc(int Data) throws android.os.RemoteException;
public void KeyButtonCallBack(int Data) throws android.os.RemoteException;
public void EEPRomCallBack(int Data) throws android.os.RemoteException;
public void FlashCallBack(int Data) throws android.os.RemoteException;
public void CIDCallBack() throws android.os.RemoteException;
public void ASCallBack() throws android.os.RemoteException;
public void StopCommServiceCallBack() throws android.os.RemoteException;
}
