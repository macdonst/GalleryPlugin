package com.simonmacdonald.cordova.plugins;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;

import android.annotation.TargetApi;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

@TargetApi(Build.VERSION_CODES.FROYO)
public class GalleryPlugin extends CordovaPlugin {

    private static final String LOG_TAG = "GalleryPlugin";

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        final CallbackContext cbContext = callbackContext;
        if (action.equals("add")) {
          String imagePath = args.optString(0);
          if ("".equals(imagePath)) {
              Log.d(LOG_TAG, "No image path passed in");
              cbContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, 0));
              return true;
          } else {
              imagePath = stripFileProtocol(imagePath);
              PluginResult result = new PluginResult(PluginResult.Status.NO_RESULT);
              result.setKeepCallback(true);
              cbContext.sendPluginResult(result);
              MediaScannerConnection.scanFile(this.cordova.getActivity(),
                      new String[] { imagePath }, null,
                      new MediaScannerConnection.OnScanCompletedListener() {
                  public void onScanCompleted(String path, Uri uri) {
                      cbContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, uri.toString()));
                  }
              });
              return true;
          }
        }
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
        return false;
    }

    /**
     * This method removes the "file://" from the passed in filePath
     *
     * @param filePath to be checked.
     * @return
     */
    private static String stripFileProtocol(String filePath) {
        if (filePath.startsWith("file://")) {
            filePath = filePath.substring(7);
        }
        return filePath;
    }
}
