package demo.vicshady.classes.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import demo.vicshady.classes.R;
import demo.vicshady.classes.app.AppConstants;

public class HomeFragment extends Fragment {

	private ViewPager mPager;
	private ViewGroup bottomPages;
	private TextView ticker;
	public static final int NUM_PAGES = 7;
	static Typeface styleFont;
	ImageLoader imageLoader;
	DisplayImageOptions options;
	
    public final static String[] imageUrls = new String[] {
    	"https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s240-c/A%252520Photographer.jpg",
        "https://lh4.googleusercontent.com/--dq8niRp7W4/URquVgmXvgI/AAAAAAAAAbs/-gnuLQfNnBA/s240-c/A%252520Song%252520of%252520Ice%252520and%252520Fire.jpg",
        "https://lh5.googleusercontent.com/-7qZeDtRKFKc/URquWZT1gOI/AAAAAAAAAbs/hqWgteyNXsg/s240-c/Another%252520Rockaway%252520Sunset.jpg",
        "https://lh3.googleusercontent.com/--L0Km39l5J8/URquXHGcdNI/AAAAAAAAAbs/3ZrSJNrSomQ/s240-c/Antelope%252520Butte.jpg",
        "https://lh6.googleusercontent.com/-8HO-4vIFnlw/URquZnsFgtI/AAAAAAAAAbs/WT8jViTF7vw/s240-c/Antelope%252520Hallway.jpg",
        "https://lh4.googleusercontent.com/-WIuWgVcU3Qw/URqubRVcj4I/AAAAAAAAAbs/YvbwgGjwdIQ/s240-c/Antelope%252520Walls.jpg",
        "https://lh6.googleusercontent.com/-UBmLbPELvoQ/URqucCdv0kI/AAAAAAAAAbs/IdNhr2VQoQs/s240-c/Apre%2525CC%252580s%252520la%252520Pluie.jpg"
    };
	 public HomeFragment(){}
     
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
	        mPager = (ViewPager)rootView.findViewById(R.id.viewPager);
	        ticker = (TextView)rootView.findViewById(R.id.ticker);
	        bottomPages = (ViewGroup)rootView.findViewById(R.id.bottom_pages_image);
	        return rootView;
	    }

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			styleFont = Typeface.createFromAsset(getActivity().getAssets(), AppConstants.fontStyle);
			imageLoader = ImageLoader.getInstance();
	        // Initialize ImageLoader with configuration. Do it once.
	        imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
	        
	        options = new DisplayImageOptions.Builder()
	        .showImageOnLoading(R.drawable.ic_launcher) // resource or drawable
	        .showImageForEmptyUri(R.drawable.ic_launcher) // resource or drawable
	        .showImageOnFail(R.drawable.ic_launcher) //this is the image that will be displayed if download fails
	        .cacheInMemory()
	        .cacheOnDisc()
	        .build();
	        
	        mPager.setAdapter(new MyPagerAdapter());
	        
	        ticker.setSelected(true);
	        ticker.setTypeface(styleFont);
	        
		}
		
		
		/**
	     * A simple pager adapter that represents ImageView objects, in
	     * sequence.
	     */
	    private class MyPagerAdapter extends PagerAdapter {
	        public int getCount() {
	            return NUM_PAGES;
	        }

	        @Override
	        public Object instantiateItem(ViewGroup container, int position) {
	        	ImageView imageView = new ImageView(getActivity());
	        	imageView.setScaleType(ScaleType.FIT_CENTER);
	        	imageLoader.displayImage(imageUrls[position], imageView, options);
	        	container.addView(imageView);
				return imageView;
	        	}

	        @Override
	        public void destroyItem(ViewGroup container, int position, Object object) {
	            container.removeView((View) object);
	        }

	        @Override
	        public void setPrimaryItem(ViewGroup container, int position, Object object) {
	            super.setPrimaryItem(container, position, object);
	            int count = bottomPages.getChildCount();
	            for (int a = 0; a < count; a++) {
	                View child = bottomPages.getChildAt(a);
	                if (a == position) {
	                    child.setBackgroundColor(0xffcc0000); //#cc0000 //2ca5e0
	                } else {
	                    child.setBackgroundColor(0xffbbbbbb);
	                }
	            }
	        }

	        @Override
	        public boolean isViewFromObject(View view, Object object) {
	            return view.equals(object);
	        }

	        @Override
	        public void finishUpdate(View arg0) {
	        }

	        @Override
	        public void restoreState(Parcelable arg0, ClassLoader arg1) {
	        }

	        @Override
	        public Parcelable saveState() {
	            return null;
	        }

	        @Override
	        public void startUpdate(View arg0) {
	        }
	    }
}
