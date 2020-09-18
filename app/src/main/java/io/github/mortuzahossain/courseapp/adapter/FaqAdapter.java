package io.github.mortuzahossain.courseapp.adapter;
/*
 * Created by mortuza on 5/7/20 | 12:50 AM for DMCDreamer
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection;

import io.github.mortuzahossain.courseapp.R;
import io.github.mortuzahossain.courseapp.model.FaqModel;


public class FaqAdapter extends FirestoreRecyclerAdapter<FaqModel, FaqAdapter.RecyclerHolder> {

    private final ExpansionLayoutCollection expansionsCollection = new ExpansionLayoutCollection();

    public FaqAdapter(@NonNull FirestoreRecyclerOptions<FaqModel> options) {
        super(options);
        expansionsCollection.openOnlyOne(true);
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RecyclerHolder.buildFor(parent);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerHolder holder, int position, @NonNull FaqModel model) {
        holder.bind();
        holder.title.setText(model.getTitle());
        holder.tvDescription.setText(model.getDescription());
        expansionsCollection.add(holder.getExpansionLayout());
    }

    public final static class RecyclerHolder extends RecyclerView.ViewHolder {

        private static final int LAYOUT = R.layout.item_faq_panel;

        ExpansionLayout expansionLayout;
        TextView title;
        TextView tvDescription;

        public static RecyclerHolder buildFor(ViewGroup viewGroup) {
            return new RecyclerHolder(LayoutInflater.from(viewGroup.getContext()).inflate(LAYOUT, viewGroup, false));
        }

        public RecyclerHolder(View itemView) {
            super(itemView);
            expansionLayout = itemView.findViewById(R.id.expansionLayout);
            title = itemView.findViewById(R.id.title);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }

        public void bind() {
            expansionLayout.collapse(false);
        }

        public ExpansionLayout getExpansionLayout() {
            return expansionLayout;
        }
    }
}
