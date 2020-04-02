package co.id.peruri;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.util.Base64;
import id.co.peruri.SDKPeruri;

import static android.app.Activity.RESULT_OK;

public class RNPeruriModule extends ReactContextBaseJavaModule {

  private static final String E_FAILED_ENCODE_TO_BASE64 = "E_FAILED_ENCODE_TO_BASE64";
  private static final String E_FAILED_START_VIDEO = "E_FAILED_START_VIDEO";

  private final ReactApplicationContext reactContext;

  public RNPeruriModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;

    reactContext.addActivityEventListener(mActivityEventListener);
  }

  private Promise mStartVideoPromise;

  private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {

  @Override
  public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
    if (resultCode == RESULT_OK && requestCode == SDKPeruri.REQUEST_RECORD_VIDEO) {
      SDKPeruri.Result result = SDKPeruri.GetResult(data);
      if (result != null) {
        try {
          String base64Video = encodeFileToBase64Binary(result.getPath());
          mStartVideoPromise.resolve(base64Video);
        } catch (IOException e) {
          mStartVideoPromise.reject(E_FAILED_ENCODE_TO_BASE64, "Failed to encode to base64");
          e.printStackTrace();
        }

        mStartVideoPromise = null;
      }
    }
  }
 };

  private String encodeFileToBase64Binary(String fileName) throws IOException {
    File file = new File(fileName);
    byte[] bytes = loadFile(file);
    byte[] encoded = Base64.encode(bytes,Base64.DEFAULT);
    String encodedString = new String(encoded);

    return encodedString;
  }

  private static byte[] loadFile(File file) throws IOException {
    InputStream is = new FileInputStream(file);

    long length = file.length();
    if (length > Integer.MAX_VALUE) {
      throw new IOException("File too large");
    }
    byte[] bytes = new byte[(int)length];

    int offset = 0;
    int numRead = 0;
    while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
      offset += numRead;
    }

    if (offset < bytes.length) {
      throw new IOException("Could not completely read file " + file.getName());
    }

    is.close();
    return bytes;
  }

  @Override
  public String getName() {
    return "RNPeruri";
  }

  @ReactMethod
  public void startVideo(Promise promise) {
    mStartVideoPromise = promise;
    try {
      SDKPeruri sdk = new SDKPeruri(reactContext.getCurrentActivity());
      sdk.startVideo();
    } catch (Exception e) {
      mStartVideoPromise.reject(E_FAILED_START_VIDEO, "Failed to start video");
      e.printStackTrace();
    }
  }
}