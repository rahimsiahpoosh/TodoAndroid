package ebookfrenzy.com.todo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {



    public MyReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {


        Toast.makeText(context,";)",Toast.LENGTH_LONG).show();
        String dataString=intent.getDataString();
        String action=intent.getAction();
        if(action!=null) {

            Toast.makeText(context,action,Toast.LENGTH_LONG).show();
        }
        if(dataString!=null) {

            Toast.makeText(context,dataString,Toast.LENGTH_LONG).show();
        }

    }
}
