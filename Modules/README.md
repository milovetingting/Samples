# 组件化开发的主要步骤：

### 一、新建Modules

1、新建Project,作为应用的主Module。

2、新建Module:"Common"，类型选择"Android Library",作为所有其它Module的基础依赖库。

3、新建Module:"Home"，类型选择"Android Library",依赖"Common"。

4、新建Module:"Project"，类型选择"Android Library",依赖"Common"。

5、新建Module:"User"，类型选择"Android Library",依赖"Common"。

**具体新建怎样的Module，可以根据实际业务来调整。这里选择新建"Home"、"Project"、"User"来模拟业务。**

### 二、增加Flag以便在release和debug模式下切换

**1、在gradle.properties文件中增加一个变量**

    isDebug = false

![flag.png](https://upload-images.jianshu.io/upload_images/3381990-ba4cca25a5fa0501.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**当isDebug为true时，为Debug模式，其它的Module可以作为单独的App运行。当isDebug为false时，为Release模式，其它的Module为Library模式，不能单独运行,此时只有主App可以运行。**

**2、修改app的build.gradle文件**

    implementation project(':common')
    if (!isDebug.toBoolean()) {
        implementation project(':home')
        implementation project(':project')
        implementation project(':user')
    }

![app_flag.png](https://upload-images.jianshu.io/upload_images/3381990-527cfa0ff9e9e29b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**3、修改home的build.gradle文件**

    if (isDebug.toBoolean()) {
	    apply plugin: 'com.android.application'
	} else {
	    apply plugin: 'com.android.library'
	}

![home_flag.png](https://upload-images.jianshu.io/upload_images/3381990-772b26caf4703d31.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**4、修改project的build.gradle文件**

    if (isDebug.toBoolean()) {
	    apply plugin: 'com.android.application'
	} else {
	    apply plugin: 'com.android.library'
	}

![project_flag.png](https://upload-images.jianshu.io/upload_images/3381990-7c724ab1c7beb40c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**5、修改user的build.gradle文件**

    if (isDebug.toBoolean()) {
	    apply plugin: 'com.android.application'
	} else {
	    apply plugin: 'com.android.library'
	}

![user_flag.png](https://upload-images.jianshu.io/upload_images/3381990-b259028ccc40ef1c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 为便于各Module单独调试开发，可以在各Module下根据isDebug的变量区分模式。

切换工程到Project模式下，将原来的AndroidManifest.xml文件移除，在Module的src目录下新建debug和release目录，在新建的两个目录下，分别新建AndroidManifest.xml文件。以Home模块为例：

![home_manifest.png](https://upload-images.jianshu.io/upload_images/3381990-859a2e9d3286e5cd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**Debug模式下的AndroidManifest.xml**

![home_debug_manifest.png](https://upload-images.jianshu.io/upload_images/3381990-e908c187b36f0479.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**Release模式下的AndroidManifest.mxl**

![home_release_manifest.png](https://upload-images.jianshu.io/upload_images/3381990-d77a62d53186f828.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


在Home下的build.gradle文件中配置AndroidManifest.xml

    sourceSets {
        main {
            if (isDebug.toBoolean()) {
                manifest.srcFile 'src/debug/AndroidManifest.xml'
            } else {
                manifest.srcFile 'src/release/AndroidManifest.xml'
                java { exclude 'debug/**' }
            }
        }
    }


![home_gradle_source.png](https://upload-images.jianshu.io/upload_images/3381990-a4ab6113a8372490.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


其它Module也是相似的处理。


### 三、统一管理Module版本号

1、为便于统一管理版本号，在项目的根目录下的build.gradle文件中增加统一的版本号:

    ext {
	    compileSdkVersion = 28
	
	    minSdkVersion = 21
	    targetSdkVersion = 28
	    versionCode = 1
	    versionName = "1.0"
	}

![version.png](https://upload-images.jianshu.io/upload_images/3381990-d9f695ecf08c0158.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


2、在其它Module下相应修改

**App模块:**

![app_version.png](https://upload-images.jianshu.io/upload_images/3381990-1189de646a14b7cd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**Common模块:**

![common_version.png](https://upload-images.jianshu.io/upload_images/3381990-3cf02be42e527ff3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**Home模块:**

![home_version.png](https://upload-images.jianshu.io/upload_images/3381990-002f4c055092b0c7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**Project模块:**

![project_version.png](https://upload-images.jianshu.io/upload_images/3381990-4b83cc48cbef2858.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**User模块:**

![user_version.png](https://upload-images.jianshu.io/upload_images/3381990-d109a2ccaebf2d05.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 四、各Module间通信

为解决各Module间通信的问题，引入ARouter框架。GitHub地址：[ARouter](https://github.com/alibaba/ARouter "ARouter")

为避免各Module重复引用，在Common中引用一次，其它Module复用即可。

![common_arouter.png](https://upload-images.jianshu.io/upload_images/3381990-63ca33cec654c55c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**注意：由于其它依赖Common的Module也需要使用Arouter，因此在引入时，需要把implementation改为api。如果使用implementation,其它Module会无法使用Arouter。**

其它Module中使用:

不需要再次implementation,但是还是需要在dependencies增加

    annotationProcessor 'com.alibaba:arouter-compiler:1.2.2'

以及在android-defaultConfig中增加：

	javaCompileOptions {
            annotationProcessorOptions {
                arguments = [AROUTER_MODULE_NAME: project.getName()]
            }
        }    

注意："AROUTER_MODULE_NAME"这个名称，不可以改为其它字符串，否则会编译报错。

![home_arouter.png](https://upload-images.jianshu.io/upload_images/3381990-c274f6834a4c0c00.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


在Common模块下增加BaseApplication,对ARouter进行初始化。

    public class BaseApplication extends Application {

	    private boolean isDebugARouter = true;
	
	    @Override
	    public void onCreate() {
	        super.onCreate();
	
	        if (isDebugARouter) {
	            ARouter.openLog();
	            ARouter.openDebug();
	        }
	        ARouter.init(this);
	    }
	}

在主Module:App中增加App,继承自BaseApplication,然后在AndroidManifefst.xml中引用。

>    `public class App extends BaseApplication {}`

    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:tools="http://schemas.android.com/tools"
	    package="com.wangyz.modules">
	
	    <application
	        android:name=".App"
	        android:allowBackup="true"
	        android:appComponentFactory="whateverString"
	        android:icon="@mipmap/ic_launcher"
	        android:label="@string/app_name"
	        android:roundIcon="@mipmap/ic_launcher_round"
	        android:supportsRtl="true"
	        android:theme="@style/AppTheme"
	        tools:replace="android:appComponentFactory">
	
	        <activity android:name=".MainActivity">
	            <intent-filter>
	                <action android:name="android.intent.action.MAIN" />
	                <category android:name="android.intent.category.LAUNCHER" />
	            </intent-filter>
	        </activity>
	
	    </application>
	</manifest>

对于需要被调用的Activity或者Fragment增加注解：

![home_route.png](https://upload-images.jianshu.io/upload_images/3381990-9fe806ab55f395f1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**可以新建一个常量类，用来保存这些路由地址。这里出于简化，没有再定义这个常量类。**

#### 调用方使用ARouter：

    Fragment fragment = (Fragment) ARouter.getInstance().build("/home/fragment").navigation();
    mFragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

![app_arouter.png](https://upload-images.jianshu.io/upload_images/3381990-1486accfe0e539c0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 五、ButterKnife的引入

ButterKnife在单Module中使用时，比较简单，当在多Module下使用时，还是有些需要注意的事项。具体引用步骤如下:

**1、在项目根目录的build.gradle中引入依赖:**

	dependencies {
	        classpath 'com.android.tools.build:gradle:3.1.4'
	        classpath 'com.jakewharton:butterknife-gradle-plugin:9.0.0'
	
	        // NOTE: Do not place your application dependencies here; they belong
	        // in the individual module build.gradle files
	    }

![root_gradle.png](https://upload-images.jianshu.io/upload_images/3381990-3a9be6f741385005.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


在common中引入依赖:

    api 'com.jakewharton:butterknife:9.0.0'
    annotationProcessor 'com.jakewharton:butterknife-compiler:9.0.0'

![common_butterknife.png](https://upload-images.jianshu.io/upload_images/3381990-8baa5b8a03e8d093.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


在具体使用ButterKnife的Module中引入依赖:

    apply plugin: 'com.jakewharton.butterknife'
	
	annotationProcessor 'com.jakewharton:butterknife-compiler:9.0.0'


![home_butterknife_1.png](https://upload-images.jianshu.io/upload_images/3381990-cf6f9c1df6263c7e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![home_butterknife_2.png](https://upload-images.jianshu.io/upload_images/3381990-ba45575d98034b3f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


和ARouter一样，使用ButterKnife的Module虽然不用重复引用butterknife本身这个库，但是注解相关的库还是需要引用。

具体使用：

    @BindView(R2.id.click)
    TextView mText;

**BindView的时候，需要使用R2.id.xx**

	@OnClick(R2.id.click)
    public void click() {
        Toast.makeText(getActivity().getApplicationContext(), "click", Toast.LENGTH_SHORT).show();
    }

**对应的点击事件等，如果是单个使用，也是使用R2.id.xx。如果是多个id一起使用，内部通过id来判断，则需要使用if...else if...，不能使用switch...case，并且if判断的id需要使用R.id.xx**

**默认是会报错，找不到R2相关的class，需要手动build一次才会生成。**

**注意：ButterKnife.9.0以后，需要jdk版本1.8以上，否则编译会报错。**



