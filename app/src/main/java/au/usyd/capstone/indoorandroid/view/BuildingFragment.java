package au.usyd.capstone.indoorandroid.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import au.usyd.capstone.indoorandroid.R;
import au.usyd.capstone.indoorandroid.domain.Building;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BuildingFragment.OnBuildingFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BuildingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
 * Created by LYH on 16/3/18.
 */
public class BuildingFragment extends Fragment {

//    RecyclerView
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private static Drawable drawableBackground;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnBuildingFragmentInteractionListener mListener;

    public BuildingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuildingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuildingFragment newInstance(String param1, String param2) {
        BuildingFragment fragment = new BuildingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
//        设置fragment背景
        drawableBackground = getResources().getDrawable(R.drawable.page_background_repeat);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_building, container, false);

//        设置整个view的背景色
        view.setBackgroundDrawable(drawableBackground);

        // init RecyclerView
        if (view.getId() == R.id.buildingRecyclerView) {
            mRecyclerView = (RecyclerView) view;
            Log.e("BuildingFragment", "mRecyclerView");
        } else {
            mRecyclerView = (RecyclerView) view.findViewById(R.id.buildingRecyclerView);
            Log.e("BuildingFragment", "mRecyclerView create");
        }

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        Log.e("BuildingFragment", "mLayoutManager");

        // specify an adapter (see also next example)
        mAdapter = new BuildingAdapter(getContext(), createList(20));
        mRecyclerView.setAdapter(mAdapter);
        Log.e("BuildingFragment", "mAdapter");

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onBuildingFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBuildingFragmentInteractionListener) {
            mListener = (OnBuildingFragmentInteractionListener) context;
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
     * to the activity and potentially menu_other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnBuildingFragmentInteractionListener {
        // TODO: Update argument type and name
        void onBuildingFragmentInteraction(Uri uri);
    }


    private List createList(int size) {

        List result = new ArrayList();
        for (int i = 1; i <= size; i++) {
            Building ci = new Building();
            ci.setBuildingImage(getResources().getDrawable(R.drawable.building_example));
            ci.setBuildingName("Building Name: Name_" + i);
            ci.setBuildingOpeningTime("Opening Time: TIME_" + i);

            result.add(ci);

        }

        return result;
    }


}
