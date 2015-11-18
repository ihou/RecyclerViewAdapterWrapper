package woody.demo.com.example;//package woody.demo.com.recyclerviewadapterwrapper;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;
    private ArrayList<Model> modelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });


        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (!ViewCompat.canScrollVertically(recyclerView, -1)) {
                    //top
                } else if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                    //bottom
                    Toast.makeText(getApplicationContext(), "load", Toast.LENGTH_SHORT).show();
                    final int index = modelList.get(modelList.size() - 1).getIndex();
                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<Model> tmp = new ArrayList<>();
                            for (int i = 0; i < 10; i++) {
                                tmp.add(new Model(0, index + i + 1));
                            }
                            modelList.addAll(modelList.size(), tmp);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
            }
        });


        for (int i = 0; i < 100; i++) {
            modelList.add(new Model(0, i + 1));
        }

        final View header = View.inflate(this, R.layout.row_header, null);
        View footer = View.inflate(this, R.layout.row_footer, null);
        mAdapter = new MyRecyclerViewAdapter(modelList, header, footer);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((TextView) header.findViewById(R.id.tv)).setText("Header Changed");
            }
        }, 3000);


        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelList.add(0, new Model(0, modelList.get(0).getIndex() - 1));
                mAdapter.notifyItemInserted(mAdapter.hasHeader() ? 1 : 0);
                mRecyclerView.scrollToPosition(0);
            }
        });


    }

}
