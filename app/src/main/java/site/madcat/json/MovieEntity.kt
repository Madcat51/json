package site.madcat.json

import java.util.*

data class MovieEntity(
  //  val id: Long,
    val original_language: String,
    val vote_average: Long,
    val vote_count: Long,
    val overview: String,
    val release_date: Date
)

