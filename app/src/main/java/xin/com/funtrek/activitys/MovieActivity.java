package xin.com.funtrek.activitys;

import android.content.res.Configuration;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import xin.com.funtrek.R;

public class MovieActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MovieActivity";
    @BindView(R.id.sv_main_surfaceview)
    SurfaceView surfaceView;
    @BindView(R.id.record_control)
    Button mRecordControl;
    @BindView(R.id.PauseRecord)
    Button mPauseRecord;
    private SurfaceHolder mSurfaceHolder;

    //DATA
    private boolean isPause;     //暂停标识
    private boolean isRecording; // 标记，判断当前是否正在录制
    private long mRecordCurrentTime = 0;  //录制时间间隔

    // 存储文件
    private File mVecordFile;
    private Camera mCamera;
    private MediaRecorder mediaRecorder;
    private MediaRecorder.OnErrorListener onErrorListener = new MediaRecorder.OnErrorListener() {
        @Override
        public void onError(MediaRecorder mediaRecorder, int what, int extra) {
            try {
                if (mediaRecorder != null) {
                    mediaRecorder.reset();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);
        //getWindow().setFormat(PixelFormat.TRANSLUCENT);
        initView();
    }

    private void initView() {
        mRecordControl.setOnClickListener(this);

        //配置SurfaceHodler
        mSurfaceHolder = surfaceView.getHolder();
        // 设置Surface不需要维护自己的缓冲区
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        // 设置分辨率
        mSurfaceHolder.setFixedSize(320, 280);
        // 设置该组件不会让屏幕自动关闭
        mSurfaceHolder.setKeepScreenOn(true);
        mSurfaceHolder.addCallback(mCallBack); //相机创建回调接口
    }

    private SurfaceHolder.Callback mCallBack = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            initCamera();
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
            if (mSurfaceHolder.getSurface() == null) {
                return;
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            stopCamera();
        }
    };


    /**
     * 初始化摄像头
     *
     * @author liuzhongjun
     * @date 2016-3-16
     */
    private void initCamera() {
        if (mCamera != null) {
            stopCamera();
        }
        //默认启动后置摄像头
        mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
        if (mCamera == null) {
            Toast.makeText(this, "未能获取到相机！", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            mCamera.setPreviewDisplay(mSurfaceHolder);
            //配置CameraParams
            setCameraParams();
            //启动相机预览
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }

    }


    /**
     * 设置摄像头为竖屏
     *
     * @author liuzhongjun
     * @date 2016-3-16
     */
    private void setCameraParams() {
        if (mCamera != null) {
            Camera.Parameters params = mCamera.getParameters();
            //设置相机的很速屏幕
            if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                params.set("orientation", "portrait");
                mCamera.setDisplayOrientation(90);
            } else {
                params.set("orientation", "landscape");
                mCamera.setDisplayOrientation(0);
            }
            //设置聚焦模式
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            //缩短Recording启动时间
            params.setRecordingHint(true);
            //是否支持影像稳定能力，支持则开启
            if (params.isVideoStabilizationSupported())
                params.setVideoStabilization(true);
            mCamera.setParameters(params);
        }
    }


    /**
     * 释放摄像头资源
     *
     * @author liuzhongjun
     * @date 2016-2-5
     */
    private void stopCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.record_control:
                if (!isRecording) {

                    Toast.makeText(this,"未完成",Toast.LENGTH_SHORT).show();
                    //startRecord();
                } else {
                    Toast.makeText(this,"未完成",Toast.LENGTH_SHORT).show();
                    //停止视频录制
                    //stopRecord();
                }
                break;
        }
    }

    public void startRecord() {
        boolean creakOk = createRecordDir();
        if (!creakOk) {
            return;
        }
        initCamera();
        mCamera.unlock();
        setConfigRecord();
        try {
            //开始录制
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        isRecording = true;
    }
    /**
     * 停止录制视频
     */
    public void stopRecord() {
        if (isRecording && mediaRecorder != null) {
            // 设置后不会崩
            mediaRecorder.setOnErrorListener(null);
            mediaRecorder.setPreviewDisplay(null);
            //停止录制
            mediaRecorder.stop();
            mediaRecorder.reset();
            //释放资源
            mediaRecorder.release();
            mediaRecorder = null;
            //设置开始按钮可点击，停止按钮不可点击
            mRecordControl.setEnabled(true);
            mPauseRecord.setEnabled(false);
            isRecording = false;
        }
    }


    /**
     * 创建视频文件保存路径
     */
    private boolean createRecordDir() {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Toast.makeText(this, "请查看您的SD卡是否存在！", Toast.LENGTH_SHORT).show();
            return false;
        }
        File sampleDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Record");
        if (!sampleDir.exists()) {
            sampleDir.mkdirs();
        }
        String recordName = "VID_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".mp4";
        mVecordFile = new File(sampleDir, recordName);
        return true;
    }

    /**
     * 配置MediaRecorder()
     */
    private void setConfigRecord() {
        //mCamera.release();
        //mCamera.unlock();
        mediaRecorder = new MediaRecorder();
        mediaRecorder.reset();
        mediaRecorder.setCamera(mCamera);
        //mediaRecorder.setOnErrorListener(OnErrorListener);
        //使用SurfaceView预览
        mediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
        //1.设置采集声音
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //设置采集图像
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        //2.设置视频，音频的输出格式 mp4
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        //3.设置音频的编码格式
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        //设置图像的编码格式
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        //设置立体声
//        mediaRecorder.setAudioChannels(2);
        //设置最大录像时间 单位：毫秒
//        mediaRecorder.setMaxDuration(60 * 1000);
        //设置最大录制的大小 单位，字节
//        mediaRecorder.setMaxFileSize(1024 * 1024);
        //音频一秒钟包含多少数据位
        CamcorderProfile mProfile = CamcorderProfile.get(CamcorderProfile.QUALITY_LOW);
        mediaRecorder.setAudioEncodingBitRate(44100);
        if (mProfile.videoBitRate > 2 * 1024 * 1024)
            mediaRecorder.setVideoEncodingBitRate(2 * 1024 * 1024);
        else
            mediaRecorder.setVideoEncodingBitRate(1024 * 1024);
        mediaRecorder.setVideoFrameRate(mProfile.videoFrameRate);

        //设置选择角度，顺时针方向，因为默认是逆向90度的，这样图像就是正常显示了,这里设置的是观看保存后的视频的角度
        mediaRecorder.setOrientationHint(90);
        //设置录像的分辨率
        mediaRecorder.setVideoSize(352, 288);

        mediaRecorder.setOutputFile(mVecordFile.getAbsolutePath());

    }

}
