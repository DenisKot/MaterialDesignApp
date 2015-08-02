package com.inspiriongames.materialdesignappdemo;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private RelativeLayout mRoot;
    private TextInputLayout mEmailLayout;
    private TextInputLayout mPasswordLayout;
    private TextView mInputEmail;
    private TextView mInputPassword;
    private FloatingActionButton fab;

    private View.OnClickListener mSnackBarClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mRoot = (RelativeLayout) findViewById(R.id.rootActivitySecond);

        mEmailLayout = (TextInputLayout) findViewById(R.id.inputLayout);
        mPasswordLayout = (TextInputLayout) findViewById(R.id.inputPasswordLayout);

        mInputEmail = (TextView) findViewById(R.id.input_email);
        mInputPassword = (TextView) findViewById(R.id.input_password);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.setVisibility(View.GONE);
                Snackbar.make(mRoot, "FAB Clicked", Snackbar.LENGTH_LONG)
                        .setAction("Show", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                fab.setVisibility(View.VISIBLE);
                            }
                        })
                        .show();
            }
        });

        //mEmailLayout.setError("Email cannot be blank");
        //mPasswordLayout.setError("Password cannot be blank");
    }

    public void submit(View view){
        boolean isEmptyEmail = isEmptyEmail();
        boolean isEmptyPassword = isEmptyPassword();

        if(isEmptyEmail && isEmptyPassword){
            Snackbar.make(mRoot, "One Or More Fields Are Blank", Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.text_dissmis), mSnackBarClickListener)
                .show();
        } else if(isEmptyEmail && !isEmptyPassword) {
            mEmailLayout.setError("Email Cannot Be Empty");
            mPasswordLayout.setError(null);
        } else if(!isEmptyEmail && isEmptyPassword) {
            mEmailLayout.setError(null);
            mPasswordLayout.setError("Password Cannot Be Empty");
        }
    }

    private boolean isEmptyEmail(){
        return mInputEmail.getText() == null ||
                mInputEmail.getText().toString() == null ||
                mInputEmail.getText().toString().isEmpty();
    }

    private boolean isEmptyPassword(){
        return mInputPassword.getText() == null ||
                mInputPassword.getText().toString() == null ||
                mInputPassword.getText().toString().isEmpty();
    }

}
