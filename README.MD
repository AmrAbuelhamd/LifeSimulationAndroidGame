while progressing in this project... 
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



----------------------------------------
why changed interface to abstract class, because it tells more about the subclasses, also i found 
that i am defining the same variables two times, and there's general functions that can be 
written only once for all of them, and in case of chaging the functionality i can override it.
----------------------------------------
implement part of code every x seconds
https://stackoverflow.com/questions/12908412/print-hello-world-every-x-seconds
remove while iterating 
https://stackoverflow.com/questions/1196586/calling-remove-in-foreach-loop-in-java
the question code itself is usefull
https://stackoverflow.com/questions/29612984/object-movement-in-a-2d-array-grid
usefull answers for x and y as key
https://stackoverflow.com/questions/44515273/make-longitude-and-latitude-as-key-of-hashmap-in-java
https://stackoverflow.com/questions/4509798/finding-nearest-point-in-an-efficient-way

perfect explanation of ConcurrentModificationException with solution
  https://www.journaldev.com/378/java-util-concurrentmodificationexception

how this error work  
https://stackoverflow.com/questions/33705346/concurrentmodificationexception-when-multiple-thread-access-the-same-collection

https://www.javatpoint.com/concurrentmodificationexception-in-java#:~:text=The%20ConcurrentModificationException%20occurs%20when%20an,thread%20is%20iterating%20over%20it.


linkedhashmap
https://stackoverflow.com/questions/1391918/does-java-have-a-linkedconcurrenthashmap-data-structure

delete from CopyOnWriteArrayList 
https://stackoverflow.com/questions/5612470/remove-elements-from-copyonwritearraylist
**important
while creating a lot of objects of type CopyOnWriteArrayList i must create them in temp ArrayList
then copy them into  CopyOnWriteArrayList