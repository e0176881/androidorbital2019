package com.example.orbital2019.basic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.orbital2019.R;

public class WidgetsLayoutFragment extends Fragment {

    // defining all elements in this fragment
    TextView textView;
    EditText textField;
    Button changeTextBtn;
    ImageView imageView;
    Button toggleImageBtn;
    Button toastBtn;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // link the elements to predefined variables with findViewById() method
        textView = view.findViewById(R.id.text_view);
        textField = view.findViewById(R.id.text_field);
        changeTextBtn = view.findViewById(R.id.change_text_button);
        imageView = view.findViewById(R.id.image_view);
        toggleImageBtn = view.findViewById(R.id.toggle_image_button);
        toastBtn = view.findViewById(R.id.toast_button);

        // add button click listeners and the corresponding reactions
        changeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the text entered into text field and display it in text view.
                textView.setText(textField.getText());
            }
        });

        toggleImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // toggling invisibility
                // TAKE NOTE: there is a third possible value returned by getVisibility(),
                //            which is View.GONE, there's a difference with View.INVISIBLE
                if (imageView.getVisibility() == View.VISIBLE) {
                    imageView.setVisibility(View.INVISIBLE);
                } else if (imageView.getVisibility() == View.INVISIBLE) {
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        });

        toastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // simple method call to display a toast message
                Toast.makeText(getActivity().getApplicationContext(), "This is a toast Message", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_widgetslayout, null);
    }
}