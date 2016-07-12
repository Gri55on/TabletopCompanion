package garethgriffiths.tabletopcompanion;

    import java.io.IOException;

    import android.content.Context;
    import android.hardware.Camera;
    import android.view.SurfaceHolder;
    import android.view.SurfaceView;

    public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback
    {
        private SurfaceHolder mHolder;
        private Camera mCamera;

        //Create an instance of itself
        public CameraPreview(Context context)
        {
            super(context);
            mHolder = getHolder();
            mHolder.addCallback(this);
            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder)
        {
            //If camera not in use open camera
            if (null == mCamera)
            {
                mCamera = Camera.open();
            }
            try//Try to display camera preview in designated holder
            {
                mCamera.setPreviewDisplay(mHolder);
                mCamera.startPreview();
            } catch (IOException e)
            {
                mCamera.release();
                mCamera = null;
            }
        }

        //When surface changes perform action
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
        {

        }


        @Override
        public void surfaceDestroyed(SurfaceHolder holder)
        {
            if (null == mCamera)
            {
                return;
            }

            //SUPER IMPORTANT!
            //Release camera if in use so other applications can use it
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }

    }