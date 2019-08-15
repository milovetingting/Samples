# Android多渠道打包--Gradle打包

## 前言

由于App一般都会在多个应用市场上架，为了分析App在每个不同渠道的具体的数据，一般都会对不同渠道打包不同的App。多渠道打包有多种方式，这里只介绍利用Gradle进行多渠道打包。

## 步骤

### 1、在AndroidManifest.xml中增加配置

```xml
<meta-data
            android:name="APP_CHANNEL"
            android:value="${APP_CHANNEL_VALUE}" />
```

其中,APP_CHANNEL为配置的属性名。APP_CHANNEL_VALUE为在build.gradle中配置的变量。

### 2、在build.gradle中增加productFlavors配置

```xml
productFlavors {
        baidu {
            manifestPlaceholders = [APP_CHANNEL_VALUE: "baidu"]
        }
        qq {
            manifestPlaceholders = [APP_CHANNEL_VALUE: "qq"]
        }
    }
```

在这里，增加了两个渠道配置信息：baidu和qq。在manifestPlaceholders的配置中，APP_CHANNEL_VALUE即为在AndroidManifest用到的变量。

注意:Gradle3.0以上版本，需要在defaultConfig节点中增加以下配置：

```xml
flavorDimensions "default"
```

### 3、渠道名称获取

```java
private String getMetaData(Context context, String key) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context
                    .getPackageName(), PackageManager.GET_META_DATA);
            return applicationInfo.metaData.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
```

### 4、修改应用名称

```xml
productFlavors {
        baidu {
            manifestPlaceholders = [app_name: "@string/app_name_baidu", APP_CHANNEL_VALUE: "baidu"]
        }
        qq {
            manifestPlaceholders = [app_name: "@string/app_name_qq",APP_CHANNEL_VALUE: "qq"]
        }
    }
```

在manifestPlaceholders中增加app_name的配置，在AndroidManifest.xml中引用:

```xml
<application
        android:allowBackup="true"
        android:icon="${app_icon}"
        android:label="${app_name}"
        android:roundIcon="${app_icon}"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">

        <meta-data
            android:name="APP_CHANNEL"
            android:value="${APP_CHANNEL_VALUE}" />

        //省略其它内容
    </application>
```

### 5、修改应用图标

```xml
productFlavors {
        baidu {
            manifestPlaceholders = [app_name: "@string/app_name_baidu",app_icon:"@mipmap/icon_baidu", APP_CHANNEL_VALUE: "baidu"]
        }
        qq {
            manifestPlaceholders = [app_name: "@string/app_name_qq", app_icon:"@mipmap/icon_qq",APP_CHANNEL_VALUE: "qq"]
        }
    }
```

在manifestPlaceholders中增加app_icon的配置，在AndroidManifest.xml中引用:

```xml
<application
        android:allowBackup="true"
        android:icon="${app_icon}"
        android:label="${app_name}"
        android:roundIcon="${app_icon}"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">

        <meta-data
            android:name="APP_CHANNEL"
            android:value="${APP_CHANNEL_VALUE}" />

        //省略其它内容
    </application>
```

### 6、修改应用包名

```xml
productFlavors {
        baidu {
            applicationIdSuffix ".baidu"
            manifestPlaceholders = [app_name: "@string/app_name_baidu",app_icon:"@mipmap/icon_baidu", APP_CHANNEL_VALUE: "baidu"]
        }
        qq {
            applicationIdSuffix ".qq"
            manifestPlaceholders = [app_name: "@string/app_name_qq", app_icon:"@mipmap/icon_qq",APP_CHANNEL_VALUE: "qq"]
        }
    }
```

增加applicationIdSuffix属性。

### 7、Java中调用gradle中的变量

```xml
buildTypes {
        debug {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            buildConfigField "String","FIELD_TEST","\"FIELD_TEST\""
        }
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            buildConfigField "String","FIELD_TEST","\"FIELD_TEST\""
        }
    }
```

增加buildConfigField。同步后，通过BuildConfig.FIELD_TEST能获取到新增的变量值。

### 8、完整的gradle

```xml
apply plugin: 'com.android.application'

android {
    compileSdkVersion 28
    defaultConfig {
        applicationId "com.wangyz.multichannel"
        minSdkVersion 21
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
        flavorDimensions "default"
    }
    buildTypes {
        debug {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            buildConfigField "String","FIELD_TEST","\"FIELD_TEST\""
        }
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            buildConfigField "String","FIELD_TEST","\"FIELD_TEST\""
        }
    }
    productFlavors {
        baidu {
            applicationIdSuffix ".baidu"
            manifestPlaceholders = [app_name: "@string/app_name_baidu",app_icon:"@mipmap/icon_baidu", APP_CHANNEL_VALUE: "baidu"]
        }
        qq {
            applicationIdSuffix ".qq"
            manifestPlaceholders = [app_name: "@string/app_name_qq", app_icon:"@mipmap/icon_qq",APP_CHANNEL_VALUE: "qq"]
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:28.+'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
}
```

### 9、完整的AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.wangyz.multichannel">

    <application
        android:allowBackup="true"
        android:icon="${app_icon}"
        android:label="${app_name}"
        android:roundIcon="${app_icon}"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">

        <meta-data
            android:name="APP_CHANNEL"
            android:value="${APP_CHANNEL_VALUE}" />

        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```