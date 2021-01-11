package com.shokh.sample

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.kit.showkitapp.R
import com.kit.showkitapp.utils.SessionManager
import okhttp3.MediaType
import okhttp3.RequestBody

import java.io.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

open class BaseActivity : AppCompatActivity() {

    lateinit var toolBar: Toolbar
    lateinit var edtSearch: EditText
    lateinit var frameSearch: FrameLayout
    lateinit var tvToolbarTitle: TextView
    lateinit var imgLeft: ImageView
    lateinit var imgRight: ImageView


    //utils
//    lateinit var connectionDetector: ConnectivityManager
    lateinit var sessionManager: SessionManager
    lateinit var progressDialog: Dialog

    lateinit var activity: Activity
    lateinit var conxt: Context


    private val VIDEO_DIRECTORY = "/testkotlin"

    var x: (String) -> Unit = { x -> print("hi" + x) }

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        activity = this@BaseActivity
        conxt = this@BaseActivity

        //utils
//        connectionDetector = ConnectivityManager(this)
//        utilities = Utilities(this, activity)
        sessionManager = SessionManager(this)
//        progressDialog = utilities.initProgressDialog(this)
//
//
//        //di injection
//        AndroidInjection.inject(this)


    }


    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
    }


    fun edit_title_Visibility(edt_titleFlag: Boolean) {
        if (edt_titleFlag) {
            frameSearch.visibility = View.VISIBLE
            tvToolbarTitle.visibility = View.GONE
        } else {
            frameSearch.visibility = View.GONE
            tvToolbarTitle.visibility = View.VISIBLE
        }
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

    fun statusBarColorChanger()
    {
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
    }

    // for converting string to requestbody
    fun stringConvertToRequestBody(pram: String): RequestBody {
        val covertedParam = RequestBody.create(MediaType.parse("text/plain"), pram)
        return covertedParam
    }

    fun setImgLeft(visible: Int/*, int resource*/) {
        imgLeft.visibility = visible
//         imgLeft.setImageResource(resource);
    }

    fun setImgRight(visible: Int/*, int resource*/) {
        imgRight.setVisibility(visible)
        //imgRight.setImageResource(resource);
    }

    fun onLeft_AsBackClick() {
        imgLeft.setOnClickListener()
        {

            super@BaseActivity.onBackPressed()

        }
        /*  imgPerson.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        imgPerson.setOnClickListener {

            view: View? ->


        }*/

        // imgLeft.setOnClickListener { super@BaseActivity.onBackPressed() }
    }


    fun showSnackbar(editText: EditText, message: String) {
        hideKeyBoard(editText)

        val snackbar = Snackbar
            .make(editText, message, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    /*  fun hideKeyBoard(editText: EditText) {
          val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
          imm!!.hideSoftInputFromWindow(editText.windowToken, 0)
      }*/

    public fun hideKeyBoard(editText: EditText) {
        var imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }


    fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(
            source, 0, 0, source.width, source.height,
            matrix, true
        )
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

    /* public void showToast(String message)
    {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

*/
    fun compressUriToBitmap(uri: Uri): Bitmap {
        var imageStream: InputStream? = null
        try {
            imageStream = contentResolver.openInputStream(
                uri
            )
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        val bmp = BitmapFactory.decodeStream(imageStream)

        var stream: ByteArrayOutputStream? = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG, 80, stream)
        val byteArray = stream!!.toByteArray()
        try {
            stream.close()
            stream = null
        } catch (e: IOException) {

            e.printStackTrace()
        }

        return bmp
    }

    private fun saveVideoToInternalStorage(filePath: String) {

        val newfile: File

        try {

            val currentFile = File(filePath)
            val wallpaperDirectory =
                File(Environment.getExternalStorageDirectory().toString() + VIDEO_DIRECTORY)
            newfile =
                File(wallpaperDirectory, Calendar.getInstance().timeInMillis.toString() + ".mp4")

            if (!wallpaperDirectory.exists()) {
                wallpaperDirectory.mkdirs()
            }

            if (currentFile.exists()) {

                val `in` = FileInputStream(currentFile)
                val out = FileOutputStream(newfile)

                // Copy the bits from instream to outstream
                val buf = ByteArray(1024)
                var len: Int

                while (`in`.read(buf).also { len = it } >= 0) {
                    //while ((len = `in`.read(buf)) > 0) {
                    out.write(buf, 0, len)
                }
                `in`.close()
                out.close()
                Log.v("vii", "Video file saved successfully.")
            } else {
                Log.v("vii", "Video saving failed. Source file missing.")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun getPath(uri: Uri): String? {
        val projection: Array<String> = arrayOf(MediaStore.Video.Media.DATA)
        val cursor = contentResolver.query(uri, projection, null, null, null)
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

    fun getImageUri(activity: BaseActivity, inImage: Bitmap?): String? {
        var bytes = ByteArrayOutputStream()
        inImage?.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        var path: String = MediaStore.Images.Media.insertImage(
            activity.getContentResolver(),
            inImage,
            "Title",
            null
        );
        return path;
    }


    fun setIsLoading(value: Boolean) {
        //hide and show progress bar
        if (value) {
            progressDialog.show()
            // true
            Log.e("change in dataVal bool", value.toString())
        } else {
            progressDialog.dismiss()

            // false
            Log.e("change in dataVal bool", value.toString())
        }


    }

    fun showSnackBar(text: String) {
        val snackbar = Snackbar.make(
            activity!!.findViewById(android.R.id.content),
            text,
            Snackbar.LENGTH_SHORT
        )
        snackbar.view.setBackgroundColor(ContextCompat.getColor(activity!!, R.color.whiter))
        snackbar.setTextColor(ContextCompat.getColor(activity!!, R.color.red))
        snackbar.show()
    }

    fun showToast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    /**
     * validate your email address format. Ex-akhi@mani.com
     */
    fun emailValidator(email: String): Boolean {
        var pattern: Pattern
        var matcher: Matcher
        var EMAIL_PATTERN: String =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


}