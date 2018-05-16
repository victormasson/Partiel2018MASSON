package masson.diiage.org.partiel2018.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import masson.diiage.org.partiel2018.Entities.Release;
import masson.diiage.org.partiel2018.R;

public class ReleaseAdapter extends BaseAdapter {
    private ArrayList<Release> listRelease;
    private Activity context;
    private LayoutInflater layoutInflater;

    public ReleaseAdapter(ArrayList<Release> listRelease, Activity context) {
        this.listRelease = listRelease;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() { return listRelease.size(); }

    @Override
    public Release getItem(int i) { return listRelease.get(i); }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        // La vue qui est retournée
        View view = convertView;
        // Permet de mémoriser les calculs de findByIds
        ReleaseViewHolder releaseViewHolder;

        try {
            // La vue est recyclée si le convertView est null
            if (convertView != null) {
                // On récupère la vue
                releaseViewHolder = (ReleaseViewHolder)view.getTag();
            } else {
                view = layoutInflater.inflate(R.layout.item_release, null);
                releaseViewHolder = new ReleaseViewHolder((TextView) view.findViewById(R.id.releaseTitle));
                view.setTag(releaseViewHolder);
            }

            Release release = getItem(i);
            releaseViewHolder.labelTitle.setText(release.getTitle().toString());
        }
        catch (Exception e) {
            e.getStackTrace();
        }

        return view;
    }

    private class ReleaseViewHolder {
        public TextView labelTitle;

        public ReleaseViewHolder () {
        }

        public ReleaseViewHolder(TextView lTitle) {
            this.labelTitle = lTitle;
        }
    }
}
