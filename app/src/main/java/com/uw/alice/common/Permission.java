package com.uw.alice.common;

import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permission {

    private static final String TAG = "Permission";

    //检查是否具有相机权限
    /*if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
        //未授权
        Log.d(TAG, "测试点位: 未授权相机权限");
        //是否应该需要解释
        //如果用户之前已拒绝该请求，该方法将返回 true  如果用户已拒绝某项权限并且选中权限请求对话框中的不再询问选项，或者如果设备政策禁止该权限，该方法将返回 false。
        //如果用户不断尝试使用需要某项权限的功能，但一直拒绝权限请求，这或许意味着，用户不理解为什么应用需要该权限才能提供这项功能。在这种情况下，显示解释或许是不错的做法
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),Manifest.permission.CAMERA)){
            //向用户显示一个解释*异步*——不要阻止这个线程等待用户的响应！用户看到解释后，再次尝试请求权限。
            Log.d(TAG, "测试点位: 调用相机拍摄照片需要授权相机权限");
            Toast.makeText(mContext, "求求您嘞，给个权限吧", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(requireActivity(),new String[]{Manifest.permission.CAMERA},REQUEST_PERMISSION_CAMERA);
        }else {
            //不需要解释,直接请求权限
            Toast.makeText(mContext, "来不及解释了，我直接请求权限了", Toast.LENGTH_SHORT).show();
            //ActivityCompat.requestPermissions(requireActivity(),new String[]{Manifest.permission.CAMERA},REQUEST_PERMISSION_CAMERA);
        }
    }else {
        //已授权
                *//*Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //返回处理此意图的组件 ComponentInfo{com.android.camera/com.android.camera.Camera}
                if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }*//*
        Toast.makeText(mContext, "感谢老铁给的超级权限，反手来一个超级加倍", Toast.LENGTH_SHORT).show();
    }*/



    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CAMERA) { // 如果请求被取消，则结果数组为空。
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 用户同意权限
                *//*Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //返回处理此意图的组件 ComponentInfo{com.android.camera/com.android.camera.Camera}
                if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }*//*
                Toast.makeText(mContext, "感谢老铁给的超级权限，反手来一个超级加倍", Toast.LENGTH_SHORT).show();
            } else {
                // 权限被拒绝
                Toast.makeText(mContext, "求求您嘞，给个权限吧", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "测试点位: 求求您嘞，给个权限吧");
            }
        }
    }*/



}
