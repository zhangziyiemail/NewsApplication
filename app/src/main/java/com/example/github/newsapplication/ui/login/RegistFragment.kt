package com.example.github.newsapplication.ui.login

import android.Manifest
import android.R.attr.bitmap
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.github.newsapplication.MyApplication
import com.example.github.newsapplication.R
import com.example.github.newsapplication.Utils.DialogUtil
import com.example.github.newsapplication.Utils.SharePreferencesUtils
import com.example.github.newsapplication.base.BaseFragment
import com.example.github.newsapplication.databinding.FragmentRegistBinding
import kotlinx.android.synthetic.main.fragment_regist.*
import java.io.ByteArrayOutputStream


/**
 *   Created by zhangziyi on 9/21/20 04:51
 */
class RegistFragment : BaseFragment<FragmentRegistBinding>(), DialogUtil.NoticeDialogListener {


    override fun getLayoutId(): Int = R.layout.fragment_regist
    val dailog = DialogUtil()
    var imageBitmap: Bitmap? = null
    override fun actionsOnViewInflate() {

    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        dailog.setListener(this)
        mBinding?.let { binding ->
            binding.viewclick = View.OnClickListener { view ->
                when (view.id) {
                    R.id.but_submit -> submit(account.text.toString(), password.text.toString())
                    R.id.imageview -> upImage()
                }
            }
        }

    }

    fun submit(account: String, password: String) {
        if (SharePreferencesUtils.getString(MyApplication.instance, account).isEmpty()) {
            SharePreferencesUtils.saveString(MyApplication.instance, account, password)
            imageBitmap?.let { SharePreferencesUtils.saveString(MyApplication.instance, "image", bitMapToString()) }
            mNavController.popBackStack()
        }
    }

    fun upImage() {
        activity?.supportFragmentManager?.let { dailog.show(it, "missiles") }
    }

    override fun onDialogItemClick(which: Int) {
        when (which) {
            0 -> camera()
            1 -> photo()
        }
    }

    fun camera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            activity?.packageManager?.let {
                    takePictureIntent.resolveActivity(it)?.also {
                        startActivityForResult(takePictureIntent, 0)
                    }
                }

//            if (ContextCompat.checkSelfPermission(requireContext(),
//                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){ //表示未授权时
//
//                activity?.let { ActivityCompat.requestPermissions(it,
//                    arrayOf(Manifest.permission.CAMERA),1) };
//            }else{
//                activity?.packageManager?.let {
//                    takePictureIntent.resolveActivity(it)?.also {
//                        startActivityForResult(takePictureIntent, 0)
//                    }
//                }
//
//            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            imageBitmap = data?.extras?.get("data") as Bitmap
        } else if (requestCode == 1 && resultCode == RESULT_OK) {
            var uri: Uri = data?.getData() as Uri
            var cr = context?.getContentResolver()
            imageBitmap =BitmapFactory.decodeStream(cr?.openInputStream(uri))
        }
        Glide.with(imageview.context)
            .load(imageBitmap)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(imageview)
    }

    fun photo() {
        startActivityForResult(Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT), 1)
    }

    fun bitMapToString(): String{
        val baos = ByteArrayOutputStream()
        imageBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
}