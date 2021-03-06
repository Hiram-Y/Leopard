package cn.yuan.leopard;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.yuan.leopardkit.LeopardHttp;
import com.yuan.leopardkit.ui.activitys.LeopardActivity;

import cn.yuan.leopard.fragments.MainTabFragment;
import cn.yuan.leopard.fragments.RquestFragment;
import cn.yuan.leopard.fragments.DownloadFragment;
import cn.yuan.leopard.fragments.UploadFragment;

public class MainActivity extends LeopardActivity {

    private TabLayout tabLayout;
    private MainTabFragment mainTabFragment;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**  建议初始化放在application **/
        LeopardHttp.init(this);//如果只想用下载 上传，直接初始化即可
        LeopardHttp.bindServer("http://wxwusy.applinzi.com/leopardWeb/app/");// 如果用到请求，要提前绑定域名喔
//        LeopardHttp.setUseCache(true);// 是否启动缓存
        initView();
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.main_tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("基本请求"));
        tabLayout.addTab(tabLayout.newTab().setText("下载管理"));
        tabLayout.addTab(tabLayout.newTab().setText("上传管理"));

        mainTabFragment = new MainTabFragment() {
            @Override
            public void addSubViewTab() {
                addTab("基本请求", RquestFragment.class);
                addTab("下载管理", DownloadFragment.class);
                addTab("上传管理", UploadFragment.class);
            }

            @Override
            public void loadFinishView(ViewPager viewPager, FragmentPagerAdapter mAdapter) {
                mViewPager = viewPager;
                mViewPager.setOffscreenPageLimit(2);//缓存多一个fragmemt
                tabLayout.setupWithViewPager(mViewPager);
                tabLayout.setTabsFromPagerAdapter(mAdapter);
            }

        };

        getSupportFragmentManager().beginTransaction().add(R.id.test_contanter, mainTabFragment).commit();
        //关联Tab

    }
}
