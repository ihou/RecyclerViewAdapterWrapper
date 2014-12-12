package row;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import woody.demo.com.recyclerviewadapterwrapper.R;

/**
 * Created by houwenchang on 2014/12/12.
 */
public class RowHeader {
    public static RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        View view = View.inflate(context, R.layout.row_header, null);
        view.setBackgroundResource(R.drawable.list_selector_background);
        Holder holder = new Holder(view);
        holder.setIsRecyclable(false);
        holder.title = (TextView) view.findViewById(R.id.title);

        return holder;
    }

    public static void onBindDataViewHolder(RecyclerView.ViewHolder holder) {

    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView title;

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
