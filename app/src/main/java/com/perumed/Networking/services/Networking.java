package com.perumed.Networking.services;

import com.perumed.Networking.models.request.DistritoBody;
import com.perumed.Networking.models.request.MedicamentosBody;
import com.perumed.Networking.models.request.ProvinciaBody;
import com.perumed.Networking.models.response.ResultMedicamentosModel;
import com.perumed.Networking.models.response.ResultUbigeoModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Networking {

    @POST("Precios/ProcesoL/Consulta/BusquedaGral.aspx/GetListaMedicamentos")
    @Headers("Content-Type: application/json")
    Call<ResultMedicamentosModel> getMedicamentos(@Body MedicamentosBody body);

    @POST("Precios/ProcesoL/Consulta/BusquedaGral.aspx/GetProvincias")
    @Headers("Content-Type: application/json")
    Call<ResultUbigeoModel> getProvincias(@Body ProvinciaBody body);

    @POST("Precios/ProcesoL/Consulta/BusquedaGral.aspx/GetDistritos")
    @Headers("Content-Type: application/json")
    Call<ResultUbigeoModel> getDistritos(@Body DistritoBody body);

}
