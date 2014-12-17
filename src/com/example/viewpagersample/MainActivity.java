package com.example.viewpagersample;

import java.util.ArrayList;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView1;
	private TextView textView2;
	private TextView textView3;
	
	private ImageView tabImage;
	private int imageWidth = 0;
	private int offset = 0;
	
	private View view1;
	private View view2;
	private View view3;
	
	private ArrayList<View> viewList = null;
	private ImageView[] dotList;
	//private ArrayList<String> titleList = null;
	
	private ViewPager viewPager;
	private MyViewPagerAdapter pagerAdapter;
	//private PagerTitleStrip pageTitle;
	//private PagerTabStrip pageTitleTab;
	
	private int currentIndex = 0;
	private ImageView dotImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initImageView();
		initTextView();
		initViewPager();	
		//titleList = new ArrayList<String>();
		//titleList.add("page 1");
		//titleList.add("page 2");
		//titleList.add("page 3");	
		//pageTitle = (PagerTitleStrip) findViewById(R.id.pagertitle);	
		//pageTitleTab = (PagerTabStrip) findViewById(R.id.pagertitleTab); 
		//pageTitleTab.setTabIndicatorColor(getResources().getColor(R.color.gold));  
		//pageTitleTab.setDrawFullUnderline(false); 
		//pageTitleTab.setBackgroundColor(getResources().getColor(R.color.azure)); 
		//pageTitleTab.setTextSpacing(10);
	}

	private void initImageView(){
		tabImage = (ImageView) findViewById(R.id.tab_image);
		
		imageWidth = BitmapFactory.decodeResource(getResources(), R.drawable.tab_bar).getWidth();
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		offset = (dm.widthPixels/3 - imageWidth)/2;
		
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		tabImage.setImageMatrix(matrix);
	}
	
	private void initTextView(){
		textView1 = (TextView) findViewById(R.id.text_view_1);
		textView2 = (TextView) findViewById(R.id.text_view_2);
		textView3 = (TextView) findViewById(R.id.text_view_3);
		
		textView1.setOnClickListener(new MyOnClickListener(0));
		textView2.setOnClickListener(new MyOnClickListener(1));
		textView3.setOnClickListener(new MyOnClickListener(2));
	}
	
	private void initViewPager(){
		LayoutInflater flater = (LayoutInflater) LayoutInflater.from(this);
		view1 = flater.inflate(R.layout.view_1, null);
		view2 = flater.inflate(R.layout.view_2, null);
		view3 = flater.inflate(R.layout.view_3, null);
		
		viewList = new ArrayList<View>();
		viewList.add(view1);
		viewList.add(view2);
		viewList.add(view3);
		
		pagerAdapter = new MyViewPagerAdapter(viewList);
		
		viewPager = (ViewPager) findViewById(R.id.search_viewpager);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setCurrentItem(0);
		
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		
		ViewGroup group = (ViewGroup) findViewById(R.id.dot_group); 
		dotList = new ImageView[viewList.size()];
		
		for(int i = 0; i < viewList.size(); i++){
			dotImage = new ImageView(MainActivity.this); 
			//dotImage.setLayoutParams(new LayoutParams(10,10)); 
			//dotImage.setPadding(10,0,10,0);
			dotList[i] = dotImage; 
			if(i == 0){ 
				//默认进入程序后第一张图片被选中; 
				dotList[i].setBackgroundResource(R.drawable.dot_icon_focused); 
			}else{ 
				dotList[i].setBackgroundResource(R.drawable.dot_icon_unfocused); 
			} 
			
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
			params.setMargins(10, 0, 10, 0);
			group.addView(dotImage, params); 
		} 
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private class MyOnClickListener implements OnClickListener {
		private int index = 0;
		
		public MyOnClickListener(int currentIndex){
			this.index = currentIndex;
		}
		@Override
		public void onClick(View v){
			viewPager.setCurrentItem(index);
		}
	}
	
	private class MyOnPageChangeListener implements OnPageChangeListener {
		
		private int ONE = 2*offset + imageWidth;
		private int TWO = ONE*2;
		
		@Override  
		public void onPageScrolled(int arg0, float arg1, int arg2){
			
		}
		  
		@Override  
		public void onPageSelected(int arg0){
			Animation animation = new TranslateAnimation(ONE * currentIndex, ONE * arg0, 0, 0);
			animation.setDuration(300);
			animation.setFillAfter(true);
			
			currentIndex = arg0;
			tabImage.startAnimation(animation);
			
			for(int i = 0; i < viewList.size(); i++){
				if(i == arg0){
					dotList[i].setBackgroundResource(R.drawable.dot_icon_focused); 
				}
				else {
					dotList[i].setBackgroundResource(R.drawable.dot_icon_unfocused);
				}
			}
		}
		  
		@Override
		public void onPageScrollStateChanged(int arg0){
			
		}
	}
}
