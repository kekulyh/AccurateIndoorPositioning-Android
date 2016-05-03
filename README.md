# AccurateIndoorPositioning-Android
Android client for AccurateIndoorPositioning

---

* First design version 2016/03/12

	* 不在MainActivity使用CoordinatorLayout，只采用单独的侧滑边栏以及AppBarLayout。主内容为FrameLayout填充的google map，初步打算点击某个地点，可以跳转至该地点的具体activity，再进行下面的室内定位工作。侧边栏操作跳转至其他的activity。

* Second design version 2016/04/05
	* 主页不采用google api，待设计， 已完成跳转至roomactivity，修改SwipeBackActivity继承AppCompatActivity。SingleRoomActivity需要用WebView加载svg和js来实现地图。需要java后端返回building、floor、room等的JSON数据。

* Third design version 2016/04/09
	* WebView与JS交互问题非常多，原来的定位功能及路径计算都是用js实现，WebView目前无法满足我的JS要求，AJAX异步更新问题也很大。似乎只能选别的方法来实现了。暂时选择高德的API显示室内地图。

* Fourth design version 2016/04/10
	* 高德SDK无法满足要求，它的地图文件是必须要经过提交及数据采集处理的，决定依然采用WebView实现。改写后端的js方法，目前可以正常与后端交互，实现定位功能。

* Fifth design version 2016/05/02
	* 3D实时姿态 (Gesture Fragment) 待实现。

* Sixth design version 2016/05/03 
	* GestureFragment error log: [ERROR:gles2_cmd_decoder.cc(7728)] [.CommandBufferContext]RENDER WARNING: there is no texture bound to the unit 0.
	* WebView对WebGl支持有问题，不能显示姿态，换用Crosswalk的XWalkVeiw。

	
---