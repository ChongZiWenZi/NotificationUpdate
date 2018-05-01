package com.example.notificationupdate;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.notificationupdate.dialog.EXAlertDialog;

public class Main2Activity extends AppCompatActivity {
    EXAlertDialog exAlertDialog;
    TextView update_tv;
    UpdateApp app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app=UpdateApp.getInstance();
        update_tv=(TextView)findViewById(R.id.update_tv);
        update_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        requestMultiplePermissions();
    }

    public void showDialog(){
        exAlertDialog=new EXAlertDialog(this);
        exAlertDialog.setMessage("有新版本");
        exAlertDialog.setTitle("更新提示");
        exAlertDialog.setNoOnclickListener("取消",new EXAlertDialog.onNoOnclickListener(){

            @Override
            public void onNoClick() {
                exAlertDialog.dismiss();
            }
        });
        exAlertDialog.setYesOnclickListener("确定",new EXAlertDialog.onYesOnclickListener(){

            @Override
            public void onYesClick(String lableText) {
                Intent it = new Intent(Main2Activity.this, NotificationUpdateActivity.class);
                startActivity(it);
//				MapApp.isDownload = true;
                app.setDownload(true);
                exAlertDialog.dismiss();
            }
        });
        exAlertDialog.show();
    }
    private static final int REQUEST_PERMISSION_CAMERA_CODE = 1;
    public void requestMultiplePermissions(){
        String[] permissions = {Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,Manifest.permission.INSTALL_PACKAGES,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE,Manifest.permission.INTERNET};
        requestPermissions(permissions,REQUEST_PERMISSION_CAMERA_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CAMERA_CODE) {
            int grantResult = grantResults[0];
            boolean granted = grantResult == PackageManager.PERMISSION_GRANTED;
            Log.i("Permissions", "onRequestPermissionsResult granted=" + granted);
        }
    }
}
