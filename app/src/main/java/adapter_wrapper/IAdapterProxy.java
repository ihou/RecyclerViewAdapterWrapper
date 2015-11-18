package adapter_wrapper;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author houwenchang
 *         <p/>
 *         2015/11/17.
 */
public interface IAdapterProxy {

    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType);

    void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position);

    int getItemCount();

    int getItemViewType(int position);

    void onViewRecycled(RecyclerView.ViewHolder holder);

    void onViewAttachedToWindow(RecyclerView.ViewHolder holder);

    void onViewDetachedFromWindow(RecyclerView.ViewHolder holder);
}
