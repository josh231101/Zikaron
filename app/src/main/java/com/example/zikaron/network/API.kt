
import android.util.Log
import com.example.retrofitdogs.network.ApiInterface
import com.example.zikaron.models.LogInDataModel
import com.example.zikaron.models.Product
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    //Configurar retrofit , creamos el objeto retrofi
    private val URL_BASE = "https://josue.ngrok.io/"
    fun getRetrofitService(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: ApiInterface = retrofit.create(ApiInterface::class.java)
        return service
    }
}

class API {
    //donde recopolaremos los datos y haremos validacion pertinentes.
    private val service = RetrofitHelper.getRetrofitService() /*
    instanciar el servicio retrofit de nuestro objet anterior*/

    suspend fun getProducts(): List<String>? {

        val response = service.products()
        Log.d("API_PRUEBA ", "status es " + response.body()?.status)
        Log.d("API_PRUEBA ", "message es " + response.body()?.data)

        response.body()?.data?.let { //si no es nulo
            for (url_imagen in response.body()?.data!!) {
                //solo para comprobar tengamos algo
                Log.d("API_PRUEBA ", "imagen es " + url_imagen)
            }
        }
        return response.body()?.data
    }

    suspend fun postLogIn(email: String, password: String): Boolean {
        val response = service.logIn(LogInDataModel(email,password))
        Log.d("POST_LOGIN ", "imagen es " + response.body()?.status)
        if ( response.body()?.status == "success") {
            return true
        }
        return false
    }
}