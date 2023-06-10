package com.soci.soci.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.soci.soci.Business.MainSys;
import com.soci.soci.Interfaces.Type;
import com.soci.soci.Model.Event;
import com.soci.soci.Model.Person;
import com.soci.soci.R;

import java.util.ArrayList;


public class EventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Type {
    private Context context;
    private Person current_Person;
    private ArrayList<Event> recyclerItemValues;
    EventsAdapterBehavior behavior;

    // interface for behavior
    public interface EventsAdapterBehavior {
        void displayEventItem(Event event);
    }

    public EventsAdapter(Context context, ArrayList<Event> recyclerItemValues, Person current_Person) {
        this.context = context;
        this.current_Person = current_Person;
        this.recyclerItemValues = recyclerItemValues;

        // interface related
        if (context instanceof EventsAdapterBehavior)
            behavior = (EventsAdapterBehavior) context;
        else {
            MainSys.msg(context, "there is problem about interface");
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == RV_ITEM_OWNER) {
            itemView = inflater.inflate(R.layout.rv_events_owner, parent, false);
            EventsAdapter_ItemHolder_Owner mViewHolder = new EventsAdapter_ItemHolder_Owner(itemView);
            return mViewHolder;
        } else if (viewType == RV_ITEM_PARTICIPATOR) {
            itemView = inflater.inflate(R.layout.rv_events_participated, parent, false);
            EventsAdapter_ItemHolder_Participated mViewHolder = new EventsAdapter_ItemHolder_Participated(itemView);
            return mViewHolder;
        } else if (viewType == RV_ITEM_NORMAL) {
            itemView = inflater.inflate(R.layout.rv_events_normal, parent, false);
            EventsAdapter_ItemHolder_Normal mViewHolder = new EventsAdapter_ItemHolder_Normal(itemView);
            return mViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Event currentItem = recyclerItemValues.get(position);

        if (getItemViewType(position) == RV_ITEM_OWNER) {
            EventsAdapter_ItemHolder_Owner itemView = (EventsAdapter_ItemHolder_Owner) holder;
            itemView.name.setText(currentItem.getName());

            String imgName = MainSys.getImgNameFromCategory(currentItem.getCategory());
            int imgId = MainSys.convertImageNameToId(context, imgName);
            itemView.categoryImage.setImageResource(imgId);

            // click event with interface behavior
            itemView.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    behavior.displayEventItem(currentItem);
                }
            });

        } else if (getItemViewType(position) == RV_ITEM_PARTICIPATOR) {
            EventsAdapter_ItemHolder_Participated itemView = (EventsAdapter_ItemHolder_Participated) holder;
            itemView.name.setText(currentItem.getName());

            String imgName = MainSys.getImgNameFromCategory(currentItem.getCategory());
            int imgId = MainSys.convertImageNameToId(context, imgName);
            itemView.categoryImage.setImageResource(imgId);

            // click event with interface behavior
            itemView.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    behavior.displayEventItem(currentItem);
                }
            });
        } else if (getItemViewType(position) == RV_ITEM_NORMAL) {
            EventsAdapter_ItemHolder_Normal itemView = (EventsAdapter_ItemHolder_Normal) holder;
            itemView.name.setText(currentItem.getName());

            String imgName = MainSys.getImgNameFromCategory(currentItem.getCategory());
            int imgId = MainSys.convertImageNameToId(context, imgName);
            itemView.categoryImage.setImageResource(imgId);

            // click event with interface behavior
            itemView.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    behavior.displayEventItem(currentItem);
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
        Event event = recyclerItemValues.get(position);
        int eventId = event.getId();

        if (current_Person.getCreated_Events().contains(eventId))
            return RV_ITEM_OWNER;
        else if (current_Person.getParticipated_Events().contains(eventId))
            return RV_ITEM_PARTICIPATOR;
        else
            return RV_ITEM_NORMAL;
    }

    // Create that class according to the xml layout file used.
    class EventsAdapter_ItemHolder_Owner extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView name;
        LinearLayout parentLayout;

        public EventsAdapter_ItemHolder_Owner(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.rvEventsOwner_Iv_Category);
            name = itemView.findViewById(R.id.rvEventsOwner_Tv_Name);
            parentLayout = itemView.findViewById(R.id.rvEventsOwner_Ll);
        }
    }

    // Create that class according to the xml layout file used.
    class EventsAdapter_ItemHolder_Participated extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView name;
        LinearLayout parentLayout;

        public EventsAdapter_ItemHolder_Participated(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.rvEventsNormal_Iv_Category);
            name = itemView.findViewById(R.id.rvEventsParticipated_Tv_Name);
            parentLayout = itemView.findViewById(R.id.rvEventsParticipated_Ll);
        }
    }

    // Create that class according to the xml layout file used.
    class EventsAdapter_ItemHolder_Normal extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView name;
        LinearLayout parentLayout;

        public EventsAdapter_ItemHolder_Normal(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.rvEventsNormal_Iv_Category);
            name = itemView.findViewById(R.id.rvEventsNormal_Tv_Name);
            parentLayout = itemView.findViewById(R.id.rvEventsNormal_Ll);
        }
    }

}
