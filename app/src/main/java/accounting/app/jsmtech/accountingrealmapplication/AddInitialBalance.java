package accounting.app.jsmtech.accountingrealmapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Sukriti on 6/20/16.
 */
public class AddInitialBalance extends AppCompatActivity {

    private EditText mInitialBalance;
    private Button mAddInitialBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_initial_balance);

        mInitialBalance = (EditText)  findViewById(R.id.transaction_amount);
        mAddInitialBalance = (Button) findViewById(R.id.add_initial_balances);

        mAddInitialBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = mInitialBalance.getText().toString();
                int amount = Integer.parseInt(a);
                if(a.isEmpty()) {
                    Toast.makeText(AddInitialBalance.this,"Empty field",Toast.LENGTH_LONG).show();
                } else {

                    SharedPreferences.Editor sP = getSharedPreferences(MainActivity.SHARED_PREFERENCES_NAME, MODE_PRIVATE).edit();
                    sP.putBoolean("first_transaction_done", true);
                    sP.putInt(MainActivity.BALANCE, amount);
                    // sP.commit();
                    sP.apply();
                    Intent i = new Intent(AddInitialBalance.this, MainActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        });



    }
}
