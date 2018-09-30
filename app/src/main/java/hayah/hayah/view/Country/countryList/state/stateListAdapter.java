package hayah.hayah.view.Country.countryList.state;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hayah.hayah.R;
import hayah.hayah.models.States.StatesResponse;
import hayah.hayah.models.country.CountryResponse;


public class stateListAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<StatesResponse> list;

    public stateListAdapter(Context context, List<StatesResponse> list) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.list = list;

    }

    public class ViewHolder {
        TextView stateName;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public StatesResponse getItem(int position) {
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
            view = inflater.inflate(R.layout.item_state, null);
            // Locate the TextViews in listview_item.xml
            holder.stateName = (TextView) view.findViewById(R.id.textView_state_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.stateName.setText(list.get(position).getName_en());



        return view;
    }


}