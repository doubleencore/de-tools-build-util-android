package com.doubleencore.buildutils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by chris on 10/29/15.
 */
public class BuildUtils {

    private static final String TAG = BuildUtils.class.getSimpleName();

    private static final String DE_BUILD_VERSION = "de_build_version";

    /**
     * Gets the build number assigned by Jenkins
     * @param context context to the app
     * @return Value assigned by Jenkins, -1 if not found (ie, dev build)
     */
    public static int getBuildNumber(@NonNull Context context) {
        String packageName = context.getPackageName();
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            ApplicationInfo ai = pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            if (null != ai.metaData && ai.metaData.containsKey(DE_BUILD_VERSION)) {
                return ai.metaData.getInt(DE_BUILD_VERSION);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Name not found: ", e);
        }
        return -1;
    }
}
