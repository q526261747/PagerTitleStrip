package com.example.viewpagedemo;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	private View view1,view2,view3;
	private ViewPager viewPager;
	private PagerTabStrip pagerTabStrip;
	
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
        
        pagerTabStrip = (PagerTabStrip) findViewById(R.id.pagertab);
        pagerTabStrip.setTabIndicatorColorResource(Color.RED);
        PagerAdapter pagerAdapter = new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0==viewList.get(Integer.parseInt(arg1.toString()));
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
				return position;
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
    }
}
