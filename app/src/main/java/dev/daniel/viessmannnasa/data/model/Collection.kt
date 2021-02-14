package dev.daniel.viessmannnasa.data.model

data class Collection(
    val href: String,
    val items: List<Item>?,
    val links: List<Paging>?,
    val metadata: Metadata,
    val version: String
)