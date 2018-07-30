package com.perumed.Networking.services;

import com.perumed.Networking.models.request.DistritoBody;
import com.perumed.Networking.models.request.MedicamentosBody;
import com.perumed.Networking.models.request.ProvinciaBody;
import com.perumed.Networking.models.response.ResultMedicamentosModel;
import com.perumed.Networking.models.response.ResultPrecioMedicamentos;
import com.perumed.Networking.models.response.ResultUbigeoModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @GET("/Precios/ProcesoL/Consulta/data.aspx?tipo_busqueda=3&totalPA=1&relacionado=1&_=1532959207003&sEcho=1&iColumns=9&sColumns=&iDisplayStart=0&iDisplayLength=150&sSearch=&bRegex=false&sSearch_0=&bRegex_0=false&bSearchable_0=true&sSearch_1=&bRegex_1=false&bSearchable_1=true&sSearch_2=&bRegex_2=false&bSearchable_2=true&sSearch_3=&bRegex_3=false&bSearchable_3=true&sSearch_4=&bRegex_4=false&bSearchable_4=true&sSearch_5=&bRegex_5=false&bSearchable_5=true&sSearch_6=&bRegex_6=false&bSearchable_6=true&sSearch_7=&bRegex_7=false&bSearchable_7=true&sSearch_8=&bRegex_8=false&bSearchable_8=true&iSortingCols=0&bSortable_0=false&bSortable_1=false&bSortable_2=false&bSortable_3=false&bSortable_4=false&bSortable_5=false&bSortable_6=false&bSortable_7=false&bSortable_8=true")
    Call<ResultPrecioMedicamentos> getListPreciosMedicamento(@Query("grupo") String grupo,
                                                             @Query("con") String con,
                                                             @Query("ffs") String ffs,
                                                             @Query("ubigeo") String ubigeo,
                                                             @Query("cad") String cad);

}
