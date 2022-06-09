package com.example.testproj.viewModel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testproj.model.CharactersDataItem
import com.example.testproj.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream

class MainActivityViewModel:ViewModel() {

    private val retrofitServiceAnnotator = RetrofitClient.createRetrofitService()
    private val _data = MutableLiveData<MutableList<CharactersDataItem>>()
    val data:LiveData<MutableList<CharactersDataItem>> = _data

    private val _image = MutableLiveData<InputStream>()
    val image:LiveData<InputStream> = _image

    private val _onError = Channel<String?>()
    val onError = _onError.receiveAsFlow()

    fun getData() = viewModelScope.launch {
        try { val data = withContext(Dispatchers.IO){
               retrofitServiceAnnotator.getCharactersData()
           }
           _data.value = data.toMutableList()
        }
        catch (e:Exception){
            _onError.send(e.localizedMessage)
        }

    }

    fun downloadImage() = viewModelScope.launch {
        try { val response = withContext(Dispatchers.IO){
            retrofitServiceAnnotator.getImage("https://www.safegold.com/display/buy-invoice/49f7a3ad-8ccc-4b20-9c10-97aa26fa217e")
        }
           if(response.isSuccessful && response.body()!=null){
               _image.value = response.body()?.byteStream()
           }
            else{
               _onError.send(response.message())
           }
        }
        catch (e:Exception){
            _onError.send(e.localizedMessage)
        }
    }



}