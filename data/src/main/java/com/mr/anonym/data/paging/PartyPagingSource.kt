package com.mr.anonym.data.paging

import android.util.Log
import androidx.annotation.Keep
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mr.anonym.data.instance.remote.PartyApiService
import com.mr.anonym.domain.model.PartysItem

@Keep
class PartyPagingSource(
    private val api: PartyApiService
) : PagingSource<Int, PartysItem>(){

    override fun getRefreshKey(state: PagingState<Int, PartysItem>): Int?  = state.anchorPosition?.let{
        state.closestPageToPosition(anchorPosition = it)?.prevKey?.plus(1) ?: state.closestPageToPosition(it) ?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PartysItem> {
        return try {

            val limit = 10
            val page = params.key?:1
            val response = api.getAllParty(page = page, limit = limit)

            val responseBody = response.body()
            val partyList = responseBody?.partys?:emptyList()
            val nextKey = if (partyList.size < limit)null else page + 1
            val prevKey = if (page == 1) null else page-1
            LoadResult.Page(
                data = partyList,
                nextKey = nextKey,
                prevKey = prevKey
            )
        }catch (e: Exception){
            Log.d("NetworkLogging", "PartyPagingSourceLoad:${e.message} ")
            LoadResult.Error(e)
        }
    }
}