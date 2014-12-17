package com.example.viewpagersample;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class MyViewPagerAdapter extends PagerAdapter {

	private ArrayList<View> viewList;
	//private ArrayList<String> titleList;
	
	public MyViewPagerAdapter(ArrayList<View> viewList){
		this.viewList = viewList;
		//this.titleList = titleList;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return viewList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager)container).removeView(viewList.get(position));
    }

    //@Override
    //public CharSequence getPageTitle(int position) {
    //    return titleList.get(position);
    //}

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager)container).addView(viewList.get(position));
        return viewList.get(position);
    }
    
    @Override  
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);  
    }  
}
