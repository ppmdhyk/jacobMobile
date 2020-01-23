package com.example.jacobmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jacobmobile.model.PostSaran;
import com.example.jacobmobile.rest.ApiClient;
import com.example.jacobmobile.rest.ApiInterface;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText input;
    String text;
    String YOUR_API_SITE_KEY="6Lcsk9AUAAAAANpYKV0Nq9P6H0He3LpGgG9vlBBb";
    ApiInterface mApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_input);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        input=findViewById(R.id.edtinputtxt);
        submit = findViewById(R.id.btnsubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=input.getText().toString();
                getCaptcha();
               // Toast.makeText(getApplicationContext(),"Submit Successfully",Toast.LENGTH_LONG).show();
                Call<PostSaran> postSaranCall = mApiInterface.saran(text);
                postSaranCall.enqueue(new Callback<PostSaran>() {
                    @Override
                    public void onResponse(Call<PostSaran> call, Response<PostSaran> response) {
                        Toast.makeText(getApplicationContext(),"Submit Successfully",Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostSaran> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Submit Error", Toast.LENGTH_LONG).show();
                    }
                });

                finish();

            }
        });

    }


    public void getCaptcha() {
        SafetyNet.getClient(getApplicationContext()).verifyWithRecaptcha(YOUR_API_SITE_KEY)
                .addOnSuccessListener( this,
                        new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                                // Indicates communication with reCAPTCHA service was
                                // successful.
                                String userResponseToken = response.getTokenResult();
                                if (!userResponseToken.isEmpty()) {
                                    // Validate the user response token using the
                                    // reCAPTCHA siteverify API.
                                    Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                .addOnFailureListener( this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            // An error occurred when communicating with the
                            // reCAPTCHA service. Refer to the status code to
                            // handle the error appropriately.
                            ApiException apiException = (ApiException) e;
                            int statusCode = apiException.getStatusCode();
                            Log.d("Captcha", "Error: " + CommonStatusCodes
                                    .getStatusCodeString(statusCode));
                        } else {
                            // A different, unknown type of error occurred.
                            Log.d("Capctha", "Error: " + e.getMessage());
                        }
                    }
                });
    }

}
