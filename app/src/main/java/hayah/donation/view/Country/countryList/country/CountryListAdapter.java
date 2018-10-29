package hayah.donation.view.Country.countryList.country;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hayah.donation.R;
import hayah.donation.helper.Utilities;
import hayah.donation.models.country.CountryResponse;


public class CountryListAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<CountryResponse> list;

    public CountryListAdapter(Context context, List<CountryResponse> list) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.list = list;

    }

    public class ViewHolder {
        TextView countryName;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CountryResponse getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_country, null);
            // Locate the TextViews in listview_item.xml
            holder.countryName = (TextView) view.findViewById(R.id.textView_country_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        if(Utilities.getLanguage().equals("en"))
        holder.countryName.setText(list.get(position).getName_en());
        else if(Utilities.getLanguage().equals("ar"))
            holder.countryName.setText(list.get(position).getName_ar());




        return view;
    }


}