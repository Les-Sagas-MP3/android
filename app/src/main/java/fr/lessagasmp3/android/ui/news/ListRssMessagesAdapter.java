package fr.lessagasmp3.android.ui.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import fr.lessagasmp3.android.R;
import fr.lessagasmp3.android.model.RssMessage;

public class ListRssMessagesAdapter extends BaseAdapter {

    private List<RssMessage> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListRssMessagesAdapter(Context aContext, List<RssMessage> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.textView_title);
            holder.subtitle = (TextView) convertView.findViewById(R.id.textView_subtitle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RssMessage rssMessage = this.listData.get(position);
        holder.title.setText(rssMessage.getTitle());
        holder.subtitle.setText(rssMessage.getAuthor());

        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView subtitle;
    }

}