package com.soci.soci.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.soci.soci.Interfaces.Type;
import com.soci.soci.Model.Event;

import java.util.ArrayList;


public class EventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Type {
    private Context context;
    private ArrayList<Event> recyclerItemValues;
    AdapterBehavior behavior;

    // interface for behavior
    interface AdapterBehavior {
        void displayItem(Event event);
    }

    public EventsAdapter(Context context, ArrayList<Event> recyclerItemValues) {
        this.context = context;
        this.recyclerItemValues = recyclerItemValues;

        // interface related
        behavior = (AdapterBehavior) context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == RV_ITEM_OWNER) {
            itemView = inflater.inflate(R.layout.recycler_birthday_item, parent, false);
            Custom_RecyclerView_Adapter_ItemHolder_Birthday mViewHolder = new Custom_RecyclerView_Adapter_ItemHolder_Birthday(itemView);
            return mViewHolder;
        } else if (viewType == RV_ITEM_PARTICIPATOR) {
            itemView = inflater.inflate(R.layout.recycler_wedding_item, parent, false);
            Custom_RecyclerView_Adapter_ItemHolder_Wedding mViewHolder = new Custom_RecyclerView_Adapter_ItemHolder_Wedding(itemView);
            return mViewHolder;
        } else if (viewType == RV_ITEM_NORMAL) {
            itemView = inflater.inflate(R.layout.recycler_wedding_item, parent, false);
            Custom_RecyclerView_Adapter_ItemHolder_Wedding mViewHolder = new Custom_RecyclerView_Adapter_ItemHolder_Wedding(itemView);
            return mViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Event currentItem = recyclerItemValues.get(position);

        if (getItemViewType(position) == RV_ITEM_OWNER) {
            Custom_RecyclerView_Adapter_ItemHolder_Birthday itemView = (Custom_RecyclerView_Adapter_ItemHolder_Birthday) holder;

            itemView.companyName.setText(((Birthday) currentItem).getCompanyName());
            itemView.concept.setText(((Birthday) currentItem).getConcept());
            itemView.numOfGuest.setText(((Birthday) currentItem).getNumOfGuest() + "");
            itemView.img.setImageResource(curentItem.getImgId());

            // click event with interface behavior
            itemView.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    behavior.displayItem(currentItem);
                }
            });

        } else if (getItemViewType(position) == RV_ITEM_PARTICIPATOR) {
            Custom_RecyclerView_Adapter_ItemHolder_Wedding itemView = (Custom_RecyclerView_Adapter_ItemHolder_Wedding) holder;
            itemView.companyName.setText(((Wedding) currentItem).getCompanyName());
            itemView.weddingType.setText(((Wedding) currentItem).getType());
            itemView.img.setImageResource(curentItem.getImgId());

            String imgName = ((Wedding) currentItem).getDecorationColor().toLowerCase();
            int imgId = context.getResources().getIdentifier(imgName, "drawable",
                    context.getPackageName());
            itemView.iv.setImageResource(imgId);

            // click event with interface behavior
            itemView.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    behavior.displayItem(currentItem);
                }
            });
        } else if (getItemViewType(position) == RV_ITEM_NORMAL) {
            Custom_RecyclerView_Adapter_ItemHolder_Wedding itemView = (Custom_RecyclerView_Adapter_ItemHolder_Wedding) holder;
            itemView.companyName.setText(((Wedding) currentItem).getCompanyName());
            itemView.weddingType.setText(((Wedding) currentItem).getType());
            itemView.img.setImageResource(curentItem.getImgId());

            String imgName = ((Wedding) currentItem).getDecorationColor().toLowerCase();
            int imgId = context.getResources().getIdentifier(imgName, "drawable",
                    context.getPackageName());
            itemView.iv.setImageResource(imgId);

            // click event with interface behavior
            itemView.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    behavior.displayItem(currentItem);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

    @Override
    public int getItemViewType(int position) {
        Event org = recyclerItemValues.get(position);
        if (org instanceof Birthday)
            return BIRTHDAY_TYPE;
        else
            return WEDDING_TYPE;
    }

    // Create that class according to the xml layout file used.
    class EventsAdapter_ItemHolder_Owner extends RecyclerView.ViewHolder {
        TextView companyName, concept, numOfGuest;

        ConstraintLayout parentLayout;

        public EventsAdapter_ItemHolder_Owner(@NonNull View itemView) {
            super(itemView);
            companyName = itemView.findViewById(R.id.tvItemBirthdayCompanyName);
            concept = itemView.findViewById(R.id.tvItemBirthdayConcept);
            numOfGuest = itemView.findViewById(R.id.tvItemBirthdaayNumOfGuest);

            parentLayout = itemView.findViewById(R.id.itemBirthdayConstraintLayout);
        }
    }

}
