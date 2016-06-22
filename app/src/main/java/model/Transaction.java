package model;

import io.realm.RealmObject;

/**
 * Created by Sukriti on 6/20/16.
 */
public class Transaction extends RealmObject {

    // @PrimaryKey @Ignore

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(boolean transactionType) {
        this.transactionType = transactionType;
    }

    // Name, Amount, Type
    private String name;
    private int amount;
    private boolean transactionType; // Expenditure or Deposit





}
