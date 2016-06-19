package dtu.tilecolor;


import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.winlosefragment, container, false);
        Bundle arguments = getArguments();

        final FragmentManager fragmentManager = getActivity().getFragmentManager();

        boolean won = arguments.getBoolean("won");
        String text = arguments.getString("text");

        TextView textView = (TextView) view.findViewById(R.id.winLoseText);
        Button next = (Button) view.findViewById(R.id.nextLevel);
        Button restart = (Button) view.findViewById(R.id.buttonRestart);
        Button menu = (Button) view.findViewById(R.id.menuFragmentButton);

        textView.setText(text);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FullscreenActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                fragmentManager.popBackStack();
                startActivity(intent);
                getActivity().finish();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().recreate();
                fragmentManager.popBackStack();
            }
        });

        if (!won) {
            next.setVisibility(View.GONE);
        }


        return view;
    }

}
