package com.example.henrylee.hackdavis2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StudentSignIn.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StudentSignIn#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentSignIn extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public StudentSignIn() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentSignIn.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentSignIn newInstance(String param1, String param2) {
        StudentSignIn fragment = new StudentSignIn();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_sign_in, container, false);
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


    //Commented out because I actually put this into StudentScreen.Java

//    public void startQuiz(View view){
//        Intent intent = new Intent(getContext(), StudentQuizScreen.class);
//        //pass that object into cloud
//
//        //DANIEL LOOK HEREEEEEEEE
//        //DANIEL LOOK HEREEEEEEEE
//        //DANIEL LOOK HEREEEEEEEE
//        //DANIEL LOOK HEREEEEEEEE
//
//        //I'm so sorry
//        //good luck
//        //may the odds ever be in your favor
//        //fuck yeah built on the second try
//        String first = ((EditText)(getActivity().findViewById(R.id.student_sign_in_first))).toString();
//        String last = ((EditText)(getActivity().findViewById(R.id.student_sign_in_last))).toString();
//        int sid = Integer.parseInt((((EditText)(getActivity().findViewById(R.id.student_sign_in_sid)))).toString());
//        String email = ((EditText)(getActivity().findViewById(R.id.student_sign_in_email))).toString();
//        int classCode = Integer.parseInt(((EditText)(getActivity().findViewById(R.id.student_sign_in_class_code))).toString());
//
//        StudentData studentData = new StudentData(first, last, sid, email, classCode);
//        startActivity(intent);
//
//    }

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
