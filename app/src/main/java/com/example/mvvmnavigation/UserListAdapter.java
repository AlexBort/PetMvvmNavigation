package com.example.mvvmnavigation;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import com.example.mvvmnavigation.models.User;
import com.squareup.picasso.Picasso;
import java.util.List;

public class UserListAdapter extends BaseAdapter {

  private List<User> list;
  private LayoutInflater layoutInflater;

  public UserListAdapter(Context context, List<User> list) {
    layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.list = list;
  }

  @Override
  public int getCount() {
    return list.size();
  }

  @Override
  public Object getItem(int position) {
    return list.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view = convertView;
    if (view == null) {
      view = layoutInflater.inflate(R.layout.image_item, parent, false);
    }

    User user = list.get(position);
    String urlMedium = user.getPicture().getMedium();
    Picasso.get().load(urlMedium).into((ImageView) view.findViewById(R.id.image));

    return view;
  }
}
