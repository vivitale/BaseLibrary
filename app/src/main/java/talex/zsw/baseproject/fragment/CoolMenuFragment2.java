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

public class CoolMenuFragment2 extends Fragment
{
    @Override public void onCreate(Bundle savedInstanceState)
    {
        KLog.d( "CoolMenuFragment2  onCreate" );
        super.onCreate( savedInstanceState );
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState)
    {
        View view = inflater.inflate( R.layout.fragment_coolmenu2, container, false );
        KLog.d( "CoolMenuFragment2  onCreateView" );
        return view;
    }

    @Override public void onActivityCreated(Bundle savedInstanceState)
    {
        KLog.d( "CoolMenuFragment2  onActivityCreated" );
        super.onActivityCreated( savedInstanceState );
    }

    @Override public void onAttach(Context context)
    {
        KLog.d( "CoolMenuFragment2  onAttach" );
        super.onAttach( context );
    }

    @Override public void onStart()
    {
        KLog.d( "CoolMenuFragment2  onStart" );
        super.onStart();
    }

    @Override public void onResume()
    {
        KLog.d( "CoolMenuFragment2  onResume" );
        super.onResume();
    }

    @Override public void onPause()
    {
        KLog.d( "CoolMenuFragment2  onPause" );
        super.onPause();
    }

    @Override public void onStop()
    {
        KLog.d( "CoolMenuFragment2  onStop" );
        super.onStop();
    }

    @Override public void onDestroy()
    {
        KLog.d( "CoolMenuFragment2  onDestroy" );
        super.onDestroy();
    }

    @Override public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint( isVisibleToUser );
        KLog.d( "CoolMenuFragment2  setUserVisibleHint   " + isVisibleToUser );
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
