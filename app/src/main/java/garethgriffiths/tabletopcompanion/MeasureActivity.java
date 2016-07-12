package garethgriffiths.tabletopcompanion;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.widget.Toast;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;


//Digital tape measure
//Use to measure distances between two models

public class MeasureActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(new CameraPreview(this));
        //setContentView(R.layout.activity_measure);
        test();
    }


    public void test()
    {
        try
        {
            System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
            Toast toast1 = Toast.makeText(this,"library loaded",Toast.LENGTH_SHORT);
            toast1.show();

        }catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Input test
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

