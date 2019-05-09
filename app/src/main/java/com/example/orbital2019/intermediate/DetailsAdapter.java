package com.example.orbital2019.intermediate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.orbital2019.R;
import com.example.orbital2019.intermediate.model.UserDetails;

import java.util.List;

    public class DetailsAdapter extends ArrayAdapter<UserDetails> {
        private final Context context;
        private final List<UserDetails> values;

        public DetailsAdapter(Context context, List<UserDetails> values) {
            super(context, R.layout.details_list_row, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.details_list_row, parent, false);

            TextView nameTV = rowView.findViewById(R.id.name);
            TextView matriculationNumberTV = rowView.findViewById(R.id.matriculation_number);

            nameTV.setText(values.get(position).getName());
            matriculationNumberTV.setText(values.get(position).getMatriculationNumber());


            return rowView;
        }
    }