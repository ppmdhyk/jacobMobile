package com.example.jacobmobile;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    ImageView back;
    EditText input;
    String YOUR_API_SITE_KEY="6Lcsk9AUAAAAANpYKV0Nq9P6H0He3LpGgG9vlBBb";
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_input);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        input = findViewById(R.id.edtinputtxt);
        back=findViewById(R.id.btnback);

//        Call<PostSaran> getSaran = mApiInterface.ambilsaran();
//        getSaran.enqueue(new Callback<PostSaran>() {
//            @Override
//            public void onResponse(Call<PostSaran> call, Response<PostSaran> response) {
//                List<Saran> saran= response.body().getmSaran();
//                for (int i=0; i<saran.size(); i++) {
//                    Log.w("Saran ke "+i, saran.get(i).getSaran());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PostSaran> call, Throwable t) {
//                Log.e("debug", "onFailure: ERROR > " + t.toString());
//            }
//        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submit = findViewById(R.id.btnsubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCaptcha();
               // Toast.makeText(getApplicationContext(),"Submit Successfully",Toast.LENGTH_LONG).show();
                Call<PostSaran> postSaranCall = mApiInterface.inputsaran(input.getText().toString());
                postSaranCall.enqueue(new Callback<PostSaran>() {
                    @Override
                    public void onResponse(Call<PostSaran> call, Response<PostSaran> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Submit Successfully",Toast.LENGTH_LONG).show();
                            finish();

                        }else{
                            Toast.makeText(getApplicationContext(),"Submit Unsuccessfully",Toast.LENGTH_LONG).show();
                            finish();
                        }
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
                                    Toast.makeText(getApplicationContext(),input.getText().toString(),Toast.LENGTH_LONG).show();
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
