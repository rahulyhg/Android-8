package com.example.nz160.chatapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nz160 on 09-03-2016.
 */
public class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {

    private List<ChatMessage> msg_list=new ArrayList<ChatMessage>();
    TextView chats;
    LinearLayout linearLayout;

    public ChatArrayAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(ChatMessage object) {
        msg_list.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.msg_list.size();
    }

    @Override
    public ChatMessage getItem(int position) {
        return this.msg_list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v=convertView;
        if(v== null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=layoutInflater.inflate(R.layout.chat,parent,false);
        }
        linearLayout=(LinearLayout)v.findViewById(R.id.message1);
        ChatMessage messageObj=getItem(position);
        chats=(TextView)v.findViewById(R.id.singlemsg);
        chats.setText(messageObj.message);
        chats.setBackgroundResource(messageObj.side ? R.drawable.chat1 : R.drawable.chat2);

        linearLayout.setGravity(messageObj.side ? Gravity.LEFT: Gravity.RIGHT);
        return v;
    }

    public Bitmap decodeToByte(byte[] decodebyte){
        return BitmapFactory.decodeByteArray(decodebyte,0,decodebyte.length);
    }
}
