package org.yamyamgoods.yamyam_android.network.get

data class GetPriceRangeResponse (
        val message: String,
        val data: PriceData
)

data class PriceData(
        val price_start: String,
        val price_end: String
)