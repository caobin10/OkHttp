package com.demo.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.demo.test.bean.TicketAuth;
import com.demo.test.http.HttpResponse;
import com.demo.test.manager.AppConfig;
import com.demo.test.util.OkHttpUtil;
import com.demo.test.util.RetrofitUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends Activity
{
    private EditText userNameEt;
    private EditText passwordEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEt = (EditText) findViewById(R.id.et_user_name);
        passwordEt = (EditText) findViewById(R.id.et_password);
    }

    public void btn(View view){

        final String username = userNameEt.getText().toString().trim();
        final String password = passwordEt.getText().toString().trim();


        RetrofitUtil.provideRetrofit(OkHttpUtil.instance().getClient(), AppConfig.Url.BASE_URL)
                .create(DataService.class).login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResponse<TicketAuth>>() //Observer:观察者
                {
                    @Override
                    public void onSubscribe(Disposable d) {//Disposable是一个接口,onSubscribe:订阅
//                        dialog = DialogUtil.createLoadingDefault(LoginActivity.this, "正在加载");
//                        dialog.show();
                    }

                    @Override
                    public void onNext(HttpResponse<TicketAuth> resData)
                    {
//                        dialog.dismiss();
                        if (resData.getCode() == AppConfig.RESPONSE_OK) {
                            final TicketAuth auth = resData.getData();
                            auth.setUsername(username);
                            auth.setPassword(password);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {//Throwable:可抛出
//                        dialog.dismiss();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
