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
import android.os.AsyncTask;
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

import com.example.myapplication.Action;
import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.SignUpActivity;
import com.example.myapplication.UserIdApplication;
import com.example.myapplication.databinding.FragmentMypageBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MypageFragment extends Fragment {

    private ImageView halfpeng_img;
    Button usSignUp, usLogin, signInButton;
    TextView checkMessage;
    private GoogleSignInClient mGoogleSignInClient;
    public static final int RC_SIGN_IN=9001;
    private static final String TAG = "requestIdToken";
    private static String idToken = new String();

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

        checkMessage = v.findViewById(R.id.textView4);
        //서버 클라이언트 아이디
        String serverClientId = getString(R.string.server_client_id);

        /* 백엔드 서버로 인증
        1. Google 로그인 및 GoogleSignInClient 개체 구성
        사용자의 ID와 기본 프로필 정보를 요청하기위해 객체를 생성함
         1.서버에 ID토큰 보내기
        When you configure Google Sign-in, call the requestIdToken method and pass it your server's web client ID.
       * */

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //.requestScopes(new Scope(Scopes.DRIVE_APPFOLDER)) //인증코드를 얻기 위해 필요한 코드
                //.requestServerAuthCode(serverClientId) //인증코드를 얻기 위해 필요한 코드 : 토큰과 교환할 수 있음.서버 아닐경우 필요없음
                .requestEmail()
                .requestIdToken(serverClientId) // 토큰//서버 아닐겅우 삭제
                .build();

        // Build a GoogleSignInClient with the options specified by gso. // 클라이언트 생성
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        /*
        * 1)버튼이 눌렸을 때의 행동을 추가
        * 2)우리들이 허가를 요청할 gso객체를 인자로 하여 만든 mGoogleSignInClient
        * 객체의 getSignInIntent(); 메소드를 이용해 Intent만듬
        * 3) 만들어진 Intent 를 startActivityForResult()에 인수로 전달해 실행함
        * 4)사용자에게 인증 허가를 요청하는 화면(Activity)보임
        *
        * */

        signInButton = v.findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

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

    @Override
    public void onStart() {
        super.onStart();

        //앱에 로그인 되어 있지 않은 상태라면 null 반환
        //앱에 이미 로그인 되어 있는 상태라면 null을 반환하지 않음
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        //updateUI(account);
    }

    // 구글 계정 선택 후 결과 처리
    // /*
    //    *사용자가 Activity에서 한 행동을 onActivityResult()에서 data로 받을 수 있으며
    //    * GoogleSignIn.getSignedInAccountFromIntent(data);메소드를 통해
    //    * Task<GoogleSignInAccount>객체로 변환가능
    //    * task객체를 인수로 handleSignInResult() 메소드를 호출함
    //    * */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // GoogleSignInClient.getSignInIntent 인텐트 실행 후 결과코드 반환
        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    // 로그인 결과 처리
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Log.v("로그인 ","handleSignInResult");
            //로그인 성공 시 계정에 맞는 UI로 업데이트
            updateUI(account); //계정 정보 가져오기

        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }
    }

    // 로그인
    // /*
    //    * 사용자가 google SignIn버튼을 클릭하면 onclick메소드 실행, signin메소드 호출.
    //    * signIn메소드는 사용자에게 허가를 요청하는 엑티비티 띄움.
    //    * startActivityForResult를 이용해서 사용자의 행동에 대한 결과 응답받음.
    //    * */
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        Log.v("구글로그인","signIn");
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // 로그아웃
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //updateUI(null);
                    }
                });
    }

    // 앱에 계정 접근 끊어내기(버튼 추가하여 온클릭 이벤트 등록): 탈퇴 개념인거같은데 정확하지 않음

    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //updateUI(null);
                    }
                });
    }


    //계정 상태에 맞는 UI로 업데이트
    private void updateUI(@Nullable GoogleSignInAccount account) {
        if (account != null) {

            //계정 정보 가져오기
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();
            //String serverAuthCode = account.getServerAuthCode(); //onCreate함수 gso부분 주석 해제하면 값이 반환됨
            idToken = account.getIdToken();

            Log.d(TAG, "handleSignInResult:personName "+personName);
            Log.d(TAG, "handleSignInResult:personGivenName "+personGivenName);
            Log.d(TAG, "handleSignInResult:personEmail "+personEmail);
            Log.d(TAG, "handleSignInResult:personId "+personId);
            Log.d(TAG, "handleSignInResult:personFamilyName "+personFamilyName);
            Log.d(TAG, "handleSignInResult:personPhoto "+personPhoto);
            //Log.d(TAG, "handleSignInResult:serverAuthCode "+serverAuthCode);
            Log.d(TAG, "handleSignInResult:idToken "+idToken);

            checkMessage.setText(personName+" 님이 로그인 되었습니다."); //다른 프래그먼트로 이동 시 메세지가 사라짐 //(에러)
            //전체 전역변수로 idtoken을 넘겨줌
            ((UserIdApplication) getActivity().getApplication()).setId(idToken);

            new Thread(){
                @Override
                public void run() {
//                        super.run();

                    try {
                        Action action=Action.getInstance();
                        JSONObject reqtoServer=new JSONObject();
                        reqtoServer.put("idToken",idToken);
                        JSONObject res=action.post(reqtoServer.toString(),"post");
                        //System.out.println(res);
                        if(res != null){
                            Log.v("성공 마이페이지 apiString", res.toString());
                        }
                        else
                            Log.v("성공 마이페이지 apiString", "null");

                    } catch (JSONException e){
                        //에러
                        e.printStackTrace();
                        Log.v("실패 마이페이지 apiString", "실패");
                    }
                }
            }.start();

        } else {
            Log.v("구글로그인 에러", "1");
        }
    }

    private void refreshIdToken() {
        // 토큰 만료 시 refresh
        // 어케쓰는지 아직 잘 모르겠음
        mGoogleSignInClient.silentSignIn()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<GoogleSignInAccount>() {
                    @Override
                    public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                        handleSignInResult(task);
                    }
                });
    }








}


