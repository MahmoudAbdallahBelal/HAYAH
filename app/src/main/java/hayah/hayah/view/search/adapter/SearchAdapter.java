package hayah.hayah.view.search.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import hayah.hayah.R;
import hayah.hayah.models.search.SearchResponse;
import hayah.hayah.models.search.VolunteersDetails;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {


    private Context context;
    private List<VolunteersDetails> list;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTxt ,mobileTxt , mobile2Txt ;
        public LinearLayout linearLayout;


        public MyViewHolder(final View view) {
            super(view);

            nameTxt = view.findViewById(R.id.text_item_search_name);
            mobileTxt = view.findViewById(R.id.text_item_search_mobile);
            mobile2Txt= view.findViewById(R.id.text_item_search_mobile2);
            linearLayout = view.findViewById(R.id.linear_item);


        }
    }


    public SearchAdapter(List<VolunteersDetails> list, Context context) {
        this.list = list;
        this.context =context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

         holder.nameTxt.setText(list.get(position).getName());
         holder.mobileTxt.setText(list.get(position).getPhone());
         holder.mobile2Txt.setText(list.get(position).getPhone2());


         holder.linearLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 final String firstPhone = list.get(position).getPhone();
                 final String secondPhone = list.get(position).getPhone2();



                 if(firstPhone != null && list.get(position).getPhone2() != null)
                 {
                     AlertDialog.Builder builder = new AlertDialog.Builder(context);
                     builder.setMessage("يمكنك الاتصال بالشخص المتبرع");

                     builder.setPositiveButton("الرقم الاول", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {

                             Uri call = Uri.parse("tel:" + firstPhone);
                             Intent surf = new Intent(Intent.ACTION_DIAL, call);
                             context.startActivity(surf);
                         }
                     });

                     builder.setNegativeButton("الرقم الثاني ", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {

                             Uri call = Uri.parse("tel:" + secondPhone);
                             Intent surf = new Intent(Intent.ACTION_DIAL, call);
                             context.startActivity(surf);
                         }
                     });

                     builder.show();
                 }

                 else if (firstPhone != null && secondPhone == null)
                 {


                     AlertDialog.Builder builder = new AlertDialog.Builder(context);
                     builder.setMessage("يمكنك الاتصال بالشخص المتبرع");

                     builder.setPositiveButton("الرقم الاول", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {

                             Uri call = Uri.parse("tel:" + firstPhone);
                             Intent surf = new Intent(Intent.ACTION_DIAL, call);
                             context.startActivity(surf);
                         }
                     });


                     builder.show();
                 }


             }
         });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }






}