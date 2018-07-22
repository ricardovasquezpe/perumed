package com.perumed.Networking.services;

import com.perumed.Networking.models.request.MedicamentosBody;
import com.perumed.Networking.models.response.ResultMedicamentosModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MedicamentosService {

    @POST("Precios/ProcesoL/Consulta/BusquedaGral.aspx/GetListaMedicamentos")
    @Headers("Content-Type: application/json")
    Call<ResultMedicamentosModel> getMedicamentos(@Body MedicamentosBody avanzado);

}
