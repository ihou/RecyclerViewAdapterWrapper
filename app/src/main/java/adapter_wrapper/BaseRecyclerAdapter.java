package adapter_wrapper;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class BaseRecyclerAdapter extends RecyclerView.Adapter {

    private final static int TYPE_HEADER = 100;
    private final static int TYPE_FOOTER = 101;
    private boolean hasHeader;
    private boolean hasFooter;
    private View headerView;
    private View footerView;

    private RecyclerView.ViewHolder createHeaderViewHolder() {
        if (headerView == null) {
            throw new IllegalStateException("headerView can not be null if you had a header set");
        }
        return new RecyclerView.ViewHolder(headerView) {
        };
    }

    private RecyclerView.ViewHolder createFooterViewHolder() {
        if (footerView == null) {
            throw new IllegalStateException("footerView can not be null if you had a footer set");
        }
        return new RecyclerView.ViewHolder(footerView) {
        };
    }

    private IAdapterProxy adapterProxy;

    public BaseRecyclerAdapter(boolean hasHeader, boolean hasFooter, View header, View footer) {
        this.hasHeader = hasHeader;
        this.hasFooter = hasFooter;
        this.headerView = header;
        this.footerView = footer;
    }


    public void setAdapterProxy(IAdapterProxy adapterProxy) {
        this.adapterProxy = adapterProxy;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return createHeaderViewHolder();
        } else if (viewType == TYPE_FOOTER) {
            return createFooterViewHolder();
        } else {
            return adapterProxy.onCreateViewHolder(parent, viewType);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (hasHeader && hasFooter) {
            if (position != 0 && position != getItemCount() - 1) {
                adapterProxy.onBindViewHolder(holder, position - 1);
            }
        } else if (hasHeader) {
            if (position != 0) {
                adapterProxy.onBindViewHolder(holder, position - 1);
            }
        } else if (hasFooter) {
            if (position != getItemCount() - 1) {
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
            } else if (position == getItemCount() - 1) {
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
        if (holder.getItemViewType() != TYPE_FOOTER && holder.getItemViewType() != TYPE_HEADER) {
            adapterProxy.onViewAttachedToWindow(holder);
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if (holder.getItemViewType() != TYPE_FOOTER && holder.getItemViewType() != TYPE_HEADER) {
            adapterProxy.onViewDetachedFromWindow(holder);
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        if (holder.getItemViewType() != TYPE_FOOTER && holder.getItemViewType() != TYPE_HEADER) {
            adapterProxy.onViewRecycled(holder);
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    public boolean hasHeader() {
        return hasHeader;
    }

    public boolean hasFooter() {
        return hasFooter;
    }

    public View getHeader() {
        return headerView;
    }

    public View getFooter() {
        return footerView;
    }
}
