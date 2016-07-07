package garethgriffiths.tabletopcompanion;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.widget.Toast;


public class MeasureActivity extends Activity
{
    private Camera mCamera;
    private CameraPreview mPreview;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(new CameraPreview(this));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int action = MotionEventCompat.getActionMasked(event);
        Toast toast;

        switch(action)
        {
            case (MotionEvent.ACTION_DOWN):
                toast = Toast.makeText(this,"Pointer " + event.getActionIndex(), Toast.LENGTH_SHORT);
                toast.show();

                return true;
            case (MotionEvent.ACTION_POINTER_DOWN):
                toast = Toast.makeText(this,"Pointer "+ event.getActionIndex(), Toast.LENGTH_SHORT);
                toast.show();
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }
}

