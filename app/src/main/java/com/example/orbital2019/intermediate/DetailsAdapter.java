package com.example.orbital2019.intermediate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.orbital2019.R;
import com.example.orbital2019.intermediate.model.UserDetails;

import java.util.List;
public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.MyViewHolder> {

    private List<UserDetails> detailsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView matriculationNumberTV, nameTV;

        public MyViewHolder(View view) {
            super(view);

            matriculationNumberTV = (TextView) view.findViewById(R.id.matriculation_number);
            nameTV = (TextView) view.findViewById(R.id.name);
        }
    }


    public DetailsAdapter(List<UserDetails> moviesList) {
        this.detailsList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.details_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UserDetails details = detailsList.get(position);
        holder.nameTV.setText(details.getName());
        holder.matriculationNumberTV.setText(details.getMatriculationNumber());
    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }
}
