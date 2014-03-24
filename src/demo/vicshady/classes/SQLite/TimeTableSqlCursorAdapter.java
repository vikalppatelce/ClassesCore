/* HISTORY
 * CATEGORY 		:- ACTIVITY
 * DEVELOPER		:- VIKALP PATEL
 * AIM			    :- ADD IPD ACTIVITY
 * DESCRIPTION 		:- SAVE IPD
 * 
 * S - START E- END  C- COMMENTED  U -EDITED A -ADDED
 * --------------------------------------------------------------------------------------------------------------------
 * INDEX       DEVELOPER		DATE			FUNCTION		DESCRIPTION
 * --------------------------------------------------------------------------------------------------------------------
 * --------------------------------------------------------------------------------------------------------------------
 */
package demo.vicshady.classes.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import demo.vicshady.classes.R;
import demo.vicshady.classes.app.AppConstants;
import demo.vicshady.classes.app.Classes;

public class TimeTableSqlCursorAdapter extends SimpleCursorAdapter implements
		OnClickListener {

	private Context context;
	Typeface stylefont;

	private Cursor currentCursor;
	
	private Uri uri;
	@SuppressWarnings("deprecation")
	public TimeTableSqlCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, Uri uri) {
		super(context, layout, c, from, to);
		this.currentCursor = c;
		this.context = context;
		this.uri = uri;
		stylefont = Typeface.createFromAsset(Classes.getApplication().getApplicationContext().getAssets(), AppConstants.fontStyle);

	}

	@Override
	public View getView(int pos, View inView, ViewGroup parent) {
		View v = inView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			v = inflater.inflate(R.layout.time_table_item, null);
			v = inflater.inflate(R.layout.new_time_table_item, null);
		}

		this.currentCursor.moveToPosition(pos);

		long id = getCursor().getLong(getCursor().getColumnIndex(DBConstant.Query_Columns.COLUMN_ID));
//		TextView date = (TextView) v.findViewById(R.id.timetabledate);
//		TextView day = (TextView) v.findViewById(R.id.timetableday);
		TextView start = (TextView) v.findViewById(R.id.timetabletime);
//		TextView batch = (TextView) v.findViewById(R.id.timetable_batchname);
		TextView lecture = (TextView) v.findViewById(R.id.timetable_lecture);
		TextView prof = (TextView) v.findViewById(R.id.timetable_prof);
		TextView remark = (TextView) v.findViewById(R.id.timetable_remark);
		
		LinearLayout layoutRemark = (LinearLayout) v.findViewById(R.id.layoutRemark);

		TextView txtsub = (TextView) v.findViewById(R.id.lecture);
		TextView txttime = (TextView) v.findViewById(R.id.time);
		TextView txtprof = (TextView) v.findViewById(R.id.prof);
		TextView txtrem = (TextView) v.findViewById(R.id.remark);
		
		
//		date.setTypeface(stylefont);
//		day.setTypeface(stylefont);
		start.setTypeface(stylefont);
//		batch.setTypeface(stylefont);
		lecture.setTypeface(stylefont);
		prof.setTypeface(stylefont);
		remark.setTypeface(stylefont);
		
		txtsub.setTypeface(stylefont);
		txttime.setTypeface(stylefont);
		txtprof.setTypeface(stylefont);
		txtrem.setTypeface(stylefont);
		
		
//		date.setText(this.currentCursor.getString(currentCursor.getColumnIndex(DBConstant.Time_Table_Columns.COLUMN_TIME_TABLE_DATE)));
//		day.setText(this.currentCursor.getString(currentCursor.getColumnIndex(DBConstant.Time_Table_Columns.COLUMN_END_TIME)));
		start.setText(this.currentCursor.getString(currentCursor.getColumnIndex(DBConstant.Time_Table_Columns.COLUMN_START_TIME))
				+" to "
				+ this.currentCursor.getString(currentCursor.getColumnIndex(DBConstant.Time_Table_Columns.COLUMN_END_TIME))
				);
//		batch.setText(this.currentCursor.getString(currentCursor.getColumnIndex(DBConstant.Time_Table_Columns.COLUMN_BATCH_NAME)));
		lecture.setText(this.currentCursor.getString(currentCursor.getColumnIndex(DBConstant.Time_Table_Columns.COLUMN_LECTURE)));
		prof.setText(this.currentCursor.getString(currentCursor.getColumnIndex(DBConstant.Time_Table_Columns.COLUMN_PROFESSOR)));
		
		if (!TextUtils.isEmpty(this.currentCursor.getString(currentCursor.getColumnIndex(DBConstant.Time_Table_Columns.COLUMN_REMARK))))
		{
			layoutRemark.setVisibility(View.VISIBLE);
			remark.setText(this.currentCursor.getString(currentCursor.getColumnIndex(DBConstant.Time_Table_Columns.COLUMN_REMARK)));	
		}
		
		v.setTag(String.valueOf(id));
//		Button removeLOV = (Button) v.findViewById(R.id.btnRemoveLOV);
//		removeLOV.setOnClickListener(this);
//		removeLOV.setTag(this.currentCursor.getString(currentCursor.getColumnIndex(DBConstant.Location_Columns.COLUMN_ID)));
		return (v);
	}

	@SuppressWarnings("deprecation")
	public void ClearSelections() {
		this.currentCursor.requery();

	}

	@Override
	public void onClick(View v) {
		Button b = (Button) v;
		String _id = (String) b.getTag();
		this.context.getContentResolver().delete(this.uri, "_id=?", new String[] { _id });
	}
}
