package cz.codecamp.seven.ui.adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cz.codecamp.seven.R;
import cz.codecamp.seven.domain.User;

/**
 * Adapter for list of users
 * Created by David Bilik[david.bilik@ackee.cz] on {07/04/16}
 **/
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    public static final String TAG = UsersAdapter.class.getName();
    Cursor cursor;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.bindUser(new User(cursor));
    }

    @Override
    public int getItemCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    public void setData(Cursor c) {
        this.cursor = c;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtSurname;
        TextView txtAge;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtSurname = (TextView) itemView.findViewById(R.id.txt_surname);
            txtAge = (TextView) itemView.findViewById(R.id.txt_age);
        }

        public void bindUser(User user) {
            txtName.setText("Name:\t" + user.getName());
            txtSurname.setText("Surname:\t" + user.getSurname());
            txtAge.setText("Age:\t" + String.valueOf(user.getAge()));
        }

    }
}
