package com.example.mvvmnavigation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.mvvmnavigation.databinding.FragmentListBinding;
import com.example.mvvmnavigation.models.User;
import java.util.List;

public class ListFragment extends Fragment {

  ListViewModel viewModel;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewModel = new ViewModelProvider(this).get(ListViewModel.class);
    getLifecycle().addObserver(viewModel);
    viewModel.getData().observe(this,
        new Observer<List<String>>() {
      @Override
      public void onChanged(List<String> strings) {
        Log.d("TEST", strings.toString());
      }
    });
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    final FragmentListBinding view = FragmentListBinding.inflate(inflater, container, false);
    view.setViewmodel(viewModel);
    final ListView listView = view.listView;
    viewModel.getUsersList().observe(this, new Observer<List<User>>() {
      @Override
      public void onChanged(List<User> list) {
        listView.setAdapter(new UserListAdapter(getContext(), list));
      }
    });
    return view.getRoot();
  }
}
