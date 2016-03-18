package au.usyd.capstone.indoorandroid;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.aboutlibraries.ui.LibsSupportFragment;

/**
 * Created by LYH on 16/3/10.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MapsFragment.OnMapsFragmentInteractionListener,
        AboutFragment.OnAboutFragmentInteractionListener,
        BuildingFragment.OnBuildingFragmentInteractionListener

{


//    得到fragmentManager,用于填充fragment
    final FragmentManager fragmentManager = getSupportFragmentManager();

//    得到开源控件AboutLibrary的fragment
    LibsSupportFragment libsSupportFragment = new LibsBuilder()
        .withAboutIconShown(true)
        .withAboutVersionShown(true)
        .withAboutDescription("This is a small sample which can be set in the about my app description file.<br /><b>You can style this with html markup :D</b>")
                //get the fragment
        .supportFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        if (fab != null) {
//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                }
//            });
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.setDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        initMap();
    }

//    返回键按下
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        // Configure the search menu_info and add any event listeners...

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    侧滑边栏 菜单选项
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            initMap();
        } else if (id == R.id.nav_building) {
            initBuilding();
        } else if (id == R.id.nav_other) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_about) {
            initAbout();
        } else if (id == R.id.nav_help) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



//    填充FrameLayout为MapFragment
    private void initMap() {
        fragmentManager.beginTransaction().replace(R.id.container, new MapsFragment()).commit();
    }

//    填充FrameLayout为MapFragment
    private void initAbout() {
        fragmentManager.beginTransaction().replace(R.id.container, new AboutFragment()).commit();
    }

    private void initBuilding(){
        fragmentManager.beginTransaction().replace(R.id.container, new BuildingFragment()).commit();
    }


//    开源组件aboutLibrary: https://github.com/mikepenz/AboutLibraries
    private void initAboutLibrary(){
        fragmentManager.beginTransaction().replace(R.id.container, libsSupportFragment).commit();
    }


//    MapsFragment.OnMapsFragmentInteractionListener
    @Override
    public void onMapsFragmentInteraction(Uri uri) {

    }

//    AboutFragment.OnAboutFragmentInteractionListener
    @Override
    public void onAboutFragmentInteraction(Uri uri) {

    }

//    BuildingFragment.OnBuildingFragmentInteractionListener
    @Override
    public void onBuildingFragmentInteraction(Uri uri) {

    }
}
