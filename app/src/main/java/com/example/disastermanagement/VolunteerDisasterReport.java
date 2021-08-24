package com.example.disastermanagement;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VolunteerDisasterReport extends Fragment {

    Button btn;
    EditText deaths;
    EditText injuries;
    EditText saves;
    EditText cars;
    EditText buildings;
    String deathstr;
    String injuriesstr;
    String savesstr;
    String carsstr;
    String buildingsstr;
    String key;
    DatabaseReference reference;
    VolunteerProfile volunteerProfile;
    Singleton ls = Singleton.getInstance();
    List<VolunteerUtils> volunteerUtilsList;
    String currentVolDisasterId;
    String currentVolName;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_volunteer_disaster_report, container, false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        volunteerUtilsList = new ArrayList<>();

        readFromDatabase();
        readFromDisasterDatabase();
        btn = getActivity().findViewById(R.id.report);
        deaths = getActivity().findViewById(R.id.deaths);
        injuries = getActivity().findViewById(R.id.injuries);
        saves = getActivity().findViewById(R.id.saves);
        cars=getActivity().findViewById(R.id.cars);
        buildings = getActivity().findViewById(R.id.buildings);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deathstr=deaths.getText().toString();
                injuriesstr=injuries.getText().toString();
                savesstr = saves.getText().toString();
                carsstr=cars.getText().toString();
                buildingsstr = buildings.getText().toString();
                Map map = new HashMap<>();
                map.put("deaths", deathstr);
                map.put("injuries", injuriesstr);
                map.put("saves", savesstr);
                map.put("cars",carsstr);
                map.put("buildings",buildingsstr);

                reference = ls.database.getReference().child("Volunteers").child(currentVolName).child("disasters").child(key);
                reference.updateChildren(map);

                reference = ls.database.getReference().child("Disasters").child(key);
                reference.updateChildren(map);


            }
        });
    }



    public void readFromDatabase() {
        volunteerProfile = new VolunteerProfile();

//        Toast.makeText(getActivity(), "Read", Toast.LENGTH_SHORT).show();
//        Toast.makeText(getActivity(), ""+ls.mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Volunteers");
        reference.orderByChild("email").equalTo(ls.mAuth.getCurrentUser().getEmail()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                                        Toast.makeText(getActivity(), "Read" , Toast.LENGTH_SHORT).show();

                if (dataSnapshot.exists() && dataSnapshot.getValue() != null) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        HashMap<String, String> map = (HashMap<String, String>) snapshot.getValue();
                        volunteerUtilsList.add(new VolunteerUtils(map.get("name"), map.get("address"), map.get("city"), map.get("contact")
                                , map.get("email"), map.get("disaster_id"), map.get("date")));

                        currentVolDisasterId = map.get("disaster_id");
//                        Toast.makeText(getActivity(), "Read" + currentVolDisasterId, Toast.LENGTH_SHORT).show();
                        currentVolName = map.get("name");

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Volunteers").child(currentVolName).child("disasters");
                        reference.orderByChild("deaths").limitToLast(1).addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                key = dataSnapshot.getKey();

                            }



                            @Override
                            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                } else {


                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException());
            }
        });

    }

    public void readFromDisasterDatabase() {



    }
}
