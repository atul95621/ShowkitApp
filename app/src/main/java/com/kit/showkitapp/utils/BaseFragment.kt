package org.mobileapp.voice.util


import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.kit.showkitapp.utils.SessionManager
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.ParseException
import java.text.SimpleDateFormat


open class BaseFragment : Fragment() {

    lateinit var toolBar: Toolbar
    lateinit var edtSearch: EditText
    lateinit var frameSearch: FrameLayout
    lateinit var tvToolbarTitle: TextView
    lateinit var imgLeft: ImageView
    lateinit var imgRight: ImageView


    //utils
//    lateinit var connectionDetector: ConnectivityManager
//    lateinit var sessionManager: SessionManager
   lateinit var progressDialog: Dialog

     var activity: Activity? = null
     var conxt: Context?= null

    /*   override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)

       }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        activity = getActivity() as Activity
        conxt = context as Context

        //utils
//        connectionDetector = ConnectivityManager(activity!!)
//        sessionManager = SessionManager(activity!!)

        /*    //injection
            AndroidSupportInjection.inject(this)*/



        return super.onCreateView(inflater, container, savedInstanceState)
    }




    /*  fun initToolbar(view: View, name: String) {
          toolBar = view.findViewById(R.id.toolBar);

          frameSearch = toolBar.findViewById(R.id.frameSearch);
          edtSearch = toolBar.findViewById(R.id.edtSearch);
          tvToolbarTitle = toolBar.findViewById(R.id.tvToolBarTitle);
          imgLeft = toolBar.findViewById(R.id.imgLeft);
          imgRight = toolBar.findViewById(R.id.imgRight);

          tvToolbarTitle.setText(name);

      }
  */


    fun hide_keyboard(activity: Activity) {
        val inputMethodManager = activity
            .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun decodeSampledBitmapFromFile(
        path: String,
        reqWidth: Int, reqHeight: Int
    ): Bitmap { // BEST QUALITY MATCH

        // First decode with inJustDecodeBounds=true to check dimensions
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, options)

        // Calculate inSampleSize
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        options.inPreferredConfig = Bitmap.Config.RGB_565
        var inSampleSize = 1

        if (height > reqHeight) {
            inSampleSize = Math.round(height.toFloat() / reqHeight.toFloat())
        }

        val expectedWidth = width / inSampleSize

        if (expectedWidth > reqWidth) {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round(width.toFloat() / reqWidth.toFloat())
        }


        options.inSampleSize = inSampleSize

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false

        return BitmapFactory.decodeFile(path, options)
    }

    fun getPath(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Video.Media.DATA)
        val cursor = conxt?.contentResolver?.query(uri, projection, null, null, null)
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            val column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(column_index)
        } else
            return null
    }
    fun stringConvertToRequestBody(pram: String): RequestBody {
        val covertedParam = RequestBody.create(MediaType.parse("text/plain"), pram)
        return covertedParam
    }

    fun convertToDate(dateString: String): String {
        var dateString2 = ""
        try {
            val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse(dateString)
            dateString2 = SimpleDateFormat("dd MMM").format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return dateString2
    }
    fun compressFile(file: File): File? {

        try {

            // BitmapFactory options to downsize the image
            val o = BitmapFactory.Options()
            o.inJustDecodeBounds = true
            o.inSampleSize = 6
            // factor of downsizing the image

            var inputStream = FileInputStream(file)
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o)
            inputStream.close()

            // The new size we want to scale to
            val REQUIRED_SIZE = 200        // x............

            // Find the correct scale value. It should be the power of 2.
            var scale = 1
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2
            }

            val o2 = BitmapFactory.Options()
            o2.inSampleSize = scale
            inputStream = FileInputStream(file)

            val selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2)
            inputStream.close()

            // here i override the original image file
            val outPutFile = File.createTempFile("abc", "image")
            val outputStream = FileOutputStream(outPutFile)
            // y.......
            selectedBitmap!!.compress(Bitmap.CompressFormat.JPEG, 70, outputStream)

            return outPutFile
        } catch (e: Exception) {
            return null
        }

    }


    fun setIsLoading(value: Boolean) {
        //hide and show progress bar
        if (value) {
            progressDialog?.show()
            // true
            Log.e("change in dataVal bool", value.toString())
        } else {
            progressDialog?.dismiss()

            // false
            Log.e("change in dataVal bool", value.toString())
        }


    }



}