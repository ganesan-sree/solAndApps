package com.solar.apps.wifi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.solar.apps.R;
import java.util.List;


public class WifiScanAdapter extends ArrayAdapter<Devices>  {

private List<Devices> wifiList;
private Context context;
private View.OnClickListener mOnClickListener;
    private int lastPosition = -1;
    public WifiScanAdapter(List<Devices> wifiList, Context context) {
        super(context, R.layout.network_list, wifiList);
        this.wifiList = wifiList;
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.network_list, viewGroup, false);
        Devices dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.network_list, viewGroup, false);
            viewHolder.vName = (TextView) convertView.findViewById(R.id.ssid_name);
            viewHolder.vImage = (ImageView) convertView.findViewById(R.id.Wifilogo);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        lastPosition = position;

        viewHolder.vName.setText(dataModel.getName());
        //viewHolder.vImage.setText(dataModel.getType());

        //viewHolder.info.setOnClickListener(this);

        viewHolder.vImage.setImageResource(R.drawable.ic_action_wifi);
        viewHolder.context = context;
        viewHolder.position = position;
        viewHolder.vName.setTag(dataModel);
        // Return the completed view to render on screen
        return convertView;
    }





    public void setOnClickListener(View.OnClickListener lis) {
        mOnClickListener = lis;
    }


    public class ViewHolder {
        protected ImageView vImage;
        protected TextView vName;
        protected  Context context;
        protected int position;

    }

}
