# 简单的记事簿
  Android Rv等控件 RuntimePermmison等操作
## 红岩安卓开发第6次LV4作业 
## 部分页面展示
  **View**  
 ![**View**](https://github.com/965023780/SixthHomework/blob/master/ReadmePicture/Screenshot_2019-12-06-21-14-56-44_47f8d1461d87795.png)
 ![**View**](https://github.com/965023780/SixthHomework/blob/master/ReadmePicture/Screenshot_2019-12-06-21-15-04-02_47f8d1461d87795.png)
## 功能 
●读取通话记录  
●拨打电话(成功后会有Toast提示call)  
●发信短信(成功后会有Toast提示Send)    
## 未修改BUG
●偶尔会抛出PagerAdapter changed the adapter's contents without calling PagerAdapter的异常导致APP闪退或显示出错
●读取权限后需要再次点击才能拨打电话号码或发送短信  
●第一次权限拒绝后（未点击不再显示），也需要进入应用设置页面设置权限(部分机型会出现这个问题)
