package pl.droidsonroids.rosieexample.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidsonroids.rosieexample.R;

public class ProgressViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.progressBar) ProgressBar progressBar;

    public ProgressViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData() {
        progressBar.setIndeterminate(true);
    }
}
