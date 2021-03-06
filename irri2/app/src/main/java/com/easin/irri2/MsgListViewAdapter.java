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
        TextView tvupz = null;
        TextView tvdwz = null;
        TextView tvtgtq = null;
        ImageView ivalert = null;
        String val = "";
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.realtime_item, null);
            //iamge = (ImageView) convertView.findViewById(R.id.color_image);
        }
        title = (TextView) convertView.findViewById(R.id.tvstnm);
        text = (TextView) convertView.findViewById(R.id.tvtm);
        tvupz = (TextView) convertView.findViewById(R.id.tvupz);
        tvdwz = (TextView) convertView.findViewById(R.id.tvdwz);
        tvtgtq = (TextView) convertView.findViewById(R.id.tvtgtq);
        tvgtophgt = (TextView) convertView.findViewById(R.id.tvgtophgt);
        ivalert = (ImageView)convertView.findViewById(R.id.imageView2);
        ivalert.setVisibility(View.GONE);
        if(mdata.get(position).warning) ivalert.setVisibility(View.VISIBLE);
        int colorPos = position % colors.length;
        convertView.setBackgroundColor(colors[colorPos]);
        title.setText(mdata.get(position).STNM);//("Hello");//
        val = mdata.get(position).TM;
        if(val == "null") val = "";
        text.setText(val);//("2015-06-21");//
        val = mdata.get(position).UPZ;
        if(val == "null") val = "";
        tvupz.setText("闸前水位(m)：" + val);
        val = mdata.get(position).DWZ;
        if(val == "null") val = "";
        tvdwz.setText("闸后水位(m)：" + val);
        val = mdata.get(position).TGTQ;
        if(val == "null") val = "";
        tvtgtq.setText("过闸流量(m3/s)：" + val);
        val = mdata.get(position).GTOPHGT;
        if(val == "null") val = "";
        tvgtophgt.setText("闸门开度(m)：" + val);
        //iamge.setImageResource(R.drawable.jay);
        return convertView;
    }
    private Context mContext;
}

class STCDINFO{
    public String STCD;  //测站编号
    public String STNM;  //测站名称
    public String TM;    //时间
    public String UPZ;   //闸前水位
    public String DWZ;   //闸后水位
    public String TGTQ;  //过闸总流量
    public String GTOPHGT;  //闸门开度
    public String WRZ;      //警戒水位
    public String GRZ;      //保证水位
    public boolean warning;
}

@SuppressWarnings("rawtypes")
class STCDINFO_CMP implements Comparator{ // 实现Comparator，定义自己的比较方法
    public int compare(Object o1, Object o2) {
        STCDINFO e1=(STCDINFO)o1;
        STCDINFO e2=(STCDINFO)o2;
        return e1.STCD.compareTo(e2.STCD);
    }
}