package woody.demo.com.recyclerviewadapterwrapper;//package woody.demo.com.recyclerviewadapterwrapper;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;

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
        final ArrayList<Model> modelList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            modelList.add(new Model(0, i + 1));
        }
        final MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(modelList);
        mRecyclerView.setAdapter(adapter);


        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelList.add(0, new Model(0, -1));
                adapter.notifyItemInserted(1);
                mRecyclerView.scrollToPosition(0);
            }
        });

    }

}
