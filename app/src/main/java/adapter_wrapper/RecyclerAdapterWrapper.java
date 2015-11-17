package adapter_wrapper;


import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class RecyclerAdapterWrapper extends RecyclerView.Adapter {

    private final static int TYPE_HEADER = 100;
    private final static int TYPE_FOOTER = 101;
    private boolean hasHeader = true;
    private boolean hasFooter = true;

    protected RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    protected RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    private IAdapterProxy adapterProxy;

    public RecyclerAdapterWrapper(boolean hasHeader, boolean hasFooter) {
        this.hasHeader = hasHeader;
        this.hasFooter = hasFooter;
    }


    public void setAdapterProxy(IAdapterProxy adapterProxy) {
        this.adapterProxy = adapterProxy;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return onCreateHeaderViewHolder(parent, viewType);
        } else if (viewType == TYPE_FOOTER) {
            return onCreateFooterViewHolder(parent, viewType);
        } else {
            return adapterProxy.onCreateViewHolder(parent, viewType);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (hasHeader && hasFooter) {
            if (position == 0) {
                onBindHeaderViewHolder(holder, position);
            } else if (position == getItemCount()) {
                onBindFooterViewHolder(holder, position);
            } else {
                adapterProxy.onBindViewHolder(holder, position - 1);
            }
        } else if (hasHeader) {
            if (position == 0) {
                onBindHeaderViewHolder(holder, position);
            } else {
                adapterProxy.onBindViewHolder(holder, position - 1);
            }
        } else if (hasFooter) {
            if (position == getItemCount() - 1 /*&& holder.getItemViewType() == TYPE_FOOTER*/) {
                onBindFooterViewHolder(holder, position);
            } else {
                adapterProxy.onBindViewHolder(holder, position);
            }
        } else {
            adapterProxy.onBindViewHolder(holder, position);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (hasHeader && hasFooter) {
            if (position == 0) {
                return TYPE_HEADER;
            } else if (position == adapterProxy.getItemCount() + 1) {
                return TYPE_FOOTER;
            } else {
                return adapterProxy.getItemViewType(position - 1);
            }
        } else if (hasHeader) {
            if (position == 0) {
                return TYPE_HEADER;
            } else {
                return adapterProxy.getItemViewType(position - 1);
            }
        } else if (hasFooter) {
            if (position == getItemCount() - 1) {
                return TYPE_FOOTER;
            } else {
                return adapterProxy.getItemViewType(position);
            }
        } else {
            return adapterProxy.getItemViewType(position);
        }

    }

    @Override
    public int getItemCount() {
        if (hasHeader && hasFooter) {
            return adapterProxy.getItemCount() + 2;
        } else if (hasHeader) {
            return adapterProxy.getItemCount() + 1;
        } else if (hasFooter) {
            return adapterProxy.getItemCount() + 1;
        }
        return adapterProxy.getItemCount();
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        if (holder.getItemViewType() == TYPE_FOOTER) {
            isFooterShown = true;
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if (holder.getItemViewType() == TYPE_FOOTER) {
            isFooterShown = false;
        }
    }

    private boolean isFooterShown;

    public boolean isFooterShown() {
        return isFooterShown;
    }

    public boolean hasHeader() {
        return hasHeader;
    }

    public boolean hasFooter() {
        return hasFooter;
    }
}
