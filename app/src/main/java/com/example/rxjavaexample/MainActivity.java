package com.example.rxjavaexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
Observable<Integer> observable;
Observable<String> stringObservable;
Observer<String> stringObserver;
    Observer<Integer> observer;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context=this;
         observable=Observable.just(1,2,4,5);
         observer=new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
                Toast.makeText(context,"Subscribed", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Toast.makeText(context,integer.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onComplete() {
                Toast.makeText(context,"Completed",Toast.LENGTH_LONG).show();
            }
        };


        observable.subscribe(observer);
        stringObservable=Observable.just("gunish","matta");
        stringObserver=new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull String s) {
                Toast.makeText(context,s,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onComplete() {

                Toast.makeText(context,"Completed String ",Toast.LENGTH_LONG).show();
            }
        };

        stringObservable.subscribe(stringObserver);

    }

    @Override
    protected void onResume() {
        super.onResume();
        observable.subscribe(observer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
        Log.d("Disposable","disposed");
    }
}