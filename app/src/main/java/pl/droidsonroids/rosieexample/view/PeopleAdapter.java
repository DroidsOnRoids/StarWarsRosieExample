package pl.droidsonroids.rosieexample.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import pl.droidsonroids.rosieexample.R;
import pl.droidsonroids.rosieexample.model.Person;

public class PeopleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_ITEM = 1;
    private static final int VIEW_PROGRESS = 0;

    private final Context context;
    private final List<Person> peopleList;

    public PeopleAdapter(final Context context, final List<Person> peopleList) {
        this.context = context;
        this.peopleList = peopleList;
    }

    @Override
    public int getItemViewType(final int position) {
        return peopleList.get(position) != null ? VIEW_ITEM : VIEW_PROGRESS;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        if (viewType == VIEW_ITEM) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false);
            return new PersonViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_view_item_progress, parent, false);
            return new ProgressViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder.getItemViewType() == VIEW_ITEM) {
            ((PersonViewHolder) holder).bindData(peopleList.get(position));
        } else {
            ((ProgressViewHolder) holder).bindData();
        }
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public void clear() {
        peopleList.clear();
        notifyDataSetChanged();
    }
}
