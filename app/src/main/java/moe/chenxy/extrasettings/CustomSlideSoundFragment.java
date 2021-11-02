package moe.chenxy.extrasettings;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;

import com.google.android.material.snackbar.Snackbar;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import moe.chenxy.extrasettings.config.ConfigFile;
import moe.chenxy.extrasettings.util.RadioSendManage;

import static androidx.core.content.PermissionChecker.checkSelfPermission;


public class CustomSlideSoundFragment extends PreferenceFragment {
    ConfigFile configFile = new ConfigFile();
    public RadioSendManage radioSendManage = RadioSendManage.radioSendManage;
    private boolean isSliderOnPath = false;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        getPreferenceManager().setSharedPreferencesName("Chen_Settings");
        addPreferencesFromResource(R.xml.custom_slide_sound_path);
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        Preference slideSoundOnPath = getPreferenceManager().findPreference("slidesoundonpath");
        Preference slideSoundOffPath = getPreferenceManager().findPreference("slidesoundoffpath");

        slideSoundOnPath.setOnPreferenceClickListener((b) -> {
            getSlideSoundPath();
            isSliderOnPath = true;
            return true;
        });

        slideSoundOffPath.setOnPreferenceClickListener((b) -> {
            getSlideSoundPath();
            isSliderOnPath = false;
            return true;
        });

        askPermission();
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    private void updateConfig(ConfigFile configFile) {
        radioSendManage.sendMsg(getActivity(), configFile);
    }

    private void getSlideSoundPath() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*"); //选择音频
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
    }

    String path;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
          Uri uri = data.getData();
          if ("file".equalsIgnoreCase(uri.getScheme())){//使用第三方应用打开
            path = uri.getPath();
            if (path != null) {
                returnPathForSlider(path);
            }
            return;
          }
            //4.4以后
            path = getPath(getContext(), uri);
            if (path != null) {
                returnPathForSlider(path);
            }
        }
    }

    private void returnPathForSlider(String path) {
        Preference slideSoundOnPath = getPreferenceManager().findPreference("slidesoundonpath");
        Preference slideSoundOffPath = getPreferenceManager().findPreference("slidesoundoffpath");
        if (isSliderOnPath) {
            configFile.SliderOpenSoundPath = processCustomSlideSoundPath(path, true);
            slideSoundOnPath.setSummary(path);
            updateConfig(configFile);
        } else {
            configFile.SliderCloseSoundPath = processCustomSlideSoundPath(path, false);
            slideSoundOffPath.setSummary(path);
            updateConfig(configFile);
        }
    }

    public String processCustomSlideSoundPath(String path, boolean isSlideOn) {
        String customSlideSoundPath = "/data/misc/chen/slide_custom_sound_" + (isSlideOn ? "on" : "off") + "." + getFileSuffix(path);
        if (path.startsWith("/storage/")) {
            Log.i("Art_Chen", "SystemServer: Detected Custom Slide Sound Path");
            String result2 = execRootCmd("rm /data/misc/chen/slide_custom_sound_" + (isSlideOn ? "on" : "off") + "*");
            String result1 = execRootCmd("ls /sdcard");
            String result = execRootCmd("cp \""+ path + "\" /data/misc/chen/slide_custom_sound_" + (isSlideOn ? "on" : "off") + "." + getFileSuffix(path));
            execRootCmd("chown system:system /data/misc/chen/*");
            execRootCmd("chmod 666 /data/misc/chen/*");
            Log.i("Art_Chen", "SystemServer: Copy Slide Sound Done! path is " + path + " result is " + result + result2);
            return customSlideSoundPath;
        } else {
            return path;
        }
    }

    public static String getFileSuffix(String pathandname) {
        int start = pathandname.lastIndexOf(".");
        if (start != -1) {
            return pathandname.substring(start + 1);
        } else {
            return null;
        }
    }

    public String execRootCmd(String cmd) {
        View view = getView();
        final String[] output = new String[1];
        if (!RootTools.isRootAvailable()) {
            Snackbar snackbar = Snackbar.make(view, "需要Root权限来执行!", Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            ((TextView) snackbarView.findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
            ((TextView) snackbarView.findViewById(R.id.snackbar_action)).setTextColor(Color.BLUE);
            //设置Snackbar的背景色
            //snackbarView.setBackgroundColor(Color.WHITE);
            snackbar.show();
        }
        Command command = new Command(0, cmd){
            @Override
            public void commandCompleted(int id, int exitcode) {
                super.commandCompleted(id, exitcode);
            }

            @Override
            public void commandOutput(int id, String line) {
                super.commandOutput(id, line);
                output[0] = line;
            }

            @Override
            public void commandTerminated(int id, String reason) {
                super.commandTerminated(id, reason);
                final Snackbar snackbar = Snackbar.make(view, "错误，原因:" + reason, Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                ((TextView) snackbarView.findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
                ((TextView) snackbarView.findViewById(R.id.snackbar_action)).setTextColor(Color.BLUE);
                snackbar.show();
            }
        };
        try {
            RootTools.getShell(true,10000).add(command);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            final Snackbar snackbar = Snackbar.make(view, "获取Root权限超时! Magisk 管理器可能不在后台运行.", Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            ((TextView) snackbarView.findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
            ((TextView) snackbarView.findViewById(R.id.snackbar_action)).setTextColor(Color.BLUE);
            snackbar.show();
            e.printStackTrace();
        } catch (RootDeniedException e) {
            e.printStackTrace();
            final Snackbar snackbar = Snackbar.make(view, "请允许Root!", Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            ((TextView) snackbarView.findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
            ((TextView) snackbarView.findViewById(R.id.snackbar_action)).setTextColor(Color.BLUE);
            snackbar.show();
        }
        return output[0];
    }

    List<String> permissions = new ArrayList<String>();

    private boolean askPermission() {
        int RECORD_AUDIO = checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO );
        int MODIFY_AUDIO_SETTING = checkSelfPermission(getActivity(), Manifest.permission.MODIFY_AUDIO_SETTINGS);
        int READ_EXTERNAL_STORAGE = checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (RECORD_AUDIO != PackageManager.PERMISSION_GRANTED || MODIFY_AUDIO_SETTING != PackageManager.PERMISSION_GRANTED || READ_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.MODIFY_AUDIO_SETTINGS);
            permissions.add(Manifest.permission.RECORD_AUDIO);
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!permissions.isEmpty()) {
            requestPermissions(permissions.toArray(new String[permissions.size()]), 1);
        } else
            return false;
        return true;

    }
    /**
    * 专为Android4.4设计的从Uri获取文件绝对路径，以前的方法已不好使
    */
    @SuppressLint("NewApi")
    public String getPath(final Context context, final Uri uri) {

        // DocumentProvider
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];


                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
          }
          // DownloadsProvider
          else if (isDownloadsDocument(uri)) {
            final String id = DocumentsContract.getDocumentId(uri);
            final Uri contentUri = ContentUris.withAppendedId(
                              Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            return getDataColumn(context, contentUri, null, null);
          }
          // MediaProvider
          else if (isMediaDocument(uri)) {
            final String docId = DocumentsContract.getDocumentId(uri);
            final String[] split = docId.split(":");
            final String type = split[0];
            Uri contentUri = null;
            if ("image".equals(type)) {
                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(type)) {
                contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(type)) {
                contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            }

            final String selection = "_id=?";
            final String[] selectionArgs = new String[]{split[1]};

            return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
          return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
          return uri.getPath();
        }
        return null;
    }


    /**
    * Get the value of the data column for this Uri. This is useful for
    * MediaStore Uris, and other file-based ContentProviders.
    *
    * @param context    The context.
    * @param uri      The Uri to query.
    * @param selection   (Optional) Filter used in the query.
    * @param selectionArgs (Optional) Selection arguments used in the query.
    * @return The value of the _data column, which is typically a file path.
    */
    public String getDataColumn(Context context, Uri uri, String selection,
                            String[] selectionArgs) {


        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};


        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                        null);
            if (cursor != null && cursor.moveToFirst()) {
            final int column_index = cursor.getColumnIndexOrThrow(column);
            return cursor.getString(column_index);
            }
        } finally {
        if (cursor != null)
            cursor.close();
        }
        return null;
    }


    /**
    * @param uri The Uri to check.
    * @return Whether the Uri authority is ExternalStorageProvider.
    */
    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }


    /**
    * @param uri The Uri to check.
    * @return Whether the Uri authority is DownloadsProvider.
    */
    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }


    /**
    * @param uri The Uri to check.
    * @return Whether the Uri authority is MediaProvider.
    */
    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}