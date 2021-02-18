package dwiyanhartono.com.ksp3.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {super(context, Environment.getExternalStoragePublicDirectory("Colsys").getAbsolutePath() + "/" + Constant.DB_NAME, null, Constant.DB_VERSION);
    }


    //WHEN TB IS CREATED
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("createdb", "New ");
        try {
            db.execSQL(Constant.CREATE_TB1);
            db.execSQL(Constant.CREATE_TB2);
            db.execSQL(Constant.CREATE_TB3);
            db.execSQL(Constant.CREATE_TB4);
            db.execSQL(Constant.CREATE_TB5);
            db.execSQL(Constant.CREATE_TB6);
            db.execSQL(Constant.CREATE_TB7);
            db.execSQL(Constant.CREATE_TB8);
            db.execSQL(Constant.CREATE_TB9);
            db.execSQL(Constant.CREATE_TB10);
            db.execSQL(Constant.CREATE_TB11);
            db.execSQL(Constant.CREATE_TB12);
            db.execSQL(Constant.CREATE_TB13);
            db.execSQL(Constant.CREATE_TB14);

            final String Insert_Data = "INSERT INTO " + Constant.TB_NAME2 + " (type,code,'desc',status) VALUES('TMR','M039','600000','1')";
            db.execSQL(Insert_Data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //UPGRADE TB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("versionluar", "Updating table from " + oldVersion + " to " + newVersion);
//        if (oldVersion < 2) {
//        }
        Log.d("versionluar", String.valueOf(newVersion) + String.valueOf(oldVersion));
    }

}