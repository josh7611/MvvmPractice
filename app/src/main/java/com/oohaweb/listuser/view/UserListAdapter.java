package com.oohaweb.listuser.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oohaweb.listuser.R;
import com.oohaweb.listuser.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<User> users;
    private static ClickListener clickListener;

    public UserListAdapter(Context context, List<User> users) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.users = users;

        clickListener = new ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                User selectedUser = users.get(position);
                Intent intent = new Intent(v.getContext(), UserDetailActivity.class);
                intent.putExtra("userId", selectedUser.getLogin());
                v.getContext().startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position, View v) {
                User selectedUser = users.get(position);
                Intent intent = new Intent(v.getContext(), UserDetailActivity.class);
                intent.putExtra("userId", selectedUser.getLogin());
                v.getContext().startActivity(intent);
            }
        };
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = users.get(position);
        holder.loginTextView.setText(user.getLogin());
        if (!user.isSite_admin()) {
            holder.siteAdminTextView.setVisibility(View.GONE);
        } else {
            holder.siteAdminTextView.setVisibility(View.VISIBLE);
        }
        Picasso.with(holder.siteAdminTextView.getContext())
                .load(user.getAvatar_url())
                .transform(new CropCircleTransformation())
                .into(holder.avatarImageView);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_user, null);
        return new ViewHolder(view);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView loginTextView;
        TextView siteAdminTextView;
        ImageView avatarImageView;

        ViewHolder(@NonNull View view) {
            super(view);
            loginTextView = view.findViewById(R.id.login);
            siteAdminTextView = view.findViewById(R.id.site_admin);
            avatarImageView = view.findViewById(R.id.avatar_view);

            LinearLayout userItem = view.findViewById(R.id.userItem);
            if (null != userItem) {
                userItem.setOnClickListener(this);
                userItem.setOnLongClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }
}
