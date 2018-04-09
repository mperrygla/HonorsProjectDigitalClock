package ans.myapp.digitalclock;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Creates SharedPreference for the application. and provides access to it
 */

public class PreferenceUtil {

    private static SharedPreferences sharedPreferences = null;



    public static SharedPreferences.Editor putData(Context mContext) {
        if (sharedPreferences == null) {
            sharedPreferences = mContext.getSharedPreferences("file", Context.MODE_PRIVATE);
        }
        return sharedPreferences.edit();
    }

    public static SharedPreferences.Editor clearall(Context mContext) {
        if (sharedPreferences == null) {
            sharedPreferences = mContext.getSharedPreferences("file", Context.MODE_PRIVATE);
        }
        return sharedPreferences.edit();
    }

    public static SharedPreferences getData(Context mContext) {
        if (sharedPreferences == null) {
            sharedPreferences = mContext.getSharedPreferences("file", Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }


    /**
     * Puts new Key and its Values into SharedPreference map.
     *
     * @param key
     * @param value
     */
   /* public static void putValue(String key, String value) {
        editor.putString(key, value);
    }*/

    /**
     * Puts new Key and its Values into SharedPreference map.
     *
     * @param key
     * @param value
     */
    /*public static void putValue(String key, int value) {
        editor.putInt(key, value);
    }*/

    /**
     * Puts new Key and its Values into SharedPreference map.
     *
     * @param key
     * @param value
     */
    /*public static void putValue(String key, long value) {
        editor.putLong(key, value);
    }*/

    /**
     * Puts new Key and its Values into SharedPreference map.
     *
     * @param key
     * @param value
     */
   /* public static void putValue(String key, boolean value) {
        editor.putBoolean(key, value);
    }*/

    /**
     * saves the values from the editor to the SharedPreference
     */
    /*public static void save() {
        editor.commit();
    }*/

    /**
     * returns a values associated with a Key default value ""
     *
     * @return String
     */
/*    public static String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }*/

    /**
     * returns a values associated with a Key default value -1
     *
     * @return String
     */
 /*   public static int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }*/

    /**
     * returns a values associated with a Key default value -1
     *
     * @return String
     */
    public static long getLong(String key, long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    /**
     * returns a values associated with a Key default value false
     *
     * @return String
     */
   /* public static boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }*/

    /**
     * Checks if key is exist in SharedPreference
     *
     * @param key
     * @return boolean
     */
    public static boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * returns map of all the key value pair available in SharedPreference
     *
     * @return Map<String, ?>
     */
   /* public static Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }*/

}
