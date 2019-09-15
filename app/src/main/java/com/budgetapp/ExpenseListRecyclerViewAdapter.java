package com.budgetapp;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Purchase} and makes a call to the
 * specified {@link HomeFragment.OnFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ExpenseListRecyclerViewAdapter extends RecyclerView.Adapter<ExpenseListRecyclerViewAdapter.ViewHolder> {

    private final List<Purchase> mValues;
    private final HomeFragment.OnFragmentInteractionListener mListener;

    public ExpenseListRecyclerViewAdapter(List<Purchase> items, HomeFragment.OnFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    public void setItems(List<Purchase> items) {
        mValues.clear();
        mValues.addAll(items);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_expense_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getPurchaseDate().substring(0, 10));
        holder.mContentView.setText('$' + Integer.toString(mValues.get(position).getCost()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Purchase mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
