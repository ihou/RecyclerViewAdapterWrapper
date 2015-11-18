package woody.demo.com.example;//package woody.demo.com.recyclerviewadapterwrapper;

import java.util.List;

import android.view.View;


import adapter_wrapper.BaseRecyclerAdapter;

/**
 * @author houwenchang
 *         <p/>
 *         2014/12/11.
 */
public class MyRecyclerViewAdapter extends BaseRecyclerAdapter {
    public MyRecyclerViewAdapter(List<Model> modelList, View header, View footer) {
        super(true, true, header, footer);
        setAdapterProxy(new MyAdapterProxy(this, modelList));
    }
}
