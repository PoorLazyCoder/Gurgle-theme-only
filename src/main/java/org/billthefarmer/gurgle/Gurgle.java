package org.billthefarmer.gurgle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.TextView;
import android.widget.Toast;




public class Gurgle extends Activity
{
    private static final String USER_THEME_ID = "USER_THEME_ID";



    private TextView displayTextViews[][];
    private Toast toast;



    protected void ring(){
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        RingtoneManager.getRingtone(this,uri).play();
    }



    // On create
    @Override
    @SuppressWarnings("deprecation")
    public void onCreate(Bundle savedInstanceState)
    {

        //setTheme(R.style.Purple_Theme);

        int themeIDIndex = readPrefInt(USER_THEME_ID);
        setTheme(themeIDs[themeIDIndex]);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        getActionBar().setDisplayShowTitleEnabled(true);
         getActionBar().setTitle("Gurgle");


       ring();

    }

    // On create options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it
        // is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    // On options item selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Get id
        int id = item.getItemId();
        switch (id)
        {

            case R.id.theme_action:
                showThemeDialog();
                break;

        default:
            return false;
        }

        return true;
    }

    final String[] themes =  {
            "Blue",
            "Red",
            "Black",
            "Orange",
            "Cyan",
            "Purple" ,
            "Brown",
            "Green"
    };

    final int[] themeIDs =  {
            R.style.Blue_Theme,
            R.style.Red_Theme,
            R.style.Black_Theme,
            R.style.Orange_Theme,
            R.style.Cyan_Theme,
            R.style.Purple_Theme,
            R.style.Brown_Theme,
            R.style.Green_Theme
    };


    private void showThemeDialog() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose a Theme:").setItems(themes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int clickedIndex) {
                savePrefInt("USER_THEME_ID", clickedIndex );
                showToastString("Need to restart the app to take effect");
            }
        });

        builder.create().show();
    }



    private void savePrefInt(String key, int num){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key,num) ;
        editor.commit();
    }

    private int readPrefInt(String key ){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return prefs.getInt(key,0);
    }



    void showToastString(String text)
    {

        toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


}
