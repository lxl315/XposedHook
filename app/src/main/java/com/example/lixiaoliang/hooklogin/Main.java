package com.example.lixiaoliang.hooklogin;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.XposedHelpers;

public class Main implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) {

        Log.d("login","-------->loadPackageParam:"+loadPackageParam.packageName);

        XposedHelpers.findAndHookMethod("com.example.lixiaoliang.login.loginActivity",
                loadPackageParam.classLoader,
                "isOk",
                String.class, String.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("账号"+param.args[0].toString()+"密码"+param.args[1].toString());
                        Log.d("login","------>账号"+param.args[0].toString()+"密码"+param.args[1].toString());
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Log.d("loginresult--------:",param.getResult().toString());
                        XposedBridge.log("loginresult"+param.getResult().toString());
                    }
                });
    }
}
