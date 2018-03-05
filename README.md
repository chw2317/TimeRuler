# 此项目已经迁移至：https://github.com/huangdali/RulerView
> 最新内容请见上面的仓库地址，此仓库不再更新内容


# TimeRuler

时间轴、时间刻度尺
- 继承至TextureView，效率更高
- 已适配横竖屏
- 缩放功能（分钟、小时级别）
- 自动移动（自由决定开启与关闭移动）
- 时间轴中选择时间
- 实时设置当天时间
- 显示有效视频时间
- 超时（超过00:00:00,、23:59:59）自动处理
- 带拖动开始、结束、自动移动、超时回调
- 带时间选择回调
- 属性自由配置


## 效果图

![](https://github.com/huangdali/TimeRuler/blob/master/timerulers.gif)

## 新增时间选择

通过setSelectTimeArea(bool)就可以设置是否显示时间选择

![](https://github.com/huangdali/TimeRuler/blob/master/new.png)

## 使用

### 导入
app.build中使用

```java
    compile 'com.jwkj:TimeLineView:v1.3.7'
```

### 混淆配置

```java
#timeruler
-keep class com.hdl.timeruler.**{*;}
-dontwarn com.hdl.timeruler.**
```


### 开启硬件加速

所在activity需要开启硬件加速(建议配置横竖屏不重新走一遍生命周期)

 ```java
    <activity
       ...
       android:configChanges="orientation|keyboardHidden|screenSize"
       android:hardwareAccelerated="true">
       ...
    </activity>
 ```

### 布局
> TextureView本身不支持直接设置背景颜色（android:background="color"，设置之后会抛出异常），可以通过属性viewBackgroundColor来设置背景色

最简单的使用（属性使用默认值）

```java
 <com.hdl.timeruler.TimeRulerView
            android:id="@+id/tr_line"
            android:layout_width="match_parent"
            android:layout_height="166dp" />
```

配置属性（更多属性说明见本文附录）

```java
    <com.hdl.timeruler.TimeRulerView
            android:id="@+id/tr_line"
            android:layout_width="match_parent"
            android:layout_height="166dp"
            app:centerLineColor="#ff6e9fff"
            app:rulerLineColor="#ffb5b5b5"
            app:rulerTextColor="#ff444242"
            app:vedioAreaColor="#336e9fff"
            app:viewBackgroundColor="#fff"
            ...
            />
```

### 设置当前时间

```java
tRuler.setCurrentTimeMillis(设置中心线的时间)
```

### 初始化视频时间段

```java
        List<TimeSlot> times = new ArrayList<>();
        times.add(new TimeSlot(DateUtils.getTodayStart(System.currentTimeMillis()),DateUtils.getTodayStart(System.currentTimeMillis()) + 60 * 60 * 1000, DateUtils.getTodayStart(System.currentTimeMillis()) + 120 * 60 * 1000));
        times.add(new TimeSlot(DateUtils.getTodayStart(System.currentTimeMillis()),DateUtils.getTodayStart(System.currentTimeMillis()) + 3*60 * 60 * 1000, DateUtils.getTodayStart(System.currentTimeMillis()) + 4*60 * 60 * 1000));
        tRuler.setVedioTimeSlot(times);
```

### 是否自动移动
```java
    tRuler.openMove();//打开移动
    tRuler.closeMove();//关闭移动
```

### 关于横竖屏适配

#### 为什么要适配？

由于横竖屏切换之后，view宽高不能保持一致导致需要适配

#### 适配步骤

1、定义一个全局的当前时间毫秒值

```java
 private long currentTimeMillis;
```

2、在onBarMoving回调方法中记录currentTimeMillis

```java
tRuler.setOnBarMoveListener(new OnBarMoveListener() {
             ...
            @Override
            public void onBarMoving(long currentTime) {
                currentTimeMillis = currentTime;
            }
          ...
        });
```

3、在时间轴所在activity/fragment中重写onConfigurationChanged方法

```java
  @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ...
        tRuler.setCurrentTimeMillis(currentTimeMillis);
        ...
    }
```

>通过以上三个步骤即可适配横竖屏（手动与自动横竖屏都可以）


## 附表

### 布局文件属性配置说明
> 所有属性都有默认值，可以不设置

- 中轴线颜色-->centerLineColor
- 时间文字颜色-->rulerTextColor
- 含有录像背景颜色-->vedioAreaColor
- 刻度线颜色-->rulerLineColor
- 选择时间的边框颜色-->selectTimeBorderColor
- 已选择时间颜色-->selectTimeAreaColor
- 中轴线大小-->centerLineSize
- 时间文字大小-->rulerTextSize
- 小刻度线宽度-->samllRulerLineWidth
- 小刻度线高度-->samllRulerLineHeight
- 大刻度线宽度-->largeRulerLineWidth
- 大刻度线高度-->largeRulerLineHeight
- 选择时间边框大小-->selectTimeBorderSize
- 设置背景颜色-->viewBackgroundColor

## 版本记录

v1.3.9( [2018.01.11]() )

- 【修复】onMoveExceedEndTime()回调两次问题

v1.3.8( [2018.01.11]() )

- 【优化】拖动的灵敏度

v1.3.7( [2018.01.10]() )

- 【修复】提示页面箭头与图片不一致问题

v1.3.6( [2018.01.10]() )

- 【新增】新增视频开始和结束，再继续拖动时的提示(横屏)

v1.3.5( [2018.01.09]() )

- 【新增】新增视频开始和结束，再继续拖动时的提示（竖屏）

v1.3.3( [2017.11.09]() )

- 【新增】缩放到最大和最小时的回调方法

v1.3.2( [2017.10.26]() )

- 【修复】部分手机时间文本被挡住一小部分

v1.2.8( [2017.09.12]() )

- 【优化】需要手动适配横竖屏切换

v1.2.7( [2017.09.08]() )

- 【优化】连续拖动不回调onBarMoveFinish()，直到用户停止拖动超过1.5秒才认为用户拖动结束

v1.2.6( [2017.09.07]() )

- 【修复】部分机型快速横竖屏切换导致横竖屏显示时间不对应问题

v1.2.5( [2017.09.06]() )

- 【优化】延迟onBarMoveFinish回调时间为2秒

v1.2.4( [2017.09.06]() )

- 【新增】设置背景颜色方法，同样布局文件中可以app:viewBackgroundColor="#fff"

v1.2.3( [2017.09.06]() )

- 【修复】未设置setOnBarMoveListener时抛出空指针异常

v1.2.2( [2017.09.06]() )

- 【优化】删除无用日志

v1.2.1( [2017.09.06]() )

- 【新增】 缩放功能（分钟、小时级别）
- 【新增】 布局文件中配置颜色、大小属性，同样也提供setXXX()方法
- 【优化】 TimeSlot构造方法新增第一个参数为当天开始时间毫秒值（即当天凌晨00:00:00的毫秒值）

v1.1.2( [2017.09.05]() )

- 【优化】 onBarMoving在主线程中执行

v1.0.8( [2017.09.05]() )

- 【优化】 删除无用依赖和无用代码

v1.0.7( [2017.09.05]() )

- 【优化】 使用openMove()、closeMove()代替setMoving(bool)

v1.0.6( [2017.09.05]() )

- 【新增】超过今天开始时间（00：00:00）、今天结束时间（23:59:59）回调，并自动回到开始/结束时间

v1.0.5( [2017.09.05]() )

- 【新增】新增时间轴上选择时间

v1.0.4( [2017.09.04]() )

- 【修复】往小于15分钟拉取时时间倒跑问题

> 更早版本未记录
