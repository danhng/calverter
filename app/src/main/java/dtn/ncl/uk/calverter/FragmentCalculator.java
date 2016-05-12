package dtn.ncl.uk.calverter;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import calverter.Expression;
import calverter.Number;
import calverter.NumberHelper;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener_Calculator} interface
 * to handle interaction events.
 * Use the {@link FragmentCalculator#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCalculator extends Fragment {
    static String name = "FragmentCalculator";

    /*************************************** RESFOR ***********************************************/

    private String[] resfor_values = new String[4];

    private TextView[] resfor_textviews = new TextView[4];

    private ListView resfor_listView;

    private GridView gridopton_gridView;

    private TextView resMain_textView;

    private TextView resSupport_textView;

    /************************************** LOGIC SPECIFIC ****************************************
     *
     */
    private int currentMode;

    private Expression exp;

    private Expression buf;

    /************************************** GRIDOPTON*********************************************/


    static String[] resforUnitStrings = {"BIN", "OCT", "DEC", "HEX"};

    // todo gridOpton


    private OnFragmentInteractionListener_Calculator mListener;
    private Context c;

    public FragmentCalculator() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FragmentCalculator(Context c) {
        super();
        this.c = c;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentCalculator.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCalculator newInstance(Context c) {
        FragmentCalculator fragment = new FragmentCalculator(c);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calculator, container, false);

        // resfor related creation
        resfor_listView = (ListView) v.findViewById(R.id.calculator_resfor_listview);
        // initiate adapter for resfor listView
        ResforAdapter adapter = new ResforAdapter((Context) mListener, resforUnitStrings);
        resfor_listView.setAdapter(adapter);

//        // hook textviews
//        for (int i = 0; i < Number.HEX_MODE; i++)
//            resfor_textviews[i] = (TextView) ((LinearLayout) resfor_listView.getChildAt(i)).getChildAt(1);

        gridopton_gridView = (GridView) v.findViewById(R.id.gridview);
        OptonAdapter gridAdapter = new OptonAdapter((Context) mListener, Opton.CALCULATOR_OPTONS);
        gridopton_gridView.setAdapter(gridAdapter);

        resMain_textView = (TextView) v.findViewById(R.id.calculator_resMain);
        resSupport_textView = (TextView) v.findViewById(R.id.calculator_resSupport);

        return v;
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
        if (context instanceof OnFragmentInteractionListener_Calculator)
        {
            mListener = (OnFragmentInteractionListener_Calculator) context;
        }
        else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener_Calculator");
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
    public interface OnFragmentInteractionListener_Calculator {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    TextView getResforTV(int modeIn) {
        //todo exception
        if (resfor_textviews[modeIn] != null)
            return resfor_textviews[modeIn];
        try {
            resfor_textviews[modeIn] = (TextView) ((LinearLayout) resfor_listView.getChildAt(modeIn)).getChildAt(1);
        } catch (NullPointerException ignored) {
        }
        return resfor_textviews[modeIn];
    }

    /**
     *
     * @param modeIn
     * @param newValue
     * @return
     */
    boolean setResfor(int modeIn, String newValue) {
        String tmp = newValue;
        tmp = tmp.trim();
        tmp = tmp.replaceAll("\\s", "");
        try {
            if (NumberHelper.isValidFormat(tmp, modeIn)) {
                Log.d("setResfor", "Valid new value of mode " + modeIn + ": " + newValue);
                getResforTV(modeIn).setText(newValue);
                return true;
            }
            return false;
        }
        catch (NullPointerException ignored){}
        Main.restart(this.c);
        return false;
    }

    boolean setResultMain(String r, boolean setOnInvalid) {
        boolean b =  (NumberHelper.isValidFormat(r, currentMode));
        if (b)
            resMain_textView.setText(r);;
        return b;
    }

    boolean setResultSupport(String r, boolean setOnInvalid) {
        boolean b =  (NumberHelper.isValidFormat(r, currentMode));
        if (b)
            resSupport_textView.setText(r);;
        return b;
    }

    boolean upda

}
