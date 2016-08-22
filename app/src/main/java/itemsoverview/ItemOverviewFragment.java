package itemsoverview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.sharedshopper.chris.sharedshopper.Injector;
import com.sharedshopper.chris.sharedshopper.R;

import java.util.List;

import itemadd.ItemAddActivity;
import itemdetail.ItemDetailActivity;
import utility.CustomAdapter;
import utility.pojo.Item;

public class ItemOverviewFragment extends Fragment implements ItemOverviewInterface.View{

    private CustomAdapter mAdapter;
    private ItemOverviewInterface.Presenter itemPresenter;
    private RecyclerView recyclerView;
    private Context mContext;
    public static final String EXTRA_POSITION = "position";

    public static ItemOverviewFragment newInstance(){
        return new ItemOverviewFragment();
    }

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_item_overview, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.item_recycler_view);
        recyclerView.setAdapter(mAdapter);
        itemPresenter.loadItems();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        FloatingActionButton addButton = (FloatingActionButton) root
                .findViewById(R.id.fab_new_item);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemPresenter.addNewItem();
            }
        });

        setUpRefresh(root);

        return root;
    }

    @Override
    public void updateView(List<Item> items){
        mAdapter.setItemList(items);
        mAdapter.notifyDataSetChanged();
    }



    public void setUpRefresh(View root){
        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) root
                .findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                itemPresenter.loadItems();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void goToAdd() {
        Intent intent = new Intent(mContext, ItemAddActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(ItemOverviewFragment.this.getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToDetail(int position) {
        Intent intent = new Intent(mContext, ItemDetailActivity.class);
        intent.putExtra(EXTRA_POSITION, position);
        mContext.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemPresenter = new ItemOverviewPresenter(this);
        mAdapter = new CustomAdapter(itemPresenter, mContext);
    }

    @Override
    public void onStart() {
        super.onStart();
        itemPresenter.loadItems();
    }



}
