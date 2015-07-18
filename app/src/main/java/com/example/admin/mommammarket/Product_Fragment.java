package com.example.admin.mommammarket;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Product_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Product_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Product_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    int[] arrImgProduct = {
            R.drawable.corn_caramel,
            R.drawable.corn_choco,

    };
    ArrayList<String> myDatas;
    ArrayList<String> myDatas_Desc;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Product_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Product_Fragment newInstance(String param1, String param2) {
        Product_Fragment fragment = new Product_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Product_Fragment() {
        // Required empty public constructor
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
        View rootView = inflater.inflate(R.layout.fragment_product, container, false);
        Recycler(rootView);
        return rootView;
    }

    private void Recycler(View v) {
        myDatas = new ArrayList<String>();
        myDatas_Desc = new ArrayList<String>();
        myDatas.add("Krispy Corn : คาราเมล");
        myDatas_Desc.add("ทานหลังทานข้าวรับลองฟินสุดๆ...");
        myDatas.add("Krispy Corn : รสช็อกโกแลต");
        myDatas_Desc.add("แล้วจะพูดเป็นเสียงเดียวกันว่า \"อร่อยจนหยุดไม่อยู่\"");

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_Product);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDatas, myDatas_Desc);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {


        private ArrayList<String> mDataset;
        private ArrayList<String> mDataset_Desc;


        public MyAdapter(ArrayList<String> myDatas, ArrayList<String> myDatasDesc) {
            mDataset = myDatas;
            mDataset_Desc = myDatasDesc;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout_product, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.mImageView.setImageResource(arrImgProduct[position]);
            holder.mTexView_Title.setText(mDataset.get(position));
            holder.mTextView_Desc.setText(mDataset_Desc.get(position));

            holder.setItem(position);
        }//End BindViewHolder

        private void onclick_listitem_product(int position) {
            Toast.makeText(getActivity(), mDataset.get(position), Toast.LENGTH_SHORT).show();
            switch (position) {
                case 0: {
                    Intent intent = new Intent(getActivity(), Product_item_caramel_Activity.class);
                    startActivity(intent);
                    break;
                }
                case 1: {
                    Intent intent = new Intent(getActivity(), Product_item_chocolate_Activity.class);
                    startActivity(intent);
                    break;
                }
            }//End switch case
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private TextView mTexView_Title;
            private TextView mTextView_Desc;
            private ImageView mImageView;
            private int position;


            private ViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                mTexView_Title = (TextView) itemView.findViewById(R.id.product_title);
                mTextView_Desc = (TextView) itemView.findViewById(R.id.product_desc);
                mImageView = (ImageView) itemView.findViewById(R.id.product_photo);
            }
            public void setItem(int position) {
                this.position = position;

            }
            @Override
            public void onClick(View v) {
                onclick_listitem_product(position);
            }
        }//End ViewHolder

    }//End MyAdapter


}
