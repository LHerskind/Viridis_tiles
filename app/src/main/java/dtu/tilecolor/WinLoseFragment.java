package dtu.tilecolor;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.zip.Inflater;


/**
 * Created by matiasdaugaard on 16/06/16.
 */
public class WinLoseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.winlosefragment, container, false);
        Bundle arguments = getArguments();

        boolean won = arguments.getBoolean("won");
        String text = arguments.getString("text");

        TextView textView = (TextView) view.findViewById(R.id.winLoseText);
        Button button = (Button) view.findViewById(R.id.nextLevel);

        textView.setText(text);

        if(!won){
            button.setVisibility(View.GONE);
        }


        return view;
    }

}
