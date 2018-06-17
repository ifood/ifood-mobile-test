package br.com.ifood.tweetmood.presentation.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;

import br.com.ifood.tweetmood.R;
import br.com.ifood.tweetmood.presentation.util.DialogUtils;
import dagger.android.support.DaggerFragment;

/**
 * Created by uchoa on 14/06/18.
 */


public class BaseFragment extends DaggerFragment {

    private BaseActivityView mListener;
    private ProgressDialog mProgress;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivityView) {
            mListener = (BaseActivityView) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement " +  BaseActivityView.class.getName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void replaceFragment(Fragment fragment){
        mListener.replaceFragment(fragment);
    }

    public void showProgress(){
        if(mProgress == null){
            mProgress = new DialogUtils().showProgressDialog(getContext());
            mProgress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mProgress.setContentView(R.layout.view_progress);
        } else if(!mProgress.isShowing()){
            mProgress.show();
        }
    }

    public void hideProgress(){
        if(mProgress != null)
            mProgress.cancel();
    }

    public void finishApplicationWithErrorMessage(int text){
        mListener.finishApplicationWithErrorMessage(text);
    }

    public void popFragmentWithErrorMessage(int text){
        new DialogUtils().showDialog(getActivity(),
                text,
                R.string.action_ok,
                (dialogInterface, i) -> getFragmentManager().popBackStack());
    }

    public void makeSnackbar(Context context, String message){
        try {
            Activity activity = (Activity) context;
            if(activity != null && activity.findViewById(android.R.id.content) != null && message != null ){
                android.support.design.widget.Snackbar.make(activity.findViewById(android.R.id.content), message, android.support.design.widget.Snackbar.LENGTH_LONG).show();
            }
        }catch(Exception e){
            e.getMessage();
        }
    }

}
