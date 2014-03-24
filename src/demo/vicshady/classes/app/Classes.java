package demo.vicshady.classes.app;


import android.app.Application;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.util.Log;
import demo.vicshady.classes.DTO.Preferences;
import demo.vicshady.classes.ErrReport.ExceptionHandler;
import demo.vicshady.classes.SQLite.DBConstant;
import demo.vicshady.classes.service.DataController;

public class Classes extends Application {

	static Classes ca;
	static DataController dataController;
	static SharedPreferences sharedPreferences;
	static Preferences preferences;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		ca = this;
		dataController = new DataController();
		preferences = new Preferences(this);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		ExceptionHandler.register(ca);
		createDatabase();
	}
	
	public void createDatabase()
	{
		Cursor c = getContentResolver().query(DBConstant.Query_Columns.CONTENT_URI, null, null, null, null);
		Log.i("Database", "Created");
		if( c!= null)
		{
			c.close();
			c = null;
		}
	}
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}
	public static Classes getApplication()
	{
		return ca;
	}

	public static DataController getDataController() {
		return dataController;
	}
	
	public static Preferences getPreferences() {
		return preferences;
	}
	
	public static SharedPreferences getSharedPreferences()
	{
		return sharedPreferences;
	}
}
