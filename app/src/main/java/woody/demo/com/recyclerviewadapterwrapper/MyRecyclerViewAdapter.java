package woody.demo.com.recyclerviewadapterwrapper;//package woody.demo.com.recyclerviewadapterwrapper;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

import adapter_wrapper.RecyclerAdapterWrapper;
import row.RowFooter;
import row.RowHeader;
import row.RowTypeA;

/**
 * Created by houwenchang on 2014/12/11.
 */
public class MyRecyclerViewAdapter extends RecyclerAdapterWrapper {

    private List<Model> modelList;

    public MyRecyclerViewAdapter(List<Model> modelList) {
        this.modelList = modelList;
    }


    @Override
    protected RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return RowHeader.onCreateDataViewHolder(parent, viewType);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        return RowFooter.onCreateDataViewHolder(parent, viewType);
    }


    @Override
    protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

        RowHeader.onBindDataViewHolder(holder);
    }

    @Override
    protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {
        RowFooter.onBindDataViewHolder(holder);
    }

    @Override
    public RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case Model.TYPE_A:
                return RowTypeA.onCreateDataViewHolder(viewGroup, viewType);
            case Model.TYPE_B:

                break;
            case Model.TYPE_C:

                break;
            case Model.TYPE_D:

                break;
            case Model.TYPE_E:

                break;
        }
        return null;
    }

    @Override
    public void onBindDataViewHolder(final RecyclerView.ViewHolder holder, int position) {


        int viewType = holder.getItemViewType();
        switch (viewType) {
            case Model.TYPE_A:
                RowTypeA.onBindDataViewHolder(holder, position, modelList.get(position), new ModelDeleteListener() {
                    @Override
                    public void onDelete(int pos) {


                        Log.e("","pos:"+pos);
//                        try {
                            modelList.remove(pos-1);
                            notifyItemRemoved(pos);
//                        } catch (IndexOutOfBoundsException e) {
//                            e.printStackTrace();
//                        }
                    }
                });
                break;
            case Model.TYPE_B:

                break;
            case Model.TYPE_C:

                break;
            case Model.TYPE_D:

                break;
            case Model.TYPE_E:

                break;
        }
    }

    @Override
    public int getDataItemCount() {
        return modelList.size();
    }

    @Override
    public int getDataItemViewType(int position) {
        return modelList.get(position).getType();
    }
}
