# FrameApp
App开发框架

1、网络访问框架

HttpWork类封装了okhttp的，get和post操作的方法，可以说他是实际上的网络操作类，
而BaseWebFrg只是个代理类，有涉及网络请求的fragment只需要继承BaseWebFrg，实现
其中的抽象方法，以便于回调即可。这样在请求时一句代码便可搞定。
httptool.doPost(),或者httptool.doGet().或者其他请求。
    
    几个参数的意义：

    requestcode：请求标识号，在多线程并发时便于区分每个请求，让参数，url，请求结果与处理方    法对应上。

    showdialog：是否显示等待框。

    dialogmsg：等待框内容。

    几个抽象方法的意义（需要具体子类去实现）  

    void netError(int requestcode); 网络未连接是的处理。

    Map<String, String> getParmas(int requestcode)；请求的参数。

    String getUrlSuffix(int requestcode)；请求的出去固定部分，后面的Url。

    Class<?> getParserClass(int requestcode);返回结果要解析成的实体类。

    doSuccess(int requestcode, BaseBean bean, String data)；结果返回成功时的处理。

    void doError(int requestcode, Exception e);请求失败或者其它异常时的处理。

2、fragment框架

    采用模板模式，在baseFrg类里封装了oncreateview,onstop.ondestryview等生命周期方法，
    将initView，getLayoutId抽象化，交给具体子类去实现。将titlebar的初始化和处理方法
    封装好,交给子类去具体设置等等。

    BaseWebFrg继承自BaseFrg，引入了HttpWork对象，并声明为protected,并继承HttpInter，
    让具体需要网络请求的子类去操作并实现它们。

    当然，如果是基于Listview的或者viewpager的fragment，还可以写BaseListFrg，
    BaseViewPagerFrg，方便开发拓展。

3、 Activity框架

    同fragment框架原理，采用模板模式，写了BaseActivity类封装一些固有的生命周期的方法，
    将getLayoutId和initView交给子类去实现。

4、Activity的管理类和Fragment管理类。

   AppManager负责Activity的出栈入栈及其他操作。
   
   FrgManger负责Fragment的出栈入栈及其他操作。

5、Activiity之间的跳转和Fragment之间的跳转封装。

   IntentUtil和FragmentUtil分别封装类Actiivty之间的跳转和Fragment之间的跳转

   void start_activity(Activity activity, Class<?> cls,Bundle bundle, 
   boolean finishable,    int animabletype)

   activity：当前activity

   bundle：封装了要传递的内容

   finishable：是否销毁

   animabletype: 跳转动画

   toFragmentPush(FrgManager frgManager, BaseEnum enumcls, Bundle bundle, Boolean backable)
   
   frgManager：fragment处理管理类对象

   enumcls: fragment所处于的层次，这里有enum类来管理。比如first,main,sub

   backable: 是否入栈，就是可返回。

6、Listview或者gridview的万能适配器

   CommonAdapter,ViewHolder

7、db框架

   DBConstant：封装了db的常量，比如数据库名称及版本。创建表的sql语句，表的名称等。

   DBHelper：操作database的帮助类。

   DatabaseContext: 若是需要将数据库保存的sd卡的话会用到它。

   Dao：是具体表的接口类。封装了插入，删除，读取的操作。

   DaoIml：是具体的表的实现类，实现类Dao封装的表操作，以及封装了其他的表操作。

8、各种工具类，比如shareprefutil,采用单例模式，封装起读写方法，方便使用，FileUtil，
   文件读写工具类，   JsonUtil，json串处理工具类，TimeZonUtil时间格式转换工具类，
   ToastUtil，Toast显示工具类，StringUtil,字符串处理工具类等等。

9、Logger日志框架，需要引入compile 'com.orhanobut:logger:1.3'
   页面之间或者前台后台之间信息同步用到了eventbus，
   compile 'de.greenrobot:eventbus:2.4.0'
   图片下载缓存库用到了picasso,
   compile 'com.squareup.picasso:picasso:2.5.2'
   分析内存泄露OOM用到了leakcanary开源项目。
   解析json串用到了Gson，
   compile 'com.google.code.gson:gson:2.3.1'
   单元测试用到了andtoidjunittestrunner,
   androidTestCompile    'com.android.support.test:runner:0.3'



   
   





