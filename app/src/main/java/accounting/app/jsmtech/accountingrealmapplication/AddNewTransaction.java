package accounting.app.jsmtech.accountingrealmapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import model.Transaction;

/**
 * Created by Sukriti on 6/20/16.
 */
public class AddNewTransaction extends AppCompatActivity {

    private TextView mTransactionName;
    private TextView mTransactionAmount;
    private Button mAddTransaction;
    private Switch mTransactionType;
    private Realm r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction);

        r = Realm.getInstance(this);

        mTransactionName = (TextView) findViewById(R.id.transaction_name);
        mTransactionAmount = (TextView) findViewById(R.id.transaction_amount);
        mAddTransaction = (Button) findViewById(R.id.add_trasaction);
        mTransactionType = (Switch) findViewById(R.id.transaction_type);


        mAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tN = mTransactionName.getText().toString();
                String temp = mAddTransaction.getText().toString();

                if (tN.isEmpty() || temp.isEmpty()) {
                    Toast.makeText(AddNewTransaction.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Add new Transaction and save to databas using Realm.
                    int tA = Integer.parseInt(mTransactionAmount.getText().toString());
                    r.beginTransaction();

                    Transaction t = r.createObject(Transaction.class);
                    t.setName(tN);
                    t.setAmount(tA);
                    t.setTransactionType(mTransactionType.isChecked());

                    r.commitTransaction(); // This Transaction has nothing to do with the app!

                    Toast.makeText(AddNewTransaction.this, "Transaction Added!", Toast.LENGTH_SHORT).show();

                    SharedPreferences prefs = getSharedPreferences(MainActivity.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor sP = getSharedPreferences(MainActivity.SHARED_PREFERENCES_NAME, MODE_PRIVATE).edit();
                    Integer newBal = prefs.getInt(MainActivity.BALANCE, 0);
                    sP.putInt(MainActivity.BALANCE, (tA + newBal));
                    sP.commit();
                    Intent i = new Intent(AddNewTransaction.this, MainActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent i = new Intent(AddNewTransaction.this, MainActivity.class);
//        startActivity(i);
//        finish();
//    }
}
