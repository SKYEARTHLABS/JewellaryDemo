package com.example.navigationbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.navigationbar.model.User;
import com.example.navigationbar.sql.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    RecyclerView recyclerView;
    private User user;
    private EditText textInputEditTextName;
    private Toolbar mToolbar;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar=(Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle= new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        textInputEditTextName = (EditText) findViewById(R.id.FullNameEtt);
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

        user=new User();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView= findViewById(R.id.design_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
      //This statment is use for display username in navigation header
       TextView txtProfileView=navigationView.getHeaderView(0).findViewById(R.id.textView2);
        txtProfileView.setText(databaseHelper.getUsername());
//end
 /*       SQLiteDatabase db= databaseHelper.getReadableDatabase();
        String table = "TABLE_USER";
        String[] columns = {"COLUMN_USER_NAME"};
        String selection = "COLUMN_USER_EMAIL =?";
        String[] selectionArgs = {"COLUMN_USER_NAME"};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;
       Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
       startManagingCursor(cursor);
       */


       /*
        * This is for multiple image
        * which is shown in dashboard
          */
        RecyclerView recyclerView2=findViewById(R.id.recycler_jewellay2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext());
        recyclerView2.setAdapter(recyclerViewAdapter);

    }
   @Override
  public void onBackPressed() {
       Intent i = new Intent(getApplicationContext(),MainActivity.class);
       startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if(mToggle.onOptionsItemSelected(item)){
            return true;
       }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.nav_account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MyAccountFragment()).commit();
                break;

            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingFragment()).commit();
                break;
            case R.id.nav_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MessageFragment()).commit();
                break;
            case R.id.nav_aboutus:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutUsFragment()).commit();
                break;
            case R.id.nav_logout:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LogoutFragment()).commit();
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);



        return true;

    }
    @Override
    protected void onStart() {
        super.onStart();
        int spacing=20;
        int spanCount=2;
        boolean includeEdge=false;
        recyclerView = findViewById(R.id.recycler_jewellay1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        JwelaryAdapter adapter = new JwelaryAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount,spacing,includeEdge));

    }
}
