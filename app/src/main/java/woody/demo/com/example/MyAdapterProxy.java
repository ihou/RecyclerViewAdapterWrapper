package woody.demo.com.example;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Toast;


import adapter_wrapper.IAdapterProxy;
import woody.demo.com.example.row.RowTypeA;

/**
 * @author houwenchang
 *         <p/>
 *         2015/11/17.
 */
public class MyAdapterProxy implements IAdapterProxy {
    private List<Model> modelList;
    private RecyclerView.Adapter adapter;

    public MyAdapterProxy(RecyclerView.Adapter adapter, List<Model> modelList) {
        this.modelList = modelList;
        this.adapter = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        int viewType = holder.getItemViewType();
        switch (viewType) {
            case Model.TYPE_A:
                final Model model = modelList.get(position);
                RowTypeA.onBindDataViewHolder(holder, position, model, new ModelDeleteListener() {
                    @Override
                    public void onDelete(int pos) {
                        Toast.makeText(holder.itemView.getContext(), "pos:" + pos + "  " + model.getIndex(), Toast.LENGTH_SHORT).show();
                        modelList.remove(pos - 1);
                        adapter.notifyItemRemoved(pos);
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
    public int getItemCount() {
        return modelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return modelList.get(position).getType();
    }

}
