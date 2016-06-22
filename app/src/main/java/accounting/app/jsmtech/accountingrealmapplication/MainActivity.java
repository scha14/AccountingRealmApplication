package accounting.app.jsmtech.accountingrealmapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.TransactionAdapter;
import io.realm.Realm;
import io.realm.RealmResults;
import model.Transaction;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFERENCES_NAME = "BalancePrefernce";
    public static final String BALANCE = "initial_balance";

    private TextView mBalance;
    private RecyclerView recList;
    private TransactionAdapter mAdapter;
    private ArrayList<Transaction> mTrasactionList = new ArrayList<>();
    private Realm r;

    int balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = Realm.getInstance(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
     //   balance = prefs.getInt(BALANCE, -1);
        boolean a = prefs.getBoolean("first_transaction_done", false);
        balance = prefs.getInt(BALANCE, 0);

        if(a == false) {
            // User has not set Initial Balance Ever,
            Intent i = new Intent(MainActivity.this, AddInitialBalance.class);
            startActivity(i);
        }


        mBalance = (TextView) findViewById(R.id.balance);
        mBalance.setText(balance + "");
        recList = (RecyclerView) findViewById(R.id.transaction_recyclier_view);



        final LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        mAdapter = new TransactionAdapter(MainActivity.this, mTrasactionList);
        recList.setAdapter(mAdapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent  = new Intent(MainActivity.this, AddNewTransaction.class);
                startActivity(intent);

            }
        });



        // Fetch Data From RealmDatabase using a RealmQuery!


        RealmResults<Transaction> results1 =
                r.where(Transaction.class).findAll();



        for(Transaction c : results1) {
            mTrasactionList.add(c);
        }

        mAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
