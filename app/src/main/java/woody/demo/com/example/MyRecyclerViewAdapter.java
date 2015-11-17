package woody.demo.com.example;//package woody.demo.com.recyclerviewadapterwrapper;

import java.util.List;


import android.view.View;


import adapter_wrapper.RecyclerAdapter;

/**
 * @author houwenchang
 *         <p/>
 *         2014/12/11.
 */
public class MyRecyclerViewAdapter extends RecyclerAdapter {

    private List<Model> modelList;

    public MyRecyclerViewAdapter(final List<Model> modelList,View header,View footer) {
        super(true, true);
        this.modelList = modelList;
        setAdapterProxy(new MyAdapterProxy(this, modelList));
        setHeader(header);
        setFooter(footer);
    }
}
