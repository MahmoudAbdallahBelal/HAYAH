package hayah.donation.view.Country.countryList.city;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hayah.donation.R;
import hayah.donation.helper.Utilities;
import hayah.donation.models.city.CityResponse;


public class CityListAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<CityResponse> list;

    public CityListAdapter(Context context, List<CityResponse> list) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.list = list;

    }

    public class ViewHolder {
        TextView cityName;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CityResponse getItem(int position) {
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
            view = inflater.inflate(R.layout.item_city, null);
            // Locate the TextViews in listview_item.xml
            holder.cityName = (TextView) view.findViewById(R.id.textView_city_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews

       if(Utilities.getLanguage().equals("en")) {
            holder.cityName.setText(list.get(position).getName_en());
        }
        else if(Utilities.getLanguage().equals("ar")){
            holder.cityName.setText(list.get(position).getName_ar());

        }
        return view;
    }


}