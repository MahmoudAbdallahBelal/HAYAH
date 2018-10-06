package hayah.donation.helper;

import android.content.Context;
import android.content.SharedPreferences;


public class Utilities {



        //   return internet status
    public static boolean checkConnection(Context mContext)
    {
            if (ConnectivityReceiver.isConnected()) {
               return  true;
            } else {
                return false;
            }

    }






}
