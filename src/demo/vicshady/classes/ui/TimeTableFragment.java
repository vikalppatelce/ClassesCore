package demo.vicshady.classes.ui;

import demo.vicshady.classes.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TimeTableFragment extends Fragment {

	 public TimeTableFragment(){}
     
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.fragment_timetable, container, false);
	        return rootView;
	    }
}
