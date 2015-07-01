package com.easin.irri2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 * Created by Administrator on 2015/7/1.
 */
public class MsgListViewAdapter  extends BaseAdapter {
    private int[] colors = new int[] { 0xff626569, 0xff4f5257 };
    public Vector<STCDINFO> mdata;
    public MsgListViewAdapter(Context context,Vector<STCDINFO> _data) {
        mContext = context;
        mdata = _data;
    }

    public int getCount() {
        return mdata.size();// mListStr.length;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iamge = null;
        TextView title = null;
        TextView text = null;
        TextView tvgtophgt = null;
        TextView tvz = null;
        TextView tvq = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.realtime_item, null);
            //iamge = (ImageView) convertView.findViewById(R.id.color_image);
        }
        title = (TextView) convertView.findViewById(R.id.tvstnm);
        text = (TextView) convertView.findViewById(R.id.tvtm);
        tvz = (TextView) convertView.findViewById(R.id.tvz);
        tvq = (TextView) convertView.findViewById(R.id.tvq);
        tvgtophgt = (TextView) convertView.findViewById(R.id.tvgtophgt);

        int colorPos = position % colors.length;
        convertView.setBackgroundColor(colors[colorPos]);
        title.setText(mdata.get(position).STNM);//("Hello");//
        text.setText(mdata.get(position).TM);//("2015-06-21");//
        tvz.setText("水位：" + mdata.get(position).Z);
        tvq.setText("流量：" + mdata.get(position).Q);
        tvgtophgt.setText("并度：" + mdata.get(position).GTOPHGT);
        //iamge.setImageResource(R.drawable.jay);
        return convertView;
    }
    private Context mContext;
}

class STCDINFO{
    public String STCD;
    public String STNM;
    public String TM;
    public String Z;
    public String Q;
    public String GTOPHGT;
}

@SuppressWarnings("rawtypes")
class STCDINFO_CMP implements Comparator{ // 实现Comparator，定义自己的比较方法
    public int compare(Object o1, Object o2) {
        STCDINFO e1=(STCDINFO)o1;
        STCDINFO e2=(STCDINFO)o2;
        return e1.STCD.compareTo(e2.STCD);
    }
}