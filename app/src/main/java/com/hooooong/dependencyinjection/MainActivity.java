package com.hooooong.dependencyinjection;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;


@Fullscreen
@EActivity(R.layout.activity_main)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class MainActivity extends AppCompatActivity {

    // ButterKnife 를 쓰는 경우
    // ButterKnife.bind(this); 를 꼭 해줘야 한다. (onCreate() 에서)
    //@BindView(R.id.text)
    //TextView text;

    // Android Annotation 을 쓰는 경우
    // Activity 의 이름 뒤에 _ 가 붙여지기 때문에
    // Manifest.xml 에서 수정해야 한다.
    @ViewById
    TextView text;

    // AfterViews 는  View 가 load 된 후 실행된다.
    @AfterViews
    public void init(){
        text.setText("Android AA");
    }

    @UiThread
    public void main(){
        // 여기 코드는 Main Thread 에서 실행이 된다.
    }

    @Background
    public void sub(){
        // 여기 코드는 Sub Thread 에서 실행이 된다.
    }
}
