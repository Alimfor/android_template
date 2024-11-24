package alim.dev.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class WatchAdapter extends BaseAdapter {
    private final Context context;
    private final List<Watch> watchList;

    public WatchAdapter(Context context, List<Watch> watchList) {
        this.context = context;
        this.watchList = watchList;
    }

    @Override
    public int getCount() {
        return watchList.size();
    }

    @Override
    public Object getItem(int position) {
        return watchList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_watch, parent, false);
        }

        Watch watch = watchList.get(position);

        ImageView imageView = convertView.findViewById(R.id.watchImage);
        TextView brandTextView = convertView.findViewById(R.id.watchBrand);
        TextView priceTextView = convertView.findViewById(R.id.watchPrice);
        TextView descriptionTextView = convertView.findViewById(R.id.watchDescription);

        imageView.setImageResource(watch.getImageResId());
        brandTextView.setText(watch.getBrand());
        priceTextView.setText(watch.getPrice());
        descriptionTextView.setText(watch.getDescription());

        return convertView;
    }
}
