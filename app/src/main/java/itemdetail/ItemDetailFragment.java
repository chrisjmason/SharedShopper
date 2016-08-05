package itemdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sharedshopper.chris.sharedshopper.R;

import utility.pojo.Item;

public class ItemDetailFragment extends Fragment implements ItemDetailInterface.View {

    private static final String ITEM_POSITION = "position";
    private ItemDetailInterface.Presenter presenter;
    private Item item;
    private int position;
    private TextView title;
    private TextView desc;
    private View fragView;

    public static ItemDetailFragment newInstance(int itemPosition){
        Bundle bundle = new Bundle();
        bundle.putInt(ITEM_POSITION, itemPosition);
        ItemDetailFragment fragment = new ItemDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ItemDetailPresenter(this);
        position = getArguments().getInt(ITEM_POSITION);
    }

    public void setUpDetail(Item item){
        title.setText(item.getTitle());
        desc.setText(item.getDescription());
        fragView.setBackgroundColor(ContextCompat.getColor(getActivity(), item.getColour()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        title = (TextView) fragView.findViewById(R.id.item_detail_title);
        desc = (TextView) fragView.findViewById(R.id.item_detail_desc);
        FloatingActionButton okFab = (FloatingActionButton) getActivity().findViewById(R.id.fab_detail_ok);

        setUpFab(okFab);

        presenter.getItem(position);

        return fragView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setUpFab(FloatingActionButton fab){
        fab.setRippleColor(ContextCompat.getColor(getContext(), R.color.yellow));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

}
