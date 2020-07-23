package com.uw.alice.common;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Widget {

    /**
     * 弹出警报对话框 Alert dialog
     */
    public static void showMaterialAlertDialog(Context context,String title,String message) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定", (dialog, which) -> {
            //Toast.makeText(context, "确定", Toast.LENGTH_SHORT).show();
            builder.create().dismiss();
        });
        //builder.setNegativeButton("取消", (dialog, which) -> builder.create().dismiss());
        builder.create().show();
    }

    /**
     * 弹出简单对话框 Alert dialog
     */
    public static int showMaterialSimpleDialog(Context context,String title,CharSequence[] item) {
        final int[] choiceItem = new int[1];
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle(title);
        builder.setItems(item, (dialog, which) -> {
            choiceItem[0] = which;
            Toast.makeText(context, "选择了" + which, Toast.LENGTH_SHORT).show();
        });
        builder.setPositiveButton("确定", (dialog, which) -> {
            Toast.makeText(context, "确定" + which, Toast.LENGTH_SHORT).show();
            builder.create().dismiss();
        });
        builder.setNegativeButton("取消", (dialog, which) -> {
            Toast.makeText(context, "取消" + which, Toast.LENGTH_SHORT).show();
            builder.create().dismiss();
        });
        builder.create().show();

        return choiceItem[0];
    }





}
