HOW TO ADD ZOOM AND PAN TO SURFACE VIEW
        canvas.scale(mScaleFactor, mScaleFactor, focusX, focusY);
        canvas.translate(mPosX, mPosY);
these two methods were the solution, now calculating the Parameters is very tricky, how to do this?
adapted this code 
http://android-innovation.blogspot.com/2013/07/how-to-implement-pinch-and-pan-zoom-on.html?m=1
----------------------------------
additional resources
- this zoom is working perfectly
https://stackoverflow.com/questions/50411590/pinch-zoom-on-surfaceview

- this one does indeed has the same code i used in my project
https://stackoverflow.com/questions/5743328/image-in-canvas-with-touch-events

- ZoomLayout.java
https://gist.github.com/anorth/9845602

- http://tienanh-it.blogspot.com/2017/10/implement-pinch-to-zoom-with.html
- https://github.com/m-damavandi/ZoomableSurfaceView/blob/master/app/src/main/java/com/damavandi/zoomablesurfaceview/ZoomableSurfaceView.java

- was very promising answer but i did't use yet
https://stackoverflow.com/questions/2079328/scrolling-a-canvas-smoothly-in-android 

- here he is offering the full solution but it's for View he also suggest a work around for surfaceview 
https://stackoverflow.com/questions/11397829/how-to-draw-on-canvas-greater-than-screen-size-and-be-able-to-scroll-pan-horiz

- the work around i guess
https://stackoverflow.com/questions/2933882/how-to-draw-an-overlay-on-a-surfaceview-used-by-camera-on-android

- drawing on surfaceview
http://android-coding.blogspot.com/2011/05/drawing-on-surfaceview.html

- Handle onTouchEvent in SurfaceView
http://android-coding.blogspot.com/2011/05/handle-ontouchevent-in-surfaceview.html