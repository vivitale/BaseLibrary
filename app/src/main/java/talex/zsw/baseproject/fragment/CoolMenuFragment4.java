package talex.zsw.baseproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import talex.zsw.baselibrary.util.klog.KLog;
import talex.zsw.baseproject.R;

/**
 * Created by peijiadi on 16/1/18.
 */
public class CoolMenuFragment4 extends Fragment
{
    @Override public void onCreate(Bundle savedInstanceState)
    {
        KLog.d( "CoolMenuFragment4  onCreate" );
        super.onCreate( savedInstanceState );
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState)
    {
        View view = inflater.inflate( R.layout.fragment_coolmenu4, container, false );
        KLog.d( "CoolMenuFragment4  onCreateView" );
        return view;
    }

    @Override public void onActivityCreated(Bundle savedInstanceState)
    {
        KLog.d( "CoolMenuFragment4  onActivityCreated" );
        super.onActivityCreated( savedInstanceState );
    }

    @Override public void onAttach(Context context)
    {
        KLog.d( "CoolMenuFragment4  onAttach" );
        super.onAttach( context );
    }

    @Override public void onStart()
    {
        KLog.d( "CoolMenuFragment4  onStart" );
        super.onStart();
    }

    @Override public void onResume()
    {
        KLog.d( "CoolMenuFragment4  onResume" );
        super.onResume();
    }

    @Override public void onPause()
    {
        KLog.d( "CoolMenuFragment4  onPause" );
        super.onPause();
    }

    @Override public void onStop()
    {
        KLog.d( "CoolMenuFragment4  onStop" );
        super.onStop();
    }

    @Override public void onDestroy()
    {
        KLog.d( "CoolMenuFragment4  onDestroy" );
        super.onDestroy();
    }

    @Override public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint( isVisibleToUser );
        KLog.d( "CoolMenuFragment4  setUserVisibleHint   " + isVisibleToUser );
        if(isVisibleToUser)
        {
            //相当于Fragment的onResume
        }
        else
        {
            //相当于Fragment的onPause
        }
    }
}

