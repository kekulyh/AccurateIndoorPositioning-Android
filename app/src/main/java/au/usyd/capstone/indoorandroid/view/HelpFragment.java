package au.usyd.capstone.indoorandroid.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import au.usyd.capstone.indoorandroid.R;
import us.feras.mdv.MarkdownView;

/**
 * Created by LYH on 16/4/3.
 */
public class HelpFragment extends Fragment {

    //    RecyclerView
    private MarkdownView mMarkdownView;

    private OnHelpFragmentInteractionListener mListener;

    public HelpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        mMarkdownView = (MarkdownView) view.findViewById(R.id.markdownView);
        mMarkdownView.loadMarkdownFile("https://raw.githubusercontent.com/kekulyh/AccurateIndoorPositioning-Android/master/README.md");

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onHelpFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHelpFragmentInteractionListener) {
            mListener = (OnHelpFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnHelpFragmentInteractionListener {
        // TODO: Update argument type and name
        void onHelpFragmentInteraction(Uri uri);
    }
}
