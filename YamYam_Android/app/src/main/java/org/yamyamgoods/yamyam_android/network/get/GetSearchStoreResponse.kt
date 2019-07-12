package org.yamyamgoods.yamyam_android.network.get


data class GetSearchStoreResponse (
        val message: String,
        val data: StoreSearchResultList
)

data class StoreSearchResultList(
        val store: ArrayList<SearchStoreData>,
        val totalCnt: Int
)

data class SearchStoreData(
        val search_after: String,
        val store_idx: Int,
        val store_name: String,
        val store_img: String,
        val hash_tag: ArrayList<String>,
        val store_scrap_flag: Int
)