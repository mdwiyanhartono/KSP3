package dwiyanhartono.com.ksp3;

import android.content.Context;

import java.io.File;

public class DeviceUtils {
    public Boolean isDeviceRooted(Context context){
        boolean isRooted = isrooted1() || isrooted2();
        return isRooted;
    }

    private boolean isrooted1() {

        File file = new File("/system/app/iColls-1.0.apk");
        if (file.exists()) {
            return true;
        }
        return false;
    }

    // try executing commands
    private boolean isrooted2() {
        return canExecuteCommand("/system/xbin/which su")
                || canExecuteCommand("/system/bin/which su")
                || canExecuteCommand("which su");
    }

    private static boolean canExecuteCommand(String command) {
        boolean executedSuccesfully;
        try {
            Runtime.getRuntime().exec(command);
            executedSuccesfully = true;
        } catch (Exception e) {
            executedSuccesfully = false;
        }

        return executedSuccesfully;
    }

}
