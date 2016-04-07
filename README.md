# AccurateIndoorPositioning-Android
Android client for AccurateIndoorPositioning

---

* First design version 2016/03/12

	* 不在MainActivity使用CoordinatorLayout，只采用单独的侧滑边栏以及AppBarLayout。主内容为FrameLayout填充的google map，初步打算点击某个地点，可以跳转至该地点的具体activity，再进行下面的室内定位工作。侧边栏操作跳转至其他的activity。

* Second design version 2016/04/05
	* 主页不采用google api，待设计， 已完成跳转至roomactivity，修改SwipeBackActivity继承AppCompatActivity。SingleRoomActivity需要用WebView加载svg和js来实现地图。需要java后端返回building、floor、room等的JSON数据。
	

	
---