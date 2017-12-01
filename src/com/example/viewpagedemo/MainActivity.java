package com.example.viewpagedemo;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.R.integer;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	private View view1,view2,view3;
	private ViewPager viewPager;
	//private PagerTabStrip pagerTabStrip;
	private ImageView cursor;
	private int bmpw = 0;
	private int offset = 0;
	private int currIndex = 0;
	
	
	private List<View> viewList;
	private List<String> titleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.layout1, null);
        view2 = inflater.inflate(R.layout.layout2, null);
        view3 = inflater.inflate(R.layout.layout3, null);
        
        viewList = new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        
        titleList = new ArrayList<String>();
        titleList.add("体育");
        titleList.add("音乐");
        titleList.add("娱乐");
        initCursorPos();
//        pagerTabStrip = (PagerTabStrip) findViewById(R.id.pagertab);
//        pagerTabStrip.setTabIndicatorColor(Color.RED);
        PagerAdapter pagerAdapter = new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0==arg1;
				//return arg0==viewList.get(Integer.parseInt(arg1.toString()));
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return viewList.size();
			}
			
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// TODO Auto-generated method stub
				container.removeView(viewList.get(position));
			}
			
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				container.addView(viewList.get(position));
				return viewList.get(position);
				//return position;
			}
			
			@Override
			public CharSequence getPageTitle(int position) {
				// TODO Auto-generated method stub
				return titleList.get(position);
			}
			/*有关数组和初始化，其实都是这了这一步，其实我们完全可以用下面这个代码来取代它们
			 * 这样效果是一样的，只是代码不好维护而已。
			 * @Override  
			public CharSequence getPageTitle(int position) {  
			    // TODO Auto-generated method stub  
			    switch (position) {  
			    case 0:  
			        return "王鹏";  
			    case 1:  
			        return "姜语";  
			    case 2:  
			        return "结婚";  
			  
			    default:  
			        return "";  
			    }  
			}  */
		};
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }
    //初始化指示器位置  
    public void initCursorPos() {  
        // 初始化动画  
        cursor = (ImageView) findViewById(R.id.cursor);  
        bmpw = BitmapFactory.decodeResource(getResources(), R.drawable.processmanager_setting_icon)  
                .getWidth();// 获取图片宽度  
  
        DisplayMetrics dm = new DisplayMetrics();  
        getWindowManager().getDefaultDisplay().getMetrics(dm);  
        int screenW = dm.widthPixels;// 获取分辨率宽度  
        offset = (screenW / viewList.size() - bmpw) / 2;// 计算偏移量  
  
        Matrix matrix = new Matrix();  
        matrix.postTranslate(offset, 0);  
        cursor.setImageMatrix(matrix);// 设置动画初始位置  
    }  
    public class MyPageChangeListener implements OnPageChangeListener{
    	int one = offset * 2 + bmpw;// 页卡1 -> 页卡2 偏移量 
    	int initial = 0;//游标的位置
 	
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override 
		public void onPageSelected(int arg0) { 
			Animation animation = new TranslateAnimation(one *initial,one *arg0,0,0); 
			initial = arg0; 
			
			currIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(300);
			cursor.startAnimation(animation);
		}
    	
    }
}
