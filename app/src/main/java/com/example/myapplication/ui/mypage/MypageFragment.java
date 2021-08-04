package com.example.myapplication.ui.mypage;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Outline;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.SignUpActivity;
import com.example.myapplication.databinding.FragmentMypageBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MypageFragment extends Fragment {

    private ImageView halfpeng_img;
    Button usSignUp, usLogin;
    GoogleSignInClient mGoogleSignInClient;
    public static final int RC_SIGN_IN=1;
    SignInButton signInButton; //SignInButton signInButton;

    public static MypageFragment newInstance() {
        return new MypageFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_mypage,container,false);

        halfpeng_img = (ImageView)v.findViewById(R.id.peng_half_image);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            halfpeng_img.setOutlineProvider(new ViewOutlineProvider() {
                @Override public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0,22, view.getWidth() , view.getHeight() - 22 , 100);
                } });
            halfpeng_img.setClipToOutline(true);
        }


        usSignUp = v.findViewById(R.id.usSignUpBT);
        usLogin = v.findViewById(R.id.usLoginBT);

        usSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        usLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        /*
        1. Google 로그인 및 GoogleSignInClient 개체 구성
        사용자의 ID와 기본 프로필 정보를 요청하기위해 객체를 생성함*/
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        //2. 기존 로그인한 사용자 확인
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
       GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        //updateUI(account); //이미 등록한 사용자면 UI를 새롭게 구성.

        /*
        * 1)버튼이 눌렸을 때의 행동을 추가
        * 2)우리들이 허가를 요청할 gso객체를 인자로 하여 만든 mGoogleSignInClient
        * 객체의 getSignInIntent(); 메소드를 이용해 Intent만듬
        * 3) 만들어진 Intent 를 startActivityForResult()에 인수로 전달해 실행함
        * 4)사용자에게 인증 허가를 요청하는 화면(Activity)보임
        *
        * */
        // Set the dimensions of the sign-in button.
        //ERRORERROR++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //signInButton = v.findViewById(R.id.sign_in_button);
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        /*
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        */
        /* //프로필 정보 얻기 - 현재 로그인 한 사용자의 프로필 정보 검사
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
        }
        */


        /*
        백엔드 서버로 인증

        1.서버에 ID토큰 보내기
        When you configure Google Sign-in, call the requestIdToken method and pass it your server's web client ID.
       * */

        // Request only the user's ID token, which can be used to identify the
        // user securely to your backend. This will contain the user's basic
        // profile (name, profile picture URL, etc) so you should not need to
        // make an additional call to personalize your application.
        /*
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        */
        //2.When your app starts, check if the user has already signed in to your app using Google,
        // on this device or another device, by calling silentSignIn:
        /*
        GoogleSignIn.silentSignIn()
                .addOnCompleteListener(
                        this,
                        new OnCompleteListener<GoogleSignInAccount>() {
                            @Override
                            public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                                handleSignInResult(task);
                            }
                        });
        */
        return v;
    }

    /*
    * 사용자가 google SignIn버튼을 클릭하면 onclick메소드 실행, signin메소드 호출.
    * signIn메소드는 사용자에게 허가를 요청하는 엑티비티 띄움.
    * startActivityForResult를 이용해서 사용자의 행동에 대한 결과 응답받음.
    * */
    /*
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
*/

    /*
    *사용자가 Activity에서 한 행동을 onActivityResult()에서 data로 받을 수 있으며
    * GoogleSignIn.getSignedInAccountFromIntent(data);메소드를 통해
    * Task<GoogleSignInAccount>객체로 변환가능
    * task객체를 인수로 handleSignInResult() 메소드를 호출함
    * */
    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
*/
    /*
    * Task<GoogleSignInAccount>객체의 getResult()메소드를 이용하여 GoogleSignInAccount 객체를
    * 반환 받을 수 있다.
    * GoogleSignInAccount객체를 통해 사용자의 Google계정 정보를 얻어올 수 있다.
    * 얻어온 account객체를 통해 사용자의 정보를 Log로 출력해본다.
    * */

//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
 //       try {
            //GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            /*
            * String email = account.getEmail();
            * String m = account.getFamilyName();
            * String m2 = account.getGivenName();
            * String m3 = account.getDisplayName();
            * Log.d("Name: ",m);
            * Log.d("Name2: ",m2);
            * Log.d("Name3: ",m3);
            * Log.d("Email: ",email);
            * */


            //updateUI(account);
 //       } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
//        }
//    }

}


