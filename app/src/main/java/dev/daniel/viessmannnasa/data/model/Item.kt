package dev.daniel.viessmannnasa.data.model


data class Item(
    val data: List<Information>,
    val href: String,
    val links: List<ImageLink>
)