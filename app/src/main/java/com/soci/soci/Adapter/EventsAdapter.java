package com.soci.soci.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.soci.soci.Business.MainSys;
import com.soci.soci.Database.DatabaseHelper;
import com.soci.soci.Database.Event_Table;
import com.soci.soci.Database.Person_Event_Helper;
import com.soci.soci.Database.Person_Event_Owner_Table;
import com.soci.soci.Database.Person_Table;
import com.soci.soci.Interfaces.Type;
import com.soci.soci.Model.Event;
import com.soci.soci.Model.Person;
import com.soci.soci.R;
import com.soci.soci.Ui.Event_Update_Activity;
import com.soci.soci.Ui.EventsFragment;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class EventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Type {
    private Context context;
    private Person current_Person;
    private ArrayList<Event> recyclerItemValues;
    private String fragmentName;
    int participated_People_Count;
    int person_Role;
    DatabaseHelper dbHelper;

    RvAdapterInterface interfaceListener;

    public interface RvAdapterInterface {
        public void rvAdapterBehavior(String fragmentName);

    }

    public EventsAdapter(Context context, String fragmentName, ArrayList<Event> recyclerItemValues, Person current_Person) {
        this.context = context;
        this.current_Person = current_Person;
        this.recyclerItemValues = recyclerItemValues;
        this.fragmentName = fragmentName; // "userEventFragment", //"eventsFragment"

        interfaceListener = (RvAdapterInterface) context;
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
        person_Role = NORMAL;
        for (Integer owned_id : current_Person.getCreated_Events()) {
            if (owned_id == current_Event.getId())
                person_Role = OWNER;
        }

        for (Integer participated_id : current_Person.getParticipated_Events()) {
            if (participated_id == current_Event.getId())
                person_Role = PARTICIPATOR;
        }

        // count participated user count
        participated_People_Count = 0;

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
        Button eventBtnCall = customDialog.findViewById(R.id.event_Btn_Call);


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
        int blueViolet = ContextCompat.getColor(context, R.color.BlueViolet);

        if (eventTVQuota.getCurrentTextColor() == redColor || eventTVQuota.getCurrentTextColor() == greenColor) {
            imgName = "actn_add_event";
        } else {
            imgName = "actn_remove_event";
        }
        imgId = MainSys.convertImageNameToId(context, imgName);
        eventBtnJoinLeave.setImageResource(imgId);


        GestureDetectorCompat gestureDetector;
        CustomGestureListener customGestureListener;
        customGestureListener = new CustomGestureListener(current_Event);
        gestureDetector = new GestureDetectorCompat(context, customGestureListener);

        // call event
        eventBtnCall.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //return MainActivity.this.mDetector.onTouchEvent(motionEvent);
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

        // create database helper
        dbHelper = new DatabaseHelper(context);

        // update btn event
        eventBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (person_Role == OWNER) {
                    Intent sendIntent = new Intent(context, Event_Update_Activity.class);
                    sendIntent.putExtra("person_id", current_Person.getId());
                    sendIntent.putExtra("event_id", current_Event.getId());
                    context.startActivity(sendIntent);
                } else {
                    MainSys.msg(context, "Just owner can update event!");
                }
            }
        });

        // join leave button event
        eventBtnJoinLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (person_Role == OWNER) {
                    MainSys.msg(context, "Owner cannot leave the event!");
                } else if (person_Role == PARTICIPATOR) {
                    person_Role = NORMAL;

                    // change text color of the text
                    eventTVQuota.setTextColor(greenColor);

                    // chnage text of the quota field
                    participated_People_Count--;
                    eventTVQuota.setText("Quota : " + participated_People_Count + "/" + quota);

                    // change image
                    String imgName = "actn_add_event";
                    int img_ID = MainSys.convertImageNameToId(context, imgName);
                    eventBtnJoinLeave.setImageResource(img_ID);

                    // remove event id, from person
                    MainSys.remove_Participated_Event_from_Person(dbHelper, current_Person.getId(), current_Event.getId());

                    // update data
                    MainSys.prepareDatabaseData(dbHelper);
                    interfaceListener.rvAdapterBehavior(fragmentName);
                } else if (person_Role == NORMAL && eventTVQuota.getCurrentTextColor() == greenColor) {
                    person_Role = PARTICIPATOR;

                    // change text color of the text
                    eventTVQuota.setTextColor(blueViolet);

                    // chnage text of the quota field
                    participated_People_Count++;
                    eventTVQuota.setText("Quota : " + participated_People_Count + "/" + quota);

                    // change image
                    String imgName = "actn_remove_event";
                    int img_ID = MainSys.convertImageNameToId(context, imgName);
                    eventBtnJoinLeave.setImageResource(img_ID);

                    // add event id, to person
                    MainSys.add_Participated_Event_To_Person(dbHelper, current_Person.getId(), current_Event.getId());

                    // update data
                    MainSys.prepareDatabaseData(dbHelper);
                    interfaceListener.rvAdapterBehavior(fragmentName);
                }
            }
        });

        // delete button event
        eventBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (person_Role == OWNER) {

                    // remove from event table
                    if (
                            Event_Table.delete(dbHelper, current_Event.getId()) != 0) {
                        MainSys.msg(context, "Event is deleted successfully.");
                    } else {
                        MainSys.msg(context, "Deletion Error!");
                    }

                    // update current data
                    MainSys.prepareDatabaseData(dbHelper);

                    // update recyler view by the help of interface
                    interfaceListener.rvAdapterBehavior(fragmentName);
                    customDialog.dismiss();
                } else {
                    MainSys.msg(context, "Just owner can delete event!");
                }
            }
        });


        customDialog.show();
    }

    class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {
        final int OWNER = 1;
        final int PARTICIPATOR = 2;
        final int NORMAL = 3;
        Event current_Event;

        public CustomGestureListener(Event current_Event) {
            this.current_Event = current_Event;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            if (person_Role == OWNER) {
                MainSys.msg(context, "This event is belonging to you.");
            } else {
                String phone_num = "-1";

                for (Person p : MainSys.people) {
                    for (Integer owner_id : p.getCreated_Events()) {
                        if (owner_id == current_Event.getId())
                            phone_num = p.getPhone() + "";
                    }
                }
                if (phone_num.equalsIgnoreCase("-1")) {
                    MainSys.msg(context, "Phone number is not shared.");
                } else {
                    MainSys.makePhoneCall((Activity) context, phone_num);
                }
            }
//            Toast.makeText(getBaseContext(), "onLongPress Over Image", Toast.LENGTH_SHORT).show();
        }
    }

}
