package com.necohorne.gymapp.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.necohorne.gymapp.R;

import static android.text.TextUtils.isEmpty;

public class RegisterNewAccount extends AppCompatActivity {

    private static final String TAG = RegisterNewAccount.class.getSimpleName();

    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private FirebaseAuth mAuth;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register_new_account );

        mAuth = FirebaseAuth.getInstance();

        setUpUi();

        Button createNewAccountButton = findViewById(R.id.register_account_create_button);
        createNewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        } );
    }

    @Override
    public void onBackPressed() {
        Toast.makeText( RegisterNewAccount.this, getString(R.string.acc_create_canceled), Toast.LENGTH_LONG ).show();
        super.onBackPressed();
    }

    private void sendVerificationEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            user.sendEmailVerification().addOnCompleteListener( new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterNewAccount.this, getString(R.string.verification_sent) , Toast.LENGTH_SHORT ).show();
                    }else {
                        Toast.makeText(RegisterNewAccount.this, getString(R.string.verification_not_sent) , Toast.LENGTH_SHORT ).show();
                    }
                }
            } );
        }
    }

    private void setUpUi() {
        mEmail = findViewById( R.id.register_account_email);
        mPassword = findViewById( R.id.register_account_password);
        mConfirmPassword = findViewById( R.id.register_account_password_confirm);
        mProgressBar = findViewById( R.id.create_account_progressbar);
    }

    private void createAccount() {
//        Log.d(TAG, "createAccount has been called");

        //check for empty fields to ensure all fields are filled in.
        if (!isEmpty(mEmail.getText().toString())
                && !isEmpty( mPassword.getText().toString())
                && !isEmpty( mConfirmPassword.getText().toString())){
            //check if passwords match
            if ((mPassword.getText().toString()).equals(mConfirmPassword.getText().toString())){
                mProgressBar.setVisibility( View.VISIBLE );
                mAuth.createUserWithEmailAndPassword( mEmail.getText().toString(), mPassword.getText().toString()).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                Log.d(TAG, "registerEmail onComplete; " + task.isSuccessful());
                                if (task.isSuccessful()){
//                                    Log.d(TAG, "registerEmail onComplete; " + FirebaseAuth.getInstance().getCurrentUser().getUid());
                                    sendVerificationEmail();
                                    FirebaseAuth.getInstance().signOut();
                                    mProgressBar.setVisibility( View.INVISIBLE );
                                    startActivity( new Intent( RegisterNewAccount.this, LoginActivity.class));
                                    finish();
                                }else {
                                    mProgressBar.setVisibility( View.INVISIBLE );
                                    Toast.makeText( RegisterNewAccount.this, getString(R.string.acc_creation_unsuccessfull), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }else {
                Toast.makeText( RegisterNewAccount.this, getString(R.string.passwords_dont_match), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText( RegisterNewAccount.this, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
        }
    }
}
