package org.yamyamgoods.yamyam_android.network.get


data class GetSearchStoreResponse (
        val message: String,
        val data: store
)

data class store(
        val data: ArrayList<SearchStoreData>
)

data class SearchStoreData(
        val search_after: List<Int>,
        val store_idx: Int,
        val store_name: String,
        val store_img: String,
        val hash_tag: List<String>
)