package pl.droidsonroids.rosieexample.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidsonroids.rosieexample.R;
import pl.droidsonroids.rosieexample.model.Person;

public class PersonViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.text_view_name) TextView textViewName;
    @Bind(R.id.text_view_birth) TextView textViewBirth;
    @Bind(R.id.image_view_gender) ImageView imageViewGender;

    public PersonViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(Person person) {
        textViewName.setText(person.getName());
        textViewBirth.setText(person.getBirthYear());
        if (person.getGender() != null) {
            switch (person.getGender()) {
                case MALE:
                    imageViewGender.setImageResource(R.drawable.ic_male);
                    break;
                case FEMALE:
                    imageViewGender.setImageResource(R.drawable.ic_female);
                    break;
                case HERMAPHRODITE:
                    imageViewGender.setImageResource(R.drawable.ic_hermaphrodite);
                    break;
                case UNKNOWN:
                default:
                    imageViewGender.setImageResource(R.drawable.ic_unknown);
                    break;
            }
        }
    }
}
