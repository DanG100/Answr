package com.example.henrylee.hackdavis2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionCreateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuestionCreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionCreateFragment extends Fragment implements  AdapterView.OnItemSelectedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private FragmentManager fragmentManager;

    public QuestionCreateFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static QuestionCreateFragment newInstance(int position) {
        QuestionCreateFragment fragment = new QuestionCreateFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }

        Log.d("FRAGMENT MANAGER STATUS", fragmentManager.toString());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_create, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Spinner questionSelectionDropdown = (Spinner) getView().findViewById(R.id.question_type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.question_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        questionSelectionDropdown.setAdapter(adapter);
        questionSelectionDropdown.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(i==0){//MULTIPLE CHOICE
            Log.d("data", "multiple choice selected");
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.mc_container, multipleChoiceQuestion.newInstance(1));
            fragmentTransaction.commit();
        }
        else if(i==1){//SHORT ANSWER
            Log.d("data", "short answer selected");
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.mc_container, ShortAnswerFragment.newInstance("as","as"));
            fragmentTransaction.commit();
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //fragmentTransaction.replace(R.id.teacher_question_container, new multipleChoiceQuestion());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        fragmentManager = getActivity().getSupportFragmentManager();

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
