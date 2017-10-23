Android Programing
----------------------------------------------------
### 2017.10.23 23일차

#### 예제
____________________________________________________

#### 공부정리
____________________________________________________

##### __DI__

- DI 란?

  > DI 는 __D__ ependency __I__ njection의 약자로 의존성 주입으로 해석할 수 있다. 프로그래밍에서 구성요소간의 의존 관계가 소스코드 내부가 아닌 외부의 설정파일 등을 통해 정의되게 하는 디자인 패턴 중의 하나이다.

  - 의존 관계 설정이 컴파일시가 아닌 실행시에 이루어져 모듈들간의 결합도 를 낮출 수 있다.

  - 코드 재사용을 높여서 작성된 모듈을 여러 곳에서 소스코드의 수정 없이 사용할 수 있다.

  - 모의 객체 등을 이용한 단위 테스트의 편의성을 높여준다.

- Android DI

  ```java
  TextView text1, text2, text3, text4;
  ImageView img1, img2, img3, img4;

  @Override
  public void onCreate(Bundle bundle){
    text1 = (TextView)findViewById(R.id.~~);
    text2 = (TextView)findViewById(R.id.~~);
    text3 = (TextView)findViewById(R.id.~~);
    // 생략
    // View 마다 설정해줘야 한다.
  }
  ```

  - View 객체가 많아질수록 관리해야 하는 View 객체가 많아지고, 코드 가독성이 떨어지고 유지보수가 힘들다.

  - Android 에서 View DI를 지원하는 Library는 `ButterKnife` 와 `AndroidAnnotations`가 있다.

- 참조 : [DI(의존성 주입)](https://ko.wikipedia.org/wiki/%EC%9D%98%EC%A1%B4%EC%84%B1_%EC%A3%BC%EC%9E%85)

##### __ButterKnife__

- ButterKnife 란?

  > View, Resource 에 대한 의존성 주입을 Annotation으로 편리하게 지원하는 Library. Event Binding Annotation 도 지원한다.

- ButterKnife 사용법

  - Gradle 설정

  ```Gradle
  compile 'com.jakewharton:butterknife:8.8.1'
  annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
  ```

  - Annotation 설정

  ```java
  class ExampleActivity extends Activity {
    // View 에 대한 Annotation 설정
    @BindView(R.id.~)
    TextView title;
    // Resource 에 대한 Annotation 설정
    @BindString(R.string.~)
    String title;

    // 배열로 생성할 수 있다.
    @BindViews({ R.id.~, R.id.~, R.id.~ })
    List<EditText> nameViews;

    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      // Activity 에서 적용 방법
      setContentView(R.layout.~);
      ButterKnife.bind(this);
      // Fragment 에서 적용 방법
      // View view = inflater.inflate(R.layout.fancy_fragment, container, false);
      // ButterKnife.bind(this, view);
    }

    // Listener 에 대한 Annotation 설정
    @OnClick(R.id.~)
    public void submit(View view) {
      // TODO submit data to server...
    }
  }
  ```

- 참조 : [ButterKnife](http://jakewharton.github.io/butterknife/)

##### __AndroidAnnotations__

- AndroidAnnotations 란?

  > View, Extras, System Service, Resource 에 대한 의존성 주입을 Annotation으로 편리하게 지원하는 Library. 또한 UIThread 와 BackgroundThread 에서 실행하게 할 수 있고, Event Binding 등 다양한 Annotation 을 지원한다.

- AndroidAnnotations 사용법

  - Gradle 설정

  ```gradle
  annotationProcessor "org.androidannotations:androidannotations:4.+"
  compile "org.androidannotations:androidannotations-api:4.+"
  ```

  - Annotation 설정

  ```java
  // Activity Theme 와 Layout 을 Annotation 으로 설정가능하다.

  // Android Annotation 을 쓰는 경우
  // Activity 의 이름 뒤에 _ 가 붙여지기 때문에
  // Manifest.xml 에서 수정해야 한다.
  @Fullscreen
  @EActivity(R.layout.activity_main)
  @WindowFeature(Window.FEATURE_NO_TITLE)
  public class MainActivity extends AppCompatActivity {
    // View 에 대한 Annotation 설정
    @ViewById
    TextView text;

    // Resource 에 대한 Annotaion 설정
    @AnimationRes
    Animation fadeIn;

    // Listener 에 대한 Annotation 설정
    @Click({R.id.~~})
    void updateBookmarksClicked() {
      searchAsync(search.getText().toString(), application.getUserId());
    }

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
  ```

- 참조 : [AndroidAnnotations](http://androidannotations.org/)
