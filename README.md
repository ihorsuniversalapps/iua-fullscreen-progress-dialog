# IUA Fullscreen Progress Dialog

Simple progress indicator dialog that blocks UI during background process is running.

![screenshot 1](https://github.com/ihorsuniversalapps/iua-fullscreen-progress-dialog/raw/master/screenshot1.png "ScreenShot Of Dialog")

## Getting started

### Dependency

[Download](https://bintray.com/phoenixria/maven/iua-fullscreen-progress-dialog/1.0.0/view)

Include the dependency:

```groovy
dependencies {
    compile 'ua.in.iua:iua-fullscreen-progress-dialog:1.0.0'
}
```
### Usage

Put next code in your `Activity` class in the `#onCreate()` method, for instance.

```java
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FullscreenProgressDialog dialog = new FullscreenProgressDialog();
        dialog.show(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000); // Do something here
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                });
            }
        }).start();
    }
```

### Customization

Progress dialog supports next customizations:

* `setTintColor` - sets the color of progress indicator and message below.
* `setDimAmount` - sets the transparency of dialog.
* `setMessage()` - sets the message below progress indicator.

# License

The MIT License (MIT)

Copyright (c) 2015 ihorsuniversalapps

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
