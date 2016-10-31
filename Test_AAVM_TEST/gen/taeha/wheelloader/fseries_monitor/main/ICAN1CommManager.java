/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\Project\\[SVN] ECS-RD12-16 - WL9F Monitor - Android_APP - UI_B\\Test_AAVM_TEST\\src\\taeha\\wheelloader\\fseries_monitor\\main\\ICAN1CommManager.aidl
 */
package taeha.wheelloader.fseries_monitor.main;
public interface ICAN1CommManager extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements taeha.wheelloader.fseries_monitor.main.ICAN1CommManager
{
private static final java.lang.String DESCRIPTOR = "taeha.wheelloader.fseries_monitor.main.ICAN1CommManager";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an taeha.wheelloader.fseries_monitor.main.ICAN1CommManager interface,
 * generating a proxy if needed.
 */
public static taeha.wheelloader.fseries_monitor.main.ICAN1CommManager asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof taeha.wheelloader.fseries_monitor.main.ICAN1CommManager))) {
return ((taeha.wheelloader.fseries_monitor.main.ICAN1CommManager)iin);
}
return new taeha.wheelloader.fseries_monitor.main.ICAN1CommManager.Stub.Proxy(obj);
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
case TRANSACTION_OpenComport:
{
data.enforceInterface(DESCRIPTOR);
this.OpenComport();
reply.writeNoException();
return true;
}
case TRANSACTION_CloseComport:
{
data.enforceInterface(DESCRIPTOR);
this.CloseComport();
reply.writeNoException();
return true;
}
case TRANSACTION_LineOutfromJNI:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _result = this.LineOutfromJNI(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_native_system_updates_Native:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.native_system_updates_Native();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_native_system_sync_Native:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.native_system_sync_Native();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_Callback_KeyButton:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.Callback_KeyButton(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_Callback_EEPRomTest:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.Callback_EEPRomTest(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_Callback_FlashTest:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.Callback_FlashTest(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_Callback_CID:
{
data.enforceInterface(DESCRIPTOR);
this.Callback_CID();
reply.writeNoException();
return true;
}
case TRANSACTION_Callback_AS:
{
data.enforceInterface(DESCRIPTOR);
this.Callback_AS();
reply.writeNoException();
return true;
}
case TRANSACTION_Callback_StopCommService:
{
data.enforceInterface(DESCRIPTOR);
this.Callback_StopCommService();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements taeha.wheelloader.fseries_monitor.main.ICAN1CommManager
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
@Override public void OpenComport() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_OpenComport, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void CloseComport() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_CloseComport, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public int LineOutfromJNI(int spk) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(spk);
mRemote.transact(Stub.TRANSACTION_LineOutfromJNI, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int native_system_updates_Native() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_native_system_updates_Native, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int native_system_sync_Native() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_native_system_sync_Native, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
////////////////////////////////////////////////////////////////////
// CALLBACK METHOD
///////////////////////////////////////////////////////////////////

@Override public void Callback_KeyButton(int Data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(Data);
mRemote.transact(Stub.TRANSACTION_Callback_KeyButton, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void Callback_EEPRomTest(int Data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(Data);
mRemote.transact(Stub.TRANSACTION_Callback_EEPRomTest, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void Callback_FlashTest(int Data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(Data);
mRemote.transact(Stub.TRANSACTION_Callback_FlashTest, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void Callback_CID() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_Callback_CID, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void Callback_AS() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_Callback_AS, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void Callback_StopCommService() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_Callback_StopCommService, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_OpenComport = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_CloseComport = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_LineOutfromJNI = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_native_system_updates_Native = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_native_system_sync_Native = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_Callback_KeyButton = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_Callback_EEPRomTest = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_Callback_FlashTest = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_Callback_CID = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_Callback_AS = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_Callback_StopCommService = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
}
public void OpenComport() throws android.os.RemoteException;
public void CloseComport() throws android.os.RemoteException;
public int LineOutfromJNI(int spk) throws android.os.RemoteException;
public int native_system_updates_Native() throws android.os.RemoteException;
public int native_system_sync_Native() throws android.os.RemoteException;
////////////////////////////////////////////////////////////////////
// CALLBACK METHOD
///////////////////////////////////////////////////////////////////

public void Callback_KeyButton(int Data) throws android.os.RemoteException;
public void Callback_EEPRomTest(int Data) throws android.os.RemoteException;
public void Callback_FlashTest(int Data) throws android.os.RemoteException;
public void Callback_CID() throws android.os.RemoteException;
public void Callback_AS() throws android.os.RemoteException;
public void Callback_StopCommService() throws android.os.RemoteException;
}
