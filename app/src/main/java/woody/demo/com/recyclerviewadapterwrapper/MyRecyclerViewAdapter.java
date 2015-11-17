package woody.demo.com.recyclerviewadapterwrapper;//package woody.demo.com.recyclerviewadapterwrapper;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import adapter_wrapper.MyAdapterProxy;
import adapter_wrapper.RecyclerAdapterWrapper;
import row.RowFooter;
import row.RowHeader;

/**
 * @author houwenchang
 *         <p/>
 *         2014/12/11.
 */
public class MyRecyclerViewAdapter extends RecyclerAdapterWrapper{

    private List<Model> modelList;

    public MyRecyclerViewAdapter(final List<Model> modelList) {
        super(true, true);
        this.modelList = modelList;
        setAdapterProxy(new MyAdapterProxy(this, modelList));
    }


    @Override
    protected RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return RowHeader.onCreateDataViewHolder(parent, viewType);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        return RowFooter.onCreateDataViewHolder(parent, viewType);
    }

}
