package cordova.plugin.uninstallapk;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class UninstallApk.
 * https://www.tutorialspoint.com/uninstall-apks-programmatically
 */

public class UninstallApk extends CordovaPlugin {

    private CallbackContext callback = null;
    private int REQUEST_CODE = 111;

    /**
     * @param action          The action to execute.
     * @param args            The exec() arguments.
     * @param callbackContext The callback context used when calling back into JavaScript.
     * @return
     * @throws JSONException
     */
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if (action.equals("Set")) {

            String PackageName = args.getString(0);
            this.Set(PackageName, callbackContext);
            return true;

        } else if (action.equals("appIsInstalled")) {

            String PackageName = args.getString(0);
            JSONObject successObj = new JSONObject();

            if (this._appIsInstalled(PackageName)) {
                successObj.put("status", PluginResult.Status.OK.ordinal());
                successObj.put("message", "Installed");
            } else {
                successObj.put("status", PluginResult.Status.NO_RESULT.ordinal());
                successObj.put("message", "Not installed");
            }

            callbackContext.success(successObj);

            return true;

        } else {

            JSONObject errorObj = new JSONObject();
            errorObj.put("status", PluginResult.Status.INVALID_ACTION.ordinal());
            errorObj.put("message", "Invalid action");
            callbackContext.error(errorObj);

        }

        PluginResult pluginResult = new  PluginResult(PluginResult.Status.NO_RESULT);
        pluginResult.setKeepCallback(true);
        return true;
    }

    /**
     * @param PackageName
     * @param callbackContext
     * @throws JSONException
     */
    private void Set(String PackageName, CallbackContext callbackContext) throws JSONException {

        if (PackageName != null && PackageName.length() > 0) {

            if (_appIsInstalled(PackageName)) {

                Intent intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
                intent.setData(Uri.parse("package:" + PackageName));
                intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
                //this.cordova.getContext().startActivity(intent);

                callback = callbackContext;
                cordova.setActivityResultCallback (this);
                cordova.startActivityForResult((CordovaPlugin) this, intent, REQUEST_CODE );

            } else {

                JSONObject successObj = new JSONObject();
                successObj.put("status", PluginResult.Status.NO_RESULT.ordinal());
                successObj.put("message", "This package is not installed");
                callbackContext.success(successObj);
            }

        } else {

            JSONObject successObj = new JSONObject();
            successObj.put("status", PluginResult.Status.NO_RESULT.ordinal());
            successObj.put("message", "This package is not installed");
            callbackContext.success(successObj);

        }

    }

    /**
     * @param PackageName
     * @return
     */
    private boolean _appIsInstalled(String PackageName) {
        PackageManager pm = cordova.getActivity().getPackageManager();
        Boolean appInstalled = false;

        try {
            pm.getPackageInfo(PackageName, PackageManager.GET_ACTIVITIES);
            appInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            appInstalled = false;
        }

        return appInstalled;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_CODE && resultCode == cordova.getActivity().RESULT_OK) {

            PluginResult result = new PluginResult(PluginResult.Status.OK);
            result.setKeepCallback(true);
            callback.sendPluginResult(result);
            return;
        }
    }
}
