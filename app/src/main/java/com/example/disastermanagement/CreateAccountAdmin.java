package com.example.disastermanagement;

import android.content.Intent;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class CreateAccountAdmin extends Fragment {

    Singleton ls = Singleton.getInstance();

    EditText createEmailEt, createPassEt, createConfirmPassEt;
    String emailStr, passStr, confirmPassStr;
    Button createAccBtn;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_create_account_admin, container, false);


    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_create_account_admin);

        createEmailEt = getActivity().findViewById(R.id.createEmailEt);
        createPassEt = getActivity().findViewById(R.id.createPassEt);
        createConfirmPassEt = getActivity().findViewById(R.id.createConfirmPassEt);
        createAccBtn = getActivity().findViewById(R.id.createAccBtn);


        createAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAcc();
            }
        });
    }

    public void createAcc()
    {
        emailStr = createEmailEt.getText().toString().trim();
        passStr = createPassEt.getText().toString().trim();
        confirmPassStr = createConfirmPassEt.getText().toString().trim();

        if (passStr.equals(confirmPassStr))
        {
            ls.mAuth.createUserWithEmailAndPassword(emailStr, passStr)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("", "createUserWithEmail:onComplete:" + task.isSuccessful());

//                            writeToDatabase(emailStr);

                            if (!task.isSuccessful()) {
                                Toast.makeText(getActivity(),
                                        "Authentication failed.", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

        }

        else{
            createConfirmPassEt.setError("Password don't match");
        }
    }
}
