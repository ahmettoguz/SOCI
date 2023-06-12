package com.soci.soci.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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

    public EventsAdapter(Context context, ArrayList<Event> recyclerItemValues, Person current_Person) {
        this.context = context;
        this.current_Person = current_Person;
        this.recyclerItemValues = recyclerItemValues;
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
                    rv_Item_Event(currentItem);
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
                    rv_Item_Event(currentItem);
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
                    rv_Item_Event(currentItem);
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
            categoryImage = itemView.findViewById(R.id.rvEventsParticipated_Iv_Category);
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

    private void rv_Item_Event(Event event) {
        createShowDialogEvent(event);
    }


    public void createShowDialogEvent(Event current_Event) {
        Dialog customDialog = new Dialog(context);
        customDialog.setContentView(R.layout.dialog_display_event);

        final int OWNER = 1;
        final int PARTICIPATOR = 2;
        final int NORMAL = 3;


        // specify user role
        int person_Role = NORMAL;
        for (Integer owned_id : current_Person.getCreated_Events()) {
            if (owned_id == current_Event.getId())
                person_Role = OWNER;
        }

        for (Integer participated_id : current_Person.getParticipated_Events()) {
            if (participated_id == current_Event.getId())
                person_Role = PARTICIPATOR;
        }

        // count participated user count
        int participated_People_Count = 0;

        for (Person p : MainSys.people) {
            for (Integer p_id : p.getParticipated_Events()) {
                if (p_id == current_Event.getId())
                    participated_People_Count++;
            }
            for (Integer p_id : p.getCreated_Events()) {
                if (p_id == current_Event.getId())
                    participated_People_Count++;
            }
        }


        // set components
        TextView eventTVName = customDialog.findViewById(R.id.event_TV_Name);
        TextView eventTVDescription = customDialog.findViewById(R.id.event_TV_Description);
        TextView eventTVStartDate = customDialog.findViewById(R.id.event_TV_StartDate);
        TextView eventTVEndDate = customDialog.findViewById(R.id.event_TV_EndDate);
        TextView eventTVPlace = customDialog.findViewById(R.id.event_TV_Place);
        TextView eventTVQuota = customDialog.findViewById(R.id.event_TV_Quota);
        ImageView eventIvCategory = customDialog.findViewById(R.id.event_Iv_Category);
        ImageView eventBtnJoinLeave = customDialog.findViewById(R.id.event_Btn_JoinLeave);
        ImageView eventBtnUpdate = customDialog.findViewById(R.id.event_Btn_Update);
        ImageView eventBtnDelete = customDialog.findViewById(R.id.event_Btn_Delete);


        eventTVName.setText(current_Event.getName());
        eventTVDescription.setText(eventTVDescription.getText() + current_Event.getDescription());
        eventTVStartDate.setText(current_Event.getStart_Date());
        eventTVEndDate.setText(current_Event.getEnd_Date());
        eventTVPlace.setText(current_Event.getLocation());

        // quota related
        String quota = current_Event.getMax_Participant() == -1 ? "No Limit" : current_Event.getMax_Participant() + "";

        // set quota
        eventTVQuota.setText(eventTVQuota.getText().toString() + participated_People_Count + "/" + quota);

        // set quota color
        if (person_Role == OWNER) {
            eventTVQuota.setTextColor(ContextCompat.getColor(context, R.color.Orange));
        } else if (person_Role == PARTICIPATOR) {
            eventTVQuota.setTextColor(ContextCompat.getColor(context, R.color.BlueViolet));
        } else {
            if (quota.equalsIgnoreCase("No Limit") || Integer.parseInt(quota) != participated_People_Count) {
                eventTVQuota.setTextColor(ContextCompat.getColor(context, R.color.Green));
            } else {
                eventTVQuota.setTextColor(ContextCompat.getColor(context, R.color.Red));
            }
        }

        // set image
        String imgName = MainSys.getImgNameFromCategory(current_Event.getCategory());
        int imgId = MainSys.convertImageNameToId(context, imgName);
        eventIvCategory.setImageResource(imgId);

        // set button image
        int redColor = ContextCompat.getColor(context, R.color.Red);
        int greenColor = ContextCompat.getColor(context, R.color.Green);
        int orangeColor = ContextCompat.getColor(context, R.color.Orange);

        if (eventTVQuota.getCurrentTextColor() == redColor || eventTVQuota.getCurrentTextColor() == greenColor) {
            imgName = "actn_add_event";
        } else {
            imgName = "actn_remove_event";
        }
        imgId = MainSys.convertImageNameToId(context, imgName);
        eventBtnJoinLeave.setImageResource(imgId);

        final int final_Person_Role = person_Role;

        // update btn event
        eventBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (final_Person_Role == OWNER) {
                    // update eklenicek
                } else {
                    MainSys.msg(context, "Just owner can update event!");
                }
            }
        });

        // join leave button event
        eventBtnJoinLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (final_Person_Role == OWNER) {
                    MainSys.msg(context, "Owner cannot leave the event!");
                } else if (final_Person_Role == PARTICIPATOR) {
//                    ... ekle çıkar gelicek
                } else if (final_Person_Role == NORMAL && eventTVQuota.getCurrentTextColor() == greenColor) {
//                    add
                }
            }
        });

        // delete button event
        eventBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (final_Person_Role == OWNER) {
                    MainSys.events.remove(current_Event);
                    recyclerItemValues.remove(current_Event);
                    EventsAdapter.this.notifyDataSetChanged();
                    MainSys.msg(context, "Event is deleted.");
                    customDialog.dismiss();
                } else {
                    MainSys.msg(context, "Just owner can delete event!");
                }
            }
        });


        customDialog.show();
    }

}
