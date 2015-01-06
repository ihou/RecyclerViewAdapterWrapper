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


//        mRecyclerView.setItemAnimator(new RecyclerView.ItemAnimator(){
//            @Override
//            public void runPendingAnimations() {
//
//            }
//
//            @Override
//            public boolean animateRemove(RecyclerView.ViewHolder holder) {
//                return false;
//            }
//
//            @Override
//            public boolean animateAdd(RecyclerView.ViewHolder holder) {
//                return false;
//            }
//
//            @Override
//            public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
//                return false;
//            }
//
//            @Override
//            public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
//                return false;
//            }
//
//            @Override
//            public void endAnimation(RecyclerView.ViewHolder item) {
//
//            }
//
//            @Override
//            public void endAnimations() {
//
//            }
//
//            @Override
//            public boolean isRunning() {
//                return false;
//            }
//        });

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
