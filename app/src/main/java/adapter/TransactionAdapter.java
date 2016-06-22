package adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import accounting.app.jsmtech.accountingrealmapplication.R;
import model.Transaction;

/**
 * Created by Sukriti on 6/20/16.
 */
public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.RV_ViewHolder> {

    private List<Transaction> listOfItems;
    private Context mContext;
    View itemView;



    public TransactionAdapter(Context context, ArrayList<Transaction> listOfGroceries) {
        this.mContext = context;
        this.listOfItems = listOfGroceries;

    }

    @Override
    public RV_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_transaction_item, parent, false); // link to xml
        return new RV_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RV_ViewHolder holder, int position) {
        final Transaction t  = listOfItems.get(holder.getAdapterPosition());
        holder.mTransactionName.setText(t.getName());
        holder.mTransactionAmount.setText(t.getAmount() + "");
        if(t.getTransactionType()==true){
            holder.mTransactionAmount.setTextColor(Color.RED);
        }
        else {
            holder.mTransactionAmount.setTextColor(Color.GREEN);
        }

    }



    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class RV_ViewHolder extends RecyclerView.ViewHolder {

        protected TextView mTransactionName;
        protected TextView mTransactionAmount;

        public RV_ViewHolder(View itemView) {
            super(itemView);

            mTransactionName = (TextView) itemView.findViewById(R.id.transaction_name);
            mTransactionAmount = (TextView) itemView.findViewById(R.id.transaction_amount);


        }


    }
}
