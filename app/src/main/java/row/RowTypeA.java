package row;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import woody.demo.com.recyclerviewadapterwrapper.Model;
import woody.demo.com.recyclerviewadapterwrapper.ModelDeleteListener;
import woody.demo.com.recyclerviewadapterwrapper.R;

/**
 *
 * @author houwenchang
 *
 *  2014/12/12.
 */
public class RowTypeA {
    public static RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        View view = View.inflate(context, R.layout.row_type1, null);
        view.setBackgroundResource(R.drawable.list_selector_background);
        Holder holder = new Holder(view);
        holder.index = (TextView) view.findViewById(R.id.title);
        holder.delete = (Button) view.findViewById(R.id.delete);


        return holder;
    }

    public static void onBindDataViewHolder(final RecyclerView.ViewHolder holder,final int position, final Model model, final ModelDeleteListener listener) {
        Holder viewHolder = (Holder) holder;
        viewHolder.index.setText(String.valueOf(model.getIndex()));
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onDelete(holder.getPosition());
                }
            }
        });
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView index;
        private Button delete;

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
