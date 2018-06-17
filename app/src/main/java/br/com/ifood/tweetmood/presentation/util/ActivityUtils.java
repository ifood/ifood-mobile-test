package br.com.ifood.tweetmood.presentation.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Zup on 28/06/2017.
 */
public class ActivityUtils {

    public void replaceActivity(Context context, Class activity, Bundle bundle, boolean finish) {
        Intent intent = new Intent(context, activity);
        if (bundle != null)
            intent.putExtras(bundle);

        context.startActivity(intent);

        if (finish)
            ((Activity) context).finish();
    }

    public void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                      @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }


    public void replaceFragment(@NonNull FragmentManager fragmentManager,
                                @NonNull Fragment fragment, int frameId){
        replaceFragment(fragmentManager, fragment, frameId, true);
    }

    private void replaceFragment(@NonNull FragmentManager fragmentManager,
                                @NonNull Fragment fragment, int frameId, boolean addToBackStack) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);

        FragmentTransaction transaction = fragmentManager.beginTransaction();


        if (addToBackStack)
            transaction.addToBackStack(fragment.getClass().toString());

        transaction.replace(frameId, fragment);
        transaction.commit();
    }


}

