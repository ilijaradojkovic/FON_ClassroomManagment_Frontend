package com.example.fon_classroommanagment_frontend.domain.use_case

import android.util.Log
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.EducationTitle
import com.example.fon_classroommanagment_frontend.domain.repository.CommonDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetAllEducationTitlesUseCase @Inject constructor(private val commonDataRepository: CommonDataRepository) {

    operator  fun invoke(): Flow<Response<List<EducationTitle>>> = flow {

        try{
            emit(Response.Loading())

            val titles=  commonDataRepository.getAllEducationTitles()
            emit(Response.Success(titles))
        }catch (httpException: HttpException){//response error sto ne pocinje sa 2 kod
            emit(Response.Error(httpException.localizedMessage ?:"neocekivana greska"))
        }
        catch (io: IOException){
            //ako nemamo net ne moze da prica sa api
            emit(Response.Error("Nema neta"))
        }catch (e:Exception){
            Log.i("cao",e.toString())
            emit(Response.Error(e.localizedMessage ?:"neocekivana greska"))
        }
    }

}