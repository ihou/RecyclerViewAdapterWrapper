package adapter_wrapper;//package woody.demo.com.recyclerviewadapterwrapper;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public abstract class RecyclerAdapterWrapper extends RecyclerView.Adapter {

    private final static int TYPE_HEADER = 100;
    private final static int TYPE_FOOTER = 101;

    protected abstract RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType);

    protected abstract RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType);

    public abstract RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup viewGroup, int viewType);

    protected abstract void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position);

    protected abstract void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position);

    public abstract void onBindDataViewHolder(RecyclerView.ViewHolder viewHolder, int position);

    public abstract int getDataItemCount();

    public abstract int getDataItemViewType(int position);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return onCreateHeaderViewHolder(parent, viewType);
        } else if (viewType == TYPE_FOOTER) {
            return onCreateFooterViewHolder(parent, viewType);
        } else {
            return onCreateDataViewHolder(parent, viewType);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0 && holder.getItemViewType() == TYPE_HEADER) {
            onBindHeaderViewHolder(holder, position);
        } else if (position == getDataItemCount() && holder.getItemViewType() == TYPE_FOOTER) {
            onBindFooterViewHolder(holder, position);
        } else {
            onBindDataViewHolder(holder, position - 1);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == getDataItemCount() + 1) {
            return TYPE_FOOTER;
        } else {
            return getDataItemViewType(position - 1);
        }
    }

    @Override
    public int getItemCount() {
        return getDataItemCount() + 2;
    }

}
