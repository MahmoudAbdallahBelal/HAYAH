package hayah.hayah.view.Country.countryList.country;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import hayah.hayah.R;
import hayah.hayah.models.country.CountryResponse;

public class ChooseCountryAdapter extends RecyclerView.Adapter<ChooseCountryAdapter.ChooseCountryHolder> implements Filterable {

    JSONArray countries;
    String countryNameSelected;
    OnCountrySelectedListener listener;

    List<CountryResponse> countryResponseList;
    List<CountryResponse> countriesFiltered;

    public ChooseCountryAdapter(List<CountryResponse> countries, String countryNameSelected, OnCountrySelectedListener listener){
        this.countryResponseList = countries;
        this.countriesFiltered = countries;
        this.countryNameSelected = countryNameSelected;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ChooseCountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.adapter_choose_country, parent, false);
        return new ChooseCountryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseCountryHolder holder, final int position) {


            holder.txtName.setText(countriesFiltered.get(position).getName_en());
            if(countryNameSelected !=null && countryNameSelected.toLowerCase().equals(countriesFiltered.get(position).getName_en().toLowerCase())){
                holder.itemRow.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.colorGray));
            }else {
                holder.itemRow.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.colorWhite));
            }
            holder.itemRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.getCountrySelected(countriesFiltered.get(position).getName_en(), countriesFiltered.get(position).getId() , countriesFiltered.get(position).getPhonecode());

                }
            });

    }

    @Override
    public int getItemCount() {
        if (countriesFiltered!= null)
            return countriesFiltered.size();
        else
            return 0;    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();
                if(charString.isEmpty()){
                    countriesFiltered = countryResponseList;
                }else {
                    List<CountryResponse> filteredList = new ArrayList<>();
                    for (CountryResponse c : countryResponseList){
                        if(c.getName_en().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(c);
                        }
                    }
                    countriesFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = countriesFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                countriesFiltered = (List<CountryResponse>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ChooseCountryHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        View itemRow;

        public ChooseCountryHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.adapter_choose_make_name);
            itemRow = itemView.findViewById(R.id.adapter_choose_make_item);
        }
    }

    public interface OnCountrySelectedListener{
        void getCountrySelected(String countryName, String countryId, String phoneCountryCode);
    }
}
