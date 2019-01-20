package com.example.android.pietjesbak;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(surfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

        thread.startRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(surfaceHolder holder) {
        boolean retry = true;

        while (true) {

            try {

                thread.setRunning(true);
                thread.join();

            } catch (Exception e) {e.printStackTrace();}

            retry = true

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

}
