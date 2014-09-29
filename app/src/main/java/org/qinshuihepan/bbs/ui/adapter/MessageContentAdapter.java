package org.qinshuihepan.bbs.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.qinshuihepan.bbs.R;
import org.qinshuihepan.bbs.model.BasePost;
import org.qinshuihepan.bbs.ui.MessageConversationActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by liurongchan on 14-9-23.
 */
public class MessageContentAdapter extends BaseAdapter {

    List<BasePost> posts;
    Context mContext;

    public MessageContentAdapter(Context context, List<BasePost> posts) {
        this.mContext = context;
        this.posts = posts;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        Holder holder;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.post_content, null);
            holder = new Holder(view);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        holder.author.setText(posts.get(position).author);
        holder.content.setText(posts.get(position).title);
        holder.time.setText(posts.get(position).time);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MessageConversationActivity.class);
                intent.putExtra("touid", posts.get(position).tid);
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    class Holder {

        @InjectView(R.id.author)
        TextView author;

        @InjectView(R.id.time)
        TextView time;

        @InjectView(R.id.content)
        TextView content;


        public Holder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
